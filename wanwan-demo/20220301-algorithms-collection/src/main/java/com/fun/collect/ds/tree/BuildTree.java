package com.fun.collect.ds.tree;

import com.fun.collect.ds.entity.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wanwan 2022/3/1
 */
public class BuildTree {
	Map<String,String> node1 = new HashMap();
	Map<String,String> node2 = new HashMap();
	Map<String,String> node3 = new HashMap();
	Map<String,String> node4 = new HashMap();
	Map<String,String> node5 = new HashMap();
	static List<Map<String,String>> val = new ArrayList();

	public void put(){
		node1.put("id","1");
		node1.put("parentId","0");
		node1.put("name","AAA");

		node2.put("id","2");
		node2.put("parentId","1");
		node2.put("name","node2");

		node3.put("id","4");
		node3.put("parentId","1");
		node3.put("name","node3");

		node4.put("id","3");
		node4.put("parentId","2");
		node4.put("name","node4");

		node5.put("id","4");
		node5.put("parentId","2");
		node5.put("name","node5");
		val.add(node1);
		val.add(node2);
		val.add(node3);
		val.add(node4);
		val.add(node5);
	}

	//1.从root根开始往下递归，val是初始数据，rid是每一层递归根的pid，第一次传入是整个树的根pid
	public List<Tree> build(List<Map<String,String>> val, String rId){
//        2.创建trl作为每一层递归得到的树，也就是上一层的一个child，返回值需要set到上一层的child里面
		List<Tree> trl = new ArrayList();
		for(int i=0;i<val.size();i++){
//          3.  判断是为了找到当前传入这一级的child，如果能进入证明val.get(i)这个值是rid这个的child
			if(val.get(i).get("parentId").equals(rId)) {
//                4. 如果能进来，则证明有根，把val.get(i)，作为根，递归下一层来寻找 val.get(i)的child
				List<Tree> child = build(val, val.get(i).get("id"));
				if(child.size()>0){
					Tree tree = new Tree();
					tree.setName(val.get(i).get("name"));
					tree.setChildren(child);
					trl.add(tree);
				}else{
					Tree tree = new Tree();
					tree.setName(val.get(i).get("name"));
					trl.add(tree);
				}
			}
		}
		return trl;
	}

	//测试
	public static void main(String[] args) {
		BuildTree b = new BuildTree();
		b.put();
		List<Tree> tr = b.build(val, "0");
		System.out.println(tr);
	}
}
