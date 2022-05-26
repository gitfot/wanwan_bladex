package com.fun.collect.ga.analog;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author wanwan 2022/5/26
 */
public class AnalogSolution {

	/**
	 * 顺时针打印矩阵
	 */
	public static int[] spiralOrder(int[][] matrix) {
		if(matrix.length == 0) {
			return new int[0];
		}
		int index = 0;
		int depth = 0;
		int[] res = new int[matrix.length * matrix[0].length];
		while (index < res.length) {
			for (int i = depth; i < matrix[0].length -depth; i++) {
				res[index++] = matrix[depth][i];
			}
			for (int i = depth+1; i < matrix.length-depth; i++) {
				res[index++] = matrix[i][matrix[0].length-1-depth];
			}
			for (int i = matrix[0].length- 2- depth; i >= depth; i--) {
				res[index++] = matrix[matrix.length - 1 -depth][i];
			}
			for (int i = matrix.length-2-depth; i >= depth+1; i--) {
				res[index++] = matrix[i][depth];
			}
			depth++;
		}
		return res;
	}

	/**
	 * 扑克牌顺子
	 */
	public static boolean isStraight(int[] nums) {
		int max = 0;
		int min = 14;
		HashSet<Integer> set = new HashSet<>();
		for (int num : nums) {
			if (num == 0) {
				continue;
			}
			if (set.contains(num)) {
				return false;
			}
			set.add(num);
			max = Math.max(num,max);
			min = Math.min(num,min);
		}
		return max-min < 5;
	}


	public static void main(String[] args) {
		int[][] arr = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
		System.out.println(Arrays.toString(spiralOrder(arr)));
	}
}
