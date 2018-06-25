package com.fulan.application.service.customer.impl;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.fulan.api.message.domain.SmsNewsInfo;
import com.fulan.api.message.domain.SmsTemplate;
import com.fulan.api.message.vo.SmsBusinessVO;
import com.fulan.api.message.vo.SmsNewsResultVO;
import com.fulan.application.common.ConstantUtil;
import com.fulan.application.common.HttpClientUtil;
import com.fulan.application.service.customer.SmsService;
import com.fulan.application.service.system.SmsNewsService;
import com.fulan.application.util.json.JsonUtil;
import com.fulan.application.util.util.JsonUtils;
import com.fulan.application.util.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @Description: 短信赛邮外部接口
 * @author: guiyang
 * @date: 2018/3/5 11:02
 */
@Service("SMS_SUB_MAIL")
public class SmsSubMailServiceImpl implements SmsService {

    private Logger logger = LoggerFactory.getLogger(SmsSubMailServiceImpl.class);

    private static final String SMS_SUB_MAIL = "SMS_SUB_MAIL";
    private static final String TO = "to";
    private static final String PROJECT = "project";
    private static final String VARS = "vars";
    private static final String MULTI = "multi";
    private static final String TEMPLATE_ID="template_id";
    private static final String SMS_TITLE = "sms_title";
    private static final String SMS_SIGNATURE = "sms_signature";
    private static final String SMS_CONTENT = "sms_content";
    private static final String STATUS = "status";
    private static final String SUCCESS = "success";
    private static final String OLD_PREFIX = "\\$\\{";
    private static final String OLD_SUFFIX = "}";
    private static final String NEW_PREFIX = "@var(";
    private static final String NEW_SUFFIX = ")";

    @Value("${sms.sub.mail.appid}")
    private String appId;
    @Value("${sms.sub.mail.appkey}")
    private String appKey;
    @Value("${sms.sub.mail.send.url}")
    private String url;
    @Value("${sms.sub.mail.multix.send.url}")
    private String multixUrl;
    @Value("${sms.sub.mail.template.url}")
    private String templateUrl;
    @Value("【富卫保险】")
    private String name;

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
            List<String> phoneList = StringUtils.stringToList(businessVO.getPhones());
            Integer phoneSize = phoneList.size();
            List<Map<String,Object>> newList = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put(PROJECT, businessVO.getTempId());
            String result;
            //发送短信处理
            if (phoneSize == 1){
                map.put(TO, businessVO.getPhones());
                map.put(VARS, JsonUtils.objectToJson(businessVO.getParameter()));
                result = HttpClientUtil.sendPost(appId,appKey,url, map);
            }else{
                for (String phone :phoneList){
                    Map<String, Object> param = new HashMap<>();
                    param.put(TO, phone);
                    param.put(VARS, businessVO.getParameter());
                    newList.add(param);
                }
                map.put(MULTI, JsonUtils.objectToJson(newList));
                result = HttpClientUtil.sendPost(appId,appKey,multixUrl, map);
            }
            //发送成功后获取返回值处理
            SmsNewsResultVO smsNewsResultVO = new SmsNewsResultVO();
            List<SmsNewsInfo> info = new ArrayList<>();
            smsNewsResultVO.setPhone(businessVO.getPhones());
            if (phoneSize == 1){
                Map<String, Object> resultMap = JsonUtils.jsonToMap(result);
                if (resultMap==null||!"success".equals(resultMap.get("status"))){
                    logger.error("------赛邮通道短信发送失败!------");
                    return false;
                }
                logger.info("------赛邮通道短信发送成功!------" + result);
                String bzid = resultMap.get("send_id")==null?null:resultMap.get("send_id").toString();
                SmsNewsInfo smsNewsInfo = new SmsNewsInfo();
                smsNewsInfo.setPhone(phoneList.get(0));
                smsNewsInfo.setMsgId(bzid);
                smsNewsInfo.setStatus(1L);
                info.add(smsNewsInfo);
            }else{
                List<Map> list = JsonUtils.jsonToList(result, Map.class);
                if (list!=null&&list.size()>0){
                    for (Map resultMap :list){
                        if (resultMap!=null&&"success".equals(resultMap.get("status"))){
                            SmsNewsInfo smsNewsInfo = new SmsNewsInfo();
                            String phone = resultMap.get("to")==null?null:resultMap.get("to").toString();
                            String bzid = resultMap.get("send_id")==null?null:resultMap.get("send_id").toString();
                            smsNewsInfo.setPhone(phone);
                            smsNewsInfo.setMsgId(bzid);
                            smsNewsInfo.setStatus(1L);
                            info.add(smsNewsInfo);
                        }else{
                            logger.error("------赛邮通道短信发送失败!------");
                            return false;
                        }
                    }
                }
            }
            smsNewsResultVO.setPhone(phoneList.get(0));
            smsNewsResultVO.setContent(businessVO.getContent());
            smsNewsResultVO.setChannelCode(SMS_SUB_MAIL);
            smsNewsResultVO.setSendTime(new Date());
            smsNewsResultVO.setStatus(1L);
            smsNewsResultVO.setResult(ConstantUtil.SEND_SUCCESS);
            smsNewsResultVO.setTargetCount(phoneList.size());
            smsNewsResultVO.setFinishCount(phoneList.size());
            smsNewsResultVO.setType(businessVO.getType());
            smsNewsResultVO.setSystemCode(businessVO.getSourceId());
            smsNewsResultVO.setSmsNewsInfos(info);
            smsNewsService.saveSmsNews(smsNewsResultVO);
            logger.info("------赛邮通道短信发送成功!------" + result);
            return true;
        } catch (Exception e) {
            logger.error("------赛邮通道短信发送失败!------");
            return false;
        }
    }

    @Override
    public String createTemplate(SmsTemplate smsTemplate) {
        String content = smsTemplate.getContent();
               content = content.replaceAll(OLD_PREFIX,NEW_PREFIX);
               content = content.replaceAll(OLD_SUFFIX,NEW_SUFFIX);
        Map<String, Object> map = new HashMap<>();
        map.put(SMS_TITLE, smsTemplate.getTitle());
        map.put(SMS_CONTENT, content);
        map.put(SMS_SIGNATURE, name);
        String result = HttpClientUtil.sendPost(appId,appKey,templateUrl, map);
        try {
            Map<String,Object> resultMap = JsonUtils.jsonToMap(result);
            if(SUCCESS.equals(resultMap.get(STATUS))){
                Object tempId =resultMap.get(TEMPLATE_ID);
                return tempId == null?null:tempId.toString();
            }
            throw new RuntimeException("-----赛邮模板创建异常-----"+smsTemplate);
        } catch (IOException e) {
            logger.info("-----赛邮模板创建数据解析异常-----"+result);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateTemplate(String tempId, String content, String type, String remark) {
        return true;
    }

    @Override
    public boolean deleteTemplate(String tempId) {
        return  true;
    }

    public QuerySendDetailsResponse querySendDetails(String bizId,String phone,Long pageIndex,Long pageSize){
              return null;
    }

}
