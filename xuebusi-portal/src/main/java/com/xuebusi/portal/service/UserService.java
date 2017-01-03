package com.xuebusi.portal.service;

import com.xuebusi.pojo.TbUser;

public interface UserService {

	TbUser getUserByToken(String token);
}
