package com.fun.collect.ga.srot;

/**
 * @author wanwan 2022/5/19
 */
public class SortSolution {

	public int[] shellSort(int[] arr) {
		int gap = arr.length / 2;
		while (gap > 0) {
			//插入排序
			for (int i = gap; i < arr.length; i++) {
				for (int j = i; j-gap > 0 && arr[j] < arr[j-gap]; j-=gap) {
					int tmp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = tmp;
				}
			}
			gap = gap / 2;
		}
		return arr;
	}


}
