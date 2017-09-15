package com.zwo.modules.member.dao;

import com.zwo.modules.member.domain.MemberPlayAccount;
import com.zwo.modules.member.domain.MemberPlayAccountCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface MemberPlayAccountMapper extends Mapper<MemberPlayAccount> {
    int countByExample(MemberPlayAccountCriteria example);

    int deleteByExample(MemberPlayAccountCriteria example);

    List<MemberPlayAccount> selectByExample(MemberPlayAccountCriteria example);

    int updateByExampleSelective(@Param("record") MemberPlayAccount record, @Param("example") MemberPlayAccountCriteria example);

    int updateByExample(@Param("record") MemberPlayAccount record, @Param("example") MemberPlayAccountCriteria example);
}