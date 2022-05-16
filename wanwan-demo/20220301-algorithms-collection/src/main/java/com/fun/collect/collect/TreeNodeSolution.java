package com.fun.collect.collect;

import cn.hutool.json.JSONUtil;
import com.fun.collect.ds.entity.TreeNode;

/**
 * @author wanwan 2022/4/17
 */
public class TreeNodeSolution {

	public static void main(String[] args) {
		TreeNode TreeNode = new TreeNode(20, null, null);
		buildTree(TreeNode, 21);
		buildTree(TreeNode, 10);
		buildTree(TreeNode, 36);
		buildTree(TreeNode, 18);
		System.out.println(JSONUtil.toJsonStr(TreeNode));
	}

	/**
	 * 前序遍历
	 */
	public static void preOrder(TreeNode tree) {
		if (tree != null) {
			System.out.println(tree.data);
			preOrder(tree.left);
			preOrder(tree.right);
		}
	}

	/**
	 * 中序遍历
	 */
	public static void inOrder(TreeNode tree) {
		if (tree != null) {
			inOrder(tree.left);
			System.out.println(tree.data);
			inOrder(tree.right);
		}
	}

	/**
	 * 后序遍历
	 */
	public static void postOrder(TreeNode tree) {
		if (tree != null) {
			postOrder(tree.left);
			postOrder(tree.right);
			System.out.println(tree.data);
		}
	}

	/**
	 * 判断是否存在节点
	 */
	public static boolean hasKey(TreeNode tree, int key) {
		if (tree == null) {
			return false;
		}
		if (tree.data == key) {
			return true;
		}
		if (key > tree.data) {
			return hasKey(tree.right, key);
		}
		else {
			return hasKey(tree.left, key);
		}
	}

	/**
	 * 构建树
	 */
	public static TreeNode buildTree(TreeNode tree, int key) {
		if (tree == null) {
			TreeNode TreeNode = new TreeNode();
			TreeNode.setData(key);
			return TreeNode;
		}
		if (key > tree.getData()) {
			tree.right = buildTree(tree.right, key);
		}
		else {
			tree.left = buildTree(tree.left, key);
		}
		return tree;
	}
}
