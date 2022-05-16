package com.fun.collect.ds.tree;

import cn.hutool.json.JSONUtil;
import com.fun.collect.collect.TreeNodeSolution;
import com.fun.collect.ds.entity.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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



	public static void main(String[] args) {
		TreeNode tree = new TreeNode(1, null, null);
		TreeNodeSolution.buildTree(tree, 2);
		TreeNodeSolution.buildTree(tree, 3);
		TreeNodeSolution.buildTree(tree, 4);
		TreeNodeSolution.buildTree(tree, 5);
//		System.out.println(JSONUtil.toJsonStr(tree));
//		System.out.println(maxDepth(tree));
//		printTree(tree);
//		printTree2(tree);

	}

}
