package com.xuebusi.service;

import com.xuebusi.common.pojo.EUDataGridResult;
import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.pojo.TbContent;

public interface ContentService {

	EUDataGridResult getContentList(int page, int rows);
	XuebusiResult insertContent(TbContent content);
}
