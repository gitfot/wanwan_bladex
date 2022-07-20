package com.fun.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wanwan 2022/2/13
 */
@Data
@AllArgsConstructor
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private Integer age;
	private Integer number;
}
