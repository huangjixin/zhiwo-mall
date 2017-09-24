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
import com.zwo.modules.mall.dao.PrProductPropertyValueMapper;
import com.zwo.modules.mall.domain.PrProductPackagePrice;
import com.zwo.modules.mall.domain.PrProductPackagePriceCriteria;
import com.zwo.modules.mall.domain.PrProductPropertyValue;
import com.zwo.modules.mall.domain.PrProductPropertyValueCriteria;
import com.zwo.modules.mall.service.IPrProductPropertyValueService;
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
public class PrProductPropertyValueServiceImpl extends BaseService<PrProductPropertyValue> implements IPrProductPropertyValueService {
	private static Logger logger = LoggerFactory.getLogger(PrProductPropertyValueServiceImpl.class);

	private static final String BASE_MESSAGE = "【PrProductPropertyValueServiceImpl服务类提供的基础操作增删改查等】";

	public static final String KEY_PRODUCTPROPERTYVALUES = "_key_productPropertyValues";
	
	@Autowired
	@Lazy(true)
	private PrProductPropertyValueMapper productPropertyValueMapper;

	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;
	
	
	@Override
	public Mapper<PrProductPropertyValue> getBaseMapper() {
		return productPropertyValueMapper;
	}

	public PrProductPropertyValueServiceImpl() {
		super();
		if (SpringContextHolder.getApplicationContext().containsBean(
				"redisTemplate")) {
			redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*
	 * @Override public int insertBatch(List<PrProductPropertyValue> list) { // TODO
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
	@CacheEvict(value = {"PrProductPropertyValue","PrProductPropertyValues"}, allEntries = true)
	public int deleteByExample(Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除开始");
		List<PrProductPropertyValue>propertyValues = productPropertyValueMapper.selectByExample(example);
		for (PrProductPropertyValue prProductPropertyValue : propertyValues) {
			removeRedisKey(prProductPropertyValue.getProductId()+KEY_PRODUCTPROPERTYVALUES);
		}
		
		// 逻辑操作
		int result = productPropertyValueMapper.deleteByExample(example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

	@CacheEvict(value = {"PrProductPropertyValue","PrProductPropertyValues"}, allEntries = true)
//	@Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		PrProductPropertyValueCriteria productPropertyValueCriteria = new PrProductPropertyValueCriteria();
		productPropertyValueCriteria.createCriteria().andIdIn(list);
		List<PrProductPropertyValue>propertyValues = productPropertyValueMapper.selectByExample(productPropertyValueCriteria);
		for (PrProductPropertyValue prProductPropertyValue : propertyValues) {
			removeRedisKey(prProductPropertyValue.getProductId()+KEY_PRODUCTPROPERTYVALUES);
		}
		
		int result = productPropertyValueMapper.deleteByExample(productPropertyValueCriteria);

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
	@CacheEvict(value = {"PrProductPropertyValue","PrProductPropertyValues"},key="#id+'_key_productPropertyValues'")
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为：" + id.toString());
		removeRedisKey(id+KEY_PRODUCTPROPERTYVALUES);
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
//	@CachePut(value = {"PrProductPropertyValue","PrProductPropertyValues"}, key = "#record.id+'_key_productPropertyValues'")
	public int insert(PrProductPropertyValue record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		
		removeRedisKey(record.getProductId()+KEY_PRODUCTPROPERTYVALUES);
		
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
	@CachePut(value = {"PrProductPropertyValue"}, key = "#record.id+'_key_productPropertyValues'")
	public int insertSelective(PrProductPropertyValue record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		removeRedisKey(record.getProductId()+KEY_PRODUCTPROPERTYVALUES);
		
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
	public List<PrProductPropertyValue> selectByExample(Object example) {
		return super.selectByExample(example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(key = "#id+'_key_productPropertyValues'", value = {"PrProductPropertyValue","PrProductPropertyValues"})
	@Transactional(readOnly = true)
	public PrProductPropertyValue selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		PrProductPropertyValue productPropertyValue = super.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return productPropertyValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@CacheEvict(value = {"PrProductPropertyValue","PrProductPropertyValues"}, allEntries = true)
	@Override
	public int updateByExampleSelective(PrProductPropertyValue record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为：" + record.toString());
		List<PrProductPropertyValue>propertyValues = productPropertyValueMapper.selectByExample(example);
		for (PrProductPropertyValue prProductPropertyValue : propertyValues) {
			removeRedisKey(prProductPropertyValue.getProductId()+KEY_PRODUCTPROPERTYVALUES);
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
	@CacheEvict(value = {"PrProductPropertyValue","PrProductPropertyValues"}, allEntries = true)
	public int updateByExample(PrProductPropertyValue record, Object example) {
		//日志记录
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample更新开始");
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample更新对象为：" + record.toString());
		List<PrProductPropertyValue>propertyValues = productPropertyValueMapper.selectByExample(example);
		for (PrProductPropertyValue prProductPropertyValue : propertyValues) {
			removeRedisKey(prProductPropertyValue.getProductId()+KEY_PRODUCTPROPERTYVALUES);
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
	@CacheEvict(value = {"PrProductPropertyValue","PrProductPropertyValues"},key="#record.id+'_key_productPropertyValues'")
	public int updateByPrimaryKeySelective(PrProductPropertyValue record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为：" + record.toString());
		removeRedisKey(record.getProductId()+KEY_PRODUCTPROPERTYVALUES);
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
	@CacheEvict(value = {"PrProductPropertyValue","PrProductPropertyValues"},key="#record.id")
	public int updateByPrimaryKey(PrProductPropertyValue record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新对象为：" + record.toString());
		removeRedisKey(record.getProductId()+KEY_PRODUCTPROPERTYVALUES);
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
	public PageInfo<PrProductPropertyValue> selectByPageInfo(Object example, PageInfo<PrProductPropertyValue> pageInfo) {
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
		IPrProductPropertyValueService productPropertyValueServiceImpl = (IPrProductPropertyValueService) context.getBean("productPropertyValueServiceImpl");
		PrProductPropertyValue productPropertyValue = new PrProductPropertyValue();
		productPropertyValue.setId(System.currentTimeMillis() + "");
		int result = productPropertyValueServiceImpl.insertSelective(productPropertyValue);
		logger.info(result + "");
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable(key = "#pId+'_key_productPropertyValues'", value = "Product_PrProductPropertyValues")
	public List<PrProductPropertyValue> selectByProductId(String pId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据商品Id查询商品属性开始");
		PrProductPropertyValueCriteria propertyValueCriteria = new PrProductPropertyValueCriteria();
		propertyValueCriteria.createCriteria().andProductIdEqualTo(pId);
		List<PrProductPropertyValue> propertyValues =  this.productPropertyValueMapper.selectByExample(propertyValueCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据商品Id查询商品属性结束，结果条目数为："+propertyValues.size());
		return propertyValues;
	}

	@Override
	@CacheEvict(value = {"Product_PrProductPropertyValues"},key="#pId+'_key_productPropertyValues'",allEntries=true)
	public void deleteByProductId(String pId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据商品Id删除商品属性开始");
		PrProductPropertyValueCriteria propertyValueCriteria = new PrProductPropertyValueCriteria();
		propertyValueCriteria.createCriteria().andProductIdEqualTo(pId);
		int result = this.productPropertyValueMapper.deleteByExample(propertyValueCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据商品Id删除商品属性结束，结果条目数为："+result);
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
