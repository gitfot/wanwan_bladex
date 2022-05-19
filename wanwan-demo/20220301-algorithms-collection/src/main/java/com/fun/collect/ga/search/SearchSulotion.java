package com.fun.collect.ga.search;

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

	public static void main(String[] args) {
		int[][] arr = new int[][] {{7,11,15},{8,12,19},{9,16,22}};
//		System.out.println(findIn2dArray(arr, 9));

	}
}
