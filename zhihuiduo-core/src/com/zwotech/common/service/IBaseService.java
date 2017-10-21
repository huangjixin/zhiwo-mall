/**
 * 一句话描述该类：<br/>
 * @author 黄记新
 * @date 2014-12-23,上午9:54:24
 *
 */
/**
 * 
 */
package com.zwotech.common.service;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 该类定义了一系列数据库基础操作，包括增删改查，批量增加，批量删除。
 * 
 * @author 黄记新
 * @date 2014-12-23,上午9:54:24
 * 
 */
public interface IBaseService<T extends Serializable, PK extends Serializable> {

	PK deleteBatch(List<PK> list);

	PK deleteByPrimaryKey(String id);

	PK insert(T record);

	PK insertSelective(T record);

	T selectByPrimaryKey(PK id);

	PK updateByPrimaryKeySelective(T record);

	PK updateByPrimaryKey(T record);
}
