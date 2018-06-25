package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.message.domain.SmsSystemChannel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmsSystemChannelMapper extends BaseMapper<SmsSystemChannel>{
    int deleteByPrimaryKey(Long id);

    int insertSelective(SmsSystemChannel record);

    SmsSystemChannel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsSystemChannel record);

    int updateByPrimaryKey(SmsSystemChannel record);
}