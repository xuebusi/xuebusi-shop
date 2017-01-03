package com.xuebusi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.rest.service.ItemService;

/**
 * 商品信息Controller
 * <p>Title: ItemController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	入云龙
 * @date	2015年9月12日上午10:22:11
 * @version 1.0
 */
@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * 测试地址：http://localhost:8081/rest/item/info/536563
	 * <p>Title: getItemBaseInfo</p>
	 * <p>Description: </p>
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public XuebusiResult getItemBaseInfo(@PathVariable Long itemId) {
		XuebusiResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public XuebusiResult getItemDesc(@PathVariable Long itemId) {
		XuebusiResult result = itemService.getItemDesc(itemId);
		return result;
	}
	
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public XuebusiResult getItemParam(@PathVariable Long itemId) {
		XuebusiResult result = itemService.getItemParam(itemId);
		return result;
	}
}
