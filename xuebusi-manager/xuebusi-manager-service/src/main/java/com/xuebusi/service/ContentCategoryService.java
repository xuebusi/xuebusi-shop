package com.xuebusi.service;

import java.util.List;

import com.xuebusi.common.pojo.EUTreeNode;
import com.xuebusi.common.pojo.XuebusiResult;

public interface ContentCategoryService {

	List<EUTreeNode> getCategoryList(long parentId);
	XuebusiResult insertContentCategory(long parentId, String name);
}
