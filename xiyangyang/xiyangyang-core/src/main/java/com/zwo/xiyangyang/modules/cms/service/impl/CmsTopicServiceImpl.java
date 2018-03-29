package com.zwo.xiyangyang.modules.cms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.cms.dao.CmsTopicMapper;
import com.zwo.xiyangyang.modules.cms.domain.CmsTopic;
import com.zwo.xiyangyang.modules.cms.service.ICmsTopicService;
import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;

@Service
@Lazy(true)
@Transactional(readOnly = false)
public class CmsTopicServiceImpl extends BaseServiceImpl<CmsTopic> implements ICmsTopicService {
	private static Logger logger = LoggerFactory.getLogger(CmsTopicServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Autowired
	private CmsTopicMapper topicMapper;

	@Override
	public Mapper<CmsTopic> getBaseMapper() {
		return topicMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return CmsTopicServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return CmsTopic.class;
	}
	
	@Override
	protected String getBaseMessage() {
		return "CMS专题基础操作";
	}

}
