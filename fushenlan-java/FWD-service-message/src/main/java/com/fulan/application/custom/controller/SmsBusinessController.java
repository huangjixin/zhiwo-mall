package com.fulan.application.custom.controller;

import com.fulan.api.message.vo.ReplyMessageVO;
import com.fulan.api.message.vo.SmsBusinessVO;
import com.fulan.application.service.customer.SmsBusinessService;
import com.fulan.application.service.customer.SmsService;
import com.fulan.application.service.customer.impl.SmsEtonenetServiceImpl;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.spring.SpringUtil;
import com.fulan.application.util.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @Description: 业务层调用短信接口
 * @author: guiyang
 * @date: 2018/3/5 11:03
 */
@Api(tags = "SmsBusinessApi", description = "业务层调用短信接口")
@Controller
@RequestMapping("/sms/business")
public class SmsBusinessController {

    private static final Logger logger = LoggerFactory.getLogger(SmsBusinessController.class);

    private static final Integer SEND_TYPE = 1;

    @Autowired
    private SmsBusinessService smsBusinessService;

    /**
     * 发送短信
     */
    @ApiOperation(value = "发送短信", notes = "发送短信", response = Response.class)
    @PostMapping(value = "sendSms", produces = { "application/json;charset=utf-8" })
    @ResponseBody
    public Response<Object> sendSms(@RequestBody SmsBusinessVO smsBusinessVO){
        try {
            if(StringUtils.isEmptry(smsBusinessVO.getPhones())){
                return new Response<>(Response.ERROR,"手机号码不能为空");
            }
            Response<Object> resp = new Response<>(Response.SUCCESS, "发送成功");
            if (smsBusinessVO.getSendType()==SEND_TYPE){
                if(StringUtils.isEmptry(smsBusinessVO.getContent())){
                    throw new RuntimeException("短信内容不能为空");
                }
                SmsService smsService = SpringUtil.getBean("SMS_ETONENET",SmsService.class);
                boolean success = smsService.sendSms(smsBusinessVO);
                if (!success){
                    return new Response<>(Response.ERROR, "发送失败");
                }
            }else{
                if(StringUtils.isEmptry(smsBusinessVO.getBusinessId())){
                    throw new RuntimeException("模板编号不能为空");
                }
                Long validateId = smsBusinessService.sendSms(smsBusinessVO);
                if (validateId!=null){
                    Map<String,String> result = new HashMap<>();
                    result.put("validateId",validateId.toString());
                    resp.setData(result);
                }
            }
            return resp;
        }catch (Exception e){
            logger.error("----发送失败----" + e.getMessage());
            e.printStackTrace();
                return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    /**
     * 验证短信
     */
    @ApiOperation(value = "验证短信", notes = "验证短信", response = Response.class)
    @PostMapping("smsValidate")
    @ResponseBody
    public Response<String> smsValidate(@RequestParam Long validateId,@RequestParam String value){
        try {
            if(validateId == null){
                return new Response<>(Response.ERROR,"验证码查询id不能为空");
            }
            if(StringUtils.isEmptry(value)){
                return new Response<>(Response.ERROR,"验证码值不能为空");
            }
            Response<String> resp;
            boolean result = smsBusinessService.smsValidate(validateId,value);
            if (result){
                resp = new Response<>(Response.SUCCESS, "验证成功");
            }else{
                resp = new Response<>(Response.ERROR, "验证失败");
            }
            return resp;
        }catch (Exception e){
            logger.error("----验证失败----" + e.getMessage());
            e.printStackTrace();
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }


    /**
     * 查询短信发送状态
     */
    @ApiOperation(value = "查询短信发送状态", notes = "查询短信发送状态", response = Response.class)
    @PostMapping("querySmsStatus")
    @ResponseBody
    public Response<Object> querySmsStatus(@RequestParam Long id, Long pageIndex, Long pageSize){
        try {
            Response<Object> response =  new Response<>(Response.SUCCESS,"短信状态查询成功");
            response.setData(smsBusinessService.querySmsStatus(id,pageIndex,pageSize));
            return response;
        }catch (Exception e){
            logger.error("----查询短信发送状态----" + e.getMessage());
            e.printStackTrace();
            return new Response<>(Response.ERROR,"短信状态查询失败! " + e.getMessage());
        }
    }

    /**
     * 短信交互回调
     */
    @ApiOperation(value = "短信上行交互回调", notes = "短信交互回调", response = Response.class)
    @GetMapping("queryCallback")
    @ResponseBody
    public Response<Object> queryCallback(ReplyMessageVO replyMessageVO){
        try {
            Response<Object> response =  new Response<>(Response.SUCCESS,"短信上行交互回调成功");
            smsBusinessService.queryCallback(replyMessageVO);
            response.setData(replyMessageVO);
            logger.info("----短信上行交互回调成功----");
            return response;
        }catch (Exception e){
            logger.error("----短信上行交互回调失败----" + e.getMessage());
            e.printStackTrace();
            return new Response<>(Response.ERROR,"短信上行交互回调失败! " + e.getMessage());
        }
    }
}