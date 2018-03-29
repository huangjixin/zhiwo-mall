/**
 * 
 */
package com.zwo.xiyangyang.modules.guess.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.guess.dao.GuessQuestionApiMapper;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestionApi;
import com.zwo.xiyangyang.modules.guess.service.IGuessQuestionApiService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class GuessQuestionApiServiceImpl extends BaseServiceImpl<GuessQuestionApi> implements IGuessQuestionApiService {
	private static Logger logger = LoggerFactory.getLogger(GuessQuestionApiServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Autowired
	private GuessQuestionApiMapper apiMapper ;

	@Override
	public Mapper<GuessQuestionApi> getBaseMapper() {
		return apiMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return GuessQuestionApiServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return GuessQuestionApi.class;
	}

	@Override
	protected String getBaseMessage() {
		return "竞猜API表基础操作";
	}

	@Override
	public List<GuessQuestionApi> selectByType(String type) {
		Example example = new Example(GuessQuestionApi.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", type);
		return apiMapper.selectByExample(example);
	}

}
