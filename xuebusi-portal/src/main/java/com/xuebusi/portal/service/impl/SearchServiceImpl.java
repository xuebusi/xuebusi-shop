package com.xuebusi.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.common.utils.HttpClientUtil;
import com.xuebusi.portal.pojo.SearchResult;
import com.xuebusi.portal.service.SearchService;

/**
 * 商品搜索Service
 * <p>Title: SearchServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.xuebusi.com</p> 
 * @author	学步思
 * @date	2017年1月2日下午4:30:23
 * @version 1.0
 */

@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	@Override
	public SearchResult search(String queryString, int page) {
		// 调用xuebusi-search的服务
		//查询参数
		Map<String, String> param = new HashMap<>();
		param.put("q", queryString);
		param.put("page", page + "");
		try {
			//调用服务
			String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
			//把字符串转换成java对象
			XuebusiResult xuebusiResult = XuebusiResult.formatToPojo(json, SearchResult.class);
			if (xuebusiResult.getStatus() == 200) {
				SearchResult result = (SearchResult) xuebusiResult.getData();
				return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
