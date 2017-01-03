package com.xuebusi.service;

import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.pojo.TbItemParam;

public interface ItemParamService {

	XuebusiResult getItemParamByCid(long cid);
	XuebusiResult insertItemParam(TbItemParam itemParam);
}
