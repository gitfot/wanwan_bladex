package com.fun.collect.ga.recall;

/**
 * @author wanwan 2022/5/23
 */
public class RecallSolution {

	/**
	 * 判断矩阵字符数组中是否包含字符串
	 */
	public static boolean exist(char[][] board, String word) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (dfsOfExist(board, i, j, word, 0)) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean dfsOfExist(char[][] board, int r, int c, String word, int index) {
		if (r >= board.length || r < 0 || c >= board[0].length || c < 0) {
			return false;
		}
		if (board[r][c] != word.charAt(index)) {
			return false;
		}
		boolean res;
		if (index == word.length()-1) {
			return true;
		}
		res = dfsOfExist(board, r-1, c, word, index+1) || dfsOfExist(board, r+1, c, word, index+1) ||
			dfsOfExist(board, r, c-1, word, index+1) ||dfsOfExist(board, r, c+1, word, index+1);
		return res;
	}

	public static void main(String[] args) {
		char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		System.out.println(exist(board,"ABCCEDF"));
	}
}
