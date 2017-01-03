package com.xuebusi.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.common.utils.HttpClientUtil;
import com.xuebusi.common.utils.JsonUtils;
import com.xuebusi.pojo.TbContent;
import com.xuebusi.portal.service.ContentService;

/**
 * 调用服务层服务，查询内容列表
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.xuebusi.com</p> 
 * @author	学步思
 * @date	2017年1月2日下午4:29:56
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;
	
	@Override
	public String getContentList() {
		//调用服务层的服务
		//REST_BASE_URL=http://hadoop02:8080/rest
		//REST_INDEX_AD_URL=/content/list/89
		//{"status":200,"msg":"OK","data":[{"id":39,"categoryId":89,"title":"测试广告焦点图标题","subTitle":"测试广告焦点图子标题","titleDesc":"测试广告焦点图内容描述","url":"","pic":"http://192.168.71.11:8088/images/2016/12/21/1482315880465748.jpg","pic2":"","created":1482373817000,"updated":1482373817000,"content":null},{"id":44,"categoryId":89,"title":"鬼地方个梵蒂冈","subTitle":"个梵蒂冈","titleDesc":"滚动","url":"","pic":"http://192.168.71.11:8088/images/2016/12/21/1482321879828360.jpg","pic2":"","created":1482379491000,"updated":1482379491000,"content":null},{"id":45,"categoryId":89,"title":"的个梵蒂冈","subTitle":"滚动个","titleDesc":"放到滚动","url":"","pic":"http://192.168.71.11:8088/images/2016/12/21/1482321986850136.jpg","pic2":"","created":1482379597000,"updated":1482379597000,"content":null},{"id":46,"categoryId":89,"title":"第三方","subTitle":"发给是","titleDesc":"发给放到","url":"","pic":"http://192.168.71.11:8088/images/2016/12/21/1482322027636359.jpg","pic2":"","created":1482379637000,"updated":1482379637000,"content":null},{"id":47,"categoryId":89,"title":"他跟他","subTitle":"特","titleDesc":"try","url":"","pic":"http://192.168.71.11:8088/images/2016/12/21/1482322084631426.jpg","pic2":"","created":1482379694000,"updated":1482379694000,"content":null},{"id":48,"categoryId":89,"title":"水电费","subTitle":"第三方","titleDesc":"fds个","url":"","pic":"http://192.168.71.11:8088/images/2016/12/21/1482322132572655.jpg","pic2":"","created":1482379742000,"updated":1482379742000,"content":null},{"id":49,"categoryId":89,"title":"gffg","subTitle":"fff","titleDesc":"fff","url":"","pic":"http://192.168.71.11:8088/images/2016/12/27/1482859962251237.jpg","pic2":"","created":1482859967000,"updated":1482859967000,"content":null}]}
		//从redis缓存中获取前台首页的焦点图数据
		String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);
		//把字符串转换成XuebusiResult
		try {
			XuebusiResult xuebusiResult = XuebusiResult.formatToList(result, TbContent.class);
			//取内容列表
			List<TbContent> list = (List<TbContent>) xuebusiResult.getData();
			List<Map> resultList = new ArrayList<>();
 			//创建一个jsp页面要求的pojo列表
			for (TbContent tbContent : list) {
				Map map = new HashMap<>();
				map.put("src", tbContent.getPic());
				map.put("height", 240);
				map.put("width", 670);
				map.put("srcB", tbContent.getPic2());
				map.put("widthB", 550);
				map.put("heightB", 240);
				map.put("href", tbContent.getUrl());
				map.put("alt", tbContent.getSubTitle());
				resultList.add(map);
			}
			return JsonUtils.objectToJson(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
