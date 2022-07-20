package com.auth.server.controller;

import cn.hutool.core.util.RandomUtil;
import com.auth.server.constant.AuthConstants;
import com.auth.server.domain.dto.Oauth2TokenDto;
import com.fun.common.api.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author wanwan 2021/9/29
 */
@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

	private final TokenEndpoint tokenEndpoint;
	private final PasswordEncoder passwordEncoder;
	private final RedisTemplate<String, String> redisTemplate;

	/**
	 * 发送验证码接口
	 */
	@GetMapping("/send/code")
	public Map<String,String> msmCode(String phone) {
		log.info(phone + "请求获取验证码");
		// 模拟调用短信平台获取验证码，以手机号为KEY，验证码为值，存入Redis，过期时间一分钟
		String code = RandomUtil.randomNumbers(6);
		redisTemplate.opsForValue().setIfAbsent(AuthConstants.PHONE_CODE_PREFIX + phone, code, 60*10, TimeUnit.SECONDS);
		String saveCode = redisTemplate.opsForValue().get(phone);// 缓存中的code
		Long expire = redisTemplate.opsForValue().getOperations().getExpire(phone, TimeUnit.SECONDS); // 查询过期时间
		Map<String,String> result=new HashMap<>();
		result.put("code",saveCode);
		result.put("过期时间",expire+"秒");
		return result;
	}

	/**
	 * Oauth2登录认证
	 */
	@PostMapping("/token")
	public R<Oauth2TokenDto> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
		//parameters.put("password", passwordEncoder.encode(parameters.get("password")));
		OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
		Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
			.token(Objects.requireNonNull(oAuth2AccessToken).getValue())
			.refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
			.expiresIn(oAuth2AccessToken.getExpiresIn())
			.tokenHead("Bearer ").build();
		return R.data(oauth2TokenDto);
	}

}
