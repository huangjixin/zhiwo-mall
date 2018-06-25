package com.fulan.application.service.customer.impl;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.fulan.api.message.domain.SmsNewsInfo;
import com.fulan.api.message.domain.SmsTemplate;
import com.fulan.api.message.vo.SmsBusinessVO;
import com.fulan.api.message.vo.SmsNewsResultVO;
import com.fulan.application.common.ConstantUtil;
import com.fulan.application.common.HttpClientUtil;
import com.fulan.application.service.system.SmsNewsService;
import com.fulan.application.service.customer.SmsService;
import com.fulan.application.util.util.StringUtils;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @Description: 短信移通外部接口
 * @author: guiyang
 * @date: 2018/3/5 11:02
 */
@Service("SMS_ETONENET")
public class SmsEtonenetServiceImpl implements SmsService {

    private Logger logger = LoggerFactory.getLogger(SmsEtonenetServiceImpl.class);

    @Value("${sms.et.pid}")
    private String pid;
    @Value("${sms.et.password}")
    private  String password;
    @Value("${sms.et.sc}")
    private String sc;
    @Value("${sms.et.url}")
    private String url;
    /**
     * 单条发送
     */
    private static final String MT_REQUEST = "MT_REQUEST";
    /**
     * 批量发送
     */
    private static final String MULTI_MT_REQUEST = "MULTI_MT_REQUEST";
    /**
     * GBK 编码对应 dc=15，UTF-16BE 编码对应 dc=8，ISO8859-1 编码对应 dc=0
     */
    private static final String code = "GBK";
    private static final String SMS_ETONENET = "SMS_ETONENET";
    private static final int dc = 15;
    @Autowired
    private SmsNewsService smsNewsService;
    /**
     * 短信发送
     * @param businessVO
     * @return
     * @throws ClientException
     */
    public boolean sendSms(SmsBusinessVO businessVO) {
        //短信内容
        String sm = null;
        try {
             sm = new String(Hex.encodeHex(businessVO.getContent().getBytes(code)));
        } catch (UnsupportedEncodingException e) {
            logger.error("-----移通短信内容编码转化异常-----");
            e.printStackTrace();
        }
        List<String> list = StringUtils.stringToList(businessVO.getPhones());
        List<String> newPhone = new ArrayList<>();
        for (String phone:list){
            newPhone.add("86"+phone);
        }
        String command = list.size()==1?MT_REQUEST:MULTI_MT_REQUEST;

        String sa = "";
        int a = new Random().nextInt(100);
        if (a<10){
            sa+="0"+a;
        }else{
            sa+=a;
        }

        StringBuilder smsUrl = new StringBuilder();
        smsUrl.append(url);
        smsUrl.append("?command=" + command);
        smsUrl.append("&spid=" + pid);
        smsUrl.append("&sppassword=" + password);
        smsUrl.append("&spsc=" + sc);
        smsUrl.append("&sa=" + sa);
        smsUrl.append((list.size()==1?"&da=":"&das=") + StringUtils.listToString(newPhone));
        smsUrl.append("&sm=" + sm);
        smsUrl.append("&dc=" + dc);
        String result = HttpClientUtil.doPost(smsUrl.toString());
        HashMap<String,String> map = HttpClientUtil.parseResStr(result);
        if ("000".equals(map.get("mterrcode"))){
            SmsNewsResultVO smsNewsResultVO = new SmsNewsResultVO();
            List<SmsNewsInfo> info = new ArrayList<>();
            smsNewsResultVO.setPhone(businessVO.getPhones());
            if (list.size()==1) {
                String bzid = map.get("mtmsgid");
                SmsNewsInfo smsNewsInfo = new SmsNewsInfo();
                smsNewsInfo.setPhone(newPhone.get(0));
                smsNewsInfo.setMsgId(bzid);
                smsNewsInfo.setStatus(1L);
                smsNewsInfo.setReceivePhone(sa);
                info.add(smsNewsInfo);
            }else{
                String mtmsgids = map.get("mtmsgids");
                List<String> msgIds = StringUtils.stringToList(mtmsgids);
                for (int i = 0;i<list.size();i++){
                    SmsNewsInfo smsNewsInfo = new SmsNewsInfo();
                    smsNewsInfo.setPhone(newPhone.get(i));
                    smsNewsInfo.setMsgId(msgIds.get(i));
                    smsNewsInfo.setStatus(1L);
                    smsNewsInfo.setReceivePhone(sa);
                    info.add(smsNewsInfo);
                }
            }
            smsNewsResultVO.setPhone(list.get(0));
            smsNewsResultVO.setContent(businessVO.getContent());
            smsNewsResultVO.setChannelCode(SMS_ETONENET);
            smsNewsResultVO.setSendTime(new Date());
            smsNewsResultVO.setStatus(1L);
            smsNewsResultVO.setResult(ConstantUtil.SEND_SUCCESS);
            smsNewsResultVO.setTargetCount(list.size());
            smsNewsResultVO.setFinishCount(list.size());
            smsNewsResultVO.setSystemCode(businessVO.getSourceId());
            smsNewsResultVO.setType(businessVO.getType());
            smsNewsResultVO.setSystemCode(businessVO.getSourceId());
            smsNewsResultVO.setSmsNewsInfos(info);
            smsNewsService.saveSmsNews(smsNewsResultVO);
            return true;
        }else if (SmsTemplate.Type.SLAVE.equals(businessVO.getChannelType())){
            throw new RuntimeException("短信发送失败");
        }
        return false;
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
          return true;
    }


    public QuerySendDetailsResponse querySendDetails(String bizId,String phone,Long pageIndex,Long pageSize){
        return null;
    }

}
