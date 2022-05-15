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
			preOrder(tree.lChild);
			preOrder(tree.rChild);
		}
	}

	/**
	 * 中序遍历
	 */
	public static void inOrder(TreeNode tree) {
		if (tree != null) {
			inOrder(tree.lChild);
			System.out.println(tree.data);
			inOrder(tree.rChild);
		}
	}

	/**
	 * 后序遍历
	 */
	public static void postOrder(TreeNode tree) {
		if (tree != null) {
			postOrder(tree.lChild);
			postOrder(tree.rChild);
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
			return hasKey(tree.getRChild(), key);
		}
		else {
			return hasKey(tree.getLChild(), key);
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
			tree.setRChild(buildTree(tree.getRChild(), key));
		}
		else {
			tree.setLChild(buildTree(tree.getLChild(), key));
		}
		return tree;
	}
}
