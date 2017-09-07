/**
* 
*/
package com.zwo.modules.member.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.common.Mapper;

import com.github.pagehelper.PageInfo;
import com.zwo.modules.member.dao.GuessQuestionMapper;
import com.zwo.modules.member.dao.GuessQuestionOptionsMapper;
import com.zwo.modules.member.domain.GuessQuestion;
import com.zwo.modules.member.domain.GuessQuestionCriteria;
import com.zwo.modules.member.domain.GuessQuestionOption;
import com.zwo.modules.member.domain.GuessQuestionOptions;
import com.zwo.modules.member.domain.GuessQuestionOptionsCriteria;
import com.zwo.modules.member.service.IGuessQuestionService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.modules.core.service.impl.BaseService;

/**
 * @author hjx
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class GuessQuestionServiceImpl extends BaseService<GuessQuestion> implements IGuessQuestionService {
	private static Logger logger = LoggerFactory.getLogger(GuessQuestionServiceImpl.class);

	private static final String BASE_MESSAGE = "【GuessQuestionServiceImpl服务类提供的基础操作增删改查等】";


	private RedisTemplate redisTemplate;
	
	@Autowired
	@Lazy(true)
	private GuessQuestionMapper guessQuestionMapper;
	
	@Autowired
	@Lazy(true)
	private GuessQuestionOptionsMapper questionOptionsMapper;

	@Override
	public Mapper<GuessQuestion> getBaseMapper() {
		return guessQuestionMapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*
	 * @Override public int insertBatch(List<GuessQuestion> list) { // TODO
	 * Auto-generated method stub return 0; }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#countByExample(java.lang.
	 * Object)
	 */
	/*
	 * @Override public int countByExample(Object example) { // TODO
	 * Auto-generated method stub return 0; }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#deleteByExample(java.lang.
	 * Object)
	 */
	@Override
	@CacheEvict(value = "GuessQuestion", allEntries = true)
	public int deleteByExample(Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除开始");

		// 逻辑操作
		int result = guessQuestionMapper.deleteByExample(example);
		if(result != 0){
			if(redisTemplate == null ){
				redisTemplate = SpringContextHolder.getBean("redisTemplate");
			}
			
			if(redisTemplate != null){
				redisTemplate.delete("selectIneffectQuestion_guessQuestion");
			}
		}
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

	@CacheEvict(value = "GuessQuestion", allEntries = true)
	// @Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		GuessQuestionCriteria guessQuestionCriteria = new GuessQuestionCriteria();
		guessQuestionCriteria.createCriteria().andIdIn(list);
		int result = guessQuestionMapper.deleteByExample(guessQuestionCriteria);
		if(result != 0){
			if(redisTemplate == null ){
				redisTemplate = SpringContextHolder.getBean("redisTemplate");
			}
			
			if(redisTemplate != null){
				redisTemplate.delete("selectIneffectQuestion_guessQuestion");
			}
		}
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#deleteByPrimaryKey(java.
	 * lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@CacheEvict(value = "GuessQuestion", key = "#id+'_guessQuestion'")
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为：" + id.toString());

		// 逻辑操作
		int result = super.deleteByPrimaryKey(id);
		if(result == 1){
			if(redisTemplate == null ){
				redisTemplate = SpringContextHolder.getBean("redisTemplate");
			}
			
			if(redisTemplate != null){
				redisTemplate.delete("selectIneffectQuestion_guessQuestion");
			}
		}
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insert(java.lang.Object)
	 */
	@Override
//	@CachePut(value = "GuessQuestion", key = "#record.id")
	public int insert(GuessQuestion record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());

		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		int result = super.insert(record);
		if(result == 1){
			if(redisTemplate == null ){
				redisTemplate = SpringContextHolder.getBean("redisTemplate");
			}
			
			if(redisTemplate != null){
				redisTemplate.delete("selectIneffectQuestion_guessQuestion");
			}
		}
		
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertSelective(java.lang.
	 * Object)
	 */

	@Override
//	@CachePut(value = "GuessQuestion", key = "#record.id")
	public int insertSelective(GuessQuestion record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());

		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		int result = super.insertSelective(record);
		if(result == 1){
			if(redisTemplate == null ){
				redisTemplate = SpringContextHolder.getBean("redisTemplate");
			}
			
			if(redisTemplate != null){
				redisTemplate.delete("selectIneffectQuestion_guessQuestion");
			}
		}
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByExample(java.lang.
	 * Object)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<GuessQuestion> selectByExample(Object example) {
		return guessQuestionMapper.selectByExample(example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(key = "#id+'_guessQuestion'", value = "GuessQuestion")
	@Transactional(readOnly = true)
	public GuessQuestion selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		GuessQuestion guessQuestion = super.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return guessQuestion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@CacheEvict(value = "GuessQuestion", allEntries = true)
	@Override
	public int updateByExampleSelective(GuessQuestion record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为：" + record.toString());

		// 逻辑操作
		int result = super.updateByExampleSelective(record, example);
		if(result == 1){
			if(redisTemplate == null ){
				redisTemplate = SpringContextHolder.getBean("redisTemplate");
			}
			
			if(redisTemplate != null){
				redisTemplate.delete("selectIneffectQuestion_guessQuestion");
			}
		}
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExample(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	@CacheEvict(value = "GuessQuestion", allEntries = true)
	public int updateByExample(GuessQuestion record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新对象为：" + record.toString());

		// 逻辑操作
		int result = super.updateByExample(record, example);
		if(result != 0){
			if(redisTemplate == null ){
				redisTemplate = SpringContextHolder.getBean("redisTemplate");
			}
			
			if(redisTemplate != null){
				redisTemplate.delete("selectIneffectQuestion_guessQuestion");
			}
		}
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByPrimaryKeySelective
	 * (java.lang.Object)
	 */
	@Override
	@CacheEvict(value = "GuessQuestion", key = "#record.id")
	public int updateByPrimaryKeySelective(GuessQuestion record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为：" + record.toString());

		// 逻辑操作
		int result = super.updateByPrimaryKeySelective(record);
		if(result != 0){
			if(redisTemplate == null ){
				redisTemplate = SpringContextHolder.getBean("redisTemplate");
			}
			
			if(redisTemplate != null){
				redisTemplate.delete("selectIneffectQuestion_guessQuestion");
			}
		}
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByPrimaryKey(java.
	 * lang.Object)
	 */
	@Override
	@CacheEvict(value = "GuessQuestion", key = "#record.id")
	public int updateByPrimaryKey(GuessQuestion record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新对象为：" + record.toString());

		// 逻辑操作
		int result = super.updateByPrimaryKey(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新结束");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPageInfo(java.lang.
	 * Object, com.github.pagehelper.PageInfo)
	 */
	@Transactional(readOnly = true)
	@Override
	public PageInfo<GuessQuestion> selectByPageInfo(Object example, PageInfo<GuessQuestion> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页参数：" + pageInfo.toString());
		pageInfo = super.selectByPageInfo(example, pageInfo);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页结束");
		return pageInfo;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/mall-applicationContext.xml");// 此文件放在SRC目录下
		IGuessQuestionService guessQuestionServiceImpl = (IGuessQuestionService) context.getBean("guessQuestionServiceImpl");
		GuessQuestion guessQuestion = new GuessQuestion();
		guessQuestion.setId(System.currentTimeMillis() + "");
		int result = guessQuestionServiceImpl.insertSelective(guessQuestion);
		logger.info(result + "");
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(key = "'selectIneffectQuestion_guessQuestion'", value = "GuessQuestiones")
	public List<GuessQuestionOption> selectIneffectQuestion() {
		List<GuessQuestionOption> guessQuestionOptions= new ArrayList<GuessQuestionOption>();
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询在用的竞猜问题开始");
		GuessQuestionCriteria questionCriteria = new GuessQuestionCriteria();
		questionCriteria.createCriteria().andDisableEqualTo(false);
		List<GuessQuestion> list = this.guessQuestionMapper.selectByExample(questionCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询在用的竞猜问题结束，结果条目数为："+list.size());
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询在用的竞猜问题循环查询设置问题开始");
		for (GuessQuestion guessQuestion : list) {
			GuessQuestionOptionsCriteria criteria = new GuessQuestionOptionsCriteria();
			criteria.createCriteria().andGuessQuestionIdEqualTo(guessQuestion.getId());
			List<GuessQuestionOptions> list2 = questionOptionsMapper.selectByExample(criteria);
			GuessQuestionOption questionOption = new GuessQuestionOption ();
			questionOption.setGuessQuestion(guessQuestion);
			questionOption.setGuessQuestionOptions(list2);
			
			guessQuestionOptions.add(questionOption);
		}
		
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询在用的竞猜问题循环查询设置问题结束");
		return guessQuestionOptions;
	}
	

}
