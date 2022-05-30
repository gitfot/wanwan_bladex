package com.fun.collect;

import java.util.Arrays;

/**
 * @author wanwan 2022/5/28
 */
public class Solution {

	public static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * 调整数组中的奇偶顺序
	 */
	public static int[] exchange(int[] nums) {
		// fast找奇数、slow偶数
		int slow = 0, fast = 0;
		while (fast < nums.length) {
			if (nums[fast] % 2 == 1) {
				swap(nums, fast, slow);
				slow++;
			}
			fast++;
		}
		return nums;
	}

	/**
	 * 构建乘积数组
	 */
	public static int[] constructArr(int[] a) {
		int[] res = new int[a.length];
		res[0] = 1;
		// 计算B[i]的下三角各元素的乘积
		for (int i = 1; i < res.length; i++) {
			res[i] = res[i-1] * a[i-1];
		}
		int tmp = 1;
		//计算 B[i]的上三角各元素的乘积，记为tmp，并乘入B[i] ；
		for (int i = res.length -2; i >= 0; i--) {
			tmp *= a[i+1];
			res[i] *=tmp;
		}
		return res;
	}

	/**
	 * 数组中出现次数超过一半的数字（数字）
	 */
	public static int majorityElement(int[] nums) {
		int x = 0, votes = 0;
		for (int num : nums) {
			if (votes == 0) {
				x = num;
			}
			votes += num == x ? 1 : -1;
		}
		return x;
	}

	/**
	 * 整数中1出现的次数
	 */
	public static int countDigitOne(int n) {
		// digit表示位因子，比如2304十位上的位因子为10
		int digit = 1, res = 0;
		int high = n / 10, cur = n % 10, low = 0;
		while (high != 0 || cur !=0) {
			if (cur == 0) {
				res += high * digit;
			}
			else if (cur == 1) {
				res += high * digit + low +1;
			}
			else {
				res += high * digit + digit;
			}
			low += cur * digit;
			high /= 10;
			cur = high % 10;
			digit *= 10;
		}
		return res;
	}



	public static void main(String[] args) {
//		System.out.println(Arrays.toString(exchange(new int[]{1,2,3,4,5,6})));
//		System.out.println(Arrays.toString(constructArr(new int[]{1,2,3,4,5})));
		System.out.println(majorityElement(new int[]{1,3,4,5,2,2}));

	}
}
