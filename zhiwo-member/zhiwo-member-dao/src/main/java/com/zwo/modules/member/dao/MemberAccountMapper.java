package com.zwo.modules.member.dao;

import com.zwo.modules.member.domain.MemberAccount;
import com.zwo.modules.member.domain.MemberAccountCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface MemberAccountMapper extends Mapper<MemberAccount> {
    int countByExample(MemberAccountCriteria example);

    int deleteByExample(MemberAccountCriteria example);

    List<MemberAccount> selectByExample(MemberAccountCriteria example);

    int updateByExampleSelective(@Param("record") MemberAccount record, @Param("example") MemberAccountCriteria example);

    int updateByExample(@Param("record") MemberAccount record, @Param("example") MemberAccountCriteria example);
}