/**
 * 
 */
package com.zwo.modules.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @author 黄记新
 *
 */
public interface BaseMapper<T> {
	
	int countByExample(Object example);

    
    int deleteByExample(Object example);

   
    int deleteByPrimaryKey(String id);

    
    int insert(T record);

   
    int insertSelective(T record);

    
    List<T> selectByExample(Object example);

    
    T selectByPrimaryKey(String id);

    
    int updateByExampleSelective(@Param("record") T record, @Param("example") Object example);

    
    int updateByExample(@Param("record") T record, @Param("example") Object example);

    
    int updateByPrimaryKeySelective(T record);

   
    int updateByPrimaryKey(T record);
}
