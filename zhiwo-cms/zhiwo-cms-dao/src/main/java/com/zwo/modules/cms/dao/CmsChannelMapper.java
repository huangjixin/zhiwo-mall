package com.zwo.modules.cms.dao;

import com.zwo.modules.cms.domain.CmsChannel;
import com.zwo.modules.cms.domain.CmsChannelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface CmsChannelMapper extends Mapper<CmsChannel> {
    int countByExample(CmsChannelCriteria example);

    int deleteByExample(CmsChannelCriteria example);

    List<CmsChannel> selectByExample(CmsChannelCriteria example);

    int updateByExampleSelective(@Param("record") CmsChannel record, @Param("example") CmsChannelCriteria example);

    int updateByExample(@Param("record") CmsChannel record, @Param("example") CmsChannelCriteria example);
}