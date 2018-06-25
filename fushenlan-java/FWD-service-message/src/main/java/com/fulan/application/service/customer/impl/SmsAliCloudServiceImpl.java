package com.fulan.application.service.customer.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fulan.api.message.domain.SmsNewsInfo;
import com.fulan.api.message.domain.SmsTemplate;
import com.fulan.api.message.vo.SmsBusinessVO;
import com.fulan.api.message.vo.SmsNewsResultVO;
import com.fulan.application.common.ConstantUtil;
import com.fulan.application.service.system.SmsNewsService;
import com.fulan.application.service.customer.SmsService;
import com.fulan.application.util.util.JsonUtils;
import com.fulan.application.util.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 短信阿里云外部接口
 * @author: guiyang
 * @date: 2018/3/5 11:02
 */
@Service("SMS_ALI_CLOUD")
public class SmsAliCloudServiceImpl implements SmsService {

    private Logger logger = LoggerFactory.getLogger(SmsAliCloudServiceImpl.class);

    private static final String SMS_ALI_CLOUD = "SMS_ALI_CLOUD";

    @Value("${sms.aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${sms.aliyun.accessKeySecret}")
    private String accessKeySecret;
    @Value("FWD投保测试版")
    private String signName;
    @Value("${sms.aliyun.signTemplateCode}")
    private String signTemplateCode;
    @Value("${sms.aliyun.product}")
    private String product;
    @Value("${sms.aliyun.domain}")
    private String domain;

    private IAcsClient acsClient;
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
            if (acsClient==null){
                install();
            }
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(businessVO.getPhones());
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(signName);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(signTemplateCode);
            request.setTemplateParam(JsonUtils.objectToJson(businessVO.getParameter()));

            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

            if (ConstantUtil.OK.equals(sendSmsResponse.getCode())) {
                List<String> list = StringUtils.stringToList(businessVO.getPhones());
                SmsNewsResultVO smsNewsResultVO = new SmsNewsResultVO();
                List<SmsNewsInfo> info = new ArrayList<>();
                smsNewsResultVO.setPhone(businessVO.getPhones());

                Integer phoneSize = list.size();
                if (phoneSize==1) {
                    String bzid = sendSmsResponse.getBizId();
                    SmsNewsInfo smsNewsInfo = new SmsNewsInfo();
                    smsNewsInfo.setPhone(businessVO.getPhones());
                    smsNewsInfo.setMsgId(bzid);
                    smsNewsInfo.setStatus(1L);
                    info.add(smsNewsInfo);
                }else{
                    String bzid = sendSmsResponse.getBizId();
                    List<String> bzids = StringUtils.stringToList(bzid,"^");
                    for (int i = 0;i<phoneSize;i++){
                        SmsNewsInfo smsNewsInfo = new SmsNewsInfo();
                        smsNewsInfo.setPhone(list.get(i));
                        smsNewsInfo.setMsgId(bzids.get(i));
                        smsNewsInfo.setStatus(1L);
                        info.add(smsNewsInfo);
                    }
                }
                smsNewsResultVO.setPhone(list.get(0));
                smsNewsResultVO.setContent(businessVO.getContent());
                smsNewsResultVO.setChannelCode(SMS_ALI_CLOUD);
                smsNewsResultVO.setSendTime(new Date());
                smsNewsResultVO.setStatus(1L);
                smsNewsResultVO.setResult(sendSmsResponse.getMessage());
                smsNewsResultVO.setTargetCount(phoneSize);
                smsNewsResultVO.setFinishCount(phoneSize);
                smsNewsResultVO.setSystemCode(businessVO.getSourceId());
                smsNewsResultVO.setType(businessVO.getType());
                smsNewsResultVO.setSmsNewsInfos(info);
                smsNewsService.saveSmsNews(smsNewsResultVO);
                return true;
            }
            if (SmsTemplate.Type.SLAVE.equals(businessVO.getChannelType())){
                throw new RuntimeException(sendSmsResponse.getMessage());
            }
            return false;
        }catch (ServerException e){
            logger.error("----阿里云服务端访问异常----");
            e.printStackTrace();
            return false;
        }catch (ClientException e){
            logger.error("----阿里云客户端访问异常----");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String createTemplate(SmsTemplate smsTemplate) {
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

    private void install() {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            acsClient = new DefaultAcsClient(profile);
        } catch (ClientException e) {
             e.printStackTrace();
        }
    }


    public QuerySendDetailsResponse querySendDetails(String bizId,String phone,Long pageIndex,Long pageSize){

        try {
            //组装请求对象
            QuerySendDetailsRequest request = new QuerySendDetailsRequest();
            //必填-号码
            request.setPhoneNumber(phone);
            //可选-流水号
            request.setBizId(bizId);
            //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
            SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
            request.setSendDate(ft.format(new Date()));
            //必填-页大小
            request.setPageSize(pageSize);
            //必填-当前页码从1开始计数
            request.setCurrentPage(pageIndex);

            return acsClient.getAcsResponse(request);
        }catch (ServerException e){
            logger.error("阿里云服务端访问异常");
            e.printStackTrace();
            throw new RuntimeException("阿里云服务端访问异常" + e.getMessage());
        }catch (ClientException e){
            logger.error("阿里云客户端访问异常");
            e.printStackTrace();
            throw new RuntimeException("阿里云客户端访问异常" + e.getMessage());
        }
    }

}
