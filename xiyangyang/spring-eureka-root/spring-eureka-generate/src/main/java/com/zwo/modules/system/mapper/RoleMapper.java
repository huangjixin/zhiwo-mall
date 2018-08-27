package com.zwo.modules.system.mapper;

import com.zwo.modules.system.domain.Role;
import com.zwo.modules.system.domain.RoleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface RoleMapper extends Mapper<Role> {
    long countByExample(RoleCriteria example);

    int deleteByExample(RoleCriteria example);

    List<Role> selectByExample(RoleCriteria example);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleCriteria example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleCriteria example);
}