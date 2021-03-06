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
package org.springzhisuan.core.secure.provider;

import java.io.Serializable;

/**
 * 多终端详情接口
 *
 * @author Chill
 */
public interface IClientDetails extends Serializable {

	/**
	 * 客户端id.
	 *
	 * @return String.
	 */
	String getClientId();

	/**
	 * 客户端密钥.
	 *
	 * @return String.
	 */
	String getClientSecret();

	/**
	 * 客户端token过期时间
	 *
	 * @return Integer
	 */
	Integer getAccessTokenValidity();

	/**
	 * 客户端刷新token过期时间
	 *
	 * @return Integer
	 */
	Integer getRefreshTokenValidity();

}
