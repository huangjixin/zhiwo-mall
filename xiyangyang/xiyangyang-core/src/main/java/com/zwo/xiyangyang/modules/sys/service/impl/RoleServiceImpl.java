/**
 * 
 */
package com.zwo.xiyangyang.modules.sys.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.sys.dao.RoleMapper;
import com.zwo.xiyangyang.modules.sys.domain.Role;
import com.zwo.xiyangyang.modules.sys.service.IRoleService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黄记新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {
	private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public Mapper<Role> getBaseMapper() {
		return roleMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return RoleServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return Role.class;
	}

	@Override
	protected String getBaseMessage() {
		return "系统角色基础操作";
	}

}
