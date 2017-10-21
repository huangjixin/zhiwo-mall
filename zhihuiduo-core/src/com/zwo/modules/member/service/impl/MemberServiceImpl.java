/**
 * 
 */
package com.zwo.modules.member.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.common.Mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwo.modules.mall.dao.OrderTradeMapper;
import com.zwo.modules.mall.dao.PrProductMapper;
import com.zwo.modules.mall.domain.OrderTrade;
import com.zwo.modules.mall.domain.OrderTradeCriteria;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.member.dao.GuessQuestionMapper;
import com.zwo.modules.member.dao.MemberAccountHisMapper;
import com.zwo.modules.member.dao.MemberAccountMapper;
import com.zwo.modules.member.dao.MemberAddressMapper;
import com.zwo.modules.member.dao.MemberMapper;
import com.zwo.modules.member.dao.MemberPlayAccountMapper;
import com.zwo.modules.member.dao.MemberPlayHisAccountMapper;
import com.zwo.modules.member.dao.MemberProfitMapper;
import com.zwo.modules.member.domain.GuessQuestion;
import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.domain.MemberAccount;
import com.zwo.modules.member.domain.MemberAccountHis;
import com.zwo.modules.member.domain.MemberAddress;
import com.zwo.modules.member.domain.MemberAddressCriteria;
import com.zwo.modules.member.domain.MemberCriteria;
import com.zwo.modules.member.domain.MemberCriteria.Criteria;
import com.zwo.modules.member.domain.MemberPlayAccount;
import com.zwo.modules.member.domain.MemberPlayHisAccount;
import com.zwo.modules.member.domain.MemberPlayHisAccountCriteria;
import com.zwo.modules.member.service.IMemberService;
import com.zwotech.common.utils.PasswordHelper;
import com.zwotech.common.utils.RedisUtil;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.modules.core.service.impl.BaseService;

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
	private static final String KEY_MEMBER = "_key_member";
	private static final String KEY_OPENID_MEMBER = "_key_openid_member";

	@Autowired
	@Lazy(true)
	private MemberMapper memberMapper;
	
	@Autowired
	@Lazy(true)
	private OrderTradeMapper orderMapper;
	
	@Autowired
	@Lazy(true)
	private MemberProfitMapper memberProfitMapper ;
	
	/**
	 * 会员智惠豆账户接口。
	 */
	@Autowired
	@Lazy(true)
	private MemberPlayAccountMapper memberPlayAccountMapper;
	
	/**
	 * 会员账户接口。
	 */
	@Autowired
	@Lazy(true)
	private MemberAccountMapper memberAccountMapper;
	
	/**
	 * 会员流水账户接口。
	 */
	@Autowired
	@Lazy(true)
	private MemberAccountHisMapper memberAccountHisMapper;
	
	@Autowired
	@Lazy(true)
	private MemberPlayHisAccountMapper memberPlayHisAccountMapper;
	
	@Autowired
	@Lazy(true)
	private PrProductMapper productMapper;
	
	@Autowired
	@Lazy(true)
	private MemberAddressMapper addressMapper;
	
	@Autowired
	@Lazy(true)
	private GuessQuestionMapper questionMapper;

	private RedisTemplate redisTemplate;
	
	@Override
	public Mapper<Member> getBaseMapper() {
		return memberMapper;
	}


	public MemberServiceImpl() {
		super();
		if (redisTemplate == null) {
			if (SpringContextHolder.getApplicationContext().containsBean(
					"redisTemplate")) {
				redisTemplate = SpringContextHolder.getBean("redisTemplate");
			}
		}
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
	public int deleteByExample(Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除开始");
		List<Member> list = this.selectByExample(example);
		for (Member member : list) {
			RedisUtil.removeRedisKey(redisTemplate, member.getId()+KEY_MEMBER);
		}
		// 逻辑操作
		int result = memberMapper.deleteByExample(example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

	@Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		MemberCriteria memberCriteria = new MemberCriteria();
		memberCriteria.createCriteria().andIdIn(list);
		List<Member> members = this.selectByExample(memberCriteria);
		for (Member member : members) {
			RedisUtil.removeRedisKey(redisTemplate, member.getId()+KEY_MEMBER);
			RedisUtil.removeRedisKey(redisTemplate, member.getOpenId()+KEY_OPENID_MEMBER);
		}
		
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
	@CacheEvict(value = "Member", key="#id+'_key_member'")
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为：" + id.toString());
		Member member = selectByPrimaryKey(id);
		RedisUtil.removeRedisKey(redisTemplate, id+KEY_MEMBER);
		RedisUtil.removeRedisKey(redisTemplate, member.getOpenId()+KEY_OPENID_MEMBER);
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
	//@CachePut(value = "Member", key = "#record.id+'_key_member'")
	public int insert(Member record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		if ("".equals(record.getParentId())) {
			record.setParentId(null);
		}
		// 逻辑操作
		if (record.getPassword() != null) {
			record.setPassword(PasswordHelper.encryptPassword(record.getPassword()));
		}		
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
	//@CachePut(value = "Member", key = "#record.id+'_key_member'")
	public int insertSelective(Member record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		if ("".equals(record.getParentId())) {
			record.setParentId(null);
		}
		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		// 逻辑操作
		if (record.getPassword() != null) {
			record.setPassword(PasswordHelper.encryptPassword(record.getPassword()));
		}
		int result = super.insertSelective(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入结束");
		//当新增一个会员成功的时候，为其创建一个游戏竞猜账号和一个分销账号。
		asycInsertOrderTrade(record);
		return result;
	}
	

	private void asycInsertOrderTrade(final Member record) {
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() {
			public void run() {
				createMemberAccount(record);
				createMemberPlayAccount(record);
			}
		});
	}

	@Async
	private int createMemberAccount(Member record){
		MemberAccount memberAccount = new MemberAccount();
		memberAccount.setId(record.getId());
		memberAccount.setMemberId(record.getId());
		memberAccount.setBalance((double) 100);//开户赠送一百智惠豆。
		int result = memberAccountMapper.insertSelective(memberAccount);
		return result;
	}
	
	@Async
	private int createMemberPlayAccount(Member record){
		MemberPlayAccount memberAccount = new MemberPlayAccount();
		memberAccount.setId(record.getId());
		memberAccount.setMemberId(record.getId());
		int result = memberPlayAccountMapper.insertSelective(memberAccount);
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
	@Cacheable(key = "#id+'_key_member'", value = "Member")
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
//	@CacheEvict(value = "Member", allEntries = true)
	@Override
	public int updateByExampleSelective(Member record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为：" + record.toString());
		if ("".equals(record.getParentId())) {
			record.setParentId(null);
		}
		List<Member> list = this.selectByExample(example);
		for (Member member : list) {
			RedisUtil.removeRedisKey(redisTemplate, member.getId()+KEY_MEMBER);
			RedisUtil.removeRedisKey(redisTemplate, member.getOpenId()+KEY_OPENID_MEMBER);
		}
		
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
		if ("".equals(record.getParentId())) {
			record.setParentId(null);
		}
		if (record.getPassword() != null) {
			record.setPassword(PasswordHelper.encryptPassword(record.getPassword()));
		}
		List<Member> list = this.selectByExample(example);
		for (Member member : list) {
			RedisUtil.removeRedisKey(redisTemplate, member.getId()+KEY_MEMBER);
			RedisUtil.removeRedisKey(redisTemplate, member.getOpenId()+KEY_OPENID_MEMBER);
		}
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
	@CacheEvict(value = "Member", key="#record.id+'_key_member'")
	public int updateByPrimaryKeySelective(Member record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为：" + record.toString());
		if ("".equals(record.getParentId())) {
			record.setParentId(null);
		}
		if (record.getPassword() != null) {
			record.setPassword(PasswordHelper.encryptPassword(record.getPassword()));
		}
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
	@CacheEvict(value = "Member", key="#record.id+'_key_member'")
	public int updateByPrimaryKey(Member record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新对象为：" + record.toString());
		if ("".equals(record.getParentId())) {
			record.setParentId(null);
		}
		if (record.getPassword() != null) {
			record.setPassword(PasswordHelper.encryptPassword(record.getPassword()));
		}
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
	public List<OrderTrade> selectOrderByMemberId(String memberId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询订单会员id为"+memberId+"开始");
		OrderTradeCriteria orderCriteria = new OrderTradeCriteria();
		orderCriteria.createCriteria().andMemberIdEqualTo(memberId);
		List<OrderTrade> list = orderMapper.selectByExample(orderCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询订单会员id为"+memberId+"结束，结果条目数为："+list.size());
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public PageInfo<OrderTrade> selectOrderByMemberId(String memberId, PageInfo<OrderTrade> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询订单会员id为"+memberId+"开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询分页参数：" + pageInfo.toString());
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		OrderTradeCriteria orderCriteria = new OrderTradeCriteria();
		orderCriteria.createCriteria().andMemberIdEqualTo(memberId);
		List<OrderTrade> list = orderMapper.selectByExample(orderCriteria);
		PageInfo<OrderTrade> page = new PageInfo<OrderTrade>(list);
		pageInfo.setList(list);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setEndRow(page.getEndRow());
		pageInfo.setStartRow(page.getStartRow());
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询订单会员id为"+memberId+"结束");
		return pageInfo;
	}

	@Override
	@Transactional(readOnly = true)
	public Double sumProfitByMemberId(String memberId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "统计会员我的分销商品盈利,会员id为"+memberId+"开始");
		Double result = this.memberProfitMapper.sumProfitByMemberId( memberId);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "统计会员我的分销商品盈利,会员id为"+memberId+"结束，结果为："+result);
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Double sumRealProfitByMemberId(String memberId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "统计会员我的分销商品盈利实际到帐,会员id为"+memberId+"开始");
		Double result = this.memberProfitMapper.sumRealProfitByMemberId( memberId);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "统计会员我的分销商品盈利实际到帐,会员id为"+memberId+"结束，结果为："+result);
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "MemberPlayAccount", key="#root.method.name+#memberId")
	public MemberPlayAccount selectMemberPlayAccountByMemberId(String memberId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据会员ID查询会员智慧豆账户记录,会员id为"+memberId+"开始");
//		MemberPlayAccountCriteria memberPlayAccountCriteria = new MemberPlayAccountCriteria();
//		memberPlayAccountCriteria.createCriteria().andMemberIdEqualTo(memberId);
//		List<MemberPlayAccount>list = memberPlayAccountMapper.selectByExample(memberPlayAccountCriteria);
		MemberPlayAccount memberPlayAccount = memberPlayAccountMapper.selectByPrimaryKey(memberId);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据会员ID查询会员智慧豆账户记录,会员id为"+memberId+"结束，结果为：");
		return memberPlayAccount;
	}

	@Override
	@Transactional(readOnly = true)
	public List<MemberPlayHisAccount> selectMemberPlayHisAccountByMemberId(String memberId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据会员ID查询会员智慧豆账户记录,会员id为"+memberId+"开始");
		MemberPlayHisAccountCriteria memberPlayAccountCriteria = new MemberPlayHisAccountCriteria();
		memberPlayAccountCriteria.createCriteria().andMemberIdEqualTo(memberId);
		List<MemberPlayHisAccount>list = memberPlayHisAccountMapper.selectByExample(memberPlayAccountCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据会员ID查询会员智慧豆账户记录,会员id为"+memberId+"结束，结果为："+list.size());
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public PageInfo<MemberPlayHisAccount> selectMemberPlayHisAccountByMemberId(String memberId, PageInfo<MemberPlayHisAccount> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页会员ID查询会员智慧豆账户历史记录,会员id为"+memberId+"开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询分页参数：" + pageInfo.toString());
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		MemberPlayHisAccountCriteria orderCriteria = new MemberPlayHisAccountCriteria();
		orderCriteria.createCriteria().andMemberIdEqualTo(memberId);
		List<MemberPlayHisAccount> list = memberPlayHisAccountMapper.selectByExample(orderCriteria);
		PageInfo<MemberPlayHisAccount> page = new PageInfo<MemberPlayHisAccount>(list);
		pageInfo.setList(list);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setEndRow(page.getEndRow());
		pageInfo.setStartRow(page.getStartRow());
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页会员ID查询会员智慧豆账户历史记录,会员id为"+memberId+"结束");
		return pageInfo;
	}

	@Override
	@Cacheable(value = "MemberAccount", key="#root.method.name+#memberId")
	public MemberAccount selectMemberAccountByMId(String memberId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据会员ID查询会员账户,会员id为"+memberId+"开始");
		/*MemberAccountCriteria memberAccountCriteria = new MemberAccountCriteria();
		memberAccountCriteria.createCriteria().andMemberIdEqualTo(memberId);
		List<MemberAccount>list = memberAccountMapper.selectByExample(memberAccountCriteria);*/
		
		MemberAccount memberAccount = memberAccountMapper.selectByPrimaryKey(memberId);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据会员ID查询会员账户,会员id为"+memberId+"结束，结果为："+memberAccount);
		return memberAccount;
	}
	
	@Override
	@CacheEvict(value = "MemberAccount", key="#root.method.name+#memberAccount.id")
	public int updateMemberAccountByPrimaryKeySelective(MemberAccount memberAccount) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "修改会员账户对象为"+memberAccount+"开始");
		int result = memberAccountMapper.updateByPrimaryKeySelective(memberAccount);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "修改会员账户，对象为"+memberAccount+"结束，结果为(1为成功，0为失败)："+result);
		MemberAccountHis accountHis = new MemberAccountHis();
		try {
			BeanUtils.copyProperties(accountHis, memberAccount);
			if (logger.isInfoEnabled())
				logger.info(BASE_MESSAGE + "新增会员流水账户，对象为"+accountHis+"开始");
			accountHis.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
			memberAccountHisMapper.insertSelective(accountHis);
			if (logger.isInfoEnabled())
				logger.info(BASE_MESSAGE + "新增会员流水账户，对象为"+accountHis+"结束，结果为："+result);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	@CacheEvict(value = "MemberAccount", key="#root.method.name+#memberAccount.id")
	public int updateMemberPlayAccountByPrimaryKeySelective(MemberPlayAccount playAccount) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "修改会员账户对象为"+playAccount+"开始");
		int result = memberPlayAccountMapper.updateByPrimaryKeySelective(playAccount);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "修改会员账户，对象为"+playAccount+"结束，结果为(1为成功，0为失败)："+result);
		MemberPlayHisAccount accountHis = new MemberPlayHisAccount();
		try {
			BeanUtils.copyProperties(accountHis, playAccount);
			if (logger.isInfoEnabled())
				logger.info(BASE_MESSAGE + "新增会员流水账户，对象为"+accountHis+"开始");
			accountHis.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
			memberPlayHisAccountMapper.insertSelective(accountHis);
			if (logger.isInfoEnabled())
				logger.info(BASE_MESSAGE + "新增会员流水账户，对象为"+accountHis+"结束，结果为："+result);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "Member", key="#usernameOrMphoneOrEmail+'_key_usernameOrMphoneOrEmail'")
	public Member selectMember(String usernameOrMphoneOrEmail) {
		MemberCriteria memberCriteria = new MemberCriteria();
		com.zwo.modules.member.domain.MemberCriteria.Criteria openidCriteria = memberCriteria.createCriteria().andOpenIdEqualTo(usernameOrMphoneOrEmail);
		com.zwo.modules.member.domain.MemberCriteria.Criteria nameCriteria = memberCriteria.createCriteria().andUsernameEqualTo(usernameOrMphoneOrEmail);
		com.zwo.modules.member.domain.MemberCriteria.Criteria phoneCriteria = memberCriteria.createCriteria().andMobilPhoneEqualTo(usernameOrMphoneOrEmail);
		com.zwo.modules.member.domain.MemberCriteria.Criteria emailCriteria = memberCriteria.createCriteria().andEmailEqualTo(usernameOrMphoneOrEmail);
		memberCriteria.or(openidCriteria);
		memberCriteria.or(nameCriteria);
		memberCriteria.or(phoneCriteria);
		memberCriteria.or(emailCriteria);
		List<Member>list = this.memberMapper.selectByExample(memberCriteria);

		return list.isEmpty()?null:list.get(0);
	}


	@Override
	@Transactional(readOnly = true)
	@Cacheable(value = "Member", key="#openId+'_key_openid_member'")
	public Member selectByOpenId(String openId) {
		MemberCriteria memberCriteria = new MemberCriteria();
		memberCriteria.createCriteria().andOpenIdEqualTo(openId);
		List<Member> list = this.memberMapper.selectByExample(memberCriteria);
		return list.isEmpty()?null:list.get(0);
	}


	@Override
	@Transactional(readOnly = true)
	public int countByOpenId(String openId) {
		MemberCriteria memberCriteria = new MemberCriteria();
		MemberCriteria.Criteria criteria = memberCriteria.createCriteria();
		criteria.andOpenIdEqualTo(openId);
		
		return this.memberMapper.selectCountByExample(memberCriteria);
	}
}
