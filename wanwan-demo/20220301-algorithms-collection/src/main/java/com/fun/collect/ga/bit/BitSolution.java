package com.fun.collect.ga.bit;

/**
 * @author wanwan 2022/5/28
 */
public class BitSolution {

	/**
	 * 数组中数字出现的次数
	 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
	 */
	public static int getSingleNumInRepeatNums(int[] arr) {
		int[] counts = new int[32];
		for (int i = 0; i < arr.length; i++) {
			//计算二进制中1的个数
			for (int j = 0; j < 32; j++) {
				counts[j] += arr[i] & 1;
				arr[i] >>>= 1;
			}
		}
		int res = 0, m = 3;
		// 对3求余获得结果
		for(int i = 0; i < 32; i++) {
			res <<= 1;
			res |= counts[31 - i] % m;
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(getSingleNumInRepeatNums(new int[]{3,5,3,3}));
	}
}
