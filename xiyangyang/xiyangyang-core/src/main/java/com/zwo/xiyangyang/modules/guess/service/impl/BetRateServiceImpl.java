package com.zwo.xiyangyang.modules.guess.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.guess.dao.GuessBetRateMapper;
import com.zwo.xiyangyang.modules.guess.domain.GuessBetRate;
import com.zwo.xiyangyang.modules.guess.domain.GuessOptions;
import com.zwo.xiyangyang.modules.guess.service.IBetRateService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class BetRateServiceImpl extends BaseServiceImpl<GuessBetRate> implements IBetRateService {
	private static Logger logger = LoggerFactory.getLogger(BetRateServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Autowired
	private GuessBetRateMapper betRateMapper;

	@Override
	public Mapper<GuessBetRate> getBaseMapper() {
		return betRateMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return BetRateServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return GuessOptions.class;
	}

	@Override
	protected String getBaseMessage() {
		return "竞猜赔率基础操作";
	}


}
