package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fulan.api.personnel.domain.WorkExperience;
@Mapper
public interface WorkExperienceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkExperience record);

    int insertSelective(WorkExperience record);

    WorkExperience selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkExperience record);

    int updateByPrimaryKey(WorkExperience record);
    
    List<WorkExperience> selectByPersonnelId(Long personnelId);
    
    int deleteByPersonnelId(Long personnelId);
}