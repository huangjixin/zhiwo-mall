package com.zwo.xiyangyang.modules.guess.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.guess.dao.GuessOptionsCombineMapper;
import com.zwo.xiyangyang.modules.guess.domain.GuessOptions;
import com.zwo.xiyangyang.modules.guess.domain.GuessOptionsCombine;
import com.zwo.xiyangyang.modules.guess.service.IOptionsCombineService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class OptionsCombineServiceImpl extends BaseServiceImpl<GuessOptionsCombine> implements IOptionsCombineService {
	private static Logger logger = LoggerFactory.getLogger(OptionsCombineServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Autowired
	private GuessOptionsCombineMapper optionsMapper;

	@Override
	public Mapper<GuessOptionsCombine> getBaseMapper() {
		return optionsMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return OptionsCombineServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return GuessOptions.class;
	}

	@Override
	protected String getBaseMessage() {
		return "竞猜问题联合选项基础操作";
	}


}
