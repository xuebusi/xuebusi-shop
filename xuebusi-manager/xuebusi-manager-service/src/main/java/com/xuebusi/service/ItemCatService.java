package com.xuebusi.service;

import java.util.List;

import com.xuebusi.common.pojo.EUTreeNode;

public interface ItemCatService {

	List<EUTreeNode> getCatList(long parentId);
}
