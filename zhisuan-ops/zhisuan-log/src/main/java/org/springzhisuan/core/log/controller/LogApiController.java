/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springzhisuan.core.log.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springzhisuan.core.log.model.LogApi;
import org.springzhisuan.core.log.service.ILogApiService;
import org.springzhisuan.core.mp.support.Condition;
import org.springzhisuan.core.mp.support.Query;
import org.springzhisuan.core.tenant.annotation.NonDS;
import org.springzhisuan.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 控制器
 *
 * @author Chill
 */
@NonDS
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class LogApiController {

	private final ILogApiService logService;

	/**
	 * 查询单条
	 */
	@GetMapping("/detail")
	public R<LogApi> detail(LogApi log) {
		return R.data(logService.getOne(Condition.getQueryWrapper(log)));
	}

	/**
	 * 查询多条(分页)
	 */
	@GetMapping("/list")
	public R<IPage<LogApi>> list(@ApiIgnore @RequestParam Map<String, Object> log, Query query) {
		IPage<LogApi> pages = logService.page(Condition.getPage(query.setDescs("create_time")), Condition.getQueryWrapper(log, LogApi.class));
		return R.data(pages);
	}

}
