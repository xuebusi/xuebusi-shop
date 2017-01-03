package com.xuebusi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuebusi.common.pojo.EUDataGridResult;
import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.common.utils.HttpClientUtil;
import com.xuebusi.mapper.TbContentMapper;
import com.xuebusi.pojo.TbContent;
import com.xuebusi.pojo.TbContent;
import com.xuebusi.pojo.TbContentExample;
import com.xuebusi.pojo.TbContentExample.Criteria;
import com.xuebusi.service.ContentService;

/**
 * 内容管理
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.xuebusi.com</p> 
 * @author	学步思
 * @date	2017年1月2日下午4:21:32
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper  contentMapper;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;

	@Override
	public EUDataGridResult getContentList(int page, int rows) {
		//查询商品列表
		TbContentExample example = new TbContentExample();
		//分页处理
		PageHelper.startPage(page, rows);
		List<TbContent> list = contentMapper.selectByExample(example);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	
	@Override
	public XuebusiResult insertContent(TbContent content) {
		//补全pojo内容
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		
		//添加缓存同步逻辑
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return XuebusiResult.ok();
	}

}
