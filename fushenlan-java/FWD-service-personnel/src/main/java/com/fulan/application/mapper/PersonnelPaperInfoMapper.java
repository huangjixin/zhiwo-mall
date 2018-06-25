package com.fulan.application.mapper;

import java.util.List;

import com.fulan.api.personnel.domain.PersonnelPaper;
import com.fulan.api.personnel.domain.PersonnelPaperInfo;

public interface PersonnelPaperInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PersonnelPaperInfo record);

    int insertSelective(PersonnelPaperInfo record);

    PersonnelPaperInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PersonnelPaperInfo record);

    int updateByPrimaryKey(PersonnelPaperInfo record);
    /**
     * 查询代理人试题信息是否存在
     * */
    List<PersonnelPaperInfo> getPersonnelPaperInfoList(PersonnelPaperInfo personnelPaperInfo);
    /**
     * 数据插入之前先进行删除操作
     * */
    int deleteByPersonnelPaperInfo(List<PersonnelPaperInfo> list);
    /**
     * 数据批量插入
     * */
    int insertByList(List<PersonnelPaperInfo> list);
    
    int deleteByRecord(PersonnelPaper record);
    
    
}