package com.zwo.modules.member.dao;

import com.zwo.modules.member.domain.MemberProductDistribution;
import com.zwo.modules.member.domain.MemberProductDistributionCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface MemberProductDistributionMapper extends Mapper<MemberProductDistribution> {
    int countByExample(MemberProductDistributionCriteria example);

    int deleteByExample(MemberProductDistributionCriteria example);

    List<MemberProductDistribution> selectByExample(MemberProductDistributionCriteria example);

    int updateByExampleSelective(@Param("record") MemberProductDistribution record, @Param("example") MemberProductDistributionCriteria example);

    int updateByExample(@Param("record") MemberProductDistribution record, @Param("example") MemberProductDistributionCriteria example);
}