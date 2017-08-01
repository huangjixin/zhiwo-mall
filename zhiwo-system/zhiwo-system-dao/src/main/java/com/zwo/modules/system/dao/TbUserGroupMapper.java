package com.zwo.modules.system.dao;

import com.zwo.modules.system.domain.TbUserGroup;
import com.zwo.modules.system.domain.TbUserGroupCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TbUserGroupMapper extends Mapper<TbUserGroup> {
    int countByExample(TbUserGroupCriteria example);

    int deleteByExample(TbUserGroupCriteria example);

    List<TbUserGroup> selectByExample(TbUserGroupCriteria example);

    int updateByExampleSelective(@Param("record") TbUserGroup record, @Param("example") TbUserGroupCriteria example);

    int updateByExample(@Param("record") TbUserGroup record, @Param("example") TbUserGroupCriteria example);
}