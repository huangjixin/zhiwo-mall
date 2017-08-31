/**
* 
*/
package com.zwo.modules.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.zwo.modules.member.dao.GuessQuestionAnswerMapper;
import com.zwo.modules.member.domain.GuessQuestionAnswer;
import com.zwo.modules.member.domain.GuessQuestionAnswerCriteria;
import com.zwo.modules.member.service.IGuessQuestionAnswerService;
import com.zwotech.modules.core.service.impl.BaseService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author hjx
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class GuessQuestionAnswerServiceImpl extends BaseService<GuessQuestionAnswer> implements IGuessQuestionAnswerService {
	private static Logger logger = LoggerFactory.getLogger(GuessQuestionAnswerServiceImpl.class);

	private static final String BASE_MESSAGE = "【GuessQuestionAnswerServiceImpl服务类提供的基础操作增删改查等】";

	@Autowired
	@Lazy(true)
	private JdbcTemplate jdbcTemplate;
	@Autowired
	@Lazy(true)
	private GuessQuestionAnswerMapper guessQuestionAnswerMapper;

	@Override
	public Mapper<GuessQuestionAnswer> getBaseMapper() {
		return guessQuestionAnswerMapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*
	 * @Override public int insertBatch(List<GuessQuestionAnswer> list) { // TODO
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
	@CacheEvict(value = "GuessQuestionAnswer", allEntries = true)
	public int deleteByExample(Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除开始");

		// 逻辑操作
		int result = guessQuestionAnswerMapper.deleteByExample(example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

	@CacheEvict(value = "GuessQuestionAnswer", allEntries = true)
	// @Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		GuessQuestionAnswerCriteria guessQuestionCriteria = new GuessQuestionAnswerCriteria();
		guessQuestionCriteria.createCriteria().andIdIn(list);
		int result = guessQuestionAnswerMapper.deleteByExample(guessQuestionCriteria);

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
	@Override
	@CacheEvict(value = "GuessQuestionAnswer", key = "#id+'_GuessQuestionAnswer'")
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为：" + id.toString());

		// 逻辑操作
		int result = super.deleteByPrimaryKey(id);

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
//	@CachePut(value = "GuessQuestionAnswer", key = "#record.id")
	public int insert(GuessQuestionAnswer record) {
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
//	@CachePut(value = "GuessQuestionAnswer", key = "#record.id")
	public int insertSelective(GuessQuestionAnswer record) {
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
	public List<GuessQuestionAnswer> selectByExample(Object example) {
		return guessQuestionAnswerMapper.selectByExample(example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(key = "#id+'_GuessQuestionAnswer'", value = "GuessQuestionAnswer")
	@Transactional(readOnly = true)
	public GuessQuestionAnswer selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		GuessQuestionAnswer guessQuestion = super.selectByPrimaryKey(id);
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
	@CacheEvict(value = "GuessQuestionAnswer", allEntries = true)
	@Override
	public int updateByExampleSelective(GuessQuestionAnswer record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为：" + record.toString());

		// 逻辑操作
		int result = super.updateByExampleSelective(record, example);
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
	@CacheEvict(value = "GuessQuestionAnswer", allEntries = true)
	public int updateByExample(GuessQuestionAnswer record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新对象为：" + record.toString());

		// 逻辑操作
		int result = super.updateByExample(record, example);
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
	@CacheEvict(value = "GuessQuestionAnswer", key = "#record.id")
	public int updateByPrimaryKeySelective(GuessQuestionAnswer record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为：" + record.toString());

		// 逻辑操作
		int result = super.updateByPrimaryKeySelective(record);
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
	@CacheEvict(value = "GuessQuestionAnswer", key = "#record.id")
	public int updateByPrimaryKey(GuessQuestionAnswer record) {
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
	public PageInfo<GuessQuestionAnswer> selectByPageInfo(Object example, PageInfo<GuessQuestionAnswer> pageInfo) {
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
		IGuessQuestionAnswerService guessQuestionServiceImpl = (IGuessQuestionAnswerService) context.getBean("guessQuestionServiceImpl");
		GuessQuestionAnswer guessQuestion = new GuessQuestionAnswer();
		guessQuestion.setId(System.currentTimeMillis() + "");
		int result = guessQuestionServiceImpl.insertSelective(guessQuestion);
		logger.info(result + "");
	}

	/*update member_play_account mpa 
inner join
(SELECT 
    gqm.member_id, gqo.BET_RATE, mgq.GUESS_ACCOUNT
FROM
    guess_question_memanswer AS gqm
        INNER JOIN
    guess_question_answer AS gqa ON gqm.question_id = gqa.question_id
        AND gqm.question_options_id = gqa.question_options_id
        INNER JOIN
    guess_question_options gqo ON gqo.id = gqa.question_options_id
        INNER JOIN
    member_guess_question mgq ON mgq.MEMBER_ID = gqm.MEMBER_ID
        AND gqa.question_id = mgq.GUESS_QUESTION_ID where gqa.question_id=? and gqa.question_options_id=?) as a
         on mpa.MEMBER_ID = a.member_id set mpa.zhihuidou_count=mpa.zhihuidou_count+a.bet_rate*a.guess_account 
*/
	@Override
	public void settleAccounts(GuessQuestionAnswer answer) {
		StringBuffer sbText  = new StringBuffer();
		sbText.append("update member_play_account mpa ");
		sbText.append("inner join");
		sbText.append("(SELECT ");
		sbText.append("    gqm.member_id, gqo.BET_RATE, mgq.GUESS_ACCOUNT");
		sbText.append("FROM");
		sbText.append("    guess_question_memanswer AS gqm");
		sbText.append("        INNER JOIN");
		sbText.append("    guess_question_answer AS gqa ON gqm.question_id = gqa.question_id");
		sbText.append("        AND gqm.question_options_id = gqa.question_options_id");
		sbText.append("        INNER JOIN");
		sbText.append("    guess_question_options gqo ON gqo.id = gqa.question_options_id");
		sbText.append("        INNER JOIN");
		sbText.append("    member_guess_question mgq ON mgq.MEMBER_ID = gqm.MEMBER_ID");
		sbText.append("        AND gqa.question_id = mgq.GUESS_QUESTION_ID where gqa.question_id=:question_id and gqa.question_options_id=:question_options_id) as a");
		sbText.append("         on mpa.MEMBER_ID = a.member_id set mpa.zhihuidou_count=mpa.zhihuidou_count+a.bet_rate*a.guess_account ");
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("question_id",answer.getQuestionId());
		params.put("question_options_id",answer.getQuestionOptionsId());
		new NamedParameterJdbcTemplate(jdbcTemplate).update(sbText.toString(),params);
		
		
		sbText  = new StringBuffer();
		sbText.append("    update guess_question_memanswer  gqm");
		sbText.append("        INNER JOIN");
		sbText.append("    guess_question_answer AS gqa ON gqm.question_id = gqa.question_id");
		sbText.append("        AND gqm.question_options_id = gqa.question_options_id");
		sbText.append("        INNER JOIN");
		sbText.append("    guess_question_options gqo ON gqo.id = gqa.question_options_id");
		sbText.append("        INNER JOIN");
		sbText.append("    member_guess_question mgq ON mgq.MEMBER_ID = gqm.MEMBER_ID");
		sbText.append("        AND gqa.question_id = mgq.GUESS_QUESTION_ID where gqm.payed=0 and gqa.question_id=:question_id and gqa.question_options_id=:question_options_id");
		sbText.append("	set gqm.payed=1");
		new NamedParameterJdbcTemplate(jdbcTemplate).update(sbText.toString(),params);
	}

}
