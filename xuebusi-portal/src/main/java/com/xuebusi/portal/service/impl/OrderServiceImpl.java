package com.xuebusi.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.common.utils.HttpClientUtil;
import com.xuebusi.common.utils.JsonUtils;
import com.xuebusi.portal.pojo.Order;
import com.xuebusi.portal.service.OrderService;

/**
 * 订单处理Service
 * <p>Title: OrderServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.xuebusi.com</p> 
 * @author	学步思
 * @date	2017年1月2日下午4:30:14
 * @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	

	@Override
	public String createOrder(Order order) {
		//调用创建订单服务之前补全用户信息。
		//从cookie中后取TT_TOKEN的内容，根据token调用sso系统的服务根据token换取用户信息。
		
		//调用xuebusi-order的服务提交订单。
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		//把json转换成xuebusiResult
		XuebusiResult xuebusiResult = XuebusiResult.format(json);
		if (xuebusiResult.getStatus() == 200) {
			Object orderId = xuebusiResult.getData();
			return orderId.toString();
		}
		return "";
	}

}