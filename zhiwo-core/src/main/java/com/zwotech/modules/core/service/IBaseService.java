package com.zwotech.modules.core.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInfo2;

/**
 * Created by Martin on 2016/7/01.
 */
public interface IBaseService<T> {
	int deleteBatch(List<String> list);

	int insertBatch(List<T> list);

	int countByExample(Object example);

	int deleteByExample(Object example);

	int deleteByPrimaryKey(String id);

	int insert(T record);

	int insertSelective(T record);

	List<T> selectByExample(Object example);

	T selectByPrimaryKey(String id);

	int updateByExampleSelective(T record, Object example);

	int updateByExample(T record, Object example);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);
	
	PageInfo2<T> selectByPageInfo(Object example,
			PageInfo2<T> pageInfo);

}
