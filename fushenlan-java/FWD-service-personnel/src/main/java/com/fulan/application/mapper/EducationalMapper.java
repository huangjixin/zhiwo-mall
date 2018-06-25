package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fulan.api.personnel.domain.Educational;
@Mapper
public interface EducationalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Educational record);

    int insertSelective(Educational record);

    Educational selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Educational record);

    int updateByPrimaryKey(Educational record);
    
    List<Educational> selectByPersonnelId(Long personnelId);
    
    int deleteByPersonnelId(Long personnelId);
    
}