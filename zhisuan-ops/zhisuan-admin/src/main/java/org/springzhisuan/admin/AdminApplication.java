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
package org.springzhisuan.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springzhisuan.core.launch.ZhisuanApplication;
import org.springzhisuan.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * admin启动器
 *
 * @author Chill
 */
@EnableAdminServer
@SpringCloudApplication
public class AdminApplication {

	public static void main(String[] args) {
		ZhisuanApplication.run(AppConstant.APPLICATION_ADMIN_NAME, AdminApplication.class, args);
	}

}
