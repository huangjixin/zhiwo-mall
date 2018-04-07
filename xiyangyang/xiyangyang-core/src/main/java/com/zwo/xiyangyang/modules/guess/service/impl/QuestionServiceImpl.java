/**
 * 
 */
package com.zwo.xiyangyang.modules.guess.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.guess.dao.GuessOptionsMapper;
import com.zwo.xiyangyang.modules.guess.dao.GuessQuestionMapper;
import com.zwo.xiyangyang.modules.guess.domain.GuessOptions;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestion;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestionCriteria;
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
	private static Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	
	@Autowired
	private GuessQuestionMapper questionMapper;
	
	@Autowired
	private GuessOptionsMapper guessOptionsMapper;

	@Override
	public Mapper<GuessQuestion> getBaseMapper() {
		return null;
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
	public int deteleBatch(List<GuessQuestion> list) {
		List<String> ids = new ArrayList<String>();
		for (GuessQuestion question : list) {
			ids.add(question.getId());
		}
		GuessQuestionCriteria guessQuestionCriteria = new GuessQuestionCriteria();
		GuessQuestionCriteria.Criteria criteria= guessQuestionCriteria.createCriteria();
		criteria.andIdIn(ids);
		/*Example example = new Example(getTypeClass());
		Example.Criteria criteria = example.createCriteria();
		criteria.andIn("id", list);*/
		return questionMapper.deleteByExample(guessQuestionCriteria);
	}
	
	@Override
	public int insertBatch(List<GuessQuestion> list) {
		return 0;
	}
	
	@Override
	@Transactional(readOnly = true)
	public int countByExample(Object example) {
		return questionMapper.countByExample((GuessQuestionCriteria) example);
	}

	@Override
	public int deleteByExample(Object example) {
		if (logger.isInfoEnabled()) {
			logger.info(getBaseMessage() + "删除开始");
		}

		int result = this.deleteByExample(example);
		if (logger.isInfoEnabled())
			logger.info(getBaseMessage() + "删除" + (result != 0 ? "成功" : "失败"));

		return result;
	}

	@Override
	public int deleteById(String id) {
		if (logger.isInfoEnabled()) {
			logger.info(getBaseMessage() + "删除开始，参数id的值是：" + id);
		}

		int result = questionMapper.deleteByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(getBaseMessage() + "插入" + (result == 1 ? "成功" : "失败"));

		return result;
	}

	@Override
	public int insert(GuessQuestion record) {
		// 利用类反射判断id属性有没有值，没有值就给赋值。
		Object o = (Object) record;
		Method method;
		try {
			method = o.getClass().getDeclaredMethod("getId");
			String id = (String) method.invoke(o);
			if (id == null) {
				method = o.getClass().getDeclaredMethod("setId", String.class);
				id = UUID.randomUUID().toString().replaceAll("-", "");
				method.invoke(o, id);
			}
			
			Method[]methods = o.getClass().getMethods();
			for (Method method2 : methods) {
				if(method2.getName().equals("getParentId")) {
					String parentId = (String) method.invoke(o);
					if("".equals(parentId)) {
						method2 = o.getClass().getDeclaredMethod("setParentId", String.class);
						method2.invoke(o, null);
					}
					break;
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (logger.isInfoEnabled()) {
			logger.info(getBaseMessage() + "插入开始");
		}

		if (getLogger().isInfoEnabled()) {
			try {
				String jsonStr = null;
				if (record != null) {
					Gson gson = new Gson();
					jsonStr = gson.toJson((Object) record);
				}

				getLogger().info(getBaseMessage() + "插入开始：" + jsonStr);
			} catch (Exception e) {
				getLogger().info("系统打印参数序列化的时候发生了异常，该异常不会影响数据库操作");
			}
			
		}
		int result = questionMapper.insert(record);
		
		if (logger.isInfoEnabled())
			logger.info(getBaseMessage() + "插入" + (result == 1 ? "成功" : "失败"));
		return result;
	}

	@Override
	public int insertSelective(GuessQuestion record) {
		// 利用类反射判断id属性有没有值，没有值就给赋值。
		Object o = (Object) record;
		Method method;
		try {
			method = o.getClass().getDeclaredMethod("getId");
			String id = (String) method.invoke(o);
			if (id == null) {
				method = o.getClass().getDeclaredMethod("setId", String.class);
				id = UUID.randomUUID().toString().replaceAll("-", "");
				method.invoke(o, id);
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		if (getLogger().isInfoEnabled()) {
			try {
				String jsonStr = null;
				if (record != null) {
					Gson gson = new Gson();
					jsonStr = gson.toJson((Object) record);
				}

				getLogger().info(getBaseMessage() + "插入开始：" + jsonStr);
			} catch (Exception e) {
				getLogger().info("系统打印参数序列化的时候发生了异常，该异常不会影响数据库操作");
			}
			
		}
		int result = questionMapper.insertSelective(record);
		if (logger.isInfoEnabled())
			logger.info(getBaseMessage() + "插入" + (result == 1 ? "成功" : "失败"));
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public GuessQuestion selectByPrimaryKey(String id) {
		if (logger.isInfoEnabled()) {
			logger.info(getBaseMessage() + "查询单条记录开始，参数id的值是：" + id);
		}

		GuessQuestion result = questionMapper.selectByPrimaryKey(id);
		if (getLogger().isInfoEnabled()) {
			try {
				String jsonStr = null;
				if (result != null) {
					Gson gson = new Gson();
					jsonStr = gson.toJson((Object) result);
				} else {
					jsonStr = "查询不到";
				}

				getLogger().info(getBaseMessage() + "查询单条记录结果：" + jsonStr);
			} catch (Exception e) {
				getLogger().info("系统打印参数序列化的时候发生了异常，该异常不会影响数据库操作");
			}
			
		}
		

		return result;
	}

	@Override
	public int updateByExampleSelective(GuessQuestion record, Object example) {
		if (logger.isInfoEnabled()) {
			logger.info(getBaseMessage() + "更新记录开始");
		}
		int result = questionMapper.updateByExampleSelective(record, (GuessQuestionCriteria) example);
		if (logger.isInfoEnabled()) {
			logger.info(getBaseMessage() + "更新记录结束");
		}

		return result;
	}

	@Override
	public int updateByExample(GuessQuestion record, Object example) {
		return questionMapper.updateByExample(record, (GuessQuestionCriteria) example);
	}

	@Override
	public int updateByPrimaryKeySelective(GuessQuestion record) {
		if (logger.isInfoEnabled()) {
			logger.info(getBaseMessage() + "更新开始");
		}

		int result = questionMapper.updateByPrimaryKeySelective(record);
		if (logger.isInfoEnabled())
			logger.info(getBaseMessage() + "更新" + (result == 1 ? "成功" : "失败"));
		return result;
	}

	@Override
	public int updateById(GuessQuestion record) {
		if (logger.isInfoEnabled()) {
			logger.info(getBaseMessage() + "更新开始");
		}

		int result = questionMapper.updateByPrimaryKey(record);
		if (logger.isInfoEnabled())
			logger.info(getBaseMessage() + "更新" + (result == 1 ? "成功" : "失败"));
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly = true)
	public List<GuessQuestion> selectByExample(Object example, PageInfo pageInfo) {
		if (pageInfo != null && pageInfo.getPageSize() != 0) {
			PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		}
		return questionMapper.selectByExample(example);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GuessQuestion> selectByExample(Object example) {
		return questionMapper.selectByExample(example);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional(readOnly = true)
	public PageInfo selectByPageInfo(Object example, PageInfo pageInfo) {
		if (pageInfo != null && pageInfo.getPageSize() != 0) {
			PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		}
		List list = questionMapper.selectByExample(example);
		pageInfo.setList(list);
		return pageInfo;
	}

	@Override
	public GuessQuestion selectByName(String name,String type) {
		List<GuessQuestion> list = questionMapper.selectByName(name,type);
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public void checkQuestion(String questionId, String optionId) {
		// 更新问题的答案为false；
		GuessOptions guessOptions = new GuessOptions();
		guessOptions.setIsRight(false);
		Example example = new Example(GuessOptions.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("guessQuestionId", questionId);
		guessOptionsMapper.updateByExampleSelective(guessOptions, example);
		
		// 更新所有问题的答案为true；
		criteria = example.createCriteria();
		criteria.andEqualTo("guessQuestionId", questionId);
		criteria.andEqualTo("id", optionId);
		guessOptions.setIsRight(true);
		guessOptionsMapper.updateByExampleSelective(guessOptions, example);
		
		
		questionMapper.updateMememberAcc(questionId, optionId);
		questionMapper.updateMememberHisAcc(questionId, optionId);
		
		// 更新问题已经合算过了。
		GuessQuestion guessQuestion = questionMapper.selectByPrimaryKey(questionId);
		guessQuestion.setChecked(1);
		questionMapper.updateByPrimaryKeySelective(guessQuestion);
	}

	@Override
	public List<GuessQuestion> selectQuestions(GuessQuestion guessQuestion, PageInfo<GuessQuestion> pageInfo) {
		if (pageInfo != null && pageInfo.getPageSize() != 0) {
			PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		}
		List<GuessQuestion> list = questionMapper.selectQuestions(guessQuestion);
		return list;
	}

}
