package com.zwo.modules.mall.dao;

import com.zwo.modules.mall.domain.PrProductPropertyValue;
import com.zwo.modules.mall.domain.PrProductPropertyValueCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface PrProductPropertyValueMapper extends Mapper<PrProductPropertyValue> {
    int countByExample(PrProductPropertyValueCriteria example);

    int deleteByExample(PrProductPropertyValueCriteria example);

    List<PrProductPropertyValue> selectByExample(PrProductPropertyValueCriteria example);

    int updateByExampleSelective(@Param("record") PrProductPropertyValue record, @Param("example") PrProductPropertyValueCriteria example);

    int updateByExample(@Param("record") PrProductPropertyValue record, @Param("example") PrProductPropertyValueCriteria example);
}