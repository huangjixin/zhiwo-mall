package com.zwo.xiyangyang.modules.cms.service.impl;

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
