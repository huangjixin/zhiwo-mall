/**
 * 
 */
package com.zwo.modules.cms.service.impl;

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
import com.zwo.modules.cms.dao.CmsDocumentMapper;
import com.zwo.modules.cms.domain.CmsDocument;
import com.zwo.modules.cms.domain.CmsDocumentCriteria;
import com.zwo.modules.cms.domain.CmsDocumentWithBLOBs;
import com.zwo.modules.cms.service.ICmsDocumentService;
import com.zwotech.modules.core.service.impl.BaseService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author hjx
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class CmsDocumentServiceImpl extends BaseService<CmsDocument> implements ICmsDocumentService {
	private static Logger logger = LoggerFactory.getLogger(CmsDocumentServiceImpl.class);

	private static final String BASE_MESSAGE = "【CmsDocumentServiceImpl服务类提供的基础操作增删改查等】";

	@Autowired
	@Lazy(true)
	private CmsDocumentMapper cmsDocumentMapper;

	@Override
	public Mapper<CmsDocument> getBaseMapper() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#insertBatch(java.util.List)
	 */
	/*
	 * @Override public int insertBatch(List<CmsDocument> list) { // TODO
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
	@CacheEvict(value = "CmsDocument", allEntries = true)
	public int deleteByExample(Object example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除开始");

		// 逻辑操作
		int result = cmsDocumentMapper.deleteByExample((CmsDocumentCriteria) example);

		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByExample批量删除结束");
		return result;
	}

	@CacheEvict(value = "CmsDocument", allEntries = true)
	// @Override
	public int deleteBatch(List<String> list) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteBatch批量删除ID为：" + list.toString());

		// 逻辑操作
		CmsDocumentCriteria cmsDocumentCriteria = new CmsDocumentCriteria();
		cmsDocumentCriteria.createCriteria().andIdIn(list);
		int result = cmsDocumentMapper.deleteByExample(cmsDocumentCriteria);

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
	@CacheEvict(value = "CmsDocument", key = "#id")
	public int deleteByPrimaryKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "deleteByPrimaryKey删除ID为：" + id.toString());

		// 逻辑操作
		int result = cmsDocumentMapper.deleteByPrimaryKey(id);

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
	public int insert(CmsDocument record) {
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
	public int insertSelective(CmsDocument record) {
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
	public List<CmsDocument> selectByExample(Object example) {
		return cmsDocumentMapper.selectByExample((CmsDocumentCriteria) example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#selectByPrimaryKey(java.
	 * lang.String)
	 */
	@Override
	public CmsDocument selectByPrimaryKey(String id) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExampleSelective(
	 * java.lang.Object, java.lang.Object)
	 */
	@Deprecated
	@Override
	public int updateByExampleSelective(CmsDocument record, Object example) {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.zwotech.modules.core.service.IBaseService#updateByExample(java.lang.
	 * Object, java.lang.Object)
	 */
	@Deprecated
	@Override
	public int updateByExample(CmsDocument record, Object example) {
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
	public int updateByPrimaryKeySelective(CmsDocument record) {
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
	@CacheEvict(value = "CmsDocument", key = "#record.id")
	public int updateByPrimaryKey(CmsDocument record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKey更新对象为：" + record.toString());

		// 逻辑操作
		int result = cmsDocumentMapper.updateByPrimaryKey(record);
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
	public PageInfo<CmsDocument> selectByPageInfo(Object example, PageInfo<CmsDocument> pageInfo) {
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页参数：" + pageInfo.toString());
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<CmsDocument> list = this.cmsDocumentMapper.selectByExample((CmsDocumentCriteria) example);
		PageInfo<CmsDocument> page = new PageInfo<CmsDocument>(list);
		pageInfo.setList(list);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setEndRow(page.getEndRow());
		pageInfo.setStartRow(page.getStartRow());
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "分页结束");
		return pageInfo;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/mall-applicationContext.xml");// 此文件放在SRC目录下
		ICmsDocumentService cmsDocumentServiceImpl = (ICmsDocumentService) context.getBean("cmsDocumentServiceImpl");
		CmsDocument cmsDocument = new CmsDocument();
		cmsDocument.setId(System.currentTimeMillis() + "");
		int result = cmsDocumentServiceImpl.insertSelective(cmsDocument);
		logger.info(result + "");
	}

	@CachePut(value = "CmsDocument", key = "#record.id")
	public int insert(CmsDocumentWithBLOBs record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());

		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		int result = cmsDocumentMapper.insert(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入结束");
		return result;
	}

	@CachePut(value = "CmsDocument", key = "#record.id")
	@CacheEvict(value = "CmsDocument", allEntries = true)
	public int insertSelective(CmsDocumentWithBLOBs record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入对象为：" + record.toString());

		// 如果数据没有设置id,默认使用时间戳
		if (null == record.getId() || "".equals(record.getId())) {
			record.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
		}
		int result = cmsDocumentMapper.insertSelective(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "insert插入结束");
		return result;
	}

	@Transactional(readOnly = true)
	public List<CmsDocumentWithBLOBs> selectByExampleWithBLOBs(CmsDocumentCriteria example) {
		return cmsDocumentMapper.selectByExampleWithBLOBs(example);
	}

	@Transactional(readOnly = true)
	public List<CmsDocument> selectByExample(CmsDocumentCriteria example) {
		return cmsDocumentMapper.selectByExample((CmsDocumentCriteria) example);
	}

	@Cacheable(key = "#id", value = "CmsDocument")
	@Transactional(readOnly = true)
	public CmsDocumentWithBLOBs selectByPrimKey(String id) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询参数为：" + id);

		// 逻辑操作
		CmsDocumentWithBLOBs cmsDocument = cmsDocumentMapper.selectByPrimaryKey(id);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "selectByPrimaryKey查询结束");
		return cmsDocument;
	}

	@CacheEvict(value = "CmsDocument", allEntries = true)
	public int updateByExampleSelective(CmsDocumentWithBLOBs record, CmsDocumentCriteria example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新条件对象为：" + record.toString());

		// 逻辑操作
		int result = cmsDocumentMapper.updateByExampleSelective(record, (CmsDocumentCriteria) example);
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleSelective更新结束");
		return result;
	}

	@CacheEvict(value = "CmsDocument", allEntries = true)
	public int updateByExampleWithBLOBs(CmsDocumentWithBLOBs record, CmsDocumentCriteria example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleWithBLOBs更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleWithBLOBs更新对象为：" + record.toString());

		// 逻辑操作
		int result = cmsDocumentMapper.updateByExampleWithBLOBs(record, example);
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExampleWithBLOBs更新结束");
		return result;
	}

	@CacheEvict(value = "CmsDocument", allEntries = true)
	public int updateByExample(CmsDocument record, CmsDocumentCriteria example) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新对象为：" + record.toString());

		// 逻辑操作
		int result = cmsDocumentMapper.updateByExample(record, example);
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByExample更新结束");
		return result;
	}

	@CacheEvict(value = "CmsDocument", key = "#record.id")
	public int updateByPrimaryKeySelective(CmsDocumentWithBLOBs record) {
		// 日志记录
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新开始");
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新对象为：" + record.toString());

		// 逻辑操作
		int result = cmsDocumentMapper.updateByPrimaryKeySelective(record);
		if (logger.isInfoEnabled())
			logger.info(BASE_MESSAGE + "updateByPrimaryKeySelective更新结束");
		return result;
	}

}
