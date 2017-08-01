package com.zwo.modules.member.dao;

import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.domain.MemberCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface MemberMapper extends Mapper<Member> {
    int countByExample(MemberCriteria example);

    int deleteByExample(MemberCriteria example);

    List<Member> selectByExample(MemberCriteria example);

    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberCriteria example);

    int updateByExample(@Param("record") Member record, @Param("example") MemberCriteria example);
}