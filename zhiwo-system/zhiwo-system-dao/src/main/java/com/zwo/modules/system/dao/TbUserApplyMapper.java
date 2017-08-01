package com.zwo.modules.system.dao;

import com.zwo.modules.system.domain.TbUserApply;
import com.zwo.modules.system.domain.TbUserApplyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TbUserApplyMapper extends Mapper<TbUserApply> {
    int countByExample(TbUserApplyCriteria example);

    int deleteByExample(TbUserApplyCriteria example);

    List<TbUserApply> selectByExample(TbUserApplyCriteria example);

    int updateByExampleSelective(@Param("record") TbUserApply record, @Param("example") TbUserApplyCriteria example);

    int updateByExample(@Param("record") TbUserApply record, @Param("example") TbUserApplyCriteria example);
}