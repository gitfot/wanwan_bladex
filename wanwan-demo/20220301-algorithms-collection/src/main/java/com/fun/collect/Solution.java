package com.fun.collect;

import cn.hutool.json.JSONUtil;

import java.util.*;

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

	/**
	 * 1 把数组排成最小的数
	 * 快速排序
	 */
	public static String minNumber(int[] nums) {
		quickSort(nums, 0, nums.length-1);
		return Arrays.toString(nums);
	}

	/**
	 * 1.1 快排
	 */
	public static void quickSort(int[] nums,int left, int right) {
		if (left > right) {
			return ;
		}
		int i = left, j = right;
		while (i < j) {
			while (j > i && compare(nums[j],nums[left]) >= 0) {
				j --;
			}
			while (i < j && compare(nums[i], nums[left])<= 0) {
				i ++;
			}
			swap(nums, i,j);
		}
		swap(nums, left, i);
		quickSort(nums, left, i -1);
		quickSort(nums, i +1, right);
	}
	/**
	 * 1.2 比较字符化的数字大小
	 */
	public static int compare(int a, int b) { //30 3
		String s1 = String.valueOf(a);
		String s2 = String.valueOf(b);
		return Integer.compare(Integer.parseInt(s1 + s2), Integer.parseInt(s2 + s1));
	}

	/**
	 * 和为s的连续正数序列
	 */
	public static int[][] findContinuousSequence(int target) {
		List<int[]> res = new ArrayList<>();
		//滑动窗口的左右边界
		int i = 1, j = 1, sum = 1;
		//窗口右边界到目标长度一半的时候就结束了
		// 比如n=9, 则n/2=4, (n/2)+1=5，所以==>> n/2 + (n/2)+1 >= n
		while (i <= target / 2) {
			if (sum > target) {
				sum-=i;
				i++;
			}
			else if (sum < target) {
				j++;
				sum +=j;
			}
			else {
				int[] tmp = new int[j-i+1];
				for (int k = i; k <= j; k++) {
					tmp[k-i] = k;
				}
				res.add(tmp);
				//继续向右滑动找下一组
				j++;
				sum +=j;
			}
		}
		return res.toArray(new int[res.size()][]);
	}

	/**
	 * 和为s的两个数字
	 */
	public static int[] twoSum(int[] nums, int target) {
		int i = 0, j = nums.length-1;
		int[] res = new int[2];
		while (i <= j) {
			if (nums[i] + nums[j] < target) {
				i++;
			}
			else if (nums[i] + nums[j] > target) {
				j--;
			}
			else {
				return new int[] {nums[i],nums[j]};
			}
		}
		return res;
	}

	/**
	 * 旋转字符串
	 * 解法1：字符串切片
	 * 执行用时：0 ms 消耗41M
	 */
	public static String reverseLeftWords(String s, int n) {
		return s.substring(n) + s.substring(0,n);
	}

	/**
	 * 圆圈中剩下的最后数字
	 */
	public int lastRemaining(int n, int m) {

		for (int i = 0; i < n; i++) {

		}

		return -1;
	}

	public static void main(String[] args) {
//		System.out.println(Arrays.toString(exchange(new int[]{1,2,3,4,5,6})));
//		System.out.println(Arrays.toString(constructArr(new int[]{1,2,3,4,5})));
//		System.out.println(majorityElement(new int[]{1,3,4,5,2,2}));
//		System.out.println(minNumber(new int[]{59,34,23,3,30,34}));
//		System.out.println(JSONUtil.toJsonStr((findContinuousSequence(9))));
//		System.out.println(Arrays.toString(twoSum(new int[]{2,7,11,15},9)));
		System.out.println(reverseLeftWords("abcdefg", 2));
	}
}
