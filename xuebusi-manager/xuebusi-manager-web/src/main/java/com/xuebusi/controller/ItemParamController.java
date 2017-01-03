package com.xuebusi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.pojo.TbItemParam;
import com.xuebusi.service.ItemParamService;

/**
 * 商品规格参数模板管理
 * <p>Title: ItemParamController</p>
 * <p>Description: </p>
 * <p>Company: www.xuebusi.com</p> 
 * @author	学步思
 * @date	2017年1月2日下午4:24:11
 * @version 1.0
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public XuebusiResult getItemParamByCid(@PathVariable Long itemCatId) {
		XuebusiResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public XuebusiResult insertItemParam(@PathVariable Long cid, String paramData) {
		//创建pojo对象
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		XuebusiResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}
}
