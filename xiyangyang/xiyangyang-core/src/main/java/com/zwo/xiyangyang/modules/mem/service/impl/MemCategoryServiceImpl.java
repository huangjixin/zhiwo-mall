/**
 * 
 */
package com.zwo.xiyangyang.modules.mem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.mem.dao.MemCategoryMapper;
import com.zwo.xiyangyang.modules.mem.domain.MemCategory;
import com.zwo.xiyangyang.modules.mem.service.IMemCategoryService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class MemCategoryServiceImpl extends BaseServiceImpl<MemCategory> implements IMemCategoryService {

	@Autowired
	private MemCategoryMapper categoryMapper ;

	@Override
	public Mapper<MemCategory> getBaseMapper() {
		return categoryMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return MemCategoryServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return MemCategory.class;
	}

	@Override
	protected String getBaseMessage() {
		return "会员分类基础操作";
	}

}
