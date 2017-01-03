package com.xuebusi.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.common.utils.ExceptionUtil;
import com.xuebusi.pojo.TbContent;
import com.xuebusi.rest.service.ContentService;

/**
 * 内容管理Controller
 * <p>
 * Title: ContentController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.xuebusi.com
 * </p>
 * 
 * @author 学步思
 * @date 2017年1月2日下午4:31:41
 * @version 1.0
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/list/{contentCategoryId}")
	@ResponseBody
	public XuebusiResult getContentList(@PathVariable Long contentCategoryId) {
		try {
			List<TbContent> list = contentService.getContentList(contentCategoryId);
			return XuebusiResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return XuebusiResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}