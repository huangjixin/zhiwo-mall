package com.fulan.application.manage.controller;

import com.fulan.api.message.domain.SmsSystemChannel;
import com.fulan.api.message.vo.SmsSystemChannelVO;
import com.fulan.application.service.system.SmsSystemChannelService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 通道规则管理控制层
 * @author: guiyang
 * @date: 2018/3/5 11:03
 */
@Api(tags = "SmsSystemChannelApi", description = "通道规则接口")
@Controller
@RequestMapping("/manage/smsSystemChannel")
public class SmsSystemChannelController {

    private static final Logger logger = LoggerFactory.getLogger(SmsSystemChannelController.class);

    @Autowired
    private SmsSystemChannelService systemChannelService;

    @ApiOperation(value = "插入通道规则", notes = "插入通道规则", response = Response.class)
    @PostMapping("saveSystemChannel")
    @ResponseBody
    public Response<Object> saveSystemChannel(SmsSystemChannel systemChannel){
        try {
            systemChannelService.saveSmsSystemChannel(systemChannel);
        }catch (Exception e){
            logger.error("----插入通道规则失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"插入通道规则失败");
        }
        return new Response<>(Response.SUCCESS,"插入通道规则成功");
    }

    @ApiOperation(value = "查询系统通道规则列表", notes = "查询系统通道规则列表", response = Response.class)
    @PostMapping("selectSmsSystemChannel")
    @ResponseBody
    public SmsSystemChannel selectSmsSystemChannel(){
        try {
            return systemChannelService.selectSmsSystemChannel();
        }catch (Exception e){
            logger.error("----查询系统通道规则列表失败----");
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "删除通道规则", notes = "删除通道规则", response = Response.class)
    @PostMapping("deleteSmsSystemChannelById")
    @ResponseBody
    public Response<String> deleteSmsSystemChannelById(Long id){
        try {
            systemChannelService.deleteSmsSystemChannelById(id);
        }catch (Exception e){
            logger.error("----删除通道规则失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"删除通道规则失败,传入参数:id = "+id);
        }
        return new Response<>(Response.SUCCESS,"删除通道规则成功");
    }

    @ApiOperation(value = "修改通道规则", notes = "修改通道规则", response = Response.class)
    @PostMapping("updateSmsSystemChannel")
    @ResponseBody
    public Response<String> updateSmsSystemChannel(SmsSystemChannel systemChannel){
        try {
            systemChannelService.updateSmsSystemChannel(systemChannel);
        }catch (Exception e){
            logger.error("----修改通道规则失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"修改通道规则失败");
        }
        return new Response<>(Response.SUCCESS,"修改通道规则失败成功");
    }
}