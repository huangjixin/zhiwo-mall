package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fulan.api.personnel.domain.ErTag;

public interface TagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ErTag record);

    int insertSelective(ErTag record);

    ErTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ErTag record);

    int updateByPrimaryKey(ErTag record);

	int removeErTag(@Param(value = "personnelId")Long personnelId, @Param(value = "tagId")Long tagId);
	
	
	int removeErTagbypersonnelId(@Param(value = "personnelId")Long personnelId);
	
	
	List<ErTag> findBypersonnelId(@Param(value = "personnelId")Integer personnelId);
	
}