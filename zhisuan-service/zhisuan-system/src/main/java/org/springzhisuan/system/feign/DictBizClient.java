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
package org.springzhisuan.system.feign;


import lombok.AllArgsConstructor;
import org.springzhisuan.core.tenant.annotation.NonDS;
import org.springzhisuan.core.tool.api.R;
import org.springzhisuan.system.entity.DictBiz;
import org.springzhisuan.system.service.IDictBizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


/**
 * 字典服务Feign实现类
 *
 * @author Chill
 */
@NonDS
@ApiIgnore
@RestController
@AllArgsConstructor
public class DictBizClient implements IDictBizClient {

	private final IDictBizService service;

	@Override
	@GetMapping(GET_BY_ID)
	public R<DictBiz> getById(Long id) {
		return R.data(service.getById(id));
	}

	@Override
	@GetMapping(GET_VALUE)
	public R<String> getValue(String code, String dictKey) {
		return R.data(service.getValue(code, dictKey));
	}

	@Override
	@GetMapping(GET_LIST)
	public R<List<DictBiz>> getList(String code) {
		return R.data(service.getList(code));
	}

}
