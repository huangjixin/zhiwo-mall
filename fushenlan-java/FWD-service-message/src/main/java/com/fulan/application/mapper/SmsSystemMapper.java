package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.message.domain.SmsSystem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmsSystemMapper extends BaseMapper<SmsSystem> {

    int deleteByPrimaryKey(Long id);

    SmsSystem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsSystem record);

    int updateByPrimaryKey(SmsSystem record);
}