package com.fulan.application.service.customer;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.fulan.api.message.domain.SmsTemplate;
import com.fulan.api.message.vo.SmsBusinessParameterVO;
import com.fulan.api.message.vo.SmsBusinessVO;

/**
 * @Description: 短信外部接口
 * @author: guiyang
 * @date: 2018/3/5 11:02
 */
public interface SmsService {

    boolean sendSms(SmsBusinessVO businessVO);

    String createTemplate(SmsTemplate smsTemplate);

    boolean updateTemplate(String tempId,String content,String type,String remark);

    boolean deleteTemplate(String tempId);

    QuerySendDetailsResponse querySendDetails(String bizId,String phone,Long pageIndex,Long pageSize);

}