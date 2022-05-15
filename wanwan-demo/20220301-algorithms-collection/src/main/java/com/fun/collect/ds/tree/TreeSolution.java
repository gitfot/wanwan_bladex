package com.fun.collect.ds.tree;

import cn.hutool.json.JSONUtil;
import com.fun.collect.collect.TreeNodeSolution;
import com.fun.collect.ds.entity.TreeNode;

/**
 * @author wanwan 2022/5/15
 */
public class TreeSolution {

	public static void main(String[] args) {
		TreeNode tree = new TreeNode(20, null, null);
		TreeNodeSolution.buildTree(tree, 21);
		TreeNodeSolution.buildTree(tree, 10);
		TreeNodeSolution.buildTree(tree, 36);
		TreeNodeSolution.buildTree(tree, 40);
		TreeNodeSolution.buildTree(tree, 18);
		System.out.println(JSONUtil.toJsonStr(tree));
		System.out.println(maxDepth(tree));
	}

	/**
	 * 计算二叉树的最大深度（非完全二叉树）
	 */
	public static int maxDepth (TreeNode root) {
		if (root == null) {
			return 0;
		}
		return Math.max(maxDepth(root.getLChild()), maxDepth(root.getRChild())) + 1;
	}


}
