/**
 * 
 */
package com.zwo.modules.mall.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.HtmlUtils;

import tk.mybatis.mapper.common.Mapper;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwo.modules.mall.dao.PrImageMapper;
import com.zwo.modules.mall.dao.PrProductMapper;
import com.zwo.modules.mall.dao.PrProductPackagePriceMapper;
import com.zwo.modules.mall.dao.PrProductPropertyValueMapper;
import com.zwo.modules.mall.domain.PrImage;
import com.zwo.modules.mall.domain.PrImageType;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.domain.PrProductCriteria;
import com.zwo.modules.mall.domain.PrProductPackagePrice;
import com.zwo.modules.mall.domain.PrProductProperty;
import com.zwo.modules.mall.domain.PrProductPropertyValue;
import com.zwo.modules.mall.domain.PrProductWithBLOBs;
import com.zwo.modules.mall.service.IPrImageService;
import com.zwo.modules.mall.service.IPrProductPackagePriceService;
import com.zwo.modules.mall.service.IPrProductPropertyService;
import com.zwo.modules.mall.service.IPrProductPropertyValueService;
import com.zwo.modules.mall.service.IPrductService;
import com.zwo.modules.webconfig.domain.WebShopConfig;
import com.zwotech.common.redis.channel.ChannelContance;
import com.zwotech.common.redis.channel.RedisPushMessage;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.modules.core.service.impl.BaseService;

/**
 * @author hjx
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class ProductServiceImpl extends BaseService<PrProduct> implements
		IPrductService {
	private static Logger logger = LoggerFactory
			.getLogger(ProductServiceImpl.class);

	private static final String BASE_MESSAGE = "【PrProductServiceImpl服务类提供的基础操作增删改查等】";

	private static final String KEY_PRODUCT_WITHOUT_BLOB = "_product_withoutBlob";
	private static final String KEY_SHOPID_PRODUCT = "_key_shopId_product";
	private static final String KEY_SHOPID_PRODUCT_COUNT = "_key_shopId_product_Count";
	private static final String KEY_GROUP_PURCSE = "_key_GroupPurcse";

	@Autowired
	@Lazy(true)
	private PrProductMapper productMapper;

	@Autowired
	@Lazy(true)
	private PrImageMapper imageMapper;

	@Autowired
	@Lazy(true)
	private WebShopConfig webShopConfig;
	
	@Autowired
	@Lazy(true)
	private PrProductPropertyValueMapper productPropertyValueMapper;

	@Autowired
	@Lazy(true)
	private PrProductPackagePriceMapper productPackagePriceMapper;

	@Autowired
	@Lazy(true)
	private IPrProductPropertyValueService productPropertyValueService;
	@Autowired
	@Lazy(true)
	private IPrProductPropertyService productPropertyService;
	@Autowired
	@Lazy(true)
	private IPrProductPackagePriceService packagePriceService;

	@Autowired
	@Lazy(true)
	private IPrImageService imageService;

	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;

	@Override
	public Mapper<PrProduct> getBaseMapper() {
		return this.productMapper;
	}

	public ProductServiceImpl() {
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
	 * @Override public int insertBatch(List<PrProduct> list) { Auto-generated
	 * method stub return 0; }
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#deleteByExample(java.lang.
	 * Object)
	 */
	@Override
	@CacheEvict(value = "PrProduct", allEntries = true)
	public int deleteByExample(Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除开始");
		List<PrProduct> list = this.productMapper
				.selectByExample((PrProductCriteria) example);
		for (PrProduct prProduct : list) {
			removeRedisKey(prProduct.getShopId() + KEY_SHOPID_PRODUCT);
			removeRedisKey(prProduct.getShopId() + KEY_SHOPID_PRODUCT_COUNT);

			removeRedisKey(prProduct.getId() + KEY_GROUP_PURCSE);
			removeRedisKey(prProduct.getId() + KEY_PRODUCT_WITHOUT_BLOB);

			RedisPushMessage.publish(redisTemplate,
					ChannelContance.PRODUCT_DELETE_TOPIC_CHANNEL,
					prProduct.getId());
		}
		// 逻辑操作
		int result = productMapper.deleteByExample((PrProductCriteria) example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

	@SuppressWarnings("unchecked")
	@CacheEvict(value = "PrProduct", allEntries = true)
	// @Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		PrProductCriteria productCriteria = new PrProductCriteria();
		productCriteria.createCriteria().andIdIn(list);

		List<PrProduct> products = this.productMapper
				.selectByExample(productCriteria);
		for (PrProduct prProduct : products) {
			removeRedisKey(prProduct.getShopId() + KEY_SHOPID_PRODUCT);
			removeRedisKey(prProduct.getShopId() + KEY_SHOPID_PRODUCT_COUNT);

			String key = prProduct.getId() + "_" + PrImageType.DETAIL
					+ "_prImages";
			removeRedisKey(key);
			key = prProduct.getId() + "_" + PrImageType.PROP + "_prImages";
			removeRedisKey(key);
			key = prProduct.getId() + "_" + PrImageType.THUMBNAIL + "_prImages";
			removeRedisKey(key);

			removeRedisKey(prProduct.getId() + KEY_GROUP_PURCSE);
			removeRedisKey(prProduct.getId() + KEY_PRODUCT_WITHOUT_BLOB);

			// 发布删除页面的通知。
			RedisPushMessage.publish(redisTemplate,
					ChannelContance.PRODUCT_DELETE_TOPIC_CHANNEL,
					prProduct.getId());
		}

		int result = productMapper.deleteByExample(productCriteria);

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
	@CacheEvict(value = "PrProduct", key = "#id+'_product'")
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为："
					+ id.toString());
		PrProduct prProduct = selectByPrimKey(id);
		removeRedisKey(prProduct.getShopId() + KEY_SHOPID_PRODUCT);
		removeRedisKey(prProduct.getShopId() + KEY_SHOPID_PRODUCT_COUNT);

		String key = prProduct.getId() + "_" + PrImageType.DETAIL + "_prImages";
		removeRedisKey(key);
		key = prProduct.getId() + "_" + PrImageType.PROP + "_prImages";
		removeRedisKey(key);
		key = prProduct.getId() + "_" + PrImageType.THUMBNAIL + "_prImages";
		removeRedisKey(key);

		removeRedisKey(prProduct.getId() + KEY_GROUP_PURCSE);
		removeRedisKey(prProduct.getId() + KEY_PRODUCT_WITHOUT_BLOB);
		// 逻辑操作
		int result = this.productMapper.deleteByPrimaryKey(id);
		// 发布删除页面的通知。
		RedisPushMessage
				.publish(redisTemplate,
						ChannelContance.PRODUCT_DELETE_TOPIC_CHANNEL,
						prProduct.getId());

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
	@CachePut(value = "PrProduct", key = "#record.id+'_product'")
	public int insert(PrProduct record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());

		PrProduct prProduct = record;
		removeRedisKey(prProduct.getShopId() + KEY_SHOPID_PRODUCT);
		removeRedisKey(prProduct.getShopId() + KEY_SHOPID_PRODUCT_COUNT);

		if (null != record.getContent() && !"".equals(record.getContent())) {
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}
		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + ""
					+ Math.round(Math.random() * 99));
		}
		int result = this.productMapper.insert(record);
		// 发布生成页面的通知。
		RedisPushMessage.publish(redisTemplate,
				ChannelContance.PRODUCT_GENERATION_TOPIC_CHANNEL,
				record.getId());
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
	@CachePut(value = "PrProduct", key = "#record.id+'_product'")
	public int insertSelective(PrProduct record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insertSelective插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insertSelective插入对象为："
					+ record.toString());
		PrProduct prProduct = record;
		removeRedisKey(prProduct.getShopId() + KEY_SHOPID_PRODUCT);
		removeRedisKey(prProduct.getShopId() + KEY_SHOPID_PRODUCT_COUNT);

		if (null != record.getContent() && !"".equals(record.getContent())) {
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}

		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + ""
					+ Math.round(Math.random() * 99));
		}
		int result = this.productMapper.insertSelective(record);
		// 发布生成页面的通知。
		RedisPushMessage.publish(redisTemplate,
				ChannelContance.PRODUCT_GENERATION_TOPIC_CHANNEL,
				record.getId());
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insertSelective插入结束");

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
	public List<PrProduct> selectByExample(Object example) {
		return productMapper.selectByExample((PrProductCriteria) example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExample(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	public int updateByExample(PrProduct record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleWithBLOBs更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleWithBLOBs更新条件对象为："
					+ record.toString());

		List<PrProduct> products = this.productMapper.selectByExample(example);
		for (PrProduct prProduct : products) {
			removeRedisKey(prProduct.getShopId() + KEY_SHOPID_PRODUCT);
			removeRedisKey(prProduct.getShopId() + KEY_SHOPID_PRODUCT_COUNT);

			removeRedisKey(prProduct.getId() + KEY_PRODUCT_WITHOUT_BLOB);
			// 发布删除页面的通知。
			RedisPushMessage.publish(redisTemplate,
					ChannelContance.PRODUCT_DELETE_TOPIC_CHANNEL,
					prProduct.getId());
		}

		if (null != record.getContent() && !"".equals(record.getContent())) {
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}
		// 逻辑操作
		int result = this.productMapper.updateByExample(record, example);
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleWithBLOBs更新结束");
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
	@CacheEvict(value = "PrProduct", key = "#record.id+'_product'")
	public int updateByPrimaryKeySelective(PrProduct record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为："
					+ record.toString());

		removeRedisKey(record.getShopId() + KEY_SHOPID_PRODUCT);
		removeRedisKey(record.getShopId() + KEY_SHOPID_PRODUCT_COUNT);
		removeRedisKey(record.getId() + KEY_PRODUCT_WITHOUT_BLOB);

		if (null != record.getContent() && !"".equals(record.getContent())) {
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}
		// 逻辑操作
		int result = this.productMapper.updateByPrimaryKeySelective(record);
		// 发布生成页面的通知。
		RedisPushMessage.publish(redisTemplate,
				ChannelContance.PRODUCT_GENERATION_TOPIC_CHANNEL,
				record.getId());
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
	@CachePut(value = "PrProduct", key = "#record.id+'_product'")
	public int updateByPrimaryKey(PrProduct record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeyWithBLOBs更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeyWithBLOBs更新对象为："
					+ record.toString());
		removeRedisKey(record.getShopId() + KEY_SHOPID_PRODUCT);
		removeRedisKey(record.getId() + KEY_PRODUCT_WITHOUT_BLOB);

		if (null != record.getContent() && !"".equals(record.getContent())) {
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}
		// 逻辑操作
		int result = this.productMapper.updateByPrimaryKey(record);
		// 发布生成页面的通知。
		RedisPushMessage.publish(redisTemplate,
				ChannelContance.PRODUCT_GENERATION_TOPIC_CHANNEL,
				record.getId());
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeyWithBLOBs更新结束");
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
	public PageInfo<PrProduct> selectByPageInfo(Object example,
			PageInfo<PrProduct> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页参数：" + pageInfo.toString());
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<PrProduct> list = this.productMapper
				.selectByExample((PrProductCriteria) example);
		PageInfo<PrProduct> page = new PageInfo<PrProduct>(list);
		pageInfo.setList(list);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setEndRow(page.getEndRow());
		pageInfo.setStartRow(page.getStartRow());
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页结束");
		return pageInfo;
	}

	public int countByExample(PrProductCriteria example) {
		return this.productMapper.selectCountByExample(example);
	}

	@Cacheable(key = "#id+'_product'", value = "PrProduct")
	@Transactional(readOnly = true)
	@Override
	public PrProduct selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		PrProduct product = this.productMapper.selectByPrimaryKey(id);
		if (null != product && null != product.getContent()
				&& !"".equals(product.getContent())) {
			String content = product.getContent();
			content = HtmlUtils.htmlUnescape(content);
			product.setContent(content);
		}
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return product;
	}
	
	@Cacheable(key = "#id+'_product'", value = "PrProduct")
	@Transactional(readOnly = true)
	public PrProduct selectByPrimKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);
		
		// 逻辑操作
		PrProduct product = this.productMapper.selectByPrimaryKey(id);
		if (null != product && null != product.getContent()
				&& !"".equals(product.getContent())) {
			String content = product.getContent();
			content = HtmlUtils.htmlUnescape(content);
			product.setContent(content);
		}
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return product;
	}

	public int updateByExampleSelective(PrProduct record,
			PrProductCriteria example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为："
					+ record.toString());

		// 逻辑操作

		List<PrProduct> products = this.productMapper.selectByExample(example);
		for (PrProduct prProduct : products) {
			removeRedisKey(prProduct.getShopId() + KEY_SHOPID_PRODUCT);
			// 发布删除页面的通知。
			RedisPushMessage.publish(redisTemplate,
					ChannelContance.PRODUCT_DELETE_TOPIC_CHANNEL,
					prProduct.getId());
		}

		if (null != record.getContent() && !"".equals(record.getContent())) {
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}
		// 逻辑操作
		int result = this.productMapper.updateByExampleSelective(record,
				example);
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新结束");
		return result;
	}

	public int updateByExample(PrProduct record, PrProductCriteria example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新对象为："
					+ record.toString());

		List<PrProduct> products = this.productMapper.selectByExample(example);
		for (PrProduct prProduct : products) {
			removeRedisKey(prProduct.getShopId() + KEY_SHOPID_PRODUCT);
			removeRedisKey(prProduct.getShopId() + KEY_SHOPID_PRODUCT_COUNT);
		}

		// 逻辑操作
		int result = this.productMapper.updateByExample(record, example);
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新结束");
		return result;
	}

	@Transactional(readOnly = true)
	@Override
	@Cacheable(value = "ShopPrProducts", key = "#shopId+'_key_shopId_product'")
	public List<PrProduct> selectPrProductsByShopId(String shopId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE
					+ "selectPrProductsByShopId根据商铺Id查询商品开始，商铺ID为：" + shopId);
		PrProductCriteria productCriteria = new PrProductCriteria();
		productCriteria.createCriteria().andShopIdEqualTo(shopId);
		List<PrProduct> list = this.productMapper
				.selectByExample(productCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE
					+ "selectPrProductsByShopId根据商铺Id查询商品结束，数量为：" + list.size());
		return list;
	}

	@Transactional(readOnly = true)
	@Override
	@Cacheable(value = "ShopPrProductsCount", key = "#shopId+'_key_shopId_product_Count'")
	public Integer selectPrProductsCountByShopId(String shopId) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE
					+ "selectPrProductsByShopId根据商铺Id查询商品数量开始，商铺ID为：" + shopId);
		PrProductCriteria productCriteria = new PrProductCriteria();
		productCriteria.createCriteria().andShopIdEqualTo(shopId);
		int count = this.productMapper.selectCountByExample(productCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE
					+ "selectPrProductsByShopId根据商铺Id查询商品结束，数量为：" + count);
		return count;
	}

	/**
	 * 移除key.
	 * 
	 * @param key
	 */
	@SuppressWarnings("unchecked")
	private void removeRedisKey(String key) {
		if (redisTemplate == null) {
			redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}

		if (redisTemplate != null) {
			if (redisTemplate.hasKey(key)) {
				redisTemplate.delete(key);
			}
		}
	}

	/*
	 * 进入分类的商品必须是可用，库存大于0，上架状态。 (non-Javadoc)
	 * 
	 * @see
	 * com.zwo.modules.mall.service.IPrductService#selectByCategoryIdPageInfo
	 * (java.lang.String, com.github.pagehelper.PageInfo)
	 */
	@Override
	@Transactional(readOnly = true)
	public PageInfo<PrProduct> selectByCategoryIdPageInfo(String categoryId,
			PageInfo<PrProduct> pageInfo) {
		PrProductCriteria productCriteria = new PrProductCriteria();
		productCriteria.createCriteria().andCategoryIdEqualTo(categoryId)
				.andDisableEqualTo(false).andStorageGreaterThan(0)
				.andStatusEqualTo("online");
		productCriteria.setOrderByClause("create_date desc");
		PageInfo<PrProduct> info = selectByPageInfo(productCriteria, pageInfo);
		return info;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PrProduct> selectAllByStatus(String status) {
		PrProductCriteria productCriteria = new PrProductCriteria();
		productCriteria.createCriteria().andStatusEqualTo(status);
		productCriteria.setOrderByClause("create_date asc");
		List<PrProduct> list = this.productMapper
				.selectByExample(productCriteria);
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public PageInfo<PrProduct> selectAllByStatus(String status,
			PageInfo<PrProduct> pageInfo) {
		PrProductCriteria productCriteria = new PrProductCriteria();
		productCriteria.createCriteria().andStatusEqualTo(status);
		productCriteria.setOrderByClause("create_date desc");
		PageInfo<PrProduct> info = selectByPageInfo(productCriteria, pageInfo);
		return info;
	}

	/*
	 * 进入首页的商品必须是可用，库存大于0，上架状态。 (non-Javadoc). (non-Javadoc)
	 * 
	 * @see
	 * com.zwo.modules.mall.service.IPrductService#selectIndex(com.github.pagehelper
	 * .PageInfo)
	 */
	@Override
	public PageInfo<PrProduct> selectIndex(PageInfo<PrProduct> pageInfo) {
		PrProductCriteria productCriteria = new PrProductCriteria();
		productCriteria.createCriteria().andDisableEqualTo(false)
				.andStorageGreaterThan(0).andStatusEqualTo("online");
		productCriteria.setOrderByClause("create_date desc");
		PageInfo<PrProduct> info = selectByPageInfo(productCriteria, pageInfo);
		return info;
	}

	@Override
	public PrProduct fetchAlibabaGoods(String url) throws Exception {
		Document document = null;
		PrProduct product = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String title = document.title();
		Element des = document.select("meta[name=description]").first();
		String description = des.attr("content");
		title = title.replace(" - 阿里巴巴", "");// 抓取商品的标题，去掉阿里巴巴的标识。
		String icon = "";//自动提取第一张作为缩略图。
		Elements devalues = document.select("td[class=de-value]");
		String marketPrice = "0.0";
		if(devalues!=null && devalues.size()>0){
			marketPrice =  devalues.first().html();
			if(marketPrice.indexOf("&yen;")==-1){
				marketPrice = "0.0";
			}else{
				marketPrice = marketPrice.replace("&yen;", "");
			}
		}
		
		// 查找相对应的脚本，该脚本包含了阿里巴巴的SKU属性。
		Elements scriptElements = document.select("script[type=text/javascript]");
		if (scriptElements != null && scriptElements.size() > 0) {
			Element element = null;
			for (int i = 0; i < scriptElements.size(); i++) {
				Element ele = scriptElements.get(i);
				String html = ele.html();
				int index = html.indexOf("iDetailData");
				if (index != -1) {
					element = ele;
					break;
				}
			}
			if(element == null){
				scriptElements = document.select("script[type=text/ javascript]");
				
			}

			if (element != null) {
				String js = element.html();

				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine engine = manager.getEngineByName("javascript");
				try {
					engine.eval(js);
					ScriptObjectMirror iDetailConfig = (ScriptObjectMirror) engine
							.get("iDetailConfig");
					ScriptObjectMirror c = (ScriptObjectMirror) engine
							.get("iDetailData");
					ScriptObjectMirror sku = (ScriptObjectMirror) c.get("sku");

					// sku属性。
					ScriptObjectMirror skuProps = (ScriptObjectMirror) sku
							.get("skuProps");
					// 这个好像Map对象。
					ScriptObjectMirror skuMapProps = (ScriptObjectMirror) sku
							.get("skuMap");
					String refPrice = (String) iDetailConfig.get("refPrice");
					String groupPurcseNum =  (String) iDetailConfig.get("beginAmount");
					String canBookCount = (String) sku.get("canBookCount");
					Object m = iDetailConfig.getMember("sku");
					
					// 商品属性。
					List<PrProductProperty> properties = productPropertyService
							.listAll();
					product = new PrProduct();
					String id = UUID.randomUUID().toString()
							.replaceAll("-", "");
					product.setId(id);
					product.setName(title);
					product.setDescription(description);
					this.insertSelective(product);

					//保存属性值数组。
					List<PrProductPropertyValue> propertyValueArray = new ArrayList<PrProductPropertyValue>();
					List<PrProductProperty> propertyArray = new ArrayList<PrProductProperty>();
					
					List pValueList = new ArrayList();
					int i = 0;
					while (skuProps.hasSlot(i)) {
						ScriptObjectMirror o = (ScriptObjectMirror) skuProps
								.getSlot(i);
						String pName = (String) o.get("prop");
						PrProductProperty property = null;
						for (PrProductProperty prProductProperty : properties) {
							if (pName.indexOf(prProductProperty.getName())!=-1) {
								property = prProductProperty;
								break;
							}
						}

						if (property != null) {
							List<String> pValueIdList = new ArrayList<String>();
							
							propertyArray.add(property);
							
							ScriptObjectMirror value = (ScriptObjectMirror) o.get("value");
							// 遍历属性值开始；
							int j = 0;
							String[] pvalueIds = new String[value.size()];
							while (value.hasSlot(j)) {
								ScriptObjectMirror valueItem = (ScriptObjectMirror) value
										.getSlot(j);
								String name = (String) valueItem.get("name");
								PrProductPropertyValue productPropertyValue = new PrProductPropertyValue();
								productPropertyValue.setId(UUID.randomUUID().toString().replaceAll("-", ""));
								productPropertyValue.setProductId(product.getId());
								productPropertyValue.setPropertyId(property.getId());
								productPropertyValue.setName(name);
								
								// 属性的URL。
								String imageUrl = (String) valueItem.get("imageUrl");
								if (null != imageUrl && !"".equals(imageUrl)) {
									HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
											.getRequestAttributes())
											.getRequest();
									download(request, product.getId(),
											PrImageType.PROP, imageUrl);
								}
								this.productPropertyValueService.insertSelective(productPropertyValue);

								pvalueIds[j]=productPropertyValue.getId();
								
								j++;
								
								propertyValueArray.add(productPropertyValue);
							}
							
							pValueList.add(pvalueIds);
						}
						i++;
					}
					
					for (i = 0; i < propertyValueArray.size(); i++) {
						//只有一种属性
						if (propertyArray.size() == 1) {
							PrProductPackagePrice price1 = new PrProductPackagePrice();
							double gPrice = Math.round(Double.valueOf(webShopConfig.getProGroupPrice())*Double.valueOf(refPrice));
							double iPrice = Math.round(Double.valueOf(webShopConfig.getProIndepentPrice())*Double.valueOf(refPrice));
							price1.setGourpPrice(gPrice+"");
							price1.setIndependentPrice(iPrice+"");
							price1.setId(UUID.randomUUID().toString().replaceAll("-", ""));
							price1.setProductId(product.getId());
							PrProductPropertyValue pValue = propertyValueArray.get(i);
							price1.setPropertyValueId(pValue.getId());
							price1.setDisable((byte) 0);
							price1.setIcon("");
							this.productPackagePriceMapper.insertSelective(price1);
						}
						for (int j = i + 1; j < propertyValueArray.size(); j++) {
							//两种属性
							if (propertyArray.size() == 2) {
								if (!propertyValueArray.get(i).getPropertyId().equals(propertyValueArray.get(j).getPropertyId())) {
									PrProductPackagePrice price2 = new PrProductPackagePrice();
									double gPrice = Math.round(Double.valueOf(webShopConfig.getProGroupPrice())*Double.valueOf(refPrice));
									double iPrice = Math.round(Double.valueOf(webShopConfig.getProIndepentPrice())*Double.valueOf(refPrice));
									price2.setGourpPrice(gPrice+"");
									price2.setIndependentPrice(iPrice+"");
									price2.setId(UUID.randomUUID().toString().replaceAll("-", ""));
									price2.setProductId(product.getId());
									String propertyValueId = propertyValueArray.get(i).getId()+ "_" + propertyValueArray.get(j).getId();
									
									price2.setPropertyValueId(propertyValueId);
									price2.setDisable((byte) 0);
									price2.setIcon("");
									this.productPackagePriceMapper.insertSelective(price2);
								}
							}
							for (int p = j + 1; p < propertyValueArray.size(); p++) {
								//三种属性
								if (propertyArray.size() == 3) {
									if (!propertyValueArray.get(i).getPropertyId().equals(propertyValueArray.get(j).getPropertyId())
											&& !propertyValueArray.get(i).getPropertyId().equals(propertyValueArray.get(p).getPropertyId())
											&& !propertyValueArray.get(j).getPropertyId().equals(propertyValueArray.get(p).getPropertyId())) {
										PrProductPackagePrice price3 = new PrProductPackagePrice();
										double gPrice = Math.round(Double.valueOf(webShopConfig.getProGroupPrice())*Double.valueOf(refPrice));
										double iPrice = Math.round(Double.valueOf(webShopConfig.getProIndepentPrice())*Double.valueOf(refPrice));
										price3.setGourpPrice(gPrice+"");
										price3.setIndependentPrice(iPrice+"");
										price3.setId(UUID.randomUUID().toString().replaceAll("-", ""));
										price3.setProductId(product.getId());
										String propertyValueId = propertyValueArray.get(i).getId()+ "_" + propertyValueArray.get(j).getId()
												+ "_" + propertyValueArray.get(p).getId();
										
										price3.setPropertyValueId(propertyValueId);
										price3.setDisable((byte) 0);
										price3.setIcon("");
										this.productPackagePriceMapper.insertSelective(price3);
									}
								}
							}
						}

					}
					
//					if(m!=null){
//						throw new Exception("");
//					}
					
					// 属性值处理完毕。

					// 商品详情图开始处理。
					Elements el = document
							.select("div[id=desc-lazyload-container]");
					String dataTfsUrl = el.first().attr("data-tfs-url");
					if (!"".equals(dataTfsUrl)) {
						Document doc = Jsoup.connect(dataTfsUrl).get();
						Elements elements = doc.select("img");
						String content = "<p>";
						for (Element node : elements) {
							if (node.hasAttr("src")) {
								String src = node.attr("src");
								src = src.replaceAll("\\\"", "");
								src = src.substring(1, src.length() - 1);

								HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
										.getRequestAttributes()).getRequest();

								PrImage prImage = download(request,
										product.getId(), PrImageType.DETAIL,
										src);
								if(prImage!=null){
									if(webShopConfig!=null){
										String para = "<img id=\""+prImage.getId()+"\" class=\"img-responsive\" src=\""+webShopConfig.getProImagePath()+"/"+prImage.getUrl()+"\"/>";
										content += para;
										if(logger.isInfoEnabled()){
											logger.info(content);
										}
									}else{
										
									}
									
								}
								
								
							}
						}
						content+="</p>";
						product.setContent(content);
					}
					// 商品详情图处理结束。
					
					Elements picElements  = document.select("ul[class=nav nav-tabs fd-clr]");
					if(picElements!=null && picElements.size()>0){
						for (Element thumbEle : picElements) {
							List<Node> childNodes = thumbEle.childNodes();
							for (Node node : childNodes) {
								String dataImgs = node.attr("data-imgs");
								if(!"".equals(dataImgs)){
									JSONObject jsonObject = JSONObject.parseObject(dataImgs);
									HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
											.getRequestAttributes()).getRequest();
									PrImage prImage = download(request,
											product.getId(), PrImageType.SWIPER,
											jsonObject.getString("preview"));
									icon = prImage.getUrl();
								}
							}
						}
					}
					
					int numCount = Integer.valueOf(groupPurcseNum);
					product.setStorage(Integer.valueOf(canBookCount));
					product.setNumberCount(numCount);
					product.setCheckStatus(0);
					product.setPurchasingCost(Double.valueOf(refPrice));
					double groupPrice = Math.round(Double.valueOf(webShopConfig.getProGroupPrice())*Double.valueOf(refPrice));
					double indepentPrice = Math.round(Double.valueOf(webShopConfig.getProIndepentPrice())*Double.valueOf(refPrice));
					
					product.setIndependentPrice(indepentPrice);
					product.setGourpSalePrice(groupPrice);
					product.setIcon(icon);
					product.setMarketPrice(Double.valueOf(marketPrice));
					this.updateByPrimaryKeySelective(product);
				} catch (ScriptException e) {
					e.printStackTrace();
				}

			}
		}
		return product;
	}

	/**
	 * 创建组合价。
	 * @param productPropertyValues
	 */
	private Object[] intePackagePrice(List productPropertyValues){
		/*Object[] tagets = new Object[productPropertyValues.size()];
		for (int i = 0; i < tagets.length; i++) {
			tagets[i] = productPropertyValues.get(i);
		}*/
		Object[] result =turns(productPropertyValues);
		for (Object string : result) {
            System.out.println(string);
        }
		return result;
	}
	
	/**
     * 两两遍历
     * @param array1
     * @param array2
     * @return
     */
    public static Object[] doubleTurns(Object [] array1,Object[] array2){
        String [] target=new String[array1.length*array2.length];
        for (int i = 0,a1=0,a2=0; i <array1.length*array2.length; i++) {
            target[i]=array1[a1]+"_"+array2[a2];
            a2++;
            if(a2==array2.length){
                a2=0;
                a1++;
            }
        }
        return target;
    }
    /**
     * 遍历组合
     * @param arrays
     * @return
     */
    public static Object[] turns(List<Object[]>arrays){
        if(arrays.size()==1){
            return arrays.get(0);
        }
        if(arrays.size()==0){
            return null;
        }
        //获得总结果数
        int count=0;
        for (int i = 0; i < arrays.size(); i++) {
            count*=arrays.get(i).length;
        }
        Object target[]=new Object[count];
        //两两遍历
        for (int i = 0; i < arrays.size(); i++) {
            if(i==0){
                target=doubleTurns(arrays.get(0),arrays.get(1));
                i++;
            }else{
                target=doubleTurns(target,arrays.get(i));
            }
        }
        return target;
    }
	
	// 下载图片。
	private PrImage download(HttpServletRequest httpServletRequest,
			String productId, String type, String imgurl) {
		try {
			Calendar date = Calendar.getInstance();
			String rootDir = httpServletRequest.getSession()
					.getServletContext().getRealPath("/");
			rootDir = "D:" + File.separator;
			String url = "passets/" + productId + "/" + date.get(Calendar.YEAR)
					+ "/" + (date.get(Calendar.MONTH) + 1) + "/"
					+ date.get(Calendar.DAY_OF_MONTH);
			String uploadPath = rootDir + "images" + File.separator + "passets";
			uploadPath = uploadPath + File.separator + productId
					+ File.separator + date.get(Calendar.YEAR) + File.separator
					+ (date.get(Calendar.MONTH) + 1) + File.separator
					+ date.get(Calendar.DAY_OF_MONTH);

			int index = imgurl.lastIndexOf(".");
			String datetimeName = new Date().getTime()
					+ ((int) Math.random() * 10000) + "";
			String name = datetimeName
					+ imgurl.substring(index, imgurl.length());

			String imageName = uploadPath + File.separator + name;
			URL uri = new URL(imgurl);
			InputStream in = uri.openStream();
			File file = new File(uploadPath);
			if (!file.exists()) {
				file.mkdirs();
			}

			FileOutputStream fo = new FileOutputStream(file.getPath()
					+ File.separator + name);
			byte[] buf = new byte[1024];
			int length = 0;
			System.out.println("开始下载:" + imgurl);
			while ((length = in.read(buf, 0, buf.length)) != -1) {
				fo.write(buf, 0, length);
			}
			in.close();
			fo.close();
			System.out.println(imageName + "下载完成");

			PrImage assets = new PrImage();
			assets.setType(type);
			assets.setIsDefault(false);
			assets.setProductId(productId);
			assets.setName(name);
			assets.setLocation(uploadPath + File.separator + name);
			assets.setUrl(url + "/" + name);
			assets.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			imageService.insertSelective(assets);
			return assets;
		} catch (Exception e) {
			System.out.println("下载失败");
		}
		return null;
	}
	/*
	 * @Transactional(readOnly = true)
	 * 
	 * @Override // @Cacheable(key = "#id+'_product'", value = "PrProduct")
	 * public List<PrImage> selectByProductId(String productId, boolean
	 * isDefault) { // 日志记录 if (logger.isInfoEnabled()) logger.info(BASE_MESSAGE
	 * + "selectByProductId根据商品ID查询图片开始");
	 * 
	 * PrImageCriteria imageCriteria = new PrImageCriteria();
	 * imageCriteria.createCriteria().andProductIdEqualTo(productId)
	 * .andIsDefaultEqualTo(isDefault);
	 * imageCriteria.setOrderByClause("id desc"); List<PrImage> list =
	 * imageMapper.selectByExample(imageCriteria); if (logger.isInfoEnabled())
	 * logger.info(BASE_MESSAGE + "selectByProductId根据商品ID查询图片结束，结果条目数：" +
	 * list.size()); return list; }
	 */

	@Override
	public PrProduct fetchAlibabaGoodsByCont(File file) throws IOException,
			Exception {
		Document document = null;
		PrProduct product = null;
		
		document = Jsoup.parse(file,"GBK");
		
		String title = document.title();
		Element des = document.select("meta[name=description]").first();
		String description = des.attr("content");
		if(description!=null && !"".equals(description)){
			description.replace("阿里巴巴", "");
			description.replace("我们还为您精选了挂钟、壁钟、墙贴钟公司黄页、行业资讯、价格行情、展会信息等，欲了解更多详细信息,请点击访问!", "");
		}
		title = title.replace(" - 阿里巴巴", "");// 抓取商品的标题，去掉阿里巴巴的标识。
		String icon = "";//自动提取第一张作为缩略图。
		Elements devalues = document.select("td[class=de-value]");
		String marketPrice = "0.0";
		if(devalues!=null && devalues.size()>0){
			marketPrice =  devalues.first().html();
			if(marketPrice.indexOf("&yen;")==-1){
				marketPrice = "0.0";
			}else{
				marketPrice = marketPrice.replace("&yen;", "");
			}
		}
		
		// 查找相对应的脚本，该脚本包含了阿里巴巴的SKU属性。
		Elements scriptElements = document.select("script[type=text/javascript]");
		if (scriptElements != null && scriptElements.size() > 0) {
			Element element = null;
			for (int i = 0; i < scriptElements.size(); i++) {
				Element ele = scriptElements.get(i);
				String html = ele.html();
				int index = html.indexOf("iDetailData");
				if (index != -1) {
					element = ele;
					break;
				}
			}
			

			if (element != null) {
				String js = element.html();

				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine engine = manager.getEngineByName("javascript");
				try {
					engine.eval(js);
					ScriptObjectMirror iDetailConfig = (ScriptObjectMirror) engine
							.get("iDetailConfig");
					ScriptObjectMirror c = (ScriptObjectMirror) engine
							.get("iDetailData");
					ScriptObjectMirror sku = (ScriptObjectMirror) c.get("sku");

					// sku属性。
					ScriptObjectMirror skuProps = (ScriptObjectMirror) sku.get("skuProps");
					// 这个好像Map对象。
					ScriptObjectMirror skuMapProps = (ScriptObjectMirror) sku.get("skuMap");
					String refPrice = (String) iDetailConfig.get("refPrice");
					String groupPurcseNum =  (String) iDetailConfig.get("beginAmount");
					String canBookCount = (String) sku.get("canBookCount");
					Object m = iDetailConfig.getMember("sku");
					
					// 商品属性。
					List<PrProductProperty> properties = productPropertyService.listAll();
					product = new PrProduct();
					String id = UUID.randomUUID().toString()
							.replaceAll("-", "");
					product.setId(id);
					product.setName(title);
					product.setDescription(description);
					this.insertSelective(product);

					//保存属性值数组。
					List<PrProductPropertyValue> propertyValueArray = new ArrayList<PrProductPropertyValue>();
					List<PrProductProperty> propertyArray = new ArrayList<PrProductProperty>();
					
					List pValueList = new ArrayList();
					int i = 0;
					while (skuProps.hasSlot(i)) {
						ScriptObjectMirror o = (ScriptObjectMirror) skuProps
								.getSlot(i);
						String pName = (String) o.get("prop");
						PrProductProperty property = null;
						for (PrProductProperty prProductProperty : properties) {
							if (pName.indexOf(prProductProperty.getName())!=-1) {
								property = prProductProperty;
								break;
							}
						}

						if (property != null) {
							List<String> pValueIdList = new ArrayList<String>();
							
							propertyArray.add(property);
							
							ScriptObjectMirror value = (ScriptObjectMirror) o.get("value");
							// 遍历属性值开始；
							int j = 0;
							String[] pvalueIds = new String[value.size()];
							String[] pvalueNames = new String[value.size()];
							while (value.hasSlot(j)) {
								ScriptObjectMirror valueItem = (ScriptObjectMirror) value.getSlot(j);
								String name = (String) valueItem.get("name");
								PrProductPropertyValue productPropertyValue = new PrProductPropertyValue();
								productPropertyValue.setId(UUID.randomUUID().toString().replaceAll("-", ""));
								productPropertyValue.setProductId(product.getId());
								productPropertyValue.setPropertyId(property.getId());
								productPropertyValue.setName(name);
								
								// 属性的URL。
								String imageUrl = (String) valueItem.get("imageUrl");
								if (null != imageUrl && !"".equals(imageUrl)) {
									HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
											.getRequestAttributes())
											.getRequest();
									download(request, product.getId(),
											PrImageType.PROP, imageUrl);
								}
								this.productPropertyValueService.insertSelective(productPropertyValue);

								pvalueIds[j]=productPropertyValue.getId();
								pvalueNames[j]=productPropertyValue.getName();
								
								j++;
								
								propertyValueArray.add(productPropertyValue);
							}
							
							pValueList.add(pvalueIds);
						}
						i++;
					}
					
					//Object [] pvlueIds = intePackagePrice(pValueList);
					
//					if(pvlueIds!=null){
//						throw new Exception();
//					}
					for (i = 0; i < propertyValueArray.size(); i++) {
						//只有一种属性
						if (propertyArray.size() == 1) {
							PrProductPackagePrice price1 = new PrProductPackagePrice();
							double gPrice = Math.round(Double.valueOf(webShopConfig.getProGroupPrice())*Double.valueOf(refPrice));
							double iPrice = Math.round(Double.valueOf(webShopConfig.getProIndepentPrice())*Double.valueOf(refPrice));
							price1.setGourpPrice(gPrice+"");
							price1.setIndependentPrice(iPrice+"");
							price1.setId(UUID.randomUUID().toString().replaceAll("-", ""));
							price1.setProductId(product.getId());
							PrProductPropertyValue pValue = propertyValueArray.get(i);
							price1.setPropertyValueId(pValue.getId());
							price1.setDisable((byte) 0);
							price1.setIcon("");
							this.productPackagePriceMapper.insertSelective(price1);
						}
						for (int j = i + 1; j < propertyValueArray.size(); j++) {
							//两种属性
							if (propertyArray.size() == 2) {
								if (!propertyValueArray.get(i).getPropertyId().equals(propertyValueArray.get(j).getPropertyId())) {
									PrProductPackagePrice price2 = new PrProductPackagePrice();
									double gPrice = Math.round(Double.valueOf(webShopConfig.getProGroupPrice())*Double.valueOf(refPrice));
									double iPrice = Math.round(Double.valueOf(webShopConfig.getProIndepentPrice())*Double.valueOf(refPrice));
									price2.setGourpPrice(gPrice+"");
									price2.setIndependentPrice(iPrice+"");
									price2.setId(UUID.randomUUID().toString().replaceAll("-", ""));
									price2.setProductId(product.getId());
									String propertyValueId = propertyValueArray.get(i).getId()+ "_" + propertyValueArray.get(j).getId();
									
									price2.setPropertyValueId(propertyValueId);
									price2.setDisable((byte) 0);
									price2.setIcon("");
									this.productPackagePriceMapper.insertSelective(price2);
								}
							}
							for (int p = j + 1; p < propertyValueArray.size(); p++) {
								//三种属性
								if (propertyArray.size() == 3) {
									if (!propertyValueArray.get(i).getPropertyId().equals(propertyValueArray.get(j).getPropertyId())
											&& !propertyValueArray.get(i).getPropertyId().equals(propertyValueArray.get(p).getPropertyId())
											&& !propertyValueArray.get(j).getPropertyId().equals(propertyValueArray.get(p).getPropertyId())) {
										PrProductPackagePrice price3 = new PrProductPackagePrice();
										double gPrice = Math.round(Double.valueOf(webShopConfig.getProGroupPrice())*Double.valueOf(refPrice));
										double iPrice = Math.round(Double.valueOf(webShopConfig.getProIndepentPrice())*Double.valueOf(refPrice));
										price3.setGourpPrice(gPrice+"");
										price3.setIndependentPrice(iPrice+"");
										price3.setId(UUID.randomUUID().toString().replaceAll("-", ""));
										price3.setProductId(product.getId());
										String propertyValueId = propertyValueArray.get(i).getId()+ "_" + propertyValueArray.get(j).getId()
												+ "_" + propertyValueArray.get(p).getId();
										
										price3.setPropertyValueId(propertyValueId);
										price3.setDisable((byte) 0);
										price3.setIcon("");
										this.productPackagePriceMapper.insertSelective(price3);
									}
								}
							}
						}

					}
					
//					if(m!=null){
//						throw new Exception("");
//					}
					
					// 属性值处理完毕。

					// 商品详情图开始处理。
					Elements el = document
							.select("div[id=desc-lazyload-container]");
					String dataTfsUrl = el.first().attr("data-tfs-url");
					if (!"".equals(dataTfsUrl)) {
						Document doc = Jsoup.connect(dataTfsUrl).get();
						Elements elements = doc.select("img");
						String content = "<p>";
						for (Element node : elements) {
							if (node.hasAttr("src")) {
								String src = node.attr("src");
								src = src.replaceAll("\\\"", "");
								src = src.substring(1, src.length() - 1);

								HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
										.getRequestAttributes()).getRequest();

								PrImage prImage = download(request,
										product.getId(), PrImageType.DETAIL,
										src);
								if(prImage!=null){
									if(webShopConfig!=null){
										String para = "<img id=\""+prImage.getId()+"\" class=\"img-responsive\" src=\""+webShopConfig.getProImagePath()+"/"+prImage.getUrl()+"\"/>";
										content += para;
										if(logger.isInfoEnabled()){
											logger.info(content);
										}
									}else{
										
									}
									
								}
								
								
							}
						}
						content+="</p>";
						product.setContent(content);
					}
					// 商品详情图处理结束。
					
					Elements picElements  = document.select("ul[class=nav nav-tabs fd-clr]");
					if(picElements!=null && picElements.size()>0){
						for (Element thumbEle : picElements) {
							List<Node> childNodes = thumbEle.childNodes();
							for (Node node : childNodes) {
								String dataImgs = node.attr("data-imgs");
								if(!"".equals(dataImgs)){
									JSONObject jsonObject = JSONObject.parseObject(dataImgs);
									HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
											.getRequestAttributes()).getRequest();
									PrImage prImage = download(request,
											product.getId(), PrImageType.SWIPER,
											jsonObject.getString("preview"));
									icon = prImage.getUrl();
								}
							}
						}
					}
					
					int numCount = Integer.valueOf(groupPurcseNum);
					product.setStorage(Integer.valueOf(canBookCount));
					product.setNumberCount(numCount);
					product.setCheckStatus(0);
					product.setPurchasingCost(Double.valueOf(refPrice));
					double groupPrice = Math.round(Double.valueOf(webShopConfig.getProGroupPrice())*Double.valueOf(refPrice));
					double indepentPrice = Math.round(Double.valueOf(webShopConfig.getProIndepentPrice())*Double.valueOf(refPrice));
					
					product.setIndependentPrice(indepentPrice);
					product.setGourpSalePrice(groupPrice);
					product.setIcon(icon);
					product.setMarketPrice(Double.valueOf(marketPrice));
					this.updateByPrimaryKeySelective(product);
				} catch (ScriptException e) {
					e.printStackTrace();
				}

			}
		}
		return product;
	}

	/*
	 * if(result != 0){ if(redisTemplate == null ){ redisTemplate =
	 * SpringContextHolder.getBean("redisTemplate"); }
	 * 
	 * if(redisTemplate != null){
	 * redisTemplate.delete("selectIneffectQuestion_guessQuestion"); } }
	 */
}
