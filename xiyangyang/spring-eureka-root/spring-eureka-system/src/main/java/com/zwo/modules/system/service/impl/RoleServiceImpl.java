/**
 * 
 */
package com.zwo.modules.system.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.modules.core.mapper.BaseMapper;
import com.zwo.modules.core.service.impl.BaseServiceImpl;
import com.zwo.modules.system.domain.Role;
import com.zwo.modules.system.mapper.RoleMapper;
import com.zwo.modules.system.service.IRoleService;
import com.zwo.modules.system.utils.BPwdEncoderUtil;
import com.zwo.modules.system.vo.RoleVo;

/**
 * @author 黄记新
 *
 */
@Transactional
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService {

	@Autowired
	private RoleMapper RoleMapper;
	
	private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	
	private static final String BASE_MESSAGE = "用户角色类RoleServiceImpl增删改查";
	
	@Override
	protected BaseMapper<Role> getBaseMapper() {
		return this.RoleMapper;
	}

	@Override
	protected String getBaseMessage() {
		return BASE_MESSAGE;
	}

	@Override
	public Class getTypeClass() {
		return Role.class;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}


}
