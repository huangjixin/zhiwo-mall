package com.zwotech.modules.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInfo2;
import com.zwotech.modules.core.mapper.IBaseMapper;
import com.zwotech.modules.core.service.IBaseService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黄记新
 *
 * @param <T>
 */
public abstract class BaseService<T> implements IBaseService<T> {

	private static Logger logger;

	public abstract Mapper<T> getBaseMapper();
	
	protected Class<?> entityClass;
	
	public int insertBatch(List<T> list) {
//		return this.getBaseMapper().insertBatch(list);
		return 0;
	}
	
	public BaseService() {
		logger = LoggerFactory.getLogger(getClass());
	}
	
	
	@Transactional(readOnly = false)
	public int countByExample(Object example) {
		return -1;
	}

	
	@Transactional(readOnly = false)
	public int deleteByExample(Object example) {
		return this.getBaseMapper().deleteByExample(example);
	}

	
	@Transactional(readOnly = false)
	public int deleteByPrimaryKey(String id) {
		int result = this.getBaseMapper().deleteByPrimaryKey(id);
		return result;
	}

	
	@Transactional(readOnly = false)
	public int insert(T record) {
		int result = this.getBaseMapper().insert(record);
		return result;
	}

	
	@Transactional(readOnly = false)
	public int insertSelective(T record) {
		int result = this.getBaseMapper().insertSelective(record);
		return result;
	}

	
	@Transactional(readOnly = true)
	public List<T> selectByExample(Object example) {
		List<T> result = this.getBaseMapper().selectByExample(example);
		return result;
	}

	
	@Transactional(readOnly = true)
	public T selectByPrimaryKey(String id) {
		T t = this.getBaseMapper().selectByPrimaryKey(id);
		return t;
	}

	
	@Transactional(readOnly = false)
	public int updateByExampleSelective(T record, Object example) {
		return this.getBaseMapper().updateByExampleSelective(record, example);
	}

	
	@Transactional(readOnly = false)
	public int updateByExample(T record, Object example) {
		int result = this.getBaseMapper().updateByExample(record, example);
		return result;
	}

	
	@Transactional(readOnly = false)
	public int updateByPrimaryKeySelective(T record) {
		return this.getBaseMapper().updateByPrimaryKeySelective(record);
	}

	
	@Transactional(readOnly = false)
	public int updateByPrimaryKey(T record) {
		return this.getBaseMapper().updateByPrimaryKey(record);
	}

	
	@Transactional(readOnly = true)
	public PageInfo2<T> selectByPageInfo(Object example,
			PageInfo2<T> pageInfo){
		PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		List<T> list = this.getBaseMapper().selectByExample(example);
//		if(logger.isInfoEnabled())
//			logger.info(MESSAGE+"分页开始");
//		if(logger.isInfoEnabled())
//			logger.info(MESSAGE+"分页参数：" + pageInfo.toString());
		
		Page<T> page = (Page<T>) list;
		pageInfo.setList(list);
		pageInfo.setTotal(page.getTotal());
		pageInfo.setEndRow(page.getEndRow());
		pageInfo.setStartRow(page.getStartRow());
//		if(logger.isInfoEnabled())
//			logger.info(MESSAGE+"分页结束");
		return pageInfo;
	}
}
