package com.zwo.xiyangyang.modules.pr.dao;

import com.zwo.xiyangyang.modules.pr.domain.PrPropertyValue;
import com.zwo.xiyangyang.modules.pr.domain.PrPropertyValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface PrPropertyValueMapper extends Mapper<PrPropertyValue> {
    int countByExample(PrPropertyValueExample example);

    int deleteByExample(PrPropertyValueExample example);

    List<PrPropertyValue> selectByExample(PrPropertyValueExample example);

    int updateByExampleSelective(@Param("record") PrPropertyValue record, @Param("example") PrPropertyValueExample example);

    int updateByExample(@Param("record") PrPropertyValue record, @Param("example") PrPropertyValueExample example);
}