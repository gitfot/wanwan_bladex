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
package com.fun.tool.utils.node;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author Chill
 */
@Data
@EqualsAndHashCode
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 菜单父主键
	 */
	private Long parentId;

	/**
	 * 菜单编号
	 */
	private String code;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 菜单别名
	 */
	private String alias;

	/**
	 * 请求地址
	 */
	private String path;

	/**
	 * 菜单资源
	 */
	private String source;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 菜单类型
	 */
	private Integer category;

	/**
	 * 操作按钮类型
	 */
	private Integer action;

	/**
	 * 是否打开新页面
	 */
	private Integer isOpen;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 是否已删除
	 */
	private Integer isDeleted;
}
