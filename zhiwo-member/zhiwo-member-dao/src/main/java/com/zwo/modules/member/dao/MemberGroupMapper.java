package com.zwo.modules.member.dao;

import com.zwo.modules.member.domain.MemberGroup;
import com.zwo.modules.member.domain.MemberGroupCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface MemberGroupMapper extends Mapper<MemberGroup> {
    int countByExample(MemberGroupCriteria example);

    int deleteByExample(MemberGroupCriteria example);

    List<MemberGroup> selectByExample(MemberGroupCriteria example);

    int updateByExampleSelective(@Param("record") MemberGroup record, @Param("example") MemberGroupCriteria example);

    int updateByExample(@Param("record") MemberGroup record, @Param("example") MemberGroupCriteria example);
}