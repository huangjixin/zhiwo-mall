package com.fulan.application.service.customer;


import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.fulan.api.message.vo.ReplyMessageVO;
import com.fulan.api.message.vo.SmsBusinessVO;

/**
 * @Description:  短信验证码生成接口定义
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
public interface SmsBusinessService {

    /**
     * 短信验证码生成
     */
    Long sendSms(SmsBusinessVO smsBusinessVO);

    void queryCallback(ReplyMessageVO replyMessageVO);

    QuerySendDetailsResponse querySmsStatus(Long id, Long pageIndex, Long pageSize) throws ClientException;

    boolean smsValidate(Long validateId,String value);
}