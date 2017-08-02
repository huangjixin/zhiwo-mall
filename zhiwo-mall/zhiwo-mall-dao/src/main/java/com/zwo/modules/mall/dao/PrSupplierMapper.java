package com.zwo.modules.mall.dao;

import com.zwo.modules.mall.domain.PrSupplier;
import com.zwo.modules.mall.domain.PrSupplierCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface PrSupplierMapper extends Mapper<PrSupplier> {
    int countByExample(PrSupplierCriteria example);

    int deleteByExample(PrSupplierCriteria example);

    List<PrSupplier> selectByExample(PrSupplierCriteria example);

    int updateByExampleSelective(@Param("record") PrSupplier record, @Param("example") PrSupplierCriteria example);

    int updateByExample(@Param("record") PrSupplier record, @Param("example") PrSupplierCriteria example);
}