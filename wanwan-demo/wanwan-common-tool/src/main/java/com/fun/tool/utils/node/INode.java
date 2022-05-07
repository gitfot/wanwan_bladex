package com.fun.tool.utils.node;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanwan 2022/4/14
 */
public interface INode<T> extends Serializable {
	Long getId();

	Long getParentId();

	List<T> getChildren();

	default Boolean getHasChildren() {
		return false;
	}
}
