/**
 * 
 */
package com.zwo.modules.system.service.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.modules.core.mapper.BaseMapper;
import com.zwo.modules.core.service.impl.BaseServiceImpl;
import com.zwo.modules.system.domain.User;
import com.zwo.modules.system.mapper.UserMapper;
import com.zwo.modules.system.service.IUserService;

/**
 * @author 黄记新
 *
 */
@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static final String BASE_MESSAGE = "用户业务类UserServiceImpl增删改查";
	
	@Override
	protected BaseMapper<User> getBaseMapper() {
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

	@Override
	public Set<String> findRoles(String username) {
		if (getLogger().isInfoEnabled()) {
			getLogger().info(getBaseMessage() + "删除开始，参数id的值是：" + id);
		}

		int result = this.userMapper.;
		if (getLogger().isInfoEnabled())
			getLogger().info(getBaseMessage() + "删除" + (result == 1 ? "成功" : "失败"));

		return result;
	}

	@Override
	public Set<String> findPermissions(String username) {
		
		return null;
	}

	@Override
	public User findByUsername(String username) {
		
		return null;
	}


}
