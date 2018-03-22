/**
 * 
 */
package com.zwo.xiyangyang.modules.pr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.pr.dao.PrPropertyMapper;
import com.zwo.xiyangyang.modules.pr.domain.PrProperty;
import com.zwo.xiyangyang.modules.pr.service.IPropertyService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class PropertyServiceImpl extends BaseServiceImpl<PrProperty> implements IPropertyService {

	@Autowired
	private PrPropertyMapper propertyMapper ;

	@Override
	public Mapper<PrProperty> getBaseMapper() {
		return propertyMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return PropertyServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return PrProperty.class;
	}

	@Override
	protected String getBaseMessage() {
		return "商品属性基础操作";
	}

}
