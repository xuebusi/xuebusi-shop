package com.xuebusi.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.portal.pojo.CartItem;

public interface CartService {

	XuebusiResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);
	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
	XuebusiResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response);
}
