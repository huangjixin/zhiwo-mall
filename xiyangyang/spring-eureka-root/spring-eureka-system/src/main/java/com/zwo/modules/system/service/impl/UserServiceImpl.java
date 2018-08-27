/**
 * 
 */
package com.zwo.modules.system.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zwo.modules.core.service.impl.BaseServiceImpl;
import com.zwo.modules.system.domain.User;
import com.zwo.modules.system.mapper.UserMapper;
import com.zwo.modules.system.service.IUserService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黄记新
 *
 */
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static final String BASE_MESSAGE = "用户业务类UserServiceImpl增删改查";
	
	@Override
	protected Mapper<User> getBaseMapper() {
		return this.userMapper;
	}

	@Override
	protected String getBaseMessage() {
		return BASE_MESSAGE;
	}

	@Override
	public Class getTypeClass() {
		return User.class;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}


}
