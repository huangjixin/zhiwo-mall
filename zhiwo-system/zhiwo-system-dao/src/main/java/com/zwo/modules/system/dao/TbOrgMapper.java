package com.zwo.modules.system.dao;

import com.zwo.modules.system.domain.TbOrg;
import com.zwo.modules.system.domain.TbOrgCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TbOrgMapper extends Mapper<TbOrg> {
    int countByExample(TbOrgCriteria example);

    int deleteByExample(TbOrgCriteria example);

    List<TbOrg> selectByExample(TbOrgCriteria example);

    int updateByExampleSelective(@Param("record") TbOrg record, @Param("example") TbOrgCriteria example);

    int updateByExample(@Param("record") TbOrg record, @Param("example") TbOrgCriteria example);
}