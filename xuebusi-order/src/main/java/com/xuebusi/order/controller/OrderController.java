package com.xuebusi.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.common.utils.ExceptionUtil;
import com.xuebusi.order.pojo.Order;
import com.xuebusi.order.service.OrderService;

/**
 * 订单
 * <p>Title: OrderController</p>
 * <p>Description: </p>
 * <p>Company: www.xuebusi.com</p> 
 * @author	学步思
 * @date	2017年1月2日下午4:25:35
 * @version 1.0
 */
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public XuebusiResult createOrder(@RequestBody Order order) {
		try {
			XuebusiResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return XuebusiResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}
