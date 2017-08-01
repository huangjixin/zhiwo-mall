package com.zwo.modules.system.dao;

import com.zwo.modules.system.domain.TbUserTradeMarke;
import com.zwo.modules.system.domain.TbUserTradeMarkeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TbUserTradeMarkeMapper extends Mapper<TbUserTradeMarke> {
    int countByExample(TbUserTradeMarkeCriteria example);

    int deleteByExample(TbUserTradeMarkeCriteria example);

    List<TbUserTradeMarke> selectByExample(TbUserTradeMarkeCriteria example);

    int updateByExampleSelective(@Param("record") TbUserTradeMarke record, @Param("example") TbUserTradeMarkeCriteria example);

    int updateByExample(@Param("record") TbUserTradeMarke record, @Param("example") TbUserTradeMarkeCriteria example);
}