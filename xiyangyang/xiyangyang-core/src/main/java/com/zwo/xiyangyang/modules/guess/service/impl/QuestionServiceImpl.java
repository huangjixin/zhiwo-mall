/**
 * 
 */
package com.zwo.xiyangyang.modules.guess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.guess.dao.GuessQuestionMapper;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestion;
import com.zwo.xiyangyang.modules.guess.service.IQuestionService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class QuestionServiceImpl extends BaseServiceImpl<GuessQuestion> implements IQuestionService {

	@Autowired
	private GuessQuestionMapper questionMapper;

	@Override
	public Mapper<GuessQuestion> getBaseMapper() {
		return questionMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return QuestionServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return GuessQuestion.class;
	}

	@Override
	protected String getBaseMessage() {
		return "竞猜问题基础操作";
	}

	@Override
	public GuessQuestion selectByName(String name) {
		Example example = new Example(GuessQuestion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", name);
		List<GuessQuestion> list = questionMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

}
