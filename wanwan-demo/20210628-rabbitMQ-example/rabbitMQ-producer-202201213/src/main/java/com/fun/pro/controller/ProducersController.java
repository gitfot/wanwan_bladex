package com.fun.pro.controller;

import com.fun.pro.entity.Student;
import com.fun.pro.send.Sender;
import com.fun.common.api.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zz
 * @date 2021/6/4
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class ProducersController {

	private final Sender sender;

	/**
	 * 简单模式测试
	 */
	@PostMapping("/send")
	public void producers(){
		sender.sendSimple("Hello World");
	}

	/**
	 * Work模式测试
	 */
	@PostMapping("/batch/send")
	public void batchProducers(){
		for (int i = 0; i < 20; i++){
			try {
				Thread.sleep(1500L);
				sender.sendSimple("Hello World" + i);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * direct模式
	 */
	@PostMapping("/send-direct")
	public R<?> testSendQueue() {
//		sender.sendDirect("direct模式测试，指定routingKey为'test'");
		String messageId = sender.sendDirect(new Student("wan", 22, 1101));
		return R.data(messageId);
	}

	/**
	 * topic模式
	 */
	@PostMapping("/send-topic")
	public void topicSendQueue() {
		sender.sendTopic("topic模式测试，指定routingKey为'blue.sky'");
	}

	/**
	 * 发送信息
	 */
	@PostMapping("/delay-send")
	public void sendDelayMessage(){
		sender.sendDelay("某某某订单已经失效，请归还库存");
	}
}
