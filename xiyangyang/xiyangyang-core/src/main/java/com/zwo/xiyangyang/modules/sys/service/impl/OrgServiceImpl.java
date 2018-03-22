/**
 * 
 */
package com.zwo.xiyangyang.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.sys.dao.OrgMapper;
import com.zwo.xiyangyang.modules.sys.domain.Org;
import com.zwo.xiyangyang.modules.sys.service.IOrgService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黄记新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class OrgServiceImpl extends BaseServiceImpl<Org> implements IOrgService {

	@Autowired
	private OrgMapper orgMapper;
	
	@Override
	public Mapper<Org> getBaseMapper() {
		return orgMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return OrgServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return Org.class;
	}

	@Override
	protected String getBaseMessage() {
		return "系统组织结构基础操作";
	}

}
