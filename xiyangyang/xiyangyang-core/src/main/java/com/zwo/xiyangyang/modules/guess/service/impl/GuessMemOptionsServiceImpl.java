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
import com.zwo.xiyangyang.modules.guess.dao.GuessMemOptionsMapper;
import com.zwo.xiyangyang.modules.guess.domain.GuessMemOptions;
import com.zwo.xiyangyang.modules.guess.service.IGuessMemOptionsService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class GuessMemOptionsServiceImpl extends BaseServiceImpl<GuessMemOptions> implements IGuessMemOptionsService {
	private static Logger logger = LoggerFactory.getLogger(GuessMemOptionsServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Autowired
	private GuessMemOptionsMapper guessMemOptionsMapper ;

	@Override
	public Mapper<GuessMemOptions> getBaseMapper() {
		return guessMemOptionsMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return GuessMemOptionsServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return GuessMemOptions.class;
	}

	@Override
	protected String getBaseMessage() {
		return "竞猜下注表基础操作";
	}

}
