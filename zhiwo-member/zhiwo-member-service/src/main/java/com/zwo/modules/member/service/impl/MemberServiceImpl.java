/**
 * 
 */
package com.zwo.modules.member.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwo.modules.mall.dao.OrderMapper;
import com.zwo.modules.mall.dao.PrProductMapper;
import com.zwo.modules.mall.domain.Order;
import com.zwo.modules.mall.domain.OrderCriteria;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.member.dao.GuessQuestionMapper;
import com.zwo.modules.member.dao.MemberAddressMapper;
import com.zwo.modules.member.dao.MemberMapper;
import com.zwo.modules.member.dao.MemberProfitMapper;
import com.zwo.modules.member.domain.GuessQuestion;
import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.domain.MemberAddress;
import com.zwo.modules.member.domain.MemberAddressCriteria;
import com.zwo.modules.member.domain.MemberCriteria;
import com.zwo.modules.member.service.IMemberService;
import com.zwotech.modules.core.service.impl.BaseService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author hjx
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class MemberServiceImpl extends BaseService<Member> implements IMemberService {
	private static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	private static final String BASE_MESSAGE = "【MemberServiceImpl服务类提供的基础操作增删改查等】";

	@Autowired
	@Lazy(true)
	private MemberMapper memberMapper;
	
	@Autowired
	@Lazy(true)
	private OrderMapper orderMapper;
	
	@Autowired
	@Lazy(true)
	private MemberProfitMapper memberProfitMapper ;
	
	@Autowired
	@Lazy(true)
	private PrProductMapper productMapper;
	
	@Autowired
	@Lazy(true)
	private MemberAddressMapper addressMapper;
	
	@Autowired
	@Lazy(true)
	private GuessQuestionMapper questionMapper;

	@Override
	public Mapper<Member> getBaseMapper() {
		return memberMapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*
	 * @Override public int insertBatch(List<Member> list) { // TODO
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
	@CacheEvict(value = "Member", allEntries = true)
	public int deleteByExample(Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除开始");

		// 逻辑操作
		int result = memberMapper.deleteByExample(example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

	@CacheEvict(value = "Member", allEntries = true)
//	@Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		MemberCriteria memberCriteria = new MemberCriteria();
		memberCriteria.createCriteria().andIdIn(list);
		int result = memberMapper.deleteByExample(memberCriteria);

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
	@CacheEvict(value = "Member", key="#id")
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
	@CachePut(value = "Member", key = "#record.id")
	public int insert(Member record) {
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
	@CachePut(value = "Member", key = "#record.id")
	public int insertSelective(Member record) {
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
	public List<Member> selectByExample(Object example) {
		return memberMapper.selectByExample(example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(key = "#id", value = "Member")
	@Transactional(readOnly = true)
	public Member selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		Member member = super.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return member;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@CacheEvict(value = "Member", allEntries = true)
	@Override
	public int updateByExampleSelective(Member record, Object example) {
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
	@CacheEvict(value = "Member", allEntries = true)
	public int updateByExample(Member record, Object example) {
		//日志记录
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample更新开始");
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample更新对象为：" + record.toString());
										
		//逻辑操作		
		int result = super.updateByExample(record, example);
		//日志记录
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample更新结束");
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
	@CacheEvict(value = "Member", key="#record.id")
	public int updateByPrimaryKeySelective(Member record) {
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
	@CacheEvict(value = "Member", key="#record.id")
	public int updateByPrimaryKey(Member record) {
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
	public PageInfo<Member> selectByPageInfo(Object example, PageInfo<Member> pageInfo) {
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
		IMemberService memberServiceImpl = (IMemberService) context.getBean("memberServiceImpl");
		Member member = new Member();
		member.setId(System.currentTimeMillis() + "");
		int result = memberServiceImpl.insertSelective(member);
		logger.info(result + "");
	}

	@Override
	@Transactional(readOnly = true)
	public List<MemberAddress> selectByMId(String memberId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "会员查询地址开始");
		MemberAddressCriteria memberAddressCriteria = new MemberAddressCriteria();
		memberAddressCriteria.createCriteria().andMemberIdEqualTo(memberId);
		List<MemberAddress> list = addressMapper.selectByExample(memberAddressCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "会员查询地址结束,结果："+list.size());
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PrProduct> selectByMemberId(String memberId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "会员查询分销的商品开始");
		List<PrProduct> list = productMapper.selectByMemberId(memberId);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "会员查询分销的商品结束,结果查询的条目数为："+list.size());
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public PageInfo<PrProduct> selectByMemberId(String memberId,PageInfo<PrProduct> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询会员ID(参数)为"+memberId+"分销的商品分页开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询会员分销的商品分页参数：" + pageInfo.toString());
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<PrProduct> list = productMapper.selectByMemberId(memberId);
		PageInfo<PrProduct> page = new PageInfo<PrProduct>(list);
		pageInfo.setList(list);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setEndRow(page.getEndRow());
		pageInfo.setStartRow(page.getStartRow());
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询会员分销的商品分页结束");
		return pageInfo;
	}

	@Override
	@Transactional(readOnly = true)
	public List<GuessQuestion> selectGuessQuestionByMemberId(String memberId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询会员id为"+memberId+"竞猜所有问题开始");
		List<GuessQuestion> list = questionMapper.selectGuessQuestionByMemberId(memberId);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询会员id为"+memberId+"竞猜所有问题结束，结果条目数为："+list.size());
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public PageInfo<GuessQuestion> selectGuessQuestionByMemberId(String memberId, PageInfo<GuessQuestion> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询会员id为"+memberId+"竞猜所有问题开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询分页参数：" + pageInfo.toString());
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<GuessQuestion> list = questionMapper.selectGuessQuestionByMemberId(memberId);
		PageInfo<GuessQuestion> page = new PageInfo<GuessQuestion>(list);
		pageInfo.setList(list);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setEndRow(page.getEndRow());
		pageInfo.setStartRow(page.getStartRow());
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询会员id为"+memberId+"竞猜所有问题开始");
		return pageInfo;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Order> selectOrderByMemberId(String memberId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询订单会员id为"+memberId+"开始");
		OrderCriteria orderCriteria = new OrderCriteria();
		orderCriteria.createCriteria().andMemberIdEqualTo(memberId);
		List<Order> list = orderMapper.selectByExample(orderCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询订单会员id为"+memberId+"结束，结果条目数为："+list.size());
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public PageInfo<Order> selectOrderByMemberId(String memberId, PageInfo<Order> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询订单会员id为"+memberId+"开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询分页参数：" + pageInfo.toString());
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		OrderCriteria orderCriteria = new OrderCriteria();
		orderCriteria.createCriteria().andMemberIdEqualTo(memberId);
		List<Order> list = orderMapper.selectByExample(orderCriteria);
		PageInfo<Order> page = new PageInfo<Order>(list);
		pageInfo.setList(list);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setEndRow(page.getEndRow());
		pageInfo.setStartRow(page.getStartRow());
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询订单会员id为"+memberId+"结束");
		return pageInfo;
	}

	@Override
	public Double sumProfitByMemberId(String memberId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "统计会员我的分销商品盈利,会员id为"+memberId+"开始");
		Double result = this.memberProfitMapper.sumProfitByMemberId( memberId);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "统计会员我的分销商品盈利,会员id为"+memberId+"结束，结果为："+result);
		return result;
	}

	@Override
	public Double sumRealProfitByMemberId(String memberId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "统计会员我的分销商品盈利实际到帐,会员id为"+memberId+"开始");
		Double result = this.memberProfitMapper.sumRealProfitByMemberId( memberId);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "统计会员我的分销商品盈利实际到帐,会员id为"+memberId+"结束，结果为："+result);
		return result;
	}
}
