package com.zwo.modules.system.dao;

import com.zwo.modules.system.domain.TbUser;
import com.zwo.modules.system.domain.TbUserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TbUserMapper extends Mapper<TbUser> {
    int countByExample(TbUserCriteria example);

    int deleteByExample(TbUserCriteria example);

    List<TbUser> selectByExample(TbUserCriteria example);

    int updateByExampleSelective(@Param("record") TbUser record, @Param("example") TbUserCriteria example);

    int updateByExample(@Param("record") TbUser record, @Param("example") TbUserCriteria example);
}