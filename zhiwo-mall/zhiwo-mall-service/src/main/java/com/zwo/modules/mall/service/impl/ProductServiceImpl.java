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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwo.modules.mall.dao.PrImageMapper;
import com.zwo.modules.mall.dao.PrProductMapper;
import com.zwo.modules.mall.dao.PrProductPackagePriceMapper;
import com.zwo.modules.mall.dao.PrProductPropertyValueMapper;
import com.zwo.modules.mall.domain.PrImage;
import com.zwo.modules.mall.domain.PrImageCriteria;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.domain.PrProductCriteria;
import com.zwo.modules.mall.domain.PrProductPackagePrice;
import com.zwo.modules.mall.domain.PrProductPropertyValue;
import com.zwo.modules.mall.domain.PrProductWithBLOBs;
import com.zwo.modules.mall.service.IPrProductPackagePriceService;
import com.zwo.modules.mall.service.IPrProductPropertyService;
import com.zwo.modules.mall.service.IPrProductPropertyValueService;
import com.zwo.modules.mall.service.IPrductService;
import com.zwotech.modules.core.service.impl.BaseService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author hjx
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class ProductServiceImpl extends BaseService<PrProduct> implements IPrductService {
	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	private static final String BASE_MESSAGE = "【PrProductServiceImpl服务类提供的基础操作增删改查等】";
	
	@Autowired
	@Lazy(true)
	private PrProductMapper productMapper;
	
	@Autowired
	@Lazy(true)
	private PrImageMapper imageMapper;
	
	@Autowired
	@Lazy(true)
	private PrProductPropertyValueMapper productPropertyValueMapper;
	
	@Autowired
	@Lazy(true)
	private PrProductPackagePriceMapper productPackagePriceMapper;

	@Override
	public Mapper<PrProduct> getBaseMapper() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*
	 * @Override public int insertBatch(List<PrProduct> list) { Auto-generated method
	 * stub return 0; }
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

		// 逻辑操作
		int result = productMapper.deleteByExample((PrProductCriteria) example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

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
	@Override
	@CacheEvict(value = "PrProduct", key = "#id+'_product'")
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为：" + id.toString());

		// 逻辑操作
		int result = this.productMapper.deleteByPrimaryKey(id);

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
	@Deprecated
	public int insert(PrProduct record) {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertSelective(java.lang.
	 * Object)
	 */

	@Override
	@Deprecated
	public int insertSelective(PrProduct record) {
		return 0;
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
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	@Deprecated
	public PrProduct selectByPrimaryKey(String id) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@Override
	@Deprecated
	public int updateByExampleSelective(PrProduct record, Object example) {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExample(java.lang.
	 * Object, java.lang.Object)
	 */
	@Override
	@Deprecated
	public int updateByExample(PrProduct record, Object example) {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByPrimaryKeySelective
	 * (java.lang.Object)
	 */
	@Override
	@Deprecated
	public int updateByPrimaryKeySelective(PrProduct record) {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByPrimaryKey(java.
	 * lang.Object)
	 */
	@Override
	@CacheEvict(value = "PrProduct", key = "#record.id+'_product'")
	public int updateByPrimaryKey(PrProduct record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新对象为：" + record.toString());

		// 逻辑操作
		int result = this.productMapper.updateByPrimaryKey(record);
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
	public PageInfo<PrProduct> selectByPageInfo(Object example, PageInfo<PrProduct> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页参数：" + pageInfo.toString());
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<PrProduct> list = this.productMapper.selectByExample((PrProductCriteria) example);
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
		return this.productMapper.countByExample(example);
	}

	@CachePut(value = "PrProduct", key = "#record.id+'_product'")
	public int insert(PrProductWithBLOBs record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());
		if(null!=record.getContent() && !"".equals(record.getContent())){
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}
		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		int result = this.productMapper.insert(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入结束");
		return result;
	}

	
//	@CachePut(value = "PrProduct", key = "#record.id+'_product'")
	public int insertSelective(PrProductWithBLOBs record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insertSelective插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insertSelective插入对象为：" + record.toString());
		if(null!=record.getContent() && !"".equals(record.getContent())){
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}
		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		int result = this.productMapper.insertSelective(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insertSelective插入结束");

		
		return result;
	}

	
	@Cacheable(key = "#id+'_product'", value = "PrProduct")
	@Transactional(readOnly = true)
	public PrProductWithBLOBs selectByPrimKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);
		
		// 逻辑操作
		PrProductWithBLOBs product = this.productMapper.selectByPrimaryKey(id);
		if(null!=product && null!=product.getContent() && !"".equals(product.getContent())){
			String content = product.getContent();
			content = HtmlUtils.htmlUnescape(content);
			product.setContent(content);
		}
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return product;
	}

	@CacheEvict(value = "PrProduct", allEntries = true)
	public int updateByExampleSelective(PrProductWithBLOBs record, PrProductCriteria example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为：" + record.toString());
		if(null!=record.getContent() && !"".equals(record.getContent())){
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}
		// 逻辑操作
		int result = this.productMapper.updateByExampleSelective(record, example);
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新结束");
		return result;
	}

	@CacheEvict(value = "PrProduct", allEntries = true)
	public int updateByExampleWithBLOBs(PrProductWithBLOBs record, PrProductCriteria example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleWithBLOBs更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleWithBLOBs更新条件对象为：" + record.toString());
		if(null!=record.getContent() && !"".equals(record.getContent())){
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}
		// 逻辑操作
		int result = this.productMapper.updateByExampleWithBLOBs(record, example);
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleWithBLOBs更新结束");
		return result;
	}

	@CacheEvict(value = "PrProduct", allEntries = true)
	public int updateByExample(PrProduct record, PrProductCriteria example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新对象为：" + record.toString());
//		if(null!=record.getContent() && !"".equals(record.getContent())){
//			String content = record.getContent();
//			content = HtmlUtils.htmlEscape(content);
//			record.setContent(content);
//		}
		// 逻辑操作
		int result = this.productMapper.updateByExample(record, example);
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新结束");
		return result;
	}

	@CacheEvict(value = "PrProduct", key = "#record.id+'_product'")
	public int updateByPrimaryKeySelective(PrProductWithBLOBs record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为：" + record.toString());
		if(null!=record.getContent() && !"".equals(record.getContent())){
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}
		// 逻辑操作
		int result = this.productMapper.updateByPrimaryKeySelective(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新结束");
		return result;
	}

	@CacheEvict(value = "PrProduct", key = "#record.id+'_product'")
	public int updateByPrimaryKeyWithBLOBs(PrProductWithBLOBs record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeyWithBLOBs更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeyWithBLOBs更新对象为：" + record.toString());
		if(null!=record.getContent() && !"".equals(record.getContent())){
			String content = record.getContent();
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}
		// 逻辑操作
		int result = this.productMapper.updateByPrimaryKeyWithBLOBs(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeyWithBLOBs更新结束");
		return result;
	}

	@Transactional(readOnly = true)
	@Override
	public List<PrImage> selectByProductId(String productId,boolean isDefault) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByProductId根据商品ID查询图片开始");	
		
		PrImageCriteria imageCriteria = new PrImageCriteria();
		imageCriteria.createCriteria().andProductIdEqualTo(productId).andIsDefaultEqualTo(isDefault);
		imageCriteria.setOrderByClause("id desc");
		List<PrImage> list = imageMapper.selectByExample(imageCriteria);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByProductId根据商品ID查询图片结束，结果条目数："+list.size());	
		return list;
	}

}
