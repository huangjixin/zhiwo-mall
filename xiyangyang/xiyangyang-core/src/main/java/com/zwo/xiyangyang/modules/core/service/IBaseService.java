/**
 * 
 */
package com.zwo.xiyangyang.modules.core.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * 表增删改查操作基础实现类。
 * @author 黄记新
 *
 */
public interface IBaseService<T> {
	
	/**
	 * 批量删除,参数当中为ID。
	 * @param list
	 * @return
	 * @throws Exception 
	 */
	int deteleBatch(@SuppressWarnings("rawtypes") List list);
	
	/**
	 * 批量插入
	 * @param list
	 * @return
	 * @throws Exception 
	 */
	int insertBatch(List<T> list);

	/**
	 * 根据条件查询数量。
	 * @param example
	 * @return
	 */
	int countByExample(Object example);

	/**
	 * 根据条件删除
	 * @param example
	 * @return
	 */
	int deleteByExample(Object example);

	/**
	 * 根据ID进行删除
	 * @param id
	 * @return
	 */
	int deleteById(String id);

	/**
	 * 插入一条记录
	 * @param record
	 * @return
	 */
	int insert(T record);

	/**
	 * 有选择地进行插入记录
	 * @param record
	 * @return
	 */
	int insertSelective(T record);

	/**
	 * 根据ID查询记录
	 * @param id
	 * @return
	 */
	T selectByPrimaryKey(String id);

	/**
	 * @param record
	 * @param example
	 * @return
	 */
	int updateByExampleSelective(T record, Object example);

	/**
	 * 根据条件进行更新。
	 * @param record
	 * @param example
	 * @return
	 */
	int updateByExample(T record, Object example);

	/**
	 * 根据条件有选择的更新。
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 * 根据ID进行记录更新。
	 * @param record
	 * @return
	 */
	int updateById(T record);

	/**
	 * @param example
	 * @param pageInfo
	 * @return
	 */
	List<T> selectByExample(Object example,@SuppressWarnings("rawtypes") PageInfo pageInfo);

	/**
	 * 根据条件进行查询。
	 * @param example
	 * @return
	 */
	List<T> selectByExample(Object example);
	
	/**
	 * @param example
	 * @param pageInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	PageInfo selectByPageInfo(Object example,
			PageInfo pageInfo);
}
