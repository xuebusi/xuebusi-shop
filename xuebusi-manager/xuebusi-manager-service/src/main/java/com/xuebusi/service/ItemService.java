package com.xuebusi.service;

import com.xuebusi.common.pojo.EUDataGridResult;
import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(long itemId);
	EUDataGridResult getItemList(int page, int rows);
	XuebusiResult createItem(TbItem item, String desc, String itemParam) throws Exception;
}
