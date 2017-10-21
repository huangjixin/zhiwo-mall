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
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.zwo.modules.mall.dao.PrProductPropertyMapper;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.domain.PrProductCriteria;
import com.zwo.modules.mall.domain.PrProductProperty;
import com.zwo.modules.mall.domain.PrProductPropertyCriteria;
import com.zwo.modules.mall.service.IPrProductPropertyService;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.modules.core.service.impl.BaseService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author hjx
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class PrProductPropertyServiceImpl extends BaseService<PrProductProperty> implements IPrProductPropertyService {
	private static Logger logger = LoggerFactory.getLogger(PrProductPropertyServiceImpl.class);

	private static final String BASE_MESSAGE = "【PrProductPropertyServiceImpl服务类提供的基础操作增删改查等】";
	public static final String LIST_ALL_PRODUCT_PROPERTY =  "listAllProductProperty";
	
	public static final String KEY_PRPRODUCTPROPERTY = "_key_PrProductProperty";
	
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;
	
	@Autowired
	@Lazy(true)
	private PrProductPropertyMapper productPropertyMapper;

	public PrProductPropertyServiceImpl() {
		super();
		if (SpringContextHolder.getApplicationContext().containsBean(
				"redisTemplate")) {
			redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}
	}

	@Override
	public Mapper<PrProductProperty> getBaseMapper() {
		return productPropertyMapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*
	 * @Override public int insertBatch(List<PrProductProperty> list) { // TODO
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
		List<PrProductProperty> list = this.productPropertyMapper
				.selectByExample(example);
		for (PrProductProperty productProperty : list) {
			removeRedisKey(productProperty.getId() + KEY_PRPRODUCTPROPERTY);
		}
		// 逻辑操作
		int result = productPropertyMapper.deleteByExample(example);

		removeRedisKey(LIST_ALL_PRODUCT_PROPERTY);
		
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

//	@CacheEvict(value = {"PrProductProperty"}, allEntries = true)
//	@Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		PrProductPropertyCriteria productPropertyCriteria = new PrProductPropertyCriteria();
		productPropertyCriteria.createCriteria().andIdIn(list);
		//移除缓存
		List<PrProductProperty> productProperties = this.productPropertyMapper
				.selectByExample(productPropertyCriteria);
		for (PrProductProperty productProperty : productProperties) {
			removeRedisKey(productProperty.getId() + KEY_PRPRODUCTPROPERTY);
		}
		
		int result = productPropertyMapper.deleteByExample(productPropertyCriteria);
		removeRedisKey(LIST_ALL_PRODUCT_PROPERTY);
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
	@CacheEvict(value = {"PrProductProperty"},key="#id+'_key_PrProductProperty'")
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为：" + id.toString());
		removeRedisKey(LIST_ALL_PRODUCT_PROPERTY);
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
//	@CachePut(value = {"PrProductProperty"}, key = "#record.id+'productProperty'")
	public int insert(PrProductProperty record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		removeRedisKey(LIST_ALL_PRODUCT_PROPERTY);
		
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
//	@CachePut(value = {"PrProductProperty"}, key = "#record.id+'productProperty'")
	public int insertSelective(PrProductProperty record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		removeRedisKey(LIST_ALL_PRODUCT_PROPERTY);
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
	public List<PrProductProperty> selectByExample(Object example) {
		return this.productPropertyMapper.selectByExample(example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(key = "#id+'_key_PrProductProperty'", value = {"PrProductProperty"})
	@Transactional(readOnly = true)
	public PrProductProperty selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		PrProductProperty productProperty = super.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return productProperty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@CacheEvict(value = {"PrProductProperty"}, allEntries = true)
	@Override
	public int updateByExampleSelective(PrProductProperty record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为：" + record.toString());
		List<PrProductProperty> list = this.productPropertyMapper
				.selectByExample(example);
		for (PrProductProperty productProperty : list) {
			removeRedisKey(productProperty.getId() + KEY_PRPRODUCTPROPERTY);
		}
		removeRedisKey(LIST_ALL_PRODUCT_PROPERTY);
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
	@CacheEvict(value = {"PrProductProperty"}, allEntries = true)
	public int updateByExample(PrProductProperty record, Object example) {
		//日志记录
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample更新开始");
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample更新对象为：" + record.toString());
		List<PrProductProperty> list = this.productPropertyMapper
				.selectByExample(example);
		for (PrProductProperty productProperty : list) {
			removeRedisKey(productProperty.getId() + KEY_PRPRODUCTPROPERTY);
		}
		removeRedisKey(LIST_ALL_PRODUCT_PROPERTY);								
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
	@CacheEvict(value = {"PrProductProperty"},key="#record.id+'_key_PrProductProperty'")
	public int updateByPrimaryKeySelective(PrProductProperty record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为：" + record.toString());
		removeRedisKey(LIST_ALL_PRODUCT_PROPERTY);
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
	@CacheEvict(value = {"PrProductProperty"},key="#record.id+'_key_PrProductProperty'")
	public int updateByPrimaryKey(PrProductProperty record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新对象为：" + record.toString());
		removeRedisKey(LIST_ALL_PRODUCT_PROPERTY);
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
	public PageInfo<PrProductProperty> selectByPageInfo(Object example, PageInfo<PrProductProperty> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页参数：" + pageInfo.toString());
		pageInfo = super.selectByPageInfo(example, pageInfo);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页结束");
		return pageInfo;
	}

	@Transactional(readOnly = true)
	@Override
	@Cacheable(value = "PrProductPropertys",key="'listAllProductProperty'")
	public List<PrProductProperty> listAll() {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询所有属性开始");
		PrProductPropertyCriteria productCriteria = null;
		productCriteria = new PrProductPropertyCriteria();
		productCriteria.setOrderByClause("id asc");
		List<PrProductProperty> properties = this.productPropertyMapper.selectByExample(productCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "查询所有属性结束，结果条目数为："+properties.size());
		return properties;
	}

	/**
	 * 移除key.
	 * @param key
	 */
	@SuppressWarnings("unchecked")
	private void removeRedisKey(String key){
		if (redisTemplate == null) {
			redisTemplate = SpringContextHolder
					.getBean("redisTemplate");
		}

		if (redisTemplate != null) {
			
			if (redisTemplate.hasKey(key)) {
				redisTemplate.delete(key);
			}
		}
	}
}
