package com.zwo.xiyangyang.modules.cms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.cms.dao.CmsAssetsMapper;
import com.zwo.xiyangyang.modules.cms.domain.CmsAssets;
import com.zwo.xiyangyang.modules.cms.service.ICmsAssetsService;
import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;

@Service
@Lazy(true)
@Transactional(readOnly = false)
public class CmsAssetsServiceImpl extends BaseServiceImpl<CmsAssets> implements ICmsAssetsService {
	private static Logger logger = LoggerFactory.getLogger(CmsAssetsServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}	
	@Autowired
	private CmsAssetsMapper assetsMapper;

	@Override
	public Mapper<CmsAssets> getBaseMapper() {
		return assetsMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return CmsAssetsServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return CmsAssets.class;
	}
	
	@Override
	protected String getBaseMessage() {
		return "竞猜下注表基础操作";
	}

}
