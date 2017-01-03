package com.xuebusi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuebusi.common.pojo.EUDataGridResult;
import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.pojo.TbContent;
import com.xuebusi.service.ContentService;

/**
 * 内容管理
 * <p>Title: ContentController</p>
 * <p>Description: </p>
 * <p>Company: www.xuebusi.com</p> 
 * @author	学步思
 * @date	2017年1月2日下午4:24:47
 * @version 1.0
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getContentList(Integer page, Integer rows) {
		EUDataGridResult result = contentService.getContentList(page, rows);
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public XuebusiResult insertContent(TbContent content) {
		XuebusiResult result = contentService.insertContent(content);
		return result;
	}
}
