package com.xuebusi.rest.service;

import java.util.List;

import com.xuebusi.pojo.TbContent;

public interface ContentService {

	List<TbContent> getContentList(long contentCid);
}
