package com.zwo.modules.member.dao;

import com.zwo.modules.member.domain.MemberProfit;
import com.zwo.modules.member.domain.MemberProfitCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface MemberProfitMapper extends Mapper<MemberProfit> {
    int countByExample(MemberProfitCriteria example);

    int deleteByExample(MemberProfitCriteria example);

    List<MemberProfit> selectByExample(MemberProfitCriteria example);

    int updateByExampleSelective(@Param("record") MemberProfit record, @Param("example") MemberProfitCriteria example);

    int updateByExample(@Param("record") MemberProfit record, @Param("example") MemberProfitCriteria example);
}