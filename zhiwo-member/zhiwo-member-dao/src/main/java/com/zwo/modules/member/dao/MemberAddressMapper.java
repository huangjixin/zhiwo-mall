package com.zwo.modules.member.dao;

import com.zwo.modules.member.domain.MemberAddress;
import com.zwo.modules.member.domain.MemberAddressCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface MemberAddressMapper extends Mapper<MemberAddress> {
    int countByExample(MemberAddressCriteria example);

    int deleteByExample(MemberAddressCriteria example);

    List<MemberAddress> selectByExample(MemberAddressCriteria example);

    int updateByExampleSelective(@Param("record") MemberAddress record, @Param("example") MemberAddressCriteria example);

    int updateByExample(@Param("record") MemberAddress record, @Param("example") MemberAddressCriteria example);
}