package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fulan.api.personnel.domain.FamilyMember;
@Mapper
public interface FamilyMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FamilyMember record);

    int insertSelective(FamilyMember record);

    FamilyMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FamilyMember record);

    int updateByPrimaryKey(FamilyMember record);
    
    List<FamilyMember> selectByPersonnelId(Long personnelId);
    
    int deleteByPersonnelId(Long personnelId);
}