/**
 * 
 */
package com.zwo.modules.mall.service.impl;

import java.util.ArrayList;
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

import com.github.pagehelper.PageInfo2;
import com.zwo.modules.mall.dao.PrCategoryMapper;
import com.zwo.modules.mall.domain.PrCategory;
import com.zwo.modules.mall.domain.PrCategoryCriteria;
import com.zwo.modules.mall.domain.PrCategoryCriteria.Criteria;
import com.zwo.modules.mall.service.IPrCategoryService;
import com.zwotech.common.utils.TreeBuilder;
import com.zwotech.modules.core.service.impl.BaseService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author hjx
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class PrCategoryServiceImpl extends BaseService<PrCategory> implements IPrCategoryService {
	private static Logger logger = LoggerFactory.getLogger(PrCategoryServiceImpl.class);

	private static final String BASE_MESSAGE = "【PrCategoryServiceImpl服务类提供的基础操作增删改查等】";

	@Autowired
	@Lazy(true)
	private PrCategoryMapper prCategoryMapper;

	@Override
	public Mapper<PrCategory> getBaseMapper() {
		return prCategoryMapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*
	 * @Override public int insertBatch(List<PrCategory> list) { // TODO
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
	@CacheEvict(value = "PrCategory", allEntries = true)
	public int deleteByExample(Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除开始");

		// 逻辑操作
		int result = prCategoryMapper.deleteByExample(example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

	@CacheEvict(value = "PrCategory", allEntries = true)
	@Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		PrCategoryCriteria prCategoryCriteria = new PrCategoryCriteria();
		prCategoryCriteria.createCriteria().andIdIn(list);
		int result = prCategoryMapper.deleteByExample(prCategoryCriteria);

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
	@CacheEvict(value = "PrCategory", allEntries = true)
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
	@CachePut(value = "PrCategory", key = "#record.id")
	public int insert(PrCategory record) {
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
	@CachePut(value = "PrCategory", key = "#record.id")
	public int insertSelective(PrCategory record) {
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
	public List<PrCategory> selectByExample(Object example) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	@Cacheable(key = "#id", value = "PrCategory")
	@Transactional(readOnly = true)
	public PrCategory selectByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		PrCategory prCategory = super.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束,结果对象为:" + prCategory == null ? "NULL"
					: prCategory.toString());
		return prCategory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@CacheEvict(value = "PrCategory", allEntries = true)
	@Override
	public int updateByExampleSelective(PrCategory record, Object example) {
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
	@CacheEvict(value = "PrCategory", allEntries = true)
	public int updateByExample(PrCategory record, Object example) {
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
	@CacheEvict(value = "PrCategory", allEntries = true)
	public int updateByPrimaryKeySelective(PrCategory record) {
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
	@CachePut(value = "PrCategory", key = "#record.id")
	public int updateByPrimaryKey(PrCategory record) {
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
	public PageInfo2<PrCategory> selectByPageInfo(Object example, PageInfo2<PrCategory> pageInfo) {
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
	public List<PrCategory> getTreeCategory(String parentId) {
		PrCategoryCriteria categoryCriteria = new PrCategoryCriteria();

		// 日志记录开始,方法开始
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "getTreeCategory开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "参数ID为：" + parentId);

		// 定义局部变量
		List<PrCategory> categories = new ArrayList<PrCategory>();
		PrCategoryCriteria prCategoryCriteria = new PrCategoryCriteria();
		Criteria criteria = prCategoryCriteria.createCriteria();
		// 业务操作开始
		if (null == parentId) {
			criteria.andParentIdIsNull();
		} else {
			criteria.andParentidsLike(parentId);
		}

		prCategoryCriteria.setOrderByClause("sort asc");

		List<PrCategory> list = prCategoryMapper.selectByExample(prCategoryCriteria);
		if (null == parentId) {
			for (PrCategory category : list) {
				PrCategoryCriteria orgCriteria = new PrCategoryCriteria();
				Criteria cri = prCategoryCriteria.createCriteria();
				cri.andParentidsLike("%" + category.getId() + "%");
				List<PrCategory> prCategories = prCategoryMapper.selectByExample(orgCriteria);
				categories.addAll(prCategories);
			}
		}

		TreeBuilder<PrCategory> tb = new TreeBuilder<PrCategory>();
		if (null == parentId) {
			categories = tb.buildListToTree(categories, false);
		} else {
			list = tb.buildListToTree(list, false);
		}

		// 业务操作结束

		// 日志记录结束,方法结束
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "getOrgCheckboxTree结束");

		return null == parentId ? categories : list;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/mall-applicationContext.xml");// 此文件放在SRC目录下
		IPrCategoryService prductService = (IPrCategoryService) context.getBean("prCategoryServiceImpl");
		// PrCategory product = new PrCategory();
		// product.setId(System.currentTimeMillis() + "");
		// int result = prductService.insertSelective(product);
		// logger.info(result + "");

		List<PrCategory> list = prductService.getTreeCategory(null);
		logger.info("");
	}

}
