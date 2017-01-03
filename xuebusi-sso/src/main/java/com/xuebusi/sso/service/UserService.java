package com.xuebusi.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.pojo.TbUser;

public interface UserService {

	XuebusiResult checkData(String content, Integer type);
	XuebusiResult createUser(TbUser user);
	XuebusiResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
	XuebusiResult getUserByToken(String token);
}
