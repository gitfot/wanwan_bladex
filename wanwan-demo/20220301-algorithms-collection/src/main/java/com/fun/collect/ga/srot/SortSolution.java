package com.fun.collect.ga.srot;

import java.util.Arrays;

/**
 * @author wanwan 2022/5/19
 */
public class SortSolution {

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	/**
	 * 希尔排序 O(Nlog2N)
	 */
	public static int[] shellSort(int[] arr) {
		int gap = arr.length / 2;
		int count = 0;
		while (gap >= 1) {
			//插入排序
			for (int i = gap; i < arr.length; i+=gap) {
				for (int j = i; j-gap >= 0 && arr[j] < arr[j-gap]; j-=gap) {
					int tmp = arr[j];
					arr[j] = arr[j-gap];
					arr[j-gap] = tmp;
					count ++;
				}
			}
			gap = gap / 2;
		}
		return arr;
	}

	/**
	 * 归并排序
	 */
	public static void mergeSortUpDown(int arr[], int start, int end) {
		if (start >= end || arr.length ==0) {
			return;
		}
		int mid = (end -start) /2;
		//递归分解
		mergeSortUpDown(arr,start,mid);
		mergeSortUpDown(arr,mid +1,end);
		//合并排序
		merge(arr, start, end, mid);
	}
	/**
	 * 用于归并排序的-排序子序列
	 */
	public static void merge(int[] arr, int start, int end, int mid) {
		int i = start, j = mid+1;
		int[] tmp = new int[end-start+1];
		int k= 0;
		while (i <= mid && j <=end) {
			if (arr[i] < arr[j]) {
				tmp[k++] = arr[i++];
			} else {
				tmp[k++] = arr[j++];
			}
		}
		while (i <= mid) {
			tmp[k++] = arr[i++];
		}
		while (j <= end) {
			tmp[k++] = arr[j++];
		}
		for (k = 0; k < tmp.length; k++) {
			arr[k+start] = tmp[k];
		}
	}

	/**
	 * 快速排序
	 */
	public static void quickSort(int[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		// i不能等于l+1，因为数组只有两个元素时 l+1不能正确交换
		int i = l, j = r;
		while (i < j) {
			//这里得j--在i++前面，才能在第二个swap正确交换基点
			while (i < j && arr[j] >= arr[l]) {
				j--;
			}
			while (i < j && arr[i] <= arr[l]) {
				i++;
			}
			swap(arr, i, j);
		}
		swap(arr, l, i);
		quickSort(arr, l, i-1);
		quickSort(arr, i+1, r);
	}

	/**
	 * 计算第 n 丑数
	 * 丑数的性质：丑数只包含因子 2, 3, 5
	 */
	public static int nthUglyNumber(int n) {
		// 数组dp[i] 表示第i个丑数
		int[] dp = new int[n+1];
		dp[1] = 1;
		// n2、n3、n5为下一组丑数，p则为对应的丑数因子
		int p2 = 1, p3 = 1, p5 = 1;
		for (int i = 2; i <= n; i++) {
			int n2 = p2 * 2, n3 = p3 * 3, n5 = p5 * 5;
			dp[i] = Math.min(Math.min(n2, n3), n5);
			//更新指针 指向下一个丑数
			if (dp[i] == n2) p2 ++;
			if (dp[i] == n3) p3 ++;
			if (dp[i] == n5) p5 ++;
		}
		return dp[n];
	}

	public static void main(String[] args) {
//		System.out.println(Arrays.toString(shellSort(new int[]{7,5,6,4})));
//		int[] arr = new int[]{2,4,0,1,3,5}; quickSort(arr, 0, 5);
//		System.out.println(Arrays.toString(arr));
		System.out.println(nthUglyNumber(10));
	}

	/**
	 * 统计数组中的逆序对
	 */
	public int reversePairs(int[] nums) {
		return 0;
	}
}
