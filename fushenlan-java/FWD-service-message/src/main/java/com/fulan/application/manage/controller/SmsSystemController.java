package com.fulan.application.manage.controller;

import com.fulan.api.message.vo.SmsBusinessParameterVO;
import com.fulan.application.service.customer.SmsService;
import com.fulan.application.service.customer.impl.SmsEtonenetServiceImpl;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.spring.SpringUtil;
import com.fulan.application.util.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 后台调用短信接口
 * @author: guiyang
 * @date: 2018/3/5 11:03
 */
@Api(tags = "SmsBusinessApi", description = "后台调用短信接口")
@Controller
@RequestMapping("/sms/system")
public class SmsSystemController {

    private static final Logger logger = LoggerFactory.getLogger(SmsSystemController.class);

    /**
     * 发送短信
     */
    @ApiOperation(value = "发送短信", notes = "发送短信", response = Response.class)
    @PostMapping("sendSms")
    @ResponseBody
    public Response<String> sendSms(@RequestBody SmsBusinessParameterVO smsBusinessVO){
        try {

            if(StringUtils.isEmptry(smsBusinessVO.getPhones())){
                return new Response<>(Response.ERROR,"手机号码不能为空");
            }
            SmsService smsService = new SmsEtonenetServiceImpl();
            boolean success = smsService.sendSms(smsBusinessVO);
            if (success){
                return new Response<>(Response.SUCCESS, "发送成功");
            }
            return new Response<>(Response.ERROR, "发送失败");
        }catch (Exception e){
            logger.error("----发送失败----" + e.getMessage());
            e.printStackTrace();
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

}