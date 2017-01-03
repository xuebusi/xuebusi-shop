package com.xuebusi.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xuebusi.common.utils.JsonUtils;
import com.xuebusi.mapper.TbContentMapper;
import com.xuebusi.pojo.TbContent;
import com.xuebusi.pojo.TbContentExample;
import com.xuebusi.pojo.TbContentExample.Criteria;
import com.xuebusi.rest.dao.JedisClient;
import com.xuebusi.rest.service.ContentService;

import sun.tools.jar.resources.jar;

/**
 * 内容管理
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.xuebusi.com</p> 
 * @author	学步思
 * @date	2017年1月2日下午4:33:11
 * @version 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;//要操作redis，首先要注入JedisClient接口
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	
	
	@Override
	public List<TbContent> getContentList(long contentCid) {
		//从缓存中取内容
		//注意，一定要有try-catch，这里即使出现异常，也不能影响正常业务逻辑的执行
		//要获取数据，首先要存数据，存数据的逻辑在下面
		try {
			//INDEX_CONTENT_REDIS_KEY：是首页内容信息在redis中保存的key
			String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid + "");
			if (!StringUtils.isBlank(result)) {
				//把字符串转换成list
				List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
				return resultList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//根据内容分类id查询内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		
		//向缓存中添加内容
		//即使这里出现异常，也不能影响正常的逻辑，所以这里也要有try-catch
		try {
			//把list转换成字符串(因为redis中存的都是字符串)
			//这里使用我们封装的JsonUtils工具类将对象转换成字符串
			String cacheString = JsonUtils.objectToJson(list);
			
			//由于将来我们的redis中可能要存很多的key，所以我们要存入的数据进行分类
			//而Hash结构中每一个主key，都对应很多的key
			//第一个参数key是一个hkey，我们需要自己定义好，它是一个常量
			//第二个参数key就不能使常量了，我们需要根据这个key来查询数据
			//INDEX_CONTENT_REDIS_KEY：是首页内容信息在redis中保存的key
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid + "", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
