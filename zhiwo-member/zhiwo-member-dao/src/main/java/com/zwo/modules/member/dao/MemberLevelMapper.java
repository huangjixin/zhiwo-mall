package com.zwo.modules.member.dao;

import com.zwo.modules.member.domain.MemberLevel;
import com.zwo.modules.member.domain.MemberLevelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface MemberLevelMapper extends Mapper<MemberLevel> {
    int countByExample(MemberLevelCriteria example);

    int deleteByExample(MemberLevelCriteria example);

    List<MemberLevel> selectByExample(MemberLevelCriteria example);

    int updateByExampleSelective(@Param("record") MemberLevel record, @Param("example") MemberLevelCriteria example);

    int updateByExample(@Param("record") MemberLevel record, @Param("example") MemberLevelCriteria example);
}