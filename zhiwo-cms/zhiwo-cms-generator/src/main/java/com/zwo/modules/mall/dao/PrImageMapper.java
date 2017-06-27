package com.zwo.modules.mall.dao;

import com.zwo.modules.mall.domain.PrImage;
import com.zwo.modules.mall.domain.PrImageCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface PrImageMapper extends Mapper<PrImage> {
    int countByExample(PrImageCriteria example);

    int deleteByExample(PrImageCriteria example);

    List<PrImage> selectByExample(PrImageCriteria example);

    int updateByExampleSelective(@Param("record") PrImage record, @Param("example") PrImageCriteria example);

    int updateByExample(@Param("record") PrImage record, @Param("example") PrImageCriteria example);
}