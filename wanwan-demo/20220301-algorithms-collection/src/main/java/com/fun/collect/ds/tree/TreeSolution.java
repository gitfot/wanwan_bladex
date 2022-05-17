package com.fun.collect.ds.tree;

import cn.hutool.json.JSONUtil;
import com.fun.collect.collect.TreeNodeSolution;
import com.fun.collect.ds.entity.TreeNode;

import javax.swing.*;
import java.nio.file.Path;
import java.util.*;

/**
 * @author wanwan 2022/5/15
 */
public class TreeSolution {

	/**
	 * 计算二叉树的最大深度（非完全二叉树）
	 */
	public static int maxDepth (TreeNode root) {
		if (root == null) {
			return 0;
		}
		return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
	}


	/**
	 * 层序遍历1
	 */
	public static void printTree(TreeNode root) {
		if (root == null) {
			return;
		}
		ArrayDeque<TreeNode> queue = new ArrayDeque<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode node = queue.pop();
			System.out.println(node.data);
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
	}

	/**
	 * 层序遍历2
	 */
	public static void printTree2(TreeNode root) {
		if (root == null) {
			return;
		}
		ArrayDeque<TreeNode> queue = new ArrayDeque<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.pop();
				System.out.println(node.data);
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
		}
	}


	/**
	 * 层序遍历反转打印（之字型）
	 */
	public static ArrayList<ArrayList<Integer> > printReverse(TreeNode pRoot) {
		LinkedList<TreeNode> queue = new LinkedList<>();
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		queue.add(pRoot);
		boolean rev = true;
		while(!queue.isEmpty()){
			int size = queue.size();
			ArrayList<Integer> list = new ArrayList<>();
			for(int i=0; i<size; i++){
				TreeNode node = queue.poll();
				if(node == null){continue;}
				if(rev){
					list.add(node.data);
				}else{
					list.add(0, node.data);
				}
				queue.offer(node.left);
				queue.offer(node.right);
			}
			if(list.size()!=0){res.add(list);}
			rev=!rev;
		}
		return res;
	}

	/**
	 * 判断树A和树b结构是否相同
	 */
	public static boolean doesHasTree(TreeNode sourceRoot, TreeNode targetRoot) {
		if (targetRoot == null) {
			return true;
		}
		if (sourceRoot == null) {
			return false;
		}
		if (sourceRoot.data != targetRoot.data) {
			return false;
		}
		return doesHasTree(sourceRoot.left, targetRoot.left)
			&& doesHasTree(sourceRoot.right,targetRoot.right);
	}

	/**
	 * 判断是树B是否树A的子树
	 */
	public static boolean hasSubtree(TreeNode sourceRoot, TreeNode targetRoot) {
		if (targetRoot == null || sourceRoot == null) {
			return false;
		}
		return doesHasTree(sourceRoot, targetRoot)
			|| hasSubtree(sourceRoot.left, targetRoot)
			|| hasSubtree(sourceRoot.right, targetRoot);
	}

	/**
	 * 二叉树中和为某一值的路径(一)
	 */
	public static boolean hasPathSumWithDfs(TreeNode node, int sum) {
		if (node == null) {
			return false;
		}
		sum -= node.data;
		if (node.left == null &&node.right == null && sum == 0) {
			return true;
		}
		return hasPathSumWithDfs(node.left, sum) || hasPathSumWithDfs(node.right, sum);
	}

	/**
	 * 二叉树中和为某一值的路径(二)
	 *  输出满足条件的所有路径
	 */
	private static final List<Deque<Integer>> res = new ArrayList<>();
	private static final Deque<Integer> path = new ArrayDeque<>();
	public static void findPathSumWithDfs(TreeNode node, int sum) {
		if (node == null) {
			return;
		}
		path.add(node.data);
		sum -= node.data;
		if (node.left == null &&node.right == null && sum == 0) {
			res.add(new ArrayDeque<>(path));
		}
		findPathSumWithDfs(node.left, sum);
		findPathSumWithDfs(node.right, sum);
		path.removeLast();
	}

	/**
	 * 序列化二叉树
	 */
	public static String serialize(TreeNode root) {
		if (root == null) {
			return "#";
		}
		return root.data +"," +serialize(root.left) +","+ serialize(root.right);
	}

	/**
	 * 反序列二叉树
	 */
	static int index = -1;
	public static TreeNode deserialize(String str) {
		String[] arr = str.split(",");
		index++;
		if (index > arr.length) {
			return null;
		}
		TreeNode node = null;
		if (!"#".equals(arr[index])) {
			node = new TreeNode(Integer.parseInt(arr[index]), null, null);
			node.left = deserialize(str);
			node.right = deserialize(str);
		}
		return node;
	}


	public static void main(String[] args) {
		TreeNode tree = new TreeNode(10, null, null);
		TreeNodeSolution.buildTree(tree, 5);
		TreeNodeSolution.buildTree(tree, 4);
		TreeNodeSolution.buildTree(tree, 7);
		TreeNodeSolution.buildTree(tree, 12);

//		System.out.println(JSONUtil.toJsonStr(tree));
//		System.out.println(maxDepth(tree));
//		printTree(tree); // 10 -> 5 -> 12 -> 4 -> 7
//		printTree2(tree);
//		System.out.println(hasSubtree(tree, tree2));
//		System.out.println(hasPathSumWithDfs(tree, 15));
//		findPathSumWithDfs(tree, 22);
//		System.out.println(res);
		System.out.println(serialize(tree));
	}

}
