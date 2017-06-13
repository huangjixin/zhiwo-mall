package com.zwotech.modules.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

/**
 * Created by Martin on 2016/7/01.
 */
public interface IBaseMapper<T> extends Mapper<T>{
	/*int deleteBatch(List list);*/

	int insertBatch(List<T> list);

	/*int countByExample(Object example);

	int deleteByExample(Object example);

	int deleteByPrimaryKey(String id);

	int insert(T record);

	int insertSelective(T record);

	List<T> selectByExample(Object example);

	T selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") T record, @Param("example") Object example);

	int updateByExample(@Param("record") T record, @Param("example") Object example);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);*/
	/*
	 * @SelectProvider(type = MapperProvider.class, method = "dynamicSQL") T
	 * selectOne(T record);
	 * 
	 * @SelectProvider(type = MapperProvider.class, method = "dynamicSQL")
	 * List<T> select(T record);
	 * 
	 * @SelectProvider(type = MapperProvider.class, method = "dynamicSQL") int
	 * selectCount(T record);
	 * 
	 * @SelectProvider(type = MapperProvider.class, method = "dynamicSQL") T
	 * selectByPrimaryKey(Object key);
	 * 
	 * 
	 * @DeleteProvider(type = MapperProvider.class, method = "dynamicSQL") int
	 * delete(T record);
	 * 
	 * @DeleteProvider(type = MapperProvider.class, method = "dynamicSQL") int
	 * deleteByPrimaryKey(Object key);
	 * 
	 * @UpdateProvider(type = MapperProvider.class, method = "dynamicSQL") int
	 * updateByPrimaryKey(T record);
	 * 
	 * @UpdateProvider(type = MapperProvider.class, method = "dynamicSQL") int
	 * updateByPrimaryKeySelective(T record);
	 * 
	 * @SelectProvider(type = MapperProvider.class, method = "dynamicSQL") int
	 * selectCountByExample(Object example);
	 * 
	 * @DeleteProvider(type = MapperProvider.class, method = "dynamicSQL") int
	 * deleteByExample(Object example);
	 * 
	 * @SelectProvider(type = MapperProvider.class, method = "dynamicSQL")
	 * List<T> selectByExample(Object example);
	 * 
	 * @UpdateProvider(type = MapperProvider.class, method = "dynamicSQL") int
	 * updateByExampleSelective(@Param("record") T record, @Param("example")
	 * Object example);
	 * 
	 * @UpdateProvider(type = MapperProvider.class, method = "dynamicSQL") int
	 * updateByExample(@Param("record") T record, @Param("example") Object
	 * example);
	 * 
	 * List<T> getAllByPage(RowBounds rowBounds);
	 */

}
