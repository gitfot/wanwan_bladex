package com.fun.collect.collect;


import java.util.Arrays;

/**
 * @author wanwan 2022/4/12
 */
public class SortSolution {

	public static void main(String[] args) {
		int[] a = {3,0,5,2,8};
//		insertSort(a);
//		shellSort(a);
//		quickSort(a, 0, a.length-1);
		System.out.println(Arrays.toString(a));
	}

	/**
	 * 简单选择排序
	 * 时间复杂度为o(n2)，但性能略优于冒泡排序
	 */
	public static void minSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[i]) {
					swap(arr, i, j);
				}
			}
		}
	}

	/**
	 * 简单插入排序
	 * 时间复杂度为o(n2)。相对于随机数据，插入排序比冒泡快一倍，比选择排序略快。
	 */
	public static void insertSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0 && arr[j] < arr[j-1]; j--) {
				swap(arr, j, j-1);
			}
		}
	}

	/**
	 * 希尔排序 针对有序序列在插入时采用交换法
	 * 时间复杂度O(Nlog2N)，第一个突破O(n^2)的排序算法；是简单插入排序的改进版；
	 */
	public static void shellSort(int []arr){
		//增量gap，并逐步缩小增量
		int gap = arr.length / 2;
		while(gap > 0) {
			//从第gap个元素，逐个对其所在组进行直接插入排序操作
			for(int i = gap; i < arr.length; i++){
				for (int j = i; j-gap >=0 && arr[j] < arr[j-gap]; j-=gap) {
					//插入排序采用交换法
					swap(arr,j, j-gap);
				}
			}
			gap /= 2;
		}
	}

	/**
	 * 快速排序
	 */
	public static void quickSort(int[] arr, int left, int right) {
		if(left < right) {
			dealPivot(arr, left, right);
			int i = left;
			int j = right - 1;
			while (true) {
				while (arr[++i] < arr[right-1]) {}
				while (j > left && arr[--j] > arr[right-1]) {}
				if (i < j) {
					swap(arr, i, j);
				}
				else {
					break;
				}
			}
			if (i < right) {
				swap(arr, i, right-1);
			}
			quickSort(arr, left, i-1);
			quickSort(arr, i+1, right);
		}
	}

	/**
	 * 枢点处理方法：三项取中排序
	 */
	public static void dealPivot(int[] arr, int left, int right) {
		int mid = left + right / 2;
		if (arr[left] > arr[right]) {
			swap(arr, left, right);
		}
		if (arr[left] > arr[mid]) {
			swap(arr, left, mid);
		}
		if (arr[mid] > arr[right]) {
			swap(arr, mid, right);
		}
		swap(arr, mid, right-1);
	}

	public static void swap(int []arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
