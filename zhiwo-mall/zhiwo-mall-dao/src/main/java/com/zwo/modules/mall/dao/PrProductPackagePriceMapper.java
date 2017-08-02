package com.zwo.modules.mall.dao;

import com.zwo.modules.mall.domain.PrProductPackagePrice;
import com.zwo.modules.mall.domain.PrProductPackagePriceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface PrProductPackagePriceMapper extends Mapper<PrProductPackagePrice> {
    int countByExample(PrProductPackagePriceCriteria example);

    int deleteByExample(PrProductPackagePriceCriteria example);

    List<PrProductPackagePrice> selectByExample(PrProductPackagePriceCriteria example);

    int updateByExampleSelective(@Param("record") PrProductPackagePrice record, @Param("example") PrProductPackagePriceCriteria example);

    int updateByExample(@Param("record") PrProductPackagePrice record, @Param("example") PrProductPackagePriceCriteria example);
}