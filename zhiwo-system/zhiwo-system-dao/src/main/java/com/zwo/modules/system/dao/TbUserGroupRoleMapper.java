package com.zwo.modules.system.dao;

import com.zwo.modules.system.domain.TbUserGroupRole;
import com.zwo.modules.system.domain.TbUserGroupRoleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TbUserGroupRoleMapper extends Mapper<TbUserGroupRole> {
    int countByExample(TbUserGroupRoleCriteria example);

    int deleteByExample(TbUserGroupRoleCriteria example);

    List<TbUserGroupRole> selectByExample(TbUserGroupRoleCriteria example);

    int updateByExampleSelective(@Param("record") TbUserGroupRole record, @Param("example") TbUserGroupRoleCriteria example);

    int updateByExample(@Param("record") TbUserGroupRole record, @Param("example") TbUserGroupRoleCriteria example);
}