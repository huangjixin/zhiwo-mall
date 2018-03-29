package com.zwo.xiyangyang.modules.cms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.cms.dao.CmsCategoryMapper;
import com.zwo.xiyangyang.modules.cms.domain.CmsCategory;
import com.zwo.xiyangyang.modules.cms.service.ICmsCategoryService;
import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;

@Service
@Lazy(true)
@Transactional(readOnly = false)
public class CmsCategoryServiceImpl extends BaseServiceImpl<CmsCategory> implements ICmsCategoryService {
	private static Logger logger = LoggerFactory.getLogger(CmsCategoryServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}	
	@Autowired
	private CmsCategoryMapper cmsCategoryMapper;

	@Override
	public Mapper<CmsCategory> getBaseMapper() {
		return cmsCategoryMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return CmsCategoryServiceImpl.class;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return CmsCategory.class;
	}
	
	@Override
	protected String getBaseMessage() {
		return "CMS分類基础操作";
	}

}
