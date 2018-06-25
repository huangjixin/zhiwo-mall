package com.fulan.application.service.customer.impl;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.BaseResult;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.common.SMSClient;
import cn.jsms.api.common.model.BatchSMSPayload;
import cn.jsms.api.common.model.RecipientPayload;
import cn.jsms.api.common.model.SMSPayload;
import cn.jsms.api.schedule.model.ScheduleResult;
import cn.jsms.api.schedule.model.ScheduleSMSPayload;
import cn.jsms.api.template.SendTempSMSResult;
import cn.jsms.api.template.TemplatePayload;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.fulan.api.message.domain.SmsNewsInfo;
import com.fulan.api.message.domain.SmsTemplate;
import com.fulan.api.message.vo.SmsBusinessParameterVO;
import com.fulan.api.message.vo.SmsBusinessVO;
import com.fulan.api.message.vo.SmsNewsResultVO;
import com.fulan.application.common.ConstantUtil;
import com.fulan.application.service.system.SmsNewsService;
import com.fulan.application.service.customer.SmsService;
import com.fulan.application.util.util.StringUtils;
import com.fulan.core.monitoring.cat.constant.Constant;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @Description: 短信极光外部接口
 * @author: guiyang
 * @date: 2018/3/5 11:02
 */
@Service("SMS_AURORA")
public class SmsAuroraServiceImpl implements SmsService {

    private static Logger looger = LoggerFactory.getLogger(SmsAuroraServiceImpl.class);

    private static final String SMS_AURORA = "SMS_AURORA";

    @Value("${sms.aurora.accessKeyId}")
    private String accessKeyId;
    @Value("${sms.aurora.accessKeySecret}")
    private String accessKeySecret;
    private SMSClient client;
    @Autowired
    private SmsNewsService smsNewsService;
    /**
     * 短信发送
     * @param businessVO
     * @return
     * @throws ClientException
     */
    public boolean sendSms(SmsBusinessVO businessVO) {
        try {
            if (client == null){
                client = new SMSClient(accessKeySecret,accessKeyId);
            }
            List<String> phoneList = StringUtils.stringToList(businessVO.getPhones());

            Map<String,String> mapNew = new HashMap<>();
            for (String string : businessVO.getParameter().keySet()) {
                mapNew.put(string, businessVO.getParameter().get(string).toString());
            }
            businessVO.setNewParam(mapNew);
            BaseResult res;
            if (phoneList.size()==1){
                if (businessVO.getTaskStatus()==SmsBusinessParameterVO.TASK_STATUS){
                    //单个定时发送
                    res = sendScheduleSMS(businessVO);
                }else{
                    //单个发送
                    res = sendTemplateSMS(businessVO);
                }
            }else{
                if (businessVO.getTaskStatus()==SmsBusinessParameterVO.TASK_STATUS){
                    //批量定时发送
                    res = sendBatchScheduleSMS(businessVO);
                }else{
                    //批量发送
                    res = sendBatchTemplateSMS(businessVO);
                }
            }
            assertTrue(res.isResultOK());
            if (res.isResultOK()){
                SmsNewsResultVO smsNewsResultVO = new SmsNewsResultVO();
                List<SmsNewsInfo> info = new ArrayList<>();
                smsNewsResultVO.setPhone(businessVO.getPhones());
                if (res instanceof SendSMSResult){
                    SendSMSResult sendSMSResult = (SendSMSResult) res;
                    String bzid = sendSMSResult.getMessageId();
                    SmsNewsInfo smsNewsInfo = new SmsNewsInfo();
                    smsNewsInfo.setPhone(phoneList.get(0));
                    smsNewsInfo.setMsgId(bzid);
                    smsNewsInfo.setStatus(1L);
                    info.add(smsNewsInfo);
                }else if (res instanceof ScheduleResult){
                    ScheduleResult scheduleResult = (ScheduleResult) res;
                    String bzid = scheduleResult.getScheduleId();
                    SmsNewsInfo smsNewsInfo = new SmsNewsInfo();
                    smsNewsInfo.setPhone(phoneList.get(0));
                    smsNewsInfo.setMsgId(bzid);
                    smsNewsInfo.setStatus(1L);
                    info.add(smsNewsInfo);
                }else{
                    for (int i = 0;i<phoneList.size();i++){
                        SmsNewsInfo smsNewsInfo = new SmsNewsInfo();
                        smsNewsInfo.setPhone(phoneList.get(i));
                        smsNewsInfo.setStatus(1L);
                        info.add(smsNewsInfo);
                    }
                }
                smsNewsResultVO.setPhone(phoneList.get(0));
                smsNewsResultVO.setContent(businessVO.getContent());
                smsNewsResultVO.setChannelCode(SMS_AURORA);
                smsNewsResultVO.setSendTime(new Date());
                smsNewsResultVO.setStatus(1L);
                smsNewsResultVO.setResult(ConstantUtil.SEND_SUCCESS);
                smsNewsResultVO.setTargetCount(phoneList.size());
                smsNewsResultVO.setFinishCount(phoneList.size());
                smsNewsResultVO.setSystemCode(businessVO.getSourceId());
                smsNewsResultVO.setType(businessVO.getType());
                smsNewsResultVO.setSystemCode(businessVO.getSourceId());
                smsNewsResultVO.setSmsNewsInfos(info);
                smsNewsService.saveSmsNews(smsNewsResultVO);
                return true;
            }else if (SmsTemplate.Type.SLAVE.equals(businessVO.getChannelType())){
                throw new RuntimeException("极光短信发送失败");
            }
        } catch (APIConnectionException e) {
            looger.error("------极光通道连接超时-----", e);
            return false;
        } catch (APIRequestException e) {
            looger.error("Error response from JPush server. Should review and fix it. ", e);
            looger.info("HTTP Status: " + e.getStatus());
            looger.info("Error Message: " + e.getMessage());
            return false;
        }
        return false;
    }

    /**
     * 单个发送
     * @param businessVO
     * @return
     * @throws APIConnectionException
     * @throws APIRequestException
     */
    private SendSMSResult sendTemplateSMS(SmsBusinessVO businessVO) throws APIConnectionException, APIRequestException {
        Map<String, String> param = businessVO.getNewParam();

        SMSPayload payload = SMSPayload.newBuilder()
                .setMobileNumber(businessVO.getPhones())
                .setTempId(Integer.valueOf(businessVO.getTempId()))
                .setTempPara(param)
                .build();
        JsonObject json = new JsonObject();
        json.addProperty("mobile", businessVO.getPhones());
        json.addProperty("temp_id", Integer.valueOf(businessVO.getTempId()));
        JsonObject tempJson = new JsonObject();
        for (Map.Entry<String, String> entry:param.entrySet()){
            tempJson.addProperty(entry.getKey(),entry.getValue());
        }
        json.add("temp_para", tempJson);
        assertEquals(payload.toJSON(), json);
        return client.sendTemplateSMS(payload);
    }

    /**
     * 批量发送
     * @param businessVO
     * @return
     * @throws APIConnectionException
     * @throws APIRequestException
     */
    private BaseResult sendBatchTemplateSMS(SmsBusinessVO businessVO) throws APIConnectionException, APIRequestException {
        Set<String> phoneSet = StringUtils.stringToSet(businessVO.getPhones());
        List<RecipientPayload> list = new ArrayList<>();
        for (String phone:phoneSet){
            RecipientPayload recipientPayload = new RecipientPayload.Builder()
                    .setMobile(phone)
                    .setTempPara(businessVO.getNewParam())
                    .build();
            list.add(recipientPayload);
        }
        RecipientPayload[] recipientPayloads = new RecipientPayload[list.size()];
        BatchSMSPayload smsPayload = BatchSMSPayload.newBuilder()
                .setTempId(Integer.valueOf(businessVO.getTempId()))
                .setRecipients(list.toArray(recipientPayloads))
                .build();
            return client.sendBatchTemplateSMS(smsPayload);
    }

    /**
     * 单个定时发送
     * @param businessVO
     * @return
     * @throws APIConnectionException
     * @throws APIRequestException
     */
    private ScheduleResult sendScheduleSMS(SmsBusinessVO businessVO) throws APIConnectionException, APIRequestException {
        ScheduleSMSPayload payload = ScheduleSMSPayload.newBuilder()
                .setMobileNumber(businessVO.getPhones())
                .setTempId(Integer.valueOf(businessVO.getTempId()))
                .setSendTime(businessVO.getTaskTime())
                .setTempPara(businessVO.getNewParam())
                .build();
             return client.sendScheduleSMS(payload);
    }

    /**
     * 批量定时发送
     * @param businessVO
     * @return
     * @throws APIConnectionException
     * @throws APIRequestException
     */
    private BaseResult sendBatchScheduleSMS(SmsBusinessVO businessVO) throws APIConnectionException, APIRequestException {
        List<RecipientPayload> list = new ArrayList<>();
        Set<String> phoneSet = StringUtils.stringToSet(businessVO.getPhones());
        for (String phone:phoneSet){
            RecipientPayload recipientPayload = new RecipientPayload.Builder()
                    .setMobile(phone)
                    .setTempPara(businessVO.getNewParam())
                    .build();
            list.add(recipientPayload);
        }
        RecipientPayload[] recipientPayloads = new RecipientPayload[list.size()];
        ScheduleSMSPayload smsPayload = ScheduleSMSPayload.newBuilder()
                .setSendTime(businessVO.getTaskTime())
                .setTempId(Integer.valueOf(businessVO.getTempId()))
                .setRecipients(list.toArray(recipientPayloads))
                .build();
        return client.sendBatchScheduleSMS(smsPayload);
    }

    public String createTemplate(SmsTemplate smsTemplate)  {
        try {
            if (client == null){
                client = new SMSClient(accessKeySecret,accessKeyId);
            }
            TemplatePayload payload = TemplatePayload.newBuilder()
                    .setTemplate(smsTemplate.getContent())
                    .setType(Constant.SMS_VALIDATE.equals(smsTemplate.getType())?1:2)
                    .setRemark(smsTemplate.getRemark())
                    .build();
            SendTempSMSResult  result = client.createTemplate(payload);
            if (result.isResultOK()){
                return String.valueOf(result.getTempId());
            }
        } catch (APIConnectionException e) {
            looger.error("------极光通道连接超时-----", e);
        } catch (APIRequestException e) {
            looger.error("Error response from JPush server. Should review and fix it. ", e);
            looger.info("HTTP Status: " + e.getStatus());
            looger.info("Error Message: " + e.getMessage());
        }
        return null;
    }

    public boolean updateTemplate(String tempId,String content,String type,String remark) {
        try {
            if (client == null){
                client = new SMSClient(accessKeySecret,accessKeyId);
            }
            Integer id = Integer.valueOf(tempId);
            TemplatePayload payload = TemplatePayload.newBuilder()
                    .setTempId(id)
                    .setTemplate(content)
                    .setType(Constant.SMS_VALIDATE.equals(type)?1:2)
                    .setRemark(remark)
                    .build();
            SendTempSMSResult result = client.updateTemplate(payload, id);
            if (result.isResultOK()){
                return true;
            }
        } catch (APIConnectionException e) {
            looger.error("------极光通道连接超时-----", e);
            return false;
        } catch (APIRequestException e) {
            looger.error("Error response from JPush server. Should review and fix it. ", e);
            looger.info("HTTP Status: " + e.getStatus());
            looger.info("Error Message: " + e.getMessage());
            return false;
        }
        return false;
    }

    public boolean deleteTemplate(String tempId) {
        try {
            if (client == null){
                client = new SMSClient(accessKeySecret,accessKeyId);
            }
            ResponseWrapper result = client.deleteTemplate(Integer.valueOf(tempId));
            if (result.responseCode == 200){
                return true;
            }
        } catch (APIConnectionException e) {
            looger.error("------极光通道连接超时-----", e);
            return false;
        } catch (APIRequestException e) {
            looger.error("Error response from JPush server. Should review and fix it. ", e);
            looger.info("HTTP Status: " + e.getStatus());
            looger.info("Error Message: " + e.getMessage());
            return false;
        }
        return false;
    }

    public QuerySendDetailsResponse querySendDetails(String bizId,String phone,Long pageIndex,Long pageSize){
           return null;
    }

}
