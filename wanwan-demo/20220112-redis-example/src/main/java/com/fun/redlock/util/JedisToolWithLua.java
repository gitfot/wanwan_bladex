package com.fun.redlock.util;

import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Collections;
import java.util.Random;
import java.util.UUID;

/**
 * @author wanwan 2022/1/13
 */
@Slf4j
public class JedisToolWithLua {

	private static final StringRedisTemplate redisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
	//private static final JedisPool jedisPool = SpringUtil.getBean(JedisPool.class);

	private static final DefaultRedisScript<Long> LOCK_SCRIPT;
	private static final DefaultRedisScript<Object> UNLOCK_SCRIPT;

	/**
	 * 单次获取锁的重试时间
	 * 		注意：这个重试时间一定要大于执行业务的消耗时间，否则此值没有意义
	 */
	private static final Long TTL_TRYLOCK = 5000L;

	/**
	 * 重试间隔最大时间
	 */
	private static final Integer RETRY_GAP_MAX_TIME = 500;

	//private static final ThreadLocal<Integer> retryCount = new ThreadLocal<>();

	static {
		// 加载锁的脚本
		LOCK_SCRIPT = new DefaultRedisScript<>();
		LOCK_SCRIPT.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/lock.lua")));
		LOCK_SCRIPT.setResultType(Long.class);

		// 加载释放锁的脚本
		UNLOCK_SCRIPT = new DefaultRedisScript<>();
		UNLOCK_SCRIPT.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/unlock.lua")));
	}
	/**
	 * 获取锁
	 * @param lockName 锁名称
	 * @param releaseTime 超时时间(单位:秒)
	 * @return key 解锁标识
	 */
	public static String tryLock(String lockName,String releaseTime) {
		// 存入的线程信息的前缀，防止与其它JVM中线程信息冲突
		String key = UUID.randomUUID().toString() + Thread.currentThread().getId();
		long retryStartTime = System.currentTimeMillis();
		int count = 0;
		while (System.currentTimeMillis() < retryStartTime + TTL_TRYLOCK) {
			//统计重入次数
			count++;
			// 执行脚本
			Long result = redisTemplate.execute(
				LOCK_SCRIPT,
				Collections.singletonList(lockName),
				key, releaseTime);

			// 判断结果
			if(result != null && result.intValue() == 1) {
				System.out.println(Thread.currentThread().getId() + "--tryLock次数：" +count);
				return key;
			}
			try {
				// 注意！最好下面重试等待替换成redis的发布订阅减少资源消耗
				Thread.sleep(new Random().nextInt(RETRY_GAP_MAX_TIME));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 释放锁
	 * @param lockName 锁名称
	 * @param key 解锁标识
	 */
	public static void unlock(String lockName,String key) {
		// 执行脚本
		redisTemplate.execute(
			UNLOCK_SCRIPT,
			Collections.singletonList(lockName),
			key);
	}

	// ==================redis基础操作方法=============================

	/*public String get(String key) {
		Jedis jedis = null;
		String value = null;
		try {
			jedis = jedisPool.getResource();
			value = jedis.get(key);
			log.info(value);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			returnResource(jedis);
		}
		return value;
	}*/

	/*public void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			returnResource(jedis);
		}
	}*/

	/**
	 * 关闭连接
	 */
	/*public void returnResource(Jedis jedis) {
		try {
			if(jedis!=null) jedis.close();
		} catch (Exception e) {
		}
	}*/
}
