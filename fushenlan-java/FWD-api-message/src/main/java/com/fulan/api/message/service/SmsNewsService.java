package com.fulan.api.message.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.message.domain.SmsNews;
import com.fulan.api.message.domain.SmsTemplate;
import com.fulan.api.message.vo.SmsNewsResultVO;
import com.fulan.api.message.vo.SmsNewsStatisticsResultVO;
import com.fulan.api.message.vo.SmsNewsStatisticsVO;
import com.fulan.api.message.vo.SmsNewsVO;
import com.fulan.application.util.page.PageInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 消息查询管理接口定义
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
@FeignClient(name="message")
public interface SmsNewsService {

    /**
     * 查询消息模板
     */
    @PostMapping("/manage/smsNews/selectSmsNews")
    PageInfo<SmsNews> selectSmsNews(@RequestBody SmsNewsVO smsNewsVO);
    
    /**
     * 查询用量统计
     */
    @PostMapping("/manage/smsNews/selectSmsNewsStatistics")
    Page<SmsNewsStatisticsResultVO> selectSmsNewsStatistics(@RequestBody SmsNewsStatisticsVO smsNewsStatisticsVO);
    
    

    @GetMapping("/manage/smsNews/setlectSmsNewsList")
	PageInfo<SmsNewsResultVO> setlectSmsNewsList(
			@RequestParam(value="content",required=false) String content,
     		@RequestParam(value="phone",required=false) String phone,
     		@RequestParam(value="status",required=false) String status,
     		@RequestParam(value="systemCode",required=false) String systemCode,
     		@RequestParam(value="channelCode",required=false) String channelCode,
     		@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize,
            @RequestParam(value="type",required=false) String type);

    

    /**
     * 根据id查询消息详情
     */
    @PostMapping("/manage/smsNews/selectSmsNewsById")
    SmsNewsResultVO selectSmsNewsById(@RequestParam(value="id",required=false) Long id);
    
    
}