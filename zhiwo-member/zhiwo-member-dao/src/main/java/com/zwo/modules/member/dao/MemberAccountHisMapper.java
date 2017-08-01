package com.zwo.modules.member.dao;

import com.zwo.modules.member.domain.MemberAccountHis;
import com.zwo.modules.member.domain.MemberAccountHisCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface MemberAccountHisMapper extends Mapper<MemberAccountHis> {
    int countByExample(MemberAccountHisCriteria example);

    int deleteByExample(MemberAccountHisCriteria example);

    List<MemberAccountHis> selectByExample(MemberAccountHisCriteria example);

    int updateByExampleSelective(@Param("record") MemberAccountHis record, @Param("example") MemberAccountHisCriteria example);

    int updateByExample(@Param("record") MemberAccountHis record, @Param("example") MemberAccountHisCriteria example);
}