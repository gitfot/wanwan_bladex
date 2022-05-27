package com.fun.collect.ga.bitop;

/**
 * @author wanwan 2022/5/26
 */
public class BitSolution {

	/**
	 * 不用加减乘除符号做加法
	 */
	public static int add(int a, int b) {
		while (b != 0) {
			int c = (a & b) << 1;
			a^=b;
			b=c;
		}
		return a;
	}

	/**
	 * 二进制中1的数量
	 */
	public static int hammingWeight(int n) {
		int count = 0;
		while (n != 0) {
			count+= n & 1;
			n >>>= 1;
		}
		return count;
	}

	/**
	 * 计算数值的整数次幂
	 */
	public static double myPow(double x, int n) {
		if(x == 0) {
			return 0;
		}
		long b = n;
		double res = 1.0;
		if(b < 0) {
			x = 1 / x;
			b = -b;
		}
		while(b > 0) {
			if((b & 1) == 1) {
				res *= x; // 1
			}
			x *= x; // 2
			b >>= 1;
		}
		return res;
	}

	/**
	 * 数组中只出现一次的两个数字
	 */
	public static int[] singleNumbers(int[] nums) {
		int x = 0, y = 0, n = 0, m = 1;
		for(int num : nums) {
			n ^= num;
		}
		// 循环找到两个不同数字的最小的不同二进制位
		while((n & m) == 0) {
			m <<= 1;
		}
		for(int num : nums) {
			if((num & m) != 0) {
				x ^= num;
			}
			else {
				y ^= num;
			}
		}
		return new int[] {x, y};
	}

	/**
	 * 字符串转int整数
	 */
	public static int strToInt(String str) {
		str = str.trim();
		if (str.length() == 0) {
			return 0;
		}
		int res = 0, sign = 1;
		int border = Integer.MAX_VALUE / 10;
		if (str.charAt(0) == '-') {
			sign = -1;
			str = str.trim();
		}
		for (int i = 0; i < str.length(); i++) {
			if (res > border) {
				return sign == 1 ? Integer.MAX_VALUE : sign * Integer.MAX_VALUE;
			}
			// str.charAt(i) - '0' 会转为ASCII码相减，结果即为对应的整数
			res = res * 10 + (str.charAt(i) - '0');
		}
		return sign * res;
	}


	public static void main(String[] args) {
//		System.out.println(add(1,2));
//		System.out.println(hammingWeight(6)); // 6 ->> 110
		System.out.println(myPow(9, 4));
		System.out.println(singleNumbers(new int[]{4,4,3,3,1,7}));
	}
}
