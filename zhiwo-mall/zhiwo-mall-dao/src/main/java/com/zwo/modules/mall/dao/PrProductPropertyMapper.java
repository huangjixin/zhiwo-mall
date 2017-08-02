package com.zwo.modules.mall.dao;

import com.zwo.modules.mall.domain.PrProductProperty;
import com.zwo.modules.mall.domain.PrProductPropertyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface PrProductPropertyMapper extends Mapper<PrProductProperty> {
    int countByExample(PrProductPropertyCriteria example);

    int deleteByExample(PrProductPropertyCriteria example);

    List<PrProductProperty> selectByExample(PrProductPropertyCriteria example);

    int updateByExampleSelective(@Param("record") PrProductProperty record, @Param("example") PrProductPropertyCriteria example);

    int updateByExample(@Param("record") PrProductProperty record, @Param("example") PrProductPropertyCriteria example);
}