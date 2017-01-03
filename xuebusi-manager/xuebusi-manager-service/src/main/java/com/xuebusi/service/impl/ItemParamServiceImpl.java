package com.xuebusi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuebusi.common.pojo.XuebusiResult;
import com.xuebusi.mapper.TbItemParamMapper;
import com.xuebusi.pojo.TbItemParam;
import com.xuebusi.pojo.TbItemParamExample;
import com.xuebusi.pojo.TbItemParamExample.Criteria;
import com.xuebusi.service.ItemParamService;

/**
 * 商品规格参数模板管理
 * <p>Title: ItemParamServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.xuebusi.com</p> 
 * @author	学步思
 * @date	2017年1月2日下午4:22:17
 * @version 1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Override
	public XuebusiResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
		if (list != null && list.size() > 0) {
			return XuebusiResult.ok(list.get(0));
		}
		
		return XuebusiResult.ok();
	}

	@Override
	public XuebusiResult insertItemParam(TbItemParam itemParam) {
		//补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return XuebusiResult.ok();
	}

}
