package com.zwo.modules.member.dao;

import com.zwo.modules.member.domain.GroupPurcseMember;
import com.zwo.modules.member.domain.GroupPurcseMemberCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface GroupPurcseMemberMapper extends Mapper<GroupPurcseMember> {
    int countByExample(GroupPurcseMemberCriteria example);

    int deleteByExample(GroupPurcseMemberCriteria example);

    List<GroupPurcseMember> selectByExample(GroupPurcseMemberCriteria example);

    int updateByExampleSelective(@Param("record") GroupPurcseMember record, @Param("example") GroupPurcseMemberCriteria example);

    int updateByExample(@Param("record") GroupPurcseMember record, @Param("example") GroupPurcseMemberCriteria example);
}