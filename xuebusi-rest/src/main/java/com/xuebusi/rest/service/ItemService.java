package com.xuebusi.rest.service;

import com.xuebusi.common.pojo.XuebusiResult;

public interface ItemService {

	XuebusiResult getItemBaseInfo(long itemId);
	XuebusiResult getItemDesc(long itemId);
	XuebusiResult getItemParam(long itemId);
}
