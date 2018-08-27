package com.zwo.modules.system.mapper;

import com.zwo.modules.system.domain.Resources;
import com.zwo.modules.system.domain.ResourcesCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface ResourcesMapper extends Mapper<Resources> {
    long countByExample(ResourcesCriteria example);

    int deleteByExample(ResourcesCriteria example);

    List<Resources> selectByExample(ResourcesCriteria example);

    int updateByExampleSelective(@Param("record") Resources record, @Param("example") ResourcesCriteria example);

    int updateByExample(@Param("record") Resources record, @Param("example") ResourcesCriteria example);
}