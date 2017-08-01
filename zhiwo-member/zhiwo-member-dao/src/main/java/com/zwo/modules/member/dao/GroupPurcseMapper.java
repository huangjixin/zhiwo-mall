package com.zwo.modules.member.dao;

import com.zwo.modules.member.domain.GroupPurcse;
import com.zwo.modules.member.domain.GroupPurcseCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface GroupPurcseMapper extends Mapper<GroupPurcse> {
    int countByExample(GroupPurcseCriteria example);

    int deleteByExample(GroupPurcseCriteria example);

    List<GroupPurcse> selectByExample(GroupPurcseCriteria example);

    int updateByExampleSelective(@Param("record") GroupPurcse record, @Param("example") GroupPurcseCriteria example);

    int updateByExample(@Param("record") GroupPurcse record, @Param("example") GroupPurcseCriteria example);
}