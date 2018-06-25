package com.fulan.application.custom.controller;

import com.fulan.api.message.domain.SmsTemplate;
import com.fulan.api.message.vo.Message;
import com.fulan.application.common.Md5;
import com.fulan.application.service.customer.SmsBusinessService;
import com.fulan.application.service.system.SmsNewsInfoService;
import com.fulan.application.service.system.SmsNewsService;
import com.fulan.application.service.system.SmsTemplateService;
import com.fulan.application.util.domain.Response;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.util.Map;
/**
 * 拦截请求路径，将获取的数据以text文件格式保存到本地
 * @author submail
 *
 */
@Controller
@RequestMapping("/sms/message")
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Value("${sms.sub.mail.appSecret}")
	private String appKey;

	@Autowired
	private SmsBusinessService smsBusinessService;
	@Autowired
	private SmsNewsService smsNewsService;
	@Autowired
	private SmsNewsInfoService smsNewsInfoService;
	@Autowired
	private SmsTemplateService smsTemplateService;

   @ResponseBody
   @RequestMapping(value = "/subMail",method = RequestMethod.POST)
   public  Response<String> messageSubhook( MultipartHttpServletRequest MultiRequest){
	   StandardMultipartHttpServletRequest defaultRequest = (StandardMultipartHttpServletRequest) MultiRequest;
		 Map<String, String[]> params = defaultRequest.getParameterMap();
		 JSONObject json = new JSONObject();
		 JSONObject result = new JSONObject();
		   for (Map.Entry<String, String[]> entry : params.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue()[0];
				json.put(key, value);
		   }
			try {
				 String newstr= Md5.getMd5(json.getString("token"),appKey);
				 //签名判断
				 if(!json.get("signature").equals(newstr)){
					 return new Response<>(Response.ERROR, "err:wrong signture");
					}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("abnormal data");
				return new Response<>(Response.ERROR, "abnormal data");
			}
			String event=(String) json.get("events");
			Message message = (Message) JSONObject.toBean(json, Message.class);
			switch(event){
			case "request":
				 //返回请求结果
				result.put("status", "send success");
				result.put("message", message);
				logger.info(result.toString());
				return new Response<>(Response.SUCCESS, result.toString());

			case "delivered":
					//短信发送成功
					result.put("status", "send success");
					result.put("message", message);
				    logger.info(result.toString());
				return new Response<>(Response.SUCCESS, result.toString());
			case "dropped":
					//短信发送失败
					result.put("status", "send fail");
					result.put("message", message);
				    logger.info(result.toString());
				return new Response<>(Response.SUCCESS, result.toString());

			case "sending":
					//正在发送短信
					result.put("status", "is sending");
					result.put("message", message);
				    logger.info(result.toString());
				    return new Response<>(Response.SUCCESS, result.toString());
			case "mo":
					//短信上行接口推送
					result.put("status", "mo");
					result.put("message", message);
				    logger.info(result.toString());
				Integer count = smsNewsInfoService.selectCount(message.getAddress());
				if (count>0){

				}
				smsNewsInfoService.updateSmsNewInfo(null);
				return new Response<>(Response.SUCCESS, result.toString());
			case "template_accept":
				//短信模板审核通过
				SmsTemplate smsTemplate = smsTemplateService.getSmsTemplateByTempId(message.getTemplate_id());
				if (smsTemplate!=null){
					if (message.getTemplate_id().equals(smsTemplate.getMasterTempId())){
						smsTemplate.setMasterStatus(SmsTemplate.PASS);
					}else {
							smsTemplate.setSlaveStatus(SmsTemplate.PASS);
					}
					smsTemplateService.updateSmsTemplate(smsTemplate);
					logger.info("------模板审核状态修改成功-----");
				}
				return new Response<>(Response.SUCCESS,"模板审核状态修改成功");
			case "unkown":
				//未知网关状态
				result.put("status", "unkown");
				result.put("message", message);
			   logger.info(result.toString());
				return new Response<>(Response.SUCCESS, result.toString());
			}
	        return new Response<>(Response.ERROR, result.toString());
			}
	}
		
		
		
		
		
		
		
		   

