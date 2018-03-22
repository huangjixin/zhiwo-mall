/**
 * 
 */
package com.zwo.xiyangyang.modules.pr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.pr.dao.PrPropertyValueMapper;
import com.zwo.xiyangyang.modules.pr.domain.PrPropertyValue;
import com.zwo.xiyangyang.modules.pr.service.IPropertyValueService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class PropertyValueServiceImpl extends BaseServiceImpl<PrPropertyValue> implements IPropertyValueService {

	@Autowired
	private PrPropertyValueMapper propertyValueMapper ;

	@Override
	public Mapper<PrPropertyValue> getBaseMapper() {
		return propertyValueMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return PropertyValueServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return PrPropertyValue.class;
	}

	@Override
	protected String getBaseMessage() {
		return "商品属性基础操作";
	}

}
