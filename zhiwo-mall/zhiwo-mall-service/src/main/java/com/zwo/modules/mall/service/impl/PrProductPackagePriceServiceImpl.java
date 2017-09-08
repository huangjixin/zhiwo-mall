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
import com.zwo.modules.mall.dao.PrProductPackagePriceMapper;
import com.zwo.modules.mall.domain.PrProductPackagePrice;
import com.zwo.modules.mall.domain.PrProductPackagePriceCriteria;
import com.zwo.modules.mall.service.IPrProductPackagePriceService;
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
public class PrProductPackagePriceServiceImpl extends BaseService<PrProductPackagePrice> implements IPrProductPackagePriceService {
	private static Logger logger = LoggerFactory.getLogger(PrProductPackagePriceServiceImpl.class);

	private static final String BASE_MESSAGE = "【PrProductPackagePriceServiceImpl服务类提供的基础操作增删改查等】";
	private static final String KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES = "_Product_PrProductPackagePrices";
	private static final String KEY_VALUED_LAST_PRPRODUCT_PACKAGEPRICES = "_valueId_PrProductPackagePrices";

	@Autowired
	@Lazy(true)
	private PrProductPackagePriceMapper productPackagePriceMapper;

	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;
	
	@Override
	public Mapper<PrProductPackagePrice> getBaseMapper() {
		return productPackagePriceMapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*
	 * @Override public int insertBatch(List<PrProductPackagePrice> list) { // TODO
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
	@CacheEvict(value = {"PrProductPackagePrice","PrProductPackagePrices"}, allEntries = true)
	public int deleteByExample(Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除开始");
		List<PrProductPackagePrice> list = this.selectByExample(example);
		for (PrProductPackagePrice prProductPackagePrice : list) {
			removeRedisKey(prProductPackagePrice.getProductId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
			removeRedisKey(prProductPackagePrice.getPropertyValueId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
		}
		
		
		// 逻辑操作
		int result = productPackagePriceMapper.deleteByExample(example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

	@CacheEvict(value = {"PrProductPackagePrice","PrProductPackagePrices"}, allEntries = true)
//	@Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		PrProductPackagePriceCriteria productPackagePriceCriteria = new PrProductPackagePriceCriteria();
		productPackagePriceCriteria.createCriteria().andIdIn(list);
		List<PrProductPackagePrice> prices = this.selectByExample(productPackagePriceCriteria);
		for (PrProductPackagePrice prProductPackagePrice : prices) {
			removeRedisKey(prProductPackagePrice.getProductId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
			removeRedisKey(prProductPackagePrice.getPropertyValueId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
		}
		
		int result = productPackagePriceMapper.deleteByExample(productPackagePriceCriteria);

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
	@CacheEvict(value = {"PrProductPackagePrice","PrProductPackagePrices"},key="#id+''")
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为：" + id.toString());
		PrProductPackagePrice prProductPackagePrice = selectByPrimaryKey(id);
		removeRedisKey(prProductPackagePrice.getProductId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
		removeRedisKey(prProductPackagePrice.getPropertyValueId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
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
//	@CachePut(value = {"PrProductPackagePrice","PrProductPackagePrices"}, key = "#record.id+'_PrProductPackagePrice'")
	public int insert(PrProductPackagePrice record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		removeRedisKey(record.getProductId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
		removeRedisKey(record.getPropertyValueId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
		
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
//	@CachePut(value = {"PrProductPackagePrice","PrProductPackagePrices"}, key = "#record.id+'_PrProductPackagePrice'")
	public int insertSelective(PrProductPackagePrice record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		removeRedisKey(record.getProductId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
		removeRedisKey(record.getPropertyValueId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
		
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
	public List<PrProductPackagePrice> selectByExample(Object example) {
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
	@Cacheable(key = "#id+''", value = {"PrProductPackagePrice","PrProductPackagePrices"})
	@Transactional(readOnly = true)
	public PrProductPackagePrice selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		PrProductPackagePrice productPackagePrice = super.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return productPackagePrice;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@CacheEvict(value = {"PrProductPackagePrice","PrProductPackagePrices"}, allEntries = true)
	@Override
	public int updateByExampleSelective(PrProductPackagePrice record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为：" + record.toString());
		List<PrProductPackagePrice> list = this.selectByExample(example);
		for (PrProductPackagePrice prProductPackagePrice : list) {
			removeRedisKey(prProductPackagePrice.getProductId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
			removeRedisKey(prProductPackagePrice.getPropertyValueId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
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
	@CacheEvict(value = {"PrProductPackagePrice","PrProductPackagePrices"}, allEntries = true)
	public int updateByExample(PrProductPackagePrice record, Object example) {
		//日志记录
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample更新开始");
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample更新对象为：" + record.toString());
		List<PrProductPackagePrice> list = this.selectByExample(example);
		for (PrProductPackagePrice prProductPackagePrice : list) {
			removeRedisKey(prProductPackagePrice.getProductId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
			removeRedisKey(prProductPackagePrice.getPropertyValueId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
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
	@CacheEvict(value = {"PrProductPackagePrice","PrProductPackagePrices"},key="#record.id+'_PrProductPackagePrice'")
	public int updateByPrimaryKeySelective(PrProductPackagePrice record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为：" + record.toString());
		removeRedisKey(record.getProductId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
		removeRedisKey(record.getPropertyValueId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
	
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
	@CacheEvict(value = {"PrProductPackagePrice"},key="#record.id+'_PrProductPackagePrice'")
	public int updateByPrimaryKey(PrProductPackagePrice record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新对象为：" + record.toString());
		removeRedisKey(record.getProductId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
		removeRedisKey(record.getPropertyValueId()+KEY_PROD_LAST_PRPRODUCT_PACKAGEPRICES);
	
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
	public PageInfo<PrProductPackagePrice> selectByPageInfo(Object example, PageInfo<PrProductPackagePrice> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页参数：" + pageInfo.toString());
		pageInfo = super.selectByPageInfo(example, pageInfo);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页结束");
		return pageInfo;
	}

	@Cacheable(key = "#pId+'_Product_PrProductPackagePrices'", value = "PrProductPackagePrices")
	@Transactional(readOnly = true)
	@Override
	public List<PrProductPackagePrice> selectByProductId(String pId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据商品Id查询开始");
		PrProductPackagePriceCriteria packagePriceCriteria = new PrProductPackagePriceCriteria();
		packagePriceCriteria.createCriteria().andProductIdEqualTo(pId);
		List<PrProductPackagePrice> packagePrices =  this.productPackagePriceMapper.selectByExample(packagePriceCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据商品Id查询结束，结果条目数为："+packagePrices.size());
		return packagePrices;
	}

	@Override
	@CacheEvict(value = {"PrProductPackagePrices"},key="#pId+'_Product_PrProductPackagePrices'",allEntries=true)
	public void deleteByProductId(String pId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据商品Id删除开始");
		PrProductPackagePriceCriteria packagePriceCriteria = new PrProductPackagePriceCriteria();
		packagePriceCriteria.createCriteria().andProductIdEqualTo(pId);
		int result =  this.productPackagePriceMapper.deleteByExample(packagePriceCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "根据商品Id删除结束，结果条目数为："+result);
	}

	@Override
	@Cacheable(key = "#pId+'_valueId_PrProductPackagePrices'", value = "PrProductPackagePrice")
	public PrProductPackagePrice selectByPropertyValueId(String valueId) {
		PrProductPackagePriceCriteria packagePriceCriteria = new PrProductPackagePriceCriteria();
		packagePriceCriteria.createCriteria().andPropertyValueIdEqualTo(valueId);
		List<PrProductPackagePrice> list = productPackagePriceMapper.selectByExample(packagePriceCriteria);
		return list==null||list.isEmpty()?null:list.get(0);
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
