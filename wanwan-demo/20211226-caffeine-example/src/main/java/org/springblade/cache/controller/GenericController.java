package org.springblade.cache.controller;

import com.fun.common.api.R;
import lombok.RequiredArgsConstructor;
import org.springblade.cache.entity.UserInfo;
import org.springblade.cache.service.CaffeineCacheServiceImpl;
import org.springblade.cache.service.UserInfoServiceImpl2;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author wanwan 2021/12/24
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GenericController {

	//private final UserInfoService userInfoService;
	private final UserInfoServiceImpl2 userInfoService;
	private final CaffeineCacheServiceImpl caffeineCacheService;

	@Resource(name = "oneMinuteCaffeineCache")
	CacheManager caffeineCacheManager;

	@PostMapping("/user")
	public R<?> getUser() {
		//userInfoService.addUserInfo(new UserInfo(1, "test","male", 20));
		//userInfoService.getByName(1);
		//return R.data(userInfoService.getByName(1));
		UserInfo personById = userInfoService.getPersonById(1,"test");


		return R.data(personById);
	}

	@PostMapping("/test")
	public R<?> test() {
		caffeineCacheService.putValue("1", "test");
		String value = caffeineCacheService.getValue("1");
		return R.data(value);
	}

	public static void main(String[] args) {
		Optional<UserInfo> userOpt = Optional.ofNullable(new UserInfo(1,"2","male",22));

		userOpt.map(UserInfo::getName)
			.map(String::toUpperCase)
			.orElse(null);
	}
}
