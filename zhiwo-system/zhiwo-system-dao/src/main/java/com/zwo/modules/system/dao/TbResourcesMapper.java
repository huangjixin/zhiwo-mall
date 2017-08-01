package com.zwo.modules.system.dao;

import com.zwo.modules.system.domain.TbResources;
import com.zwo.modules.system.domain.TbResourcesCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TbResourcesMapper extends Mapper<TbResources> {
    int countByExample(TbResourcesCriteria example);

    int deleteByExample(TbResourcesCriteria example);

    List<TbResources> selectByExample(TbResourcesCriteria example);

    int updateByExampleSelective(@Param("record") TbResources record, @Param("example") TbResourcesCriteria example);

    int updateByExample(@Param("record") TbResources record, @Param("example") TbResourcesCriteria example);
}