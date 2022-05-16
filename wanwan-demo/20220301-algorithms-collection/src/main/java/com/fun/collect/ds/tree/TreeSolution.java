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
	 * 层序遍历反转打印（之字型）
	 */
	public ArrayList<ArrayList<Integer>> printReverse(TreeNode root) {
		if (root == null) {
			return null;
		}
		ArrayDeque<TreeNode> queue = new ArrayDeque<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode node = queue.pop();
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
		return null;
	}

	/**
	 * 层序遍历反转打印（之字型）
	 */
	public static ArrayList<ArrayList<Integer> > print(TreeNode pRoot) {
		LinkedList<TreeNode> q = new LinkedList<>();
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		boolean rev = true;
		q.add(pRoot);
		while(!q.isEmpty()){
			int size = q.size();
			ArrayList<Integer> list = new ArrayList<>();
			for(int i=0; i<size; i++){
				TreeNode node = q.poll();
				if(node == null){continue;}
				if(rev){
					list.add(node.data);
				}else{
					list.add(0, node.data);
				}
				q.offer(node.left);
				q.offer(node.right);
			}
			if(list.size()!=0){res.add(list);}
			rev=!rev;
		}
		return res;
	}

	public static void main(String[] args) {
		TreeNode tree = new TreeNode(20, null, null);
		TreeNodeSolution.buildTree(tree, 21);
		TreeNodeSolution.buildTree(tree, 10);
		TreeNodeSolution.buildTree(tree, 36);
		TreeNodeSolution.buildTree(tree, 40);
		TreeNodeSolution.buildTree(tree, 18);
		//System.out.println(JSONUtil.toJsonStr(tree));
		//System.out.println(maxDepth(tree));
		print(tree);

	}

}
