package com.zwo.modules.system.dao;

import com.zwo.modules.system.domain.TbUserQuantityReport;
import com.zwo.modules.system.domain.TbUserQuantityReportCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TbUserQuantityReportMapper extends Mapper<TbUserQuantityReport> {
    int countByExample(TbUserQuantityReportCriteria example);

    int deleteByExample(TbUserQuantityReportCriteria example);

    List<TbUserQuantityReport> selectByExample(TbUserQuantityReportCriteria example);

    int updateByExampleSelective(@Param("record") TbUserQuantityReport record, @Param("example") TbUserQuantityReportCriteria example);

    int updateByExample(@Param("record") TbUserQuantityReport record, @Param("example") TbUserQuantityReportCriteria example);
}