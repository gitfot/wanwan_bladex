package com.fun.collect.ds.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wanwan 2022/5/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

	public int data;
	public TreeNode lChild;
	public TreeNode rChild;
}
