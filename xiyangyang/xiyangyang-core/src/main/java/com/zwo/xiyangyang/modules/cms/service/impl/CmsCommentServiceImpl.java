package com.zwo.xiyangyang.modules.cms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.cms.dao.CmsCommentMapper;
import com.zwo.xiyangyang.modules.cms.domain.CmsComment;
import com.zwo.xiyangyang.modules.cms.service.ICmsCommentService;
import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;

@Service
@Lazy(true)
@Transactional(readOnly = false)
public class CmsCommentServiceImpl extends BaseServiceImpl<CmsComment> implements ICmsCommentService {
	private static Logger logger = LoggerFactory.getLogger(CmsCommentServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Autowired
	private CmsCommentMapper commentMapper;

	@Override
	public Mapper<CmsComment> getBaseMapper() {
		return commentMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return CmsCommentServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return CmsComment.class;
	}
	@Override
	protected String getBaseMessage() {
		return "CMS评论基础操作";
	}

}
