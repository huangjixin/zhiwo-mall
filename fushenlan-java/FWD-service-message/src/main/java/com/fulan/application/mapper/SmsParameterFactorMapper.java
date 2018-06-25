package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.message.domain.SmsParameterFactor;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmsParameterFactorMapper extends BaseMapper<SmsParameterFactor>{
	int listForManageCount();

	List<SmsParameterFactor> listForManage(Page<SmsParameterFactor> page, int pageNo, int pageSize);

	SmsParameterFactor selectParameterById(String id);

	List<SmsParameterFactor> listFactor();
}