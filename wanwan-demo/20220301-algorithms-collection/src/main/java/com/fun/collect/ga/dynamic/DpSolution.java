package com.fun.collect.ga.dynamic;

/**
 * 动态规划相关算法
 * @author wanwan 2022/5/20
 */
public class DpSolution {

	/**
	 * 连续数组的最大和(动态规划)
	 */
	public static int findGreatestSumOfSubArray(int[] arr) {
		if (arr.length == 0) {
			return -1;
		}
		int max = arr[0], sum = arr[0];
		for (int i = 0; i < arr.length; i++) {
			sum = Math.max(sum + arr[i], arr[i]);
			max = Math.max(sum, max);
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(findGreatestSumOfSubArray(new int[]{1,-2,3,10,-4,7,2,-5}));
	}
}
