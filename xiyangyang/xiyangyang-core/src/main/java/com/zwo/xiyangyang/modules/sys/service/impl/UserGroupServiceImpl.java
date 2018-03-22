/**
 * 
 */
package com.zwo.xiyangyang.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.sys.dao.UserGroupMapper;
import com.zwo.xiyangyang.modules.sys.domain.UserGroup;
import com.zwo.xiyangyang.modules.sys.service.IUserGroupService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黄记新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class UserGroupServiceImpl extends BaseServiceImpl<UserGroup> implements IUserGroupService {

	@Autowired
	private UserGroupMapper userGroupMapper;
	
	@Override
	public Mapper<UserGroup> getBaseMapper() {
		return userGroupMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return UserGroupServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return UserGroup.class;
	}

	@Override
	protected String getBaseMessage() {
		return "系统用户组基础操作";
	}

}
