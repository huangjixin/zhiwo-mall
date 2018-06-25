package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.message.domain.SmsTemplate;
import com.fulan.api.message.vo.SmsTemplateResultVO;
import com.fulan.api.message.vo.SmsTemplateVO;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SmsTemplateMapper extends BaseMapper<SmsTemplate> {

    List<SmsTemplateResultVO> selectSmsTemplate(SmsTemplateVO smsTemplateVO);

	int listForManageCount(@Param("title") String title,@Param("type") String type,@Param("code") String code,@Param("masterCode") String masterCode);

	List<SmsTemplateResultVO> listForManage(Page<SmsTemplateResultVO> page,@Param("title") String title,@Param("type") String type,@Param("code") String code,@Param("masterCode") String masterCode,
			@Param("pageNo") int pageNo,@Param("pageSize") int pageSize);

	SmsTemplate getSmsTemplateByTempId(@Param("tempId")String tempId,@Param("status")Integer status);
}