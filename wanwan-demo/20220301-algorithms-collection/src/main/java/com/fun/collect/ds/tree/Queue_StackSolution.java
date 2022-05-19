package com.fun.collect.ds.tree;


import java.util.*;

/**
 * @author wanwan 2022/5/18
 */
public class Queue_StackSolution {

	/**
	 * 利用两个栈来实现队列的功能
	 */
	private Stack<Integer> stack1 = new Stack<>();
	private Stack<Integer> stack2= new Stack<>();
	public void push1(int e) {
		stack1.push(e);
	}
	public int pop1 () {
		if (stack2.size() <= 0) {
			for (Integer e : stack1) {
				stack2.push(stack1.pop());
			}
		}
		return stack2.pop();
	}

	/**
	 * 实现栈的min函数，要求时间复杂度为o(1)
	 */
	private final Stack<Integer> stack = new Stack<>();
	private final Stack<Integer> minStack= new Stack<>();
	public void push2(int e) {
		stack.push(e);
		if (minStack.size() == 0 || e < minStack.peek()) {
			minStack.push(e);
		}
	}
	public int pop2() {
		Integer pop = stack.pop();
		if (pop.equals(minStack.peek())) {
			minStack.pop();
		}
		return pop;
	}
	public int min() {
		return minStack.peek();
	}

	/**
	 * 判断第二个序列是否为为第一个序列的弹出序列
	 */
	public static boolean isPopOrder(int [] pushA,int [] popA) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		int j = 0;
		for (int i = 0; i < pushA.length; i++) {
			queue.push(pushA[i]);
			while (!queue.isEmpty() && queue.peek().equals(popA[j])) {
				queue.pop();
				j++;
			}
		}
		return queue.isEmpty();
	}

	/**
	 * 反转句子中的单词顺序
	 */
	public static String reverseSentence(String str) {
		String[] split = str.split(" ");
		ArrayDeque<String> queue = new ArrayDeque<>(Arrays.asList(split));
		for (String s : split) {
			queue.push(s);
		}
		StringBuilder res = new StringBuilder();
		while (!queue.isEmpty()) {
			res.append(queue.pop()).append(" ");
		}
		return res.toString();
	}

	/**
	 * 求滑动窗口的最大值：暴力法
	 */
	public static ArrayList<Integer> maxInWindows(int [] num, int size) {
		ArrayList<Integer> res = new ArrayList<>();
		for (int i = 0; i+size <= num.length; i++) {
			int maxNum = 0;
			for (int j = i; j < size + i && j < num.length; j++) {
				maxNum = Math.max(maxNum, num[j]);
			}
			res.add(maxNum);
		}
		return res;
	}

	public static void main(String[] args) {
//		boolean popOrder = isPopOrder(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1});
//		System.out.println(popOrder);
//		System.out.println(reverseSentence("coder? a you are"));
//		ArrayList<Integer> list = maxInWindows(new int[]{1, 2, 3, 4, 5}, 2);
//		System.out.println(list);

	}
}
