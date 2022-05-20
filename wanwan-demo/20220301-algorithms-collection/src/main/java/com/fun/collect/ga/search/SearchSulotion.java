package com.fun.collect.ga.search;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wanwan 2022/5/19
 */
public class SearchSulotion {

	/**
	 * 二分查找
	 */
	public static int binarySearch(int[] arr, double e) {
		int left = 0, right = arr.length -1, mid = -1;
		while (left <= right) {
			mid = (left +right) / 2;
			if (e > arr[mid]) {
				left = mid +1;
			}
			if (e < arr[mid]) {
				right = mid -1;
			} else {
				return mid;
			}
		}
		return mid;
	}

	/**
	 * 统计k在升序数组中的出现次数
	 */
	public static int countRepeatNum(int[] arr, int e) {
		int right = binarySearch(arr, e + 0.5);
		int left = binarySearch(arr, e - 0.5);
		return right - left;
	}

	/**
	 * 二维数组中的查找
	 */
	public static boolean findIn2dArray(int[][] arr, int e) {
		//获取右上角元素（相当于二叉树的根节点）
		int row = 0, column = arr.length -1;
		while (row < arr.length && column >= 0) {
			int node = arr[row][column];
			if (e > node) {
				row += 1;
			}
			else if (e < node) {
				column -=1;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * 求翻转数组的最小值
	 */
	public static int minNumberInRotateArray(int [] arr) {
		int left = 0, right = arr.length -1;
		int mid = -1;
		while (left <= right) {
			mid = (left + right) / 2;
			// m 在右排序数组中，旋转点在 [left, m]中
			if (arr[mid] < arr[right]) {
				right = mid;
			}
			// m在左排序数组中，旋转点在 [m+1, right] 中
			else if (arr[mid] > arr[right]) {
				left = mid + 1;
			}
			else {
				return mid;
			}
		}
		return mid;
	}

	/**
	 * 求字符串内的字符有多少种组合
	 */
	public static ArrayList<String> permutation(String str) {
		if (str.isEmpty()) {
			return new ArrayList<>();
		}
		HashSet<String> res = new HashSet<>();
		perm(0, str, res);
		return new ArrayList<>(res);
	}
	public static void perm(int pos, String str, Set<String> set) {
		if (pos + 1 == str.length()) {
			set.add(str);
			return;
		}
		for (int i = pos; i < str.length(); i++) {
			str = swap(str, pos, i);
			perm(pos + 1, str, set);
			str = swap(str, pos, i);
		}
	}
	public static String swap(String str, int i, int j) {
		String[] split = str.split("");
		String tmp = split[i];
		split[i] = split[j];
		split[j] = tmp;
		return StringUtils.join(split);
	}

	public static void main(String[] args) {
//		int[][] arr = new int[][] {{7,11,15},{8,12,19},{9,16,22}};
//		System.out.println(findIn2dArray(arr, 9));
//		int[] a1 = new int[] {3,4,5,6,7,1,2};
//		int[] a2 = new int[] {6,7,1,2,3,4,5};
//		System.out.println(minNumberInRotateArray(a1));
//		System.out.println(minNumberInRotateArray(a2));
//		System.out.println(permutation("ABC"));
	}
}
