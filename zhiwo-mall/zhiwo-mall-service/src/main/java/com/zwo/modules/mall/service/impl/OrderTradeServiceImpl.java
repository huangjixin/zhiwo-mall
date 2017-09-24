/**
 * 
 */
package com.zwo.modules.mall.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.common.Mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwo.modules.mall.dao.OrderTradeMapper;
import com.zwo.modules.mall.domain.OrderTrade;
import com.zwo.modules.mall.domain.OrderTradeCriteria;
import com.zwo.modules.mall.domain.OrderTradeCriteria.Criteria;
import com.zwo.modules.mall.service.IOrderTradeService;
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
public class OrderTradeServiceImpl extends BaseService<OrderTrade> implements
		IOrderTradeService {
	private static Logger logger = LoggerFactory
			.getLogger(OrderTradeServiceImpl.class);

	private static final String BASE_MESSAGE = "【OrderTradeServiceImpl服务类提供的基础操作增删改查等】";
	private static final String KEY_ORDERTRADE_COUNT_BY_PRODUCTID = "_orderTrade_Count_By_ProductId";
	private static final String KEY_ORDERTRADE_COUNT_BY_SHOPID = "_orderTrade_Count_By_shopId";

	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;

	public OrderTradeServiceImpl() {
		super();
		if(SpringContextHolder.getApplicationContext().containsBean("redisTemplate")){
			redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}
	}

	@Autowired
	@Lazy(true)
	private OrderTradeMapper orderTradeMapper;

	@Override
	public Mapper<OrderTrade> getBaseMapper() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*
	 * @Override public int insertBatch(List<OrderTrade> list) { // TODO
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
	@CacheEvict(value = "OrderTrade", allEntries = true)
	public int deleteByExample(Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除开始");
		List<OrderTrade> list = orderTradeMapper
				.selectByExample((OrderTradeCriteria) example);
		for (OrderTrade orderTrade : list) {
			RedisUtil.removeRedisKey(redisTemplate, orderTrade.getProductId()
					+ orderTrade.getStatus()
					+ KEY_ORDERTRADE_COUNT_BY_PRODUCTID);
			RedisUtil.removeRedisKey(redisTemplate, orderTrade.getShopId()
					+ orderTrade.getStatus()
					+ KEY_ORDERTRADE_COUNT_BY_PRODUCTID);
		}

		// 逻辑操作
		int result = orderTradeMapper
				.deleteByExample((OrderTradeCriteria) example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

	@CacheEvict(value = "OrderTrade", allEntries = true)
	// @Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		OrderTradeCriteria orderCriteria = new OrderTradeCriteria();
		orderCriteria.createCriteria().andIdIn(list);
		List<OrderTrade> orderTrades = orderTradeMapper
				.selectByExample(orderCriteria);
		for (OrderTrade orderTrade : orderTrades) {
			RedisUtil.removeRedisKey(redisTemplate, orderTrade.getProductId()
					+ orderTrade.getStatus()
					+ KEY_ORDERTRADE_COUNT_BY_PRODUCTID);
			RedisUtil.removeRedisKey(redisTemplate, orderTrade.getShopId()
					+ orderTrade.getStatus()
					+ KEY_ORDERTRADE_COUNT_BY_PRODUCTID);
		}

		int result = orderTradeMapper.deleteByExample(orderCriteria);

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
	@CacheEvict(value = "OrderTrade", key = "#id+'_orderTrade'")
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为："
					+ id.toString());
		// 移除缓存
		OrderTrade orderTrade = orderTradeMapper.selectByPrimaryKey(id);
		RedisUtil.removeRedisKey(redisTemplate, orderTrade.getProductId()
				+ orderTrade.getStatus() + KEY_ORDERTRADE_COUNT_BY_PRODUCTID);
		RedisUtil.removeRedisKey(redisTemplate, orderTrade.getShopId()
				+ orderTrade.getStatus() + KEY_ORDERTRADE_COUNT_BY_PRODUCTID);

		// 逻辑操作
		int result = orderTradeMapper.deleteByPrimaryKey(id);

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
	// @CachePut(value = "OrderTrade", key = "#record.id+'_orderTrade'")
	public int insert(OrderTrade record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());

		// 移除缓存
		OrderTrade orderTrade = record;
		RedisUtil.removeRedisKey(redisTemplate, orderTrade.getProductId()
				+ record.getStatus() + KEY_ORDERTRADE_COUNT_BY_PRODUCTID);
		RedisUtil.removeRedisKey(redisTemplate,
				orderTrade.getShopId() + record.getStatus()
						+ KEY_ORDERTRADE_COUNT_BY_PRODUCTID);

		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + ""
					+ Math.round(Math.random() * 99));
		}
		int result = orderTradeMapper.insert(record);
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
	@CachePut(value = "OrderTrade", key = "#record.id+'_orderTrade'")
	public int insertSelective(OrderTrade record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		// 移除缓存
		OrderTrade orderTrade = record;
		RedisUtil.removeRedisKey(redisTemplate, orderTrade.getProductId()
				+ record.getStatus() + KEY_ORDERTRADE_COUNT_BY_PRODUCTID);
		RedisUtil.removeRedisKey(redisTemplate,
				orderTrade.getShopId() + record.getStatus()
						+ KEY_ORDERTRADE_COUNT_BY_PRODUCTID);

		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + ""
					+ Math.round(Math.random() * 99));
		}
		int result = orderTradeMapper.insertSelective(record);
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
	public List<OrderTrade> selectByExample(Object example) {
		return orderTradeMapper.selectByExample((OrderTradeCriteria) example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(key = "#id+'_orderTrade'", value = "OrderTrade")
	@Transactional(readOnly = true)
	public OrderTrade selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		OrderTrade order = orderTradeMapper.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return order;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@CacheEvict(value = "OrderTrade", allEntries = true)
	@Override
	public int updateByExampleSelective(OrderTrade record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为："
					+ record.toString());

		// 逻辑操作
		int result = orderTradeMapper.updateByExampleSelective(record,
				(OrderTradeCriteria) example);
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
	@CacheEvict(value = "OrderTrade", allEntries = true)
	public int updateByExample(OrderTrade record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新对象为："
					+ record.toString());

		// 逻辑操作
		int result = orderTradeMapper.updateByExample(record,
				(OrderTradeCriteria) example);
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
	@CacheEvict(value = "OrderTrade", key = "#record.id+'_orderTrade'")
	public int updateByPrimaryKeySelective(OrderTrade record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为："
					+ record.toString());

		// 逻辑操作
		int result = orderTradeMapper.updateByPrimaryKeySelective(record);
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
	@CacheEvict(value = "OrderTrade", key = "#record.id+'_orderTrade'")
	public int updateByPrimaryKey(OrderTrade record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新对象为："
					+ record.toString());

		// 逻辑操作
		int result = orderTradeMapper.updateByPrimaryKey(record);
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
	public PageInfo<OrderTrade> selectByPageInfo(Object example,
			PageInfo<OrderTrade> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页参数：" + pageInfo.toString());
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<OrderTrade> list = orderTradeMapper
				.selectByExample((OrderTradeCriteria) example);
		PageInfo<OrderTrade> page = new PageInfo<OrderTrade>(list);
		pageInfo.setList(list);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setEndRow(page.getEndRow());
		pageInfo.setStartRow(page.getStartRow());
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页结束");
		return pageInfo;
	}

	@Override
	public PageInfo<OrderTrade> selectByUserId(String userId, String status,
			PageInfo<OrderTrade> pageInfo) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询我的订单开始");
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<OrderTrade> list = orderTradeMapper.selectByUserId(userId, status);
		PageInfo<OrderTrade> page = new PageInfo<OrderTrade>(list);
		pageInfo.setList(list);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setEndRow(page.getEndRow());
		pageInfo.setStartRow(page.getStartRow());
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询我的订单结束");
		return pageInfo;
	}

	@Cacheable(key = "#productId+#status+'_orderTrade_Count_By_ProductId'", value = "OrderTradeCountByPID")
	@Override
	public Integer selectCountByProductId(String productId, String status) {
		OrderTradeCriteria orderTradeCriteria = new OrderTradeCriteria();
		Criteria criteria = orderTradeCriteria.createCriteria();
		criteria.andProductIdEqualTo(productId);
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		int count = orderTradeMapper.countByExample(orderTradeCriteria);
		return count;
	}

	@Cacheable(key = "#shopId+#status+'_orderTrade_Count_By_ShopId'", value = "OrderTradeCountByShopId")
	@Override
	public Integer selectCountByShopId(String shopId, String status) {
		OrderTradeCriteria orderTradeCriteria = new OrderTradeCriteria();
		Criteria criteria = orderTradeCriteria.createCriteria();
		criteria.andShopIdEqualTo(shopId);
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		int count = orderTradeMapper.countByExample(orderTradeCriteria);
		return count;
	}

	/*
	 * SELECT ot.* FROM order_trade ot LEFT JOIN pr_product product ON
	 * ot.PRODUCT_ID = product.id LEFT JOIN tb_user user ON product.USER_ID =
	 * user.ID LEFT JOIN tb_user_group userGroup ON user.USERGROUP_ID =
	 * userGroup.id left join pr_supplier supplier on product.SUPPLIER_ID =
	 * supplier.id WHERE userGroup.NAME = '管理员组' order by product.id desc;
	 */
}
