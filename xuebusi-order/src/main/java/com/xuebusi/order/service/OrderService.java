package com.xuebusi.order.service;

import java.util.List;

import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.pojo.TbOrder;
import com.xuebusi.pojo.TbOrderItem;
import com.xuebusi.pojo.TbOrderShipping;

public interface OrderService {

	XuebusiResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
