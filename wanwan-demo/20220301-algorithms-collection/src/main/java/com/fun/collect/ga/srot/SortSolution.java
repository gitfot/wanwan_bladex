package com.fun.collect.ga.srot;

import java.util.Arrays;

/**
 * @author wanwan 2022/5/19
 */
public class SortSolution {

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
	 * 找出数组中的重复数字
	 */
	public int findRepeatNumber(int[] nums) {
		return 0;
	}

	/**
	 * 统计数组中的逆序对
	 */
	public int reversePairs(int[] nums) {
		return 0;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(shellSort(new int[]{7,5,6,4})));
	}
}
