package com.fulan.application.mapper;

import java.util.List;

import com.fulan.api.personnel.domain.PersonnelPaper;

public interface PersonnelPaperMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PersonnelPaper record);

    int insertSelective(PersonnelPaper record);

    PersonnelPaper selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PersonnelPaper record);

    int updateByPrimaryKey(PersonnelPaper record);
    
    /**
     * 查询代理人问答评价是否存在
     * */
    List<PersonnelPaper> getPersonnelPaperList(PersonnelPaper personnelPaper);
    /**
     * 代理人答题评价更新
     * */
    int updateByPersonnelPaper(PersonnelPaper personnelPaper);
    
    int deleteByRecord(PersonnelPaper record);
}