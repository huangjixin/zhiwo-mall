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
import com.zwo.xiyangyang.modules.guess.service.impl.QuestionServiceImpl;
import com.zwo.xiyangyang.modules.sys.dao.LoginLogMapper;
import com.zwo.xiyangyang.modules.sys.domain.LoginLog;
import com.zwo.xiyangyang.modules.sys.service.ILoginLogService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黄记新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLog> implements ILoginLogService {
	private static Logger logger = LoggerFactory.getLogger(LoginLogServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Autowired
	private LoginLogMapper loginLogMapper;
	
	@Override
	public Mapper<LoginLog> getBaseMapper() {
		return loginLogMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return LoginLogServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return LoginLog.class;
	}

	@Override
	protected String getBaseMessage() {
		return "系统用户基础操作";
	}

}
