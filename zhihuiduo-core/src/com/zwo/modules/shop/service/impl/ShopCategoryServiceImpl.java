/**
 * 
 */
package com.zwo.modules.shop.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwo.modules.shop.dao.ShopCategoryMapper;
import com.zwo.modules.shop.domain.Shop;
import com.zwo.modules.shop.domain.ShopCategory;
import com.zwo.modules.shop.domain.ShopCategoryCriteria;
import com.zwo.modules.shop.service.IShopCategoryService;
import com.zwo.modules.system.dao.TbUserAssetsMapper;
import com.zwo.modules.system.domain.TbUserAssets;
import com.zwo.modules.system.domain.TbUserAssetsCriteria;
import com.zwotech.common.utils.RedisUtil;
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
public class ShopCategoryServiceImpl extends BaseService<ShopCategory> implements IShopCategoryService {
	private static Logger logger = LoggerFactory.getLogger(ShopCategoryServiceImpl.class);

	private static final String BASE_MESSAGE = "【ShopCategoryServiceImpl服务类提供的基础操作增删改查等】";

	public static final String KEY_SHOP_CATEGORY = "_key_ShopCategory";
	
	@Autowired
	@Lazy(true)
	private TbUserAssetsMapper userAssetsMapper;
	
	@Autowired
	@Lazy(true)
	private ShopCategoryMapper shopCategoryMapper;

	@Override
	public Mapper<ShopCategory> getBaseMapper() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;
	
	public ShopCategoryServiceImpl() {
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
	 * @Override public int insertBatch(List<ShopCategory> list) { // TODO
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
//	@CacheEvict(value = "ShopCategory", allEntries = true)
	public int deleteByExample(Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除开始");
		List<ShopCategory> categories = this.selectByExample(example);
		for (ShopCategory category : categories) {
			RedisUtil.removeRedisKey(redisTemplate, category.getId()+KEY_SHOP_CATEGORY);
		}
		
		// 逻辑操作
		int result = shopCategoryMapper.deleteByExample((ShopCategoryCriteria)example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

	@CacheEvict(value = "ShopCategory", allEntries = true)
//	@Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());
		TbUserAssetsCriteria userAssetsCriteria = new TbUserAssetsCriteria();
		userAssetsCriteria.createCriteria().andOrgIdIn(list);
		
		List<TbUserAssets> assets = userAssetsMapper.selectByExample(userAssetsCriteria);
		for (TbUserAssets tbUserAssets : assets) {
			//文件
		    Path path = Paths.get(tbUserAssets.getPath());
		    if(Files.exists(path)){
		      try {
				Files.deleteIfExists(path);
		      } catch (IOException e) {
				e.printStackTrace();
		      }
		    }
		}
		// 逻辑操作
		ShopCategoryCriteria shopCategoryCriteria = new ShopCategoryCriteria();
		shopCategoryCriteria.createCriteria().andIdIn(list);
		List<ShopCategory> categories = this.selectByExample(shopCategoryCriteria);
		for (ShopCategory category : categories) {
			RedisUtil.removeRedisKey(redisTemplate, category.getId()+KEY_SHOP_CATEGORY);
		}
		
		int result = shopCategoryMapper.deleteByExample(shopCategoryCriteria);

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
	@CacheEvict(value = "ShopCategory", key="#id+'_key_ShopCategory'")
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为：" + id.toString());

		// 逻辑操作
		int result = this.shopCategoryMapper.deleteByPrimaryKey(id);

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
//	@CachePut(value = "ShopCategory", key = "#record.id+'_key_ShopCategory'")
	public int insert(ShopCategory record) {
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
		int result = this.shopCategoryMapper.insert(record);
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
//	@CachePut(value = "ShopCategory", key = "#record.id+'_key_ShopCategory'")
	public int insertSelective(ShopCategory record) {
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
		int result = this.shopCategoryMapper.insertSelective(record);
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
	public List<ShopCategory> selectByExample(Object example) {
		return shopCategoryMapper.selectByExample((ShopCategoryCriteria)example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(key = "#id+'_key_ShopCategory'", value = "ShopCategory")
	@Transactional(readOnly = true)
	public ShopCategory selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		ShopCategory shopCategory = this.shopCategoryMapper.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return shopCategory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
//	@CacheEvict(value = "ShopCategory", allEntries = true)
	@Override
	public int updateByExampleSelective(ShopCategory record, Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为：" + record.toString());
		if ("".equals(record.getParentId())) {
			record.setParentId(null);
		}
		List<ShopCategory> categories = this.selectByExample(example);
		for (ShopCategory category : categories) {
			RedisUtil.removeRedisKey(redisTemplate, category.getId()+KEY_SHOP_CATEGORY);
		}
		
		// 逻辑操作
		int result = this.shopCategoryMapper.updateByExampleSelective(record,(ShopCategoryCriteria) example);
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
//	@CacheEvict(value = "ShopCategory", allEntries = true)
	public int updateByExample(ShopCategory record, Object example) {
		//日志记录
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample更新开始");
		if(logger.isInfoEnabled())
			logger.info(BASE_MESSAGE+"updateByExample更新对象为：" + record.toString());
		if ("".equals(record.getParentId())) {
			record.setParentId(null);
		}	
		List<ShopCategory> categories = this.selectByExample(example);
		for (ShopCategory category : categories) {
			RedisUtil.removeRedisKey(redisTemplate, category.getId()+KEY_SHOP_CATEGORY);
		}
		
		//逻辑操作		
		int result = this.shopCategoryMapper.updateByExample(record, (ShopCategoryCriteria)example);
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
	@CacheEvict(value = "ShopCategory", key="#record.id+'_key_ShopCategory'")
	public int updateByPrimaryKeySelective(ShopCategory record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为：" + record.toString());
		if ("".equals(record.getParentId())) {
			record.setParentId(null);
		}
		// 逻辑操作
		int result = this.shopCategoryMapper.updateByPrimaryKeySelective(record);
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
	@CacheEvict(value = "ShopCategory", key="#record.id+'_key_ShopCategory'")
	public int updateByPrimaryKey(ShopCategory record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新对象为：" + record.toString());
		if ("".equals(record.getParentId())) {
			record.setParentId(null);
		}
		// 逻辑操作
		int result = this.shopCategoryMapper.updateByPrimaryKey(record);
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
	public PageInfo<ShopCategory> selectByPageInfo(Object example, PageInfo<ShopCategory> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页参数：" + pageInfo.toString());
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<ShopCategory> list = this.shopCategoryMapper.selectByExample((ShopCategoryCriteria) example);
		PageInfo<ShopCategory> page = new PageInfo<ShopCategory>(list);
		pageInfo.setList(list);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setEndRow(page.getEndRow());
		pageInfo.setStartRow(page.getStartRow());
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页结束");
		return pageInfo;
	}

}
