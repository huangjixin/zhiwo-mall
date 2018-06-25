package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.message.domain.SmsNews;
import com.fulan.api.message.vo.SmsNewsResultVO;
import com.fulan.api.message.vo.SmsNewsStatisticsResultVO;
import com.fulan.api.message.vo.SmsNewsStatisticsVO;
import com.fulan.api.message.vo.SmsNewsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SmsNewsMapper extends BaseMapper<SmsNews>{

    List<SmsNewsResultVO> selectSmsNews(SmsNewsVO smsNewsVO);

    List<SmsNewsStatisticsResultVO> selectSmsNewsStatistics(SmsNewsStatisticsVO smsNewsStatisticsVO);

	int listForManageCount(@Param("content") String content,
						   @Param("phone") String phone,
						   @Param("status") String status,
						   @Param("systemCode") String systemCode,
						   @Param("channelCode") String channelCode,
							@Param("type") String type);

	List<SmsNewsResultVO> listForManage(Page<SmsNewsResultVO> page,
							   @Param("content") String content,
							   @Param("phone") String phone,
							   @Param("status") String status,
							   @Param("systemCode") String systemCode,
							   @Param("channelCode") String channelCode,
							   @Param("pageNo") int pageNo,
							   @Param("pageSize") int pageSize,
							   @Param("type") String type);
}