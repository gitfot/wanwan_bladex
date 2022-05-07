package com.fun.collect.ds.entity;

import lombok.Data;

import java.util.List;

/**
 * @author wanwan 2022/3/1
 */
@Data
public class Tree {
	private String id;
	private String name;

	private List<Tree> children;
}
