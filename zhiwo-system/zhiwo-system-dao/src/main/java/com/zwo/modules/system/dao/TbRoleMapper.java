package com.zwo.modules.system.dao;

import com.zwo.modules.system.domain.TbRole;
import com.zwo.modules.system.domain.TbRoleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TbRoleMapper extends Mapper<TbRole> {
    int countByExample(TbRoleCriteria example);

    int deleteByExample(TbRoleCriteria example);

    List<TbRole> selectByExample(TbRoleCriteria example);

    int updateByExampleSelective(@Param("record") TbRole record, @Param("example") TbRoleCriteria example);

    int updateByExample(@Param("record") TbRole record, @Param("example") TbRoleCriteria example);
}