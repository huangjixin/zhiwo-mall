package com.fulan.application.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fulan.api.personnel.domain.Apply;
import com.fulan.api.personnel.vo.ApplyScan;
@Mapper
public interface ApplyMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByPersonnelId(Long personnelId);

    int insert(Apply record);

    int insertSelective(Apply record);

    Apply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);
    
    Apply selectByPersonnelId(Long personnelId);
    
    Apply getApply(String personnelId);
    
    ApplyScan getScanApply(Long personnelId);
    
    int updateByPersonnelId(Apply record);
}