package com.fulan.application.service.system;


import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.message.domain.SmsNews;
import com.fulan.api.message.vo.SmsNewsResultVO;
import com.fulan.api.message.vo.SmsNewsStatisticsResultVO;
import com.fulan.api.message.vo.SmsNewsStatisticsVO;
import com.fulan.api.message.vo.SmsNewsVO;
import com.fulan.application.util.page.PageInfo;

import java.util.List;

/**
 * @Description: 消息查询管理接口定义
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
public interface SmsNewsService {

    /**
     * 查询消息模板
     */
    Page<SmsNewsResultVO> selectSmsNews(SmsNewsVO smsNewsVO);

    SmsNewsResultVO selectSmsNewsById(Long id,Long status);

    void saveSmsNews(SmsNewsResultVO smsNews);

    Page<SmsNewsStatisticsResultVO> selectSmsNewsStatistics(SmsNewsStatisticsVO smsNewsStatisticsVO);

	PageInfo<SmsNewsResultVO> setlectSmsNewsList(Page<SmsNewsResultVO> page, String content, String phone, String status,
			String systemCode, String channelCode, int pageNo, int pageSize,String type);
}