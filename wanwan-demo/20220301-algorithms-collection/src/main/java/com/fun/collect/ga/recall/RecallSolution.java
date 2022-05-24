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

	/**
	 * 计算机器人在矩阵中的运动范围
	 */
	static int m, n, k;
	static boolean[][] visited;
	public static int movingCount(int m, int n, int k) {
		RecallSolution.m = m;
		RecallSolution.n = n;
		visited = new boolean[m][n];
		return dfs(0, 0, 0, 0);
	}
	public static int dfs(int i, int j, int si, int sj) {
		if(i >= m || j >= n || k < si + sj || visited[i][j]) {
			return 0;
		}
		visited[i][j] = true;
		return 1 + dfs(i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj)
			+ dfs(i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8);
	}


	public static void main(String[] args) {
//		char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//		System.out.println(exist(board,"ABCCEDF"));
		System.out.println(movingCount(5,5, 6));
	}
}
