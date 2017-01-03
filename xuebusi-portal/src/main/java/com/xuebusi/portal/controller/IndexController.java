package com.xuebusi.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.portal.service.ContentService;

@Controller
public class IndexController {
	
	@Autowired
	private ContentService contentService;

	/**
	 * 前台首页跳转
	 * <p>Title: showIndex</p>
	 * <p>Description: </p>
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String showIndex(Model model) {
		//获取广告焦点图数据
		String adJson = contentService.getContentList();
		model.addAttribute("ad1", adJson);
		
		return "index";
	}
	
	@RequestMapping(value="/httpclient/post", method=RequestMethod.POST, 
			produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String testPost(String username, String password) {
		String result = "username:" + username + "\tpassword:" + password;
		System.out.println(result);
		return "username:" + username + ",password:" + password;
	}
}
