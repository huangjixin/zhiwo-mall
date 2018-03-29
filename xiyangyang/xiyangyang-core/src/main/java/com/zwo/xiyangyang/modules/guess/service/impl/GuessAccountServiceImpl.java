/**
 * 
 */
package com.zwo.xiyangyang.modules.guess.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.guess.dao.GuessAccountMapper;
import com.zwo.xiyangyang.modules.guess.domain.GuessAccount;
import com.zwo.xiyangyang.modules.guess.service.IGuessAccountService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class GuessAccountServiceImpl extends BaseServiceImpl<GuessAccount> implements IGuessAccountService {
	private static Logger logger = LoggerFactory.getLogger(GuessAccountServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Autowired
	private GuessAccountMapper accountMapper ;

	@Override
	public Mapper<GuessAccount> getBaseMapper() {
		return accountMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return GuessAccountServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return GuessAccount.class;
	}
	@Override
	protected String getBaseMessage() {
		return "竞猜账号基础操作";
	}

}
