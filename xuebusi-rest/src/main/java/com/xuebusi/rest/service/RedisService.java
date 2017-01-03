package com.xuebusi.rest.service;

import com.xuebusi.common.pojo.XuebusiResult;

public interface RedisService {

	XuebusiResult syncContent(long contentCid);
}
