package com.zwo.modules.system.dao;

import com.zwo.modules.system.domain.TbRoleResources;
import com.zwo.modules.system.domain.TbRoleResourcesCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TbRoleResourcesMapper extends Mapper<TbRoleResources> {
    int countByExample(TbRoleResourcesCriteria example);

    int deleteByExample(TbRoleResourcesCriteria example);

    List<TbRoleResources> selectByExample(TbRoleResourcesCriteria example);

    int updateByExampleSelective(@Param("record") TbRoleResources record, @Param("example") TbRoleResourcesCriteria example);

    int updateByExample(@Param("record") TbRoleResources record, @Param("example") TbRoleResourcesCriteria example);
}