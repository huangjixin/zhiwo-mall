package com.zwo.xiyangyang.modules.guess.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.guess.dao.GuessOptionsMapper;
import com.zwo.xiyangyang.modules.guess.domain.GuessOptions;
import com.zwo.xiyangyang.modules.guess.service.IOptionsService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class OptionsServiceImpl extends BaseServiceImpl<GuessOptions> implements IOptionsService {

	@Autowired
	private GuessOptionsMapper optionsMapper ;

	@Override
	public Mapper<GuessOptions> getBaseMapper() {
		return optionsMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return OptionsServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return GuessOptions.class;
	}

	@Override
	protected String getBaseMessage() {
		return "竞猜问题选项基础操作";
	}

	@Override
	public int checkOption(GuessOptions guessOptions) {
		Example example = new Example(GuessOptions.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("guessQuestionId", guessOptions.getGuessQuestionId());
        criteria.andEqualTo("name", guessOptions.getName());
        int result = optionsMapper.updateByExampleSelective(guessOptions, example);
		return result;
	}

}
