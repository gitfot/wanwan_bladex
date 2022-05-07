package com.fun.tool.utils.node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanwan 2022/4/14
 */
public class ForestNodeMerger {

	public static void main(String[] args) {
		List<MenuVO> buttons = new ArrayList<>();
		buttons.add(new MenuVO(1L, 0L));
		buttons.add(new MenuVO(2L, 1L));
		buttons.add(new MenuVO(3L, 1L));
		buttons.add(new MenuVO(4L, 3L));
		buttons.add(new MenuVO(5L, 3L));
		buttons.add(new MenuVO(6L, 0L));
		merge(buttons);
	}

	/**
	 * 根据id和parentId组装tree
	 */
	public static <T extends INode<T>> List<T> merge(List<T> items) {
		ForestNodeManager<T> forestNodeManager = new ForestNodeManager<>(items);
		//建立子父联系
		items.forEach((forestNode) -> {
			if (forestNode.getParentId() != 0L) {
				INode<T> node = forestNodeManager.getTreeNodeAt(forestNode.getParentId());
				if (node != null) {
					node.getChildren().add(forestNode);
				} else {
					//处理parentId不为 0，但没有父节点的漏网之鱼
					forestNodeManager.addParentId(forestNode.getId());
				}
			}
		});
		//拎出顶级节点
		return forestNodeManager.getRoot();
	}
}
