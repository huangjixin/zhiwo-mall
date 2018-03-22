package com.zwo.xiyangyang.modules.pr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.pr.dao.PrValuePriceMapper;
import com.zwo.xiyangyang.modules.pr.domain.PrValuePrice;
import com.zwo.xiyangyang.modules.pr.service.IValuePriceService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黃記新
 * 
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class ValuePriceServiceImpl extends BaseServiceImpl<PrValuePrice> implements IValuePriceService {

	@Autowired
	private PrValuePriceMapper propertyValueMapper ;

	@Override
	public Mapper<PrValuePrice> getBaseMapper() {
		return propertyValueMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return ValuePriceServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return PrValuePrice.class;
	}

	@Override
	protected String getBaseMessage() {
		return "商品属性值价格基础操作";
	}

}
