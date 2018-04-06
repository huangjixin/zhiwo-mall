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
import com.zwo.xiyangyang.modules.guess.dao.GuessAccountHisMapper;
import com.zwo.xiyangyang.modules.guess.domain.GuessAccountHis;
import com.zwo.xiyangyang.modules.guess.service.IGuessAccountHisService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class GuessAccountHisServiceImpl extends BaseServiceImpl<GuessAccountHis> implements IGuessAccountHisService {
	private static Logger logger = LoggerFactory.getLogger(GuessAccountHisServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Autowired
	private GuessAccountHisMapper accountHisMapper ;

	@Override
	public Mapper<GuessAccountHis> getBaseMapper() {
		return accountHisMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return GuessAccountHisServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return GuessAccountHis.class;
	}
	@Override
	protected String getBaseMessage() {
		return "竞猜账号历史基础操作";
	}

}
