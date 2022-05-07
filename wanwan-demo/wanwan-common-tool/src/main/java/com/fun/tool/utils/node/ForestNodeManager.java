package com.fun.tool.utils.node;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wanwan 2022/4/14
 */
public class ForestNodeManager<T extends INode<T>> {
	private final ImmutableMap<Long, T> nodeMap;
	private final Map<Long, Object> parentIdMap = Maps.newHashMap();

	public ForestNodeManager(List<T> nodes) {
		this.nodeMap = Maps.uniqueIndex(nodes, INode::getId);
	}

	public INode<T> getTreeNodeAt(Long id) {
		return this.nodeMap.containsKey(id) ? (INode)this.nodeMap.get(id) : null;
	}

	public void addParentId(Long parentId) {
		this.parentIdMap.put(parentId, "");
	}

	public List<T> getRoot() {
		List<T> roots = new ArrayList();
		this.nodeMap.forEach((key, node) -> {
			if (node.getParentId() == 0L || this.parentIdMap.containsKey(node.getId())) {
				roots.add(node);
			}
		});
		return roots;
	}
}
