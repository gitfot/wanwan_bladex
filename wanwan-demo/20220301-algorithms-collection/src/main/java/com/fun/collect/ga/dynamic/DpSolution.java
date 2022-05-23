package com.fun.collect.ga.dynamic;

import java.util.HashMap;

/**
 * 动态规划相关算法
 * @author wanwan 2022/5/20
 */
public class DpSolution {

	/**
	 * 连续数组的最大和(动态规划)
	 */
	public static int findGreatestSumOfSubArray(int[] arr) {
		if (arr.length == 0) {
			return -1;
		}
		int max = arr[0], sum = arr[0];
		for (int i = 0; i < arr.length; i++) {
			sum = Math.max(sum + arr[i], arr[i]);
			max = Math.max(sum, max);
		}
		return max;
	}

	/**
	 * 连续数组的最大和2 返回最大的连续数组
	 */
	public static int[] findGreatestSumOfSubArray2 (int[] array) {
		//动态规划数组
		int[] dp = new int[array.length];
		int max = 0;
		dp[0] = array[0];
		//定义滑动区间
		int left = 0, right = 0;
		//定义最长区间
		int resl = 0, resr = 0;
		for (int i = 1; i < array.length; i++) {
			right++;
			//状态转移方程
			dp[i] = Math.max(dp[i-1] + array[i], array[i]);
			if (dp[i-1] + array[i] < array[i]) {
				left = right;
			}
			if (dp[i] > max) {
				max = dp[i];
				resl = left;
				resr = right;
			}
		}
		int[] res = new int[resr - resl + 1];
		for (int i = resl; i <= resr; i++) {
			res[i - resl] = array[i];
		}
		return res;
	}

	/**
	 * 青蛙跳台阶
	 * 解法一： 递归
	 */
	public int jumpFloor1(int num) {
		if (num <= 1) {
			return 1;
		}
		return jumpFloor1(num -1) + jumpFloor1(num -2);
	}

	/**
	 * 青蛙跳台阶
	 * 解法二： 动态规划
	 */
	public int jumpFloor2(int num) {
		int[] dp = new int[num];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i < num; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		return dp[num];
	}

	/**
	 * 青蛙跳台阶扩展：一次可跳n台阶
	 * 解法二： 动态规划
	 */
	public static int jumpFloorExt(int num) {
		int[] dp = new int[num + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= num; i++) {
			dp[i] = dp[i-1]  * 2 ;
		}
		return dp[num];
	}

	/**
	 * 求一组数字的两两相减的最大值，要求只能后一个元素减去前一个元素（股票收益问题）
	 */
	public static int maxProfit (int[] prices) {
		int[] dp = new int[prices.length];
		int min = prices[0];
		dp[0] = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < min) {
				min = prices[i];
			}
			dp[i] = Math.max(dp[i-1], prices[i] - min);
		}
		return dp[prices.length -1];
	}

	/**
	 * 求二维数组从左上角移动到左下角的最大和，要求只能向右或者向下移动
	 */
	public static int gridMaxValue (int[][] arr) {
		int[][] dp = new int[arr.length][arr[0].length];
		return recursionOfMaxValue(arr, dp, arr.length -1, arr[0].length -1);
	}

	public static int recursionOfMaxValue(int[][] arr, int[][] dp, int row, int column) {
		if (row == 0 && column == 0) {
			dp[0][0] = arr[row][column];
		}
		else if (column == 0) {
			dp[row][0] = arr[row][0] + recursionOfMaxValue(arr, dp, 0, row-1);
		}
		else if (row == 0) {
			dp[0][column] = arr[0][column] + recursionOfMaxValue(arr, dp, column-1, 0);
		}
		else if (dp[row][column] == 0) {
			dp[row][column] = arr[row][column] + Math.max(recursionOfMaxValue(arr, dp,column -1, row), recursionOfMaxValue(arr, dp, column, row -1));
		}
		return dp[row][column];
	}

	/**
	 * 求字符串中不重复字符的最长长度
	 * 方法一：滑动窗口 + 哈希表
	 */
	public static int lengthOfLongestSubstring1 (String s) {
		HashMap<Character, Integer> map = new HashMap<>();
		int max = 0;
		int left = 0;
		int right = 0;
		for (; right < s.length(); right++) {
			if (map.containsKey(s.charAt(right))) {
				map.put(s.charAt(right), map.get(s.charAt(right)) +1);
			} else {
				map.put(s.charAt(right), 1);
			}
			//遇到重复值时向右滑动窗口
			while (map.get(s.charAt(right)) > 1) {
				map.put(s.charAt(left), map.get(s.charAt(left)) -1);
				left++;
			}
			max = Math.max(max, right -left+1);
		}
		return max;
	}

	/**
	 * 求字符串中不重复字符的最长长度
	 * 方法二：动态规划 + 哈希表
	 */
	public static int lengthOfLongestSubstring2 (String s) {
		HashMap<Character, Integer> map = new HashMap<>(s.length());
		int max = 0;
		int start = 0;
		for (int i = 0; i < s.length(); i++) {
			if (map.containsKey(s.charAt(i))) {
				//拿到上次出现的位置+1
				start = map.get(s.charAt(i)) + 1;
			}
			//记录字符出现的位置
			map.put(s.charAt(i), i);
			max = Math.max(max, i - start + 1);
		}
		return max;
	}

	/**
	 * 把数字翻译成字符串
	 */
	public static int decodeNumString (String nums) {
		// write code here
		int[] dp = new int[nums.length() + 1];
		dp[0] = dp[1] = 1;
		for (int i = 2; i <= nums.length(); i++) {
			//获取两位字符
			String sub = nums.substring(i - 2, i);
			if (sub.compareTo("10") > 0 &&sub.compareTo("26") < 0) {
				dp[i] = dp[i-1] + dp[i-2];
			}
			else {
				dp[i] = dp[i-1];
			}
		}
		return dp[nums.length() -1];
	}


	public static void main(String[] args) {
//		System.out.println(findGreatestSumOfSubArray(new int[]{1,-2,3,10,-4,7,2,-5}));
//		System.out.println(Arrays.toString(findGreatestSumOfSubArray2(new int[]{1, -2, 3, 10, -4, 7, 2, -5})));
//		System.out.println(jumpFloorExt(4));
//		System.out.println(maxProfit(new int[]{8,9,2,5,4,7,1}));
//		int[][] arr = new int[][]{{1,3,1},{1,5,1},{4,2,1}};
//		System.out.println(gridMaxValue(arr));
//		System.out.println(lengthOfLongestSubstring2("abcabbe"));
		System.out.println(decodeNumString("12258"));
	}
}
