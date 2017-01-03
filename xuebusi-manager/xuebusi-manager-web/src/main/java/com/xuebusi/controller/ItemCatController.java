package com.xuebusi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuebusi.common.pojo.EUTreeNode;
import com.xuebusi.service.ItemCatService;

/**
 * 商品分类管理
 * <p>Title: ItemCatController</p>
 * <p>Description: </p>
 * <p>Company: www.xuebusi.com</p> 
 * @author	学步思
 * @date	2017年1月2日下午4:24:38
 * @version 1.0
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	private List<EUTreeNode> getCatList(@RequestParam(value="id",defaultValue="0")Long parentId) {
		List<EUTreeNode> list = itemCatService.getCatList(parentId);
		return list;
	}
}
