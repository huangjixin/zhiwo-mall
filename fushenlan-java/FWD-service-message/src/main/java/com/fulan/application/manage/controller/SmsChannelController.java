package com.fulan.application.manage.controller;

import com.fulan.api.message.domain.SmsChannel;
import com.fulan.application.service.system.SmsChannelService;
import com.fulan.application.util.domain.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description: 短信通道控制层
 * @author: guiyang
 * @date: 2018/3/5 11:03
 */
@Api(tags = "SmsChannelApi", description = "短信通道接口")
@Controller
@RequestMapping("/manage/smsChannel")
public class SmsChannelController {

    private static final Logger logger = LoggerFactory.getLogger(SmsChannelController.class);

    @Autowired
    private SmsChannelService channelService;

    @ApiOperation(value = "查询短信通道列表", notes = "查询短信通道列表", response = Response.class)
    @PostMapping("selectSmsChannel")
    @ResponseBody
    public List<SmsChannel> selectSmsChannel(){
        try {
            return channelService.selectSmsChannel();
        }catch (Exception e){
            logger.error("----查询短信通道列表失败----");
            e.printStackTrace();
            return null;
        }
    }

}