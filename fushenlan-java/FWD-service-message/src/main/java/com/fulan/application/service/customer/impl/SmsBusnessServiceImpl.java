package com.fulan.application.service.customer.impl;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.fulan.api.message.domain.SmsNewsInfo;
import com.fulan.api.message.domain.SmsTemplate;
import com.fulan.api.message.vo.ReplyMessageVO;
import com.fulan.api.message.vo.SmsBusinessVO;
import com.fulan.api.message.vo.SmsNewsResultVO;
import com.fulan.application.common.Consts;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.customer.SmsBusinessService;
import com.fulan.application.service.customer.SmsService;
import com.fulan.application.service.system.SmsBlackUserService;
import com.fulan.application.service.system.SmsNewsInfoService;
import com.fulan.application.service.system.SmsNewsService;
import com.fulan.application.service.system.SmsTemplateService;
import com.fulan.application.util.spring.SpringUtil;
import com.fulan.application.util.util.JsonUtils;
import com.fulan.application.util.util.StringUtils;
import com.fulan.core.monitoring.cat.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 短信验证码生成业务层
 * @author: guiyang
 * @date: 2018/3/7 19:18
 */
@Service
public class SmsBusnessServiceImpl implements SmsBusinessService {

    private Logger logger = LoggerFactory.getLogger(SmsBusnessServiceImpl.class);

    private ExecutorService executorService = Executors.newFixedThreadPool(Consts.THREAD_COUNT_BASE*2);

    private static final String VALID_TIME = "validTime";

    @Autowired
    private SmsTemplateService templateService;

    @Autowired
    private SmsBlackUserService blackUserService;

    @Autowired
    private SmsNewsService smsNewsService;

    @Autowired
    private SmsNewsInfoService smsNewsInfoService;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * @param smsBusinessVO
     * @return
     */
    @Override
    public Long sendSms(SmsBusinessVO smsBusinessVO) {
        Long validateId = null;
        String validateValue = null;
        //根据模板编码获取模板
        SmsTemplate smsTemplate = templateService.getSmsTemplate(smsBusinessVO.getBusinessId());
        if (smsTemplate ==null)
          throw new RuntimeException("未配置 "+smsBusinessVO.getBusinessId()+" 模板");

        //排除手机号码黑名单
        List<String> phones = blackUserService.selectPhone(smsBusinessVO.getPhones(), smsTemplate.getType());
        if(phones==null||phones.size()==0){
            throw new RuntimeException("无发送手机号码,号码已被黑名单清除!");
        }
        String phone = StringUtils.listToString(phones);
        Map<String, Object> parameterMap = smsBusinessVO.getParameter();
        try {
            //验证类生成随机验证码
            if (Constant.SMS_VALIDATE.equals(smsTemplate.getType())){
                validateValue = generateRandom(smsTemplate.getParameterType(),parameterMap);
            }
            //验证传递过来的参数是否全部匹配
            Set<String> templateParameter = StringUtils.stringToSet(smsTemplate.getParameterType());
            for (String parameter :templateParameter){
                if (!parameterMap.containsKey(parameter)){
                    throw new RuntimeException("参数 "+ parameter+" 未赋值");
                }
            }
            //放入缓存中,带有有效期时间限制
            if (Constant.SMS_VALIDATE.equals(smsTemplate.getType())&&parameterMap.containsKey(VALID_TIME)){
                Long validTime = Long.valueOf(parameterMap.get(VALID_TIME).toString());
                //生成唯一性id
                validateId= idGenerator.generate();
                redisUtil.set(Constant.SMS_VALID_TIME + validateId ,validateValue,validTime, TimeUnit.MINUTES);
            }
            //把模板里面的内容编码替换值
            String content = smsTemplate.getContent();
            for (Map.Entry<String,Object> entry:parameterMap.entrySet()){
                if (content.contains(entry.getKey())){
                    Object value = entry.getValue();
                    if (value == null){
                        continue;
                    }
                    content = content.replace("${"+entry.getKey()+"}", value.toString());
                }
            }
            //主通道发送
            SmsService smsService = SpringUtil.getBean(smsTemplate.getMasterChannelCode(), SmsService.class);

            smsBusinessVO.setContent(content);
            smsBusinessVO.setPhones(phone);
            smsBusinessVO.setType(smsTemplate.getType());
            smsBusinessVO.setTempId(smsTemplate.getMasterTempId());
            smsBusinessVO.setChannelType(SmsTemplate.Type.MASTER);

            System.setProperty("proxySet","true");
            System.setProperty("http.proxyHost","10.22.2.101");
            System.setProperty("http.proxyPort","80");

            if(!smsService.sendSms(smsBusinessVO)){
                //如果主通道发送失败则用备用通道
                smsService = SpringUtil.getBean(smsTemplate.getSlaveChannelCode(), SmsService.class);
                smsBusinessVO.setTempId(smsTemplate.getSlaveTempId());
                smsBusinessVO.setChannelType(SmsTemplate.Type.SLAVE);
                if(!smsService.sendSms(smsBusinessVO)){
                    throw new RuntimeException("短信发送失败!");
                }
            }
        } catch (Exception e) {
            logger.error("-----发送短信失败-----"+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return validateId;
    }

    @Override
    public void queryCallback(ReplyMessageVO replyMessageVO) {
        SmsNewsInfo info = smsNewsInfoService.selectSmsNewsInfo(replyMessageVO.getSa(),replyMessageVO.getDa());
        String moContent = StringUtils.decodeHexStr(Integer.parseInt(replyMessageVO.getDc()),replyMessageVO.getSm());
        info.setReplyContent(moContent);
        info.setStatus(2L);
        smsNewsInfoService.updateSmsNewInfo(info);

    }


    public QuerySendDetailsResponse querySmsStatus(Long id,Long pageIndex, Long pageSize) throws ClientException {

        SmsNewsResultVO smsNewsResultVO = smsNewsService.selectSmsNewsById(id, null);
        if (smsNewsResultVO ==null)
            throw new RuntimeException("消息池未找到此消息");

        if (StringUtils.isEmptry(smsNewsResultVO.getChannelCode()))
            throw new RuntimeException("该消息发送通道为空");

        SmsService smsService = SpringUtil.getBean(smsNewsResultVO.getChannelCode(),SmsService.class);
        return smsService.querySendDetails(smsNewsResultVO.getBizId(),smsNewsResultVO.getPhone(),pageIndex,pageSize);
    }

    @Override
    public boolean smsValidate(Long validateId,String value) {
        if (!redisUtil.exists(Constant.SMS_VALID_TIME+validateId)){
            return false;
        }else{
            if (value.equals(redisUtil.get(Constant.SMS_VALID_TIME+validateId))){
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * 随机生成验证码
     * @param parameterType
     * @return
     */
    private String generateRandom(String parameterType, Map<String, Object> param){
        String parameter;
        int size;
        if (parameterType.contains(SmsBusinessVO.Type.FOUR_CODE.toString())){
            parameter = SmsBusinessVO.Type.FOUR_CODE.toString();
            size = 4;
        }else{
            parameter = SmsBusinessVO.Type.SIX_CODE.toString();
            size = 6;
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            Random r = new Random();
            s.append(r.nextInt(10));
        }
        param.put(parameter, s.toString());
        return s.toString();
    }

}