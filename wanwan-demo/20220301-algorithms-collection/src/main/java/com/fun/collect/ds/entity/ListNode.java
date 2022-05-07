package com.fun.collect.ds.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 链表
 * @author wanwan 2022/5/5
 */
@AllArgsConstructor
public class ListNode<T> {

	public T data;
	public ListNode<T> next;
}
