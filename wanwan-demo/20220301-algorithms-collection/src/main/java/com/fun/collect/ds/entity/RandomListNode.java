package com.fun.collect.ds.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 链表
 * @author wanwan 2022/5/5
 */
@AllArgsConstructor
@NoArgsConstructor
public class RandomListNode<T> {

	public T data;
	public RandomListNode<T> next;
	public RandomListNode<T> randomPoint;
}
