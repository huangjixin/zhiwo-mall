package com.fulan.application.controller.message;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.message.domain.SmsBlackUser;
import com.fulan.api.message.domain.SmsChannel;
import com.fulan.api.message.domain.SmsNewsInfo;
import com.fulan.api.message.domain.SmsParameterFactor;
import com.fulan.api.message.domain.SmsSystem;
import com.fulan.api.message.domain.SmsSystemChannel;
import com.fulan.api.message.domain.SmsTemplate;
import com.fulan.api.message.service.SmsBlackUserService;
import com.fulan.api.message.service.SmsBusiness;
import com.fulan.api.message.service.SmsNewsService;
import com.fulan.api.message.service.SmsParameterService;
import com.fulan.api.message.service.SmsSystemChannelService;
import com.fulan.api.message.service.SmsSystemService;
import com.fulan.api.message.service.SmsTemplateService;
import com.fulan.api.message.vo.SmsBusinessParameterVO;
import com.fulan.api.message.vo.SmsNewsResultVO;
import com.fulan.api.message.vo.SmsNewsStatisticsResultVO;
import com.fulan.api.message.vo.SmsNewsStatisticsVO;
import com.fulan.api.message.vo.SmsTemplateResultVO;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.application.common.ReadExcel;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.file.SFTPUtil;
import com.fulan.application.util.page.PageInfo;
import com.fulan.core.monitoring.cat.constant.Constant;

@Controller
@RequestMapping("/manage/sms")
public class SmsController {
	@Autowired
	private SmsBlackUserService smsBlackUserService;
	
	@Autowired
	private SmsTemplateService smsTemplateService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private SmsNewsService smsNewsService;
	
	@Autowired
	private SmsBusiness smsBusiness;
	
	@Autowired
	private SmsParameterService smsParameterService;
	
	@Autowired
	private SmsSystemService smsSystemService;
	
	@Autowired
	private SmsSystemChannelService smsSystemChannelService;
	/**
	 * 获取黑白名单页面
	 */
	@RequestMapping(value="/getSmsList")
    public String getSmsList(
    		 Model model,
    		 @RequestParam(value="phone",required=false) String phone,
    		 @RequestParam(value="type",required=false) String type,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="10") int pageSize
    		){
		
		PageInfo<SmsBlackUser> data=smsBlackUserService.selectSmsList(phone,type,pageNo,pageSize);
		model.addAttribute("data", data);
		model.addAttribute("phone", phone);
		model.addAttribute("type", type);
		return "message/sms_list";
	}
	
	/**
	 * ajax异步刷新
	 */
	@RequestMapping(value="/getSmsListAjax")
	@ResponseBody
    public Response<PageInfo<SmsBlackUser>> getSmsListAjax(
    		 @RequestParam(value="phone",required=false) String phone,
    		 @RequestParam(value="type",required=false) String type,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="10") int pageSize
    		){
		
		PageInfo<SmsBlackUser> data=smsBlackUserService.selectSmsList(phone,type,pageNo,pageSize);
		Response<PageInfo<SmsBlackUser>> response = new Response<>(Response.SUCCESS, null);
		response.setData(data);
		return response;
	}
	
	/**
	 * 获得新增页面
	 */
	@RequestMapping(value="/addSmsPage")
    public String addSmsPage(){
		return "message/add_page";
	}
	
	/**
	 * 根据id删除黑名单
	 */
	@RequestMapping(value="/deleSmsById")
	@ResponseBody
    public Response<String> deleSmsById(
    		 Model model,String id){
		Long idl=null;
		if(null!=id && id!="" ){
			idl=Long.valueOf(id);
		}
		Response<String> response = smsBlackUserService.deleteSmsBlackUserById(idl);
		return response;
	}
	
	/**
	 * 根据id批量删除黑名单
	 */
	@RequestMapping(value="/deleBacthSmsById")
	@ResponseBody
    public Response<String> deleBacthSmsById(
    		 Model model,@RequestParam("ids") String ids){
		if(ids.length()>1){
			ids=ids.substring(0,ids.length() - 1);
		}
		String[] split = ids.split(",");
		List<Long> list=new ArrayList<Long>();
		for(int i=0;i<split.length;i++){
			if(null!=split[i] && split[i]!=""){
				list.add(Long.valueOf(split[i]));
			}
		}
		Response<String> response = smsBlackUserService.deleteBatchSmsBlackUser(list);
		return response;
	}
	
	/**
	 * 批量插入黑名单
	 */
	@RequestMapping(value="/saveSmsBlackUser")
	@ResponseBody
    public Response<String> saveSmsBlackUser(
    		  String phones,String remak,String type){
		if(phones.indexOf("，")!=-1){
			phones=phones.replaceAll("，", ",");
			
		}
			String[] split = phones.split(",");
			List<SmsBlackUser> smsBlackUserList=new ArrayList<SmsBlackUser>();
			for(int i=0;i<split.length;i++){
				SmsBlackUser smsBlackUser=new SmsBlackUser();
				smsBlackUser.setPhone(split[i]);
				smsBlackUser.setRemark(remak);
				smsBlackUser.setType(type);
				smsBlackUserList.add(smsBlackUser);
			}
			Response<String> response = smsBlackUserService.saveSmsBlackUser(smsBlackUserList);
			return response;
		
	}
	
	
	/**
	 * 获取模板页面
	 */
	@RequestMapping(value="/getTemplate")
    public String getTemplate(
    		 Model model,
    		 @RequestParam(value="title",required=false) String title,
    		 @RequestParam(value="code",required=false) String code,
    		 @RequestParam(value="type",required=false) String type,
    		 @RequestParam(value="masterCode",required=false) String masterCode,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="10") int pageSize
    		){
		
		PageInfo<SmsTemplateResultVO> data=smsTemplateService.selectTemplateList(title,code,type,masterCode,pageNo,pageSize);
		model.addAttribute("data", data);
		model.addAttribute("title", title);
		model.addAttribute("code", code);
		model.addAttribute("masterCode", masterCode);
		model.addAttribute("type", type);
		return "message/sms_template";
	}
	

	/**
	 * 获取模板页面
	 */
	@RequestMapping(value="/getTemplateAjsx")
	@ResponseBody
    public Response<PageInfo<SmsTemplateResultVO>> getTemplateAjsx(
    		 @RequestParam(value="title",required=false) String title,
    		 @RequestParam(value="code",required=false) String code,
    		 @RequestParam(value="type",required=false) String type,
    		 @RequestParam(value="masterCode",required=false) String masterCode,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="10") int pageSize
    		){
		PageInfo<SmsTemplateResultVO> data=smsTemplateService.selectTemplateList(title,code,type,masterCode,pageNo,pageSize);
		Response<PageInfo<SmsTemplateResultVO>> response = new Response<>(Response.SUCCESS, null);
		response.setData(data);
		return response;
	}
	
	
	/**
	 * 根据id删模板
	 */
	@RequestMapping(value="/deleTemplateById")
	@ResponseBody
    public Response<String> deleTemplateById(String idl){
		Long id=null;
		if(null!=idl && idl!="" ){
			id=Long.valueOf(idl);
		}
		Response<String> response = smsTemplateService.deleteSmsTemplateById(id);
		return response;
	}
	
	/**
	 * 跳转新增修改页面
	 */
	@RequestMapping(value="/editTemplateById")
    public String editTemplateById(
    		 Model model,String idl
    		){
		Long id=null;
		if(null!=idl && idl!="" ){
			id=Long.valueOf(idl);
		}
		SmsTemplate smsTemplate =null;
		if(null!=id){
			smsTemplate = smsTemplateService.selectSmsTemplateById(id);
		}
		SmsSystemChannel selectSmsSystemChannel = smsSystemChannelService.selectSmsSystemChannel();
		 List<SmsChannel> channels = selectSmsSystemChannel.getChannels();
		 Response<Object> selectSmsFactor = smsParameterService.selectSmsFactor();
		List<SmsParameterFactor> factors=(List<SmsParameterFactor>) selectSmsFactor.getData();
		model.addAttribute("smsTemplate", smsTemplate);
		model.addAttribute("factors", factors);
		model.addAttribute("channels", channels);
		return "message/edit_template";
	}
	
	/**
	 * 插入模板
	 */
	@RequestMapping(value="/saveSmsTemplate")
	@ResponseBody
    public Response<String> saveSmsTemplate( SmsTemplate smsTemplate){
		
		if(null==smsTemplate.getId()){
			Response<String> saveSmsTemplate = smsTemplateService.saveSmsTemplate(smsTemplate);
			return saveSmsTemplate;
		}else{
			Response<String> updateSmsTemplate = smsTemplateService.updateSmsTemplate(smsTemplate);
			return updateSmsTemplate;
		}
		
	}
	

	/**
	 * 获取消息查询页面
	 */
	@RequestMapping(value="/getSmsNews")
    public String getSmsNews(
    		 Model model,
    		 @RequestParam(value="content",required=false) String content,
    		 @RequestParam(value="phone",required=false) String phone,
    		 @RequestParam(value="status",required=false) String status,
    		 @RequestParam(value="systemCode",required=false) String systemCode,
    		 @RequestParam(value="channelCode",required=false) String channelCode,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="10") int pageSize
    		){
		String type="BUSNIESS";
		PageInfo<SmsNewsResultVO> data=smsNewsService.setlectSmsNewsList(content,phone,status,systemCode,channelCode, pageNo,pageSize,type);
		List<SmsSystem>  SmsSystems = (List<SmsSystem>) redisUtil.get(Constant.SMS_SYSTEM);
		List<SmsChannel>  channels = (List<SmsChannel>) redisUtil.get(Constant.SMS_CHANNEL);
		model.addAttribute("data", data);
		model.addAttribute("channels", channels);
		model.addAttribute("factors", SmsSystems);
		model.addAttribute("content", content);
		model.addAttribute("phone", phone);
		model.addAttribute("status", status);
		model.addAttribute("systemCode", systemCode);
		model.addAttribute("channelCode", channelCode);
		return "message/sms_news";
	}
	
	
	/**
	 * ajax异步刷新
	 */
	@RequestMapping(value="/getSmsNewsAjsx")
	@ResponseBody
    public Response<PageInfo<SmsNewsResultVO>>  getSmsNewsAjsx(
    		 @RequestParam(value="content",required=false) String content,
    		 @RequestParam(value="phone",required=false) String phone,
    		 @RequestParam(value="status",required=false) String status,
    		 @RequestParam(value="systemCode",required=false) String systemCode,
    		 @RequestParam(value="channelCode",required=false) String channelCode,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="10") int pageSize
    		){
		String type="BUSNIESS";
		PageInfo<SmsNewsResultVO> data=smsNewsService.setlectSmsNewsList(content,phone,status,systemCode,channelCode, pageNo,pageSize,type);
		Response<PageInfo<SmsNewsResultVO>> response = new Response<>(Response.SUCCESS, null);
		response.setData(data);
		return response;
	}

	/**
	 * 根据id查询消息详情
	 */
	@RequestMapping(value="/selectSmsNewsById")
    public String selectSmsNewsById(Model model , String idl,String type){
		Long id=null;
		if(null!=idl && idl!="" ){
			id=Long.valueOf(idl);
		}
		SmsNewsResultVO smsNewsResultVO = smsNewsService.selectSmsNewsById(id);
		List<SmsNewsInfo> smsNewsInfos = smsNewsResultVO.getSmsNewsInfos();
		model.addAttribute("smsNewsResultVO", smsNewsResultVO);
		 List<SmsChannel>  channels = (List<SmsChannel>) redisUtil.get(Constant.SMS_CHANNEL);
		 List<SmsSystem>  SmsSystems = (List<SmsSystem>) redisUtil.get(Constant.SMS_SYSTEM);
		 model.addAttribute("channels", channels);
		 model.addAttribute("SmsSystems", SmsSystems);
		 model.addAttribute("idl", idl);
		 model.addAttribute("type", type);
		 if(null!=smsNewsInfos && smsNewsInfos.size()==1){
				return "message/sms_news_viewOne";
			}
		return "message/sms_news_view";
	}
	
	/**
	 * 根据id查迅失败用户
	 */
	@RequestMapping(value="/selectFailUserById")
    public String selectFailUserById(Model model , String idl){
		Long id=null;
		if(null!=idl && idl!="" ){
			id=Long.valueOf(idl);
		}
		SmsNewsResultVO smsNewsResultVO = smsNewsService.selectSmsNewsById(id);
		model.addAttribute("smsNewsResultVO", smsNewsResultVO);
		return "message/fail_user";
	}
	
	
	/**
	 * 查询消息用量
	 */
	@RequestMapping(value="/getSmsNum")
    public String getSmsNum(
    		 Model model,
    		 @RequestParam(value="satrtTime",required=false) String satrtTime,
    		 @RequestParam(value="endTime",required=false) String endTime,
    		 @RequestParam(value="systemCode",required=false) String systemCode,
    		 @RequestParam(value="channelCode",required=false) String channelCode,
    		 @RequestParam(value="active",required=false) String active,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="20") int pageSize
    		){
		SmsNewsStatisticsVO smsNewsStatisticsVO=new SmsNewsStatisticsVO();
		smsNewsStatisticsVO.setPageIndex(pageNo);
		smsNewsStatisticsVO.setPageSize(pageSize);
		smsNewsStatisticsVO.setSystemCode(systemCode);
		smsNewsStatisticsVO.setChannelCode(channelCode);
		smsNewsStatisticsVO.setStartDate(satrtTime);
		smsNewsStatisticsVO.setEndDate(endTime);
		smsNewsStatisticsVO.setStatisticsParameter("month");
		Page<SmsNewsStatisticsResultVO> smsMonth = smsNewsService.selectSmsNewsStatistics(smsNewsStatisticsVO);
		smsMonth =addNum(smsMonth);
		model.addAttribute("smsMonth", smsMonth);
		smsNewsStatisticsVO.setStatisticsParameter("channel_code");
		Page<SmsNewsStatisticsResultVO> smsChannelCode =smsNewsService.selectSmsNewsStatistics(smsNewsStatisticsVO);
		smsChannelCode=addNum(smsChannelCode);
		model.addAttribute("smsChannelCode", smsChannelCode);
		smsNewsStatisticsVO.setStatisticsParameter("system_code");
		Page<SmsNewsStatisticsResultVO> smsSystemCode =smsNewsService.selectSmsNewsStatistics(smsNewsStatisticsVO);
		smsSystemCode=addNum(smsSystemCode);
		model.addAttribute("smsSystemCode", smsSystemCode);
		List<SmsChannel>  channels = (List<SmsChannel>) redisUtil.get(Constant.SMS_CHANNEL);
		List<SmsSystem>  SmsSystems = (List<SmsSystem>) redisUtil.get(Constant.SMS_SYSTEM);
		model.addAttribute("channels", channels);
		model.addAttribute("SmsSystems", SmsSystems);
		model.addAttribute("satrtTime", satrtTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("systemCode", systemCode);
		model.addAttribute("channelCode", channelCode);
		model.addAttribute("active", active);
		return "message/sms_statistics";
	}
	
	
	/**
	 * 异步刷新
	 */
	@RequestMapping(value="/getSmsNumMonthAjsx")
	@ResponseBody
    public Response<Page<SmsNewsStatisticsResultVO>> getSmsNumMonthAjsx(
    		 @RequestParam(value="satrtTime",required=false) String satrtTime,
    		 @RequestParam(value="endTime",required=false) String endTime,
    		 @RequestParam(value="active",required=false) String active,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="20") int pageSize
    		){
		SmsNewsStatisticsVO smsNewsStatisticsVO=new SmsNewsStatisticsVO();
		smsNewsStatisticsVO.setPageIndex(pageNo);
		smsNewsStatisticsVO.setPageSize(pageSize);
		smsNewsStatisticsVO.setStartDate(satrtTime);
		smsNewsStatisticsVO.setEndDate(endTime);
		smsNewsStatisticsVO.setStatisticsParameter("month");
		Page<SmsNewsStatisticsResultVO> smsMonth = smsNewsService.selectSmsNewsStatistics(smsNewsStatisticsVO);
		smsMonth =addNum(smsMonth);
		Response<Page<SmsNewsStatisticsResultVO>> response = new Response<>(Response.SUCCESS, null);
		response.setData(smsMonth);
		return response;
	}
	
	
	/**
	 * 异步刷新
	 */
	@RequestMapping(value="/getSmsNumChannelAjsx")
	@ResponseBody
    public Response<Page<SmsNewsStatisticsResultVO>> getSmsNumChannelAjsx(
    		 @RequestParam(value="satrtTime",required=false) String satrtTime,
    		 @RequestParam(value="endTime",required=false) String endTime,
    		 @RequestParam(value="channelCode",required=false) String channelCode,
    		 @RequestParam(value="active",required=false) String active,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="20") int pageSize
    		){
		SmsNewsStatisticsVO smsNewsStatisticsVO=new SmsNewsStatisticsVO();
		smsNewsStatisticsVO.setPageIndex(pageNo);
		smsNewsStatisticsVO.setPageSize(pageSize);
		smsNewsStatisticsVO.setChannelCode(channelCode);
		smsNewsStatisticsVO.setStartDate(satrtTime);
		smsNewsStatisticsVO.setEndDate(endTime);
		smsNewsStatisticsVO.setStatisticsParameter("channel_code");
		Page<SmsNewsStatisticsResultVO> smsChannelCode =smsNewsService.selectSmsNewsStatistics(smsNewsStatisticsVO);
		smsChannelCode=addNum(smsChannelCode);
		Response<Page<SmsNewsStatisticsResultVO>> response = new Response<>(Response.SUCCESS, null);
		response.setData(smsChannelCode);
		return response;
	}
	
	
	
	
	/**
	 * 异步刷新
	 */
	@RequestMapping(value="/getSmsNumSystemAjsx")
	@ResponseBody
    public Response<Page<SmsNewsStatisticsResultVO>> getSmsNumSystemAjsx(
    		 @RequestParam(value="satrtTime",required=false) String satrtTime,
    		 @RequestParam(value="endTime",required=false) String endTime,
    		 @RequestParam(value="systemCode",required=false) String systemCode,
    		 @RequestParam(value="active",required=false) String active,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="20") int pageSize
    		){
		SmsNewsStatisticsVO smsNewsStatisticsVO=new SmsNewsStatisticsVO();
		smsNewsStatisticsVO.setPageIndex(pageNo);
		smsNewsStatisticsVO.setPageSize(pageSize);
		smsNewsStatisticsVO.setSystemCode(systemCode);
		smsNewsStatisticsVO.setStartDate(satrtTime);
		smsNewsStatisticsVO.setEndDate(endTime);
		smsNewsStatisticsVO.setStatisticsParameter("system_code");
		Page<SmsNewsStatisticsResultVO> smsSystemCode =smsNewsService.selectSmsNewsStatistics(smsNewsStatisticsVO);
		smsSystemCode=addNum(smsSystemCode);
		Response<Page<SmsNewsStatisticsResultVO>> response = new Response<>(Response.SUCCESS, null);
		response.setData(smsSystemCode);
		return response;
	}
	
	
	
	/**
	 * 获取验证码页面
	 */
	@RequestMapping(value="/getSmsVerify")
    public String getSmsVerify(
    		 Model model,
    		 @RequestParam(value="content",required=false) String content,
    		 @RequestParam(value="phone",required=false) String phone,
    		 @RequestParam(value="status",required=false) String status,
    		 @RequestParam(value="systemCode",required=false) String systemCode,
    		 @RequestParam(value="channelCode",required=false) String channelCode,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="10") int pageSize
    		){
		String type="VALIDATE";
		PageInfo<SmsNewsResultVO> data=smsNewsService.setlectSmsNewsList(content,phone,status,systemCode,channelCode, pageNo,pageSize,type);
		List<SmsSystem>  SmsSystems = (List<SmsSystem>) redisUtil.get(Constant.SMS_SYSTEM);
		 List<SmsChannel>  channels = (List<SmsChannel>) redisUtil.get(Constant.SMS_CHANNEL);
		model.addAttribute("data", data);
		model.addAttribute("channels", channels);
		model.addAttribute("factors", SmsSystems);
		model.addAttribute("content", content);
		model.addAttribute("phone", phone);
		model.addAttribute("status", status);
		model.addAttribute("type", type);
		model.addAttribute("systemCode", systemCode);
		model.addAttribute("channelCode", channelCode);
		return "message/sms_verify";
	}
	
	
	/**
	 * ajax异步刷新
	 */
	@RequestMapping(value="/getSmsVerifyAjsx")
	@ResponseBody
    public  Response<PageInfo<SmsNewsResultVO>> getSmsVerifyAjsx(
    		 @RequestParam(value="content",required=false) String content,
    		 @RequestParam(value="phone",required=false) String phone,
    		 @RequestParam(value="status",required=false) String status,
    		 @RequestParam(value="systemCode",required=false) String systemCode,
    		 @RequestParam(value="channelCode",required=false) String channelCode,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="10") int pageSize
    		){
		String type="VALIDATE";
		PageInfo<SmsNewsResultVO> data=smsNewsService.setlectSmsNewsList(content,phone,status,systemCode,channelCode, pageNo,pageSize,type);
		 Response<PageInfo<SmsNewsResultVO>> response = new Response<>(Response.SUCCESS, null);
			response.setData(data);
			return response;
	}
	
	
	Page<SmsNewsStatisticsResultVO> addNum(Page<SmsNewsStatisticsResultVO>  sms){
		SmsNewsStatisticsResultVO smsNewsStatisticsResultVO=new SmsNewsStatisticsResultVO();
		String name="总计";
		Long smsCount=0l;
		Long smsSuccessCount=0l;
		Long smsFailCount=0l;
		Long smsUnknownCount=0l;
		List<SmsNewsStatisticsResultVO> records = sms.getRecords();
		for (SmsNewsStatisticsResultVO smsNew : records) {
			if(null !=smsNew){
				smsCount+=smsNew.getSmsCount();
				smsSuccessCount+=smsNew.getSmsSuccessCount();
				smsFailCount+=smsNew.getSmsFailCount();
				smsUnknownCount+=smsNew.getSmsUnknownCount();
			}
			
		}
		smsNewsStatisticsResultVO.setName(name);
		smsNewsStatisticsResultVO.setSmsCount(smsCount);
		smsNewsStatisticsResultVO.setSmsFailCount(smsFailCount);
		smsNewsStatisticsResultVO.setSmsSuccessCount(smsSuccessCount);
		smsNewsStatisticsResultVO.setSmsUnknownCount(smsUnknownCount);
		records.add(smsNewsStatisticsResultVO);
		sms.setRecords(records);
		return sms;
	}
	
	/**
	 * 获得发送短信页面
	 */
	@RequestMapping(value="/sendSmsPage")
    public String sendSmsPage(){
		return "message/send_sms";
	}
	
	/**
	 * 发送短信
	 */
	@RequestMapping(value="/sendSms")
	@ResponseBody
    public Response<String> sendSms( 
    	 @RequestParam(value="content",required=false) String content,
   		 @RequestParam(value="phones",required=false) String phones,
   		@RequestParam(value="sendTime",required=false) String sendTime,
   		 @RequestParam(value="taskStatus",required=false) String taskStatus){
		Integer state=null;
		SmsBusinessParameterVO smsBusinessParameterVO=new SmsBusinessParameterVO();
		if(phones.indexOf("，")!=-1){
			phones=phones.replaceAll("，", ",");
		}
		smsBusinessParameterVO.setPhones(phones);
		smsBusinessParameterVO.setContent(content);
		if(null!=taskStatus && taskStatus!=""){
			state=Integer.parseInt(taskStatus);
		}
		smsBusinessParameterVO.setTaskStatus(state);
		smsBusinessParameterVO.setTaskTime(sendTime);
		Response<String> sendSms = smsSystemService.sendSms(smsBusinessParameterVO);
		return sendSms;
	}
	
	/**
	 * 导入号码
	 * @throws IOException 
	 */
	@RequestMapping(value="/uploadOther")
	@ResponseBody
    public Response<String> upLodeSms(HttpServletRequest request){
		Response<String> response= new Response<>(Response.SUCCESS, "导入成功");
		try {
			String phone="";
			String url = request.getParameter("url");
			 MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		        //获取上传文件   name值 必须为"fileName"：
		        MultipartFile file = mRequest.getFile("fileName");
		        String name=file.getOriginalFilename();
		        if(!name.substring(name.indexOf(".")+1, name.length()).equals("xls") && !name.substring(name.indexOf(".")+1, name.length()).equals("xlsx")){
		        	response.setCode(Response.ERROR);
		        	response.setMsg("请选择xls或xlsx格式文件");
		        	return response;
		        }
		        SFTPUtil sftp = new SFTPUtil(CommenConstant.ftpuploadusername, CommenConstant.ftpuploadpassword, CommenConstant.ftpuploadhost, CommenConstant.ftpuploadport);
		        sftp.login();
		      // String dire= request.getRealPath("/");
		      /* String dire =request.getSession().getServletContext().getRealPath("/");
		       if(dire.indexOf("\\")!= -1){
		    	   dire=dire.replaceAll("\\\\", "/");  
		       }
		         dire+=name;
		         if(System.getProperty("os.name").indexOf("Windows")== -1){
		        	 dire="/home/"+name;
		         }
		          File file_dire=new File(dire);
		          if(!file_dire.exists()){
		        	  file_dire.createNewFile();
		          }
		         
		          file.transferTo(file_dire);
		          InputStream inputStream = new FileInputStream(dire);*/
		        InputStream inputStream = sftp.get("/home/apps/upload/"+url);
		         ArrayList<String> excelIn = ReadExcel.excelIn(inputStream,null);
		         for (String string : excelIn) {
		        	 phone=phone+string+",";
				}
		         if(phone.substring(phone.length()-1, phone.length()).equals(",")){
		        	 phone=phone.substring(0, phone.length()-1);
		         }
		         response.setData(phone);
		         /*if((file_dire).exists()){
		        	  deleteFile(file_dire);
		          }*/
		} catch (Exception e) {
			response.setCode(Response.ERROR);
			response.setMsg("导入失败，请重试");
		}
		
		return response;
	}
	
	/*private void deleteFile(File file){
		     if(file.isDirectory()){
		          File[] files = file.listFiles();
		          for(int i=0; i<files.length; i++){
		               deleteFile(files[i]);
		          }
		      }
		     file.delete();
		 }*/
	
	
	
	/**
	 * 获得参数列表页面
	 * @return
	 */
	@RequestMapping(value="/getParameter")
    public String getParameter(Model model,
    		@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		PageInfo<SmsParameterFactor> data= smsParameterService.selectParameter(pageNo,pageSize);
		model.addAttribute("data", data);
		return  "message/sms_parameter";
	}
	
	/**
	 * 获得参数列表页面
	 * @return
	 */
	@RequestMapping(value="/getParameterAjax")
	@ResponseBody
    public Response<PageInfo<SmsParameterFactor>> getParameterAjax(Model model,
    		@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		PageInfo<SmsParameterFactor> data= smsParameterService.selectParameter(pageNo,pageSize);
		Response<PageInfo<SmsParameterFactor>> response = new Response<>(Response.SUCCESS, null);
		response.setData(data);
		return response;
	}
	
	/**
	 * 根据id删除参数列表
	 */
	@RequestMapping(value="/deleSmsParameter")
	@ResponseBody
    public Response<String> deleSmsParameter(String id){
		Long idl=null;
		if(null != id && id !=""){
			idl=Long.valueOf(id);		}
		Response<String> response = smsParameterService.deleteSmsFactor(idl);
		return response;
	}
	
	
	/**
	 * 跳转修改或新增参数页面
	 * @return
	 */
	@RequestMapping(value="/updateSmsParameter")
    public String updateSmsParameter(Model model,String id){
		SmsParameterFactor smsParameterFactor=null;
		if(null != id && id !=""){
		smsParameterFactor=smsParameterService.selectParameterById(id);
		}
		
		model.addAttribute("smsParameterFactor", smsParameterFactor);
		return "message/addParameter";
	}
	
	/**
	 * 修改或新增参数
	 * @return
	 */
	@RequestMapping(value="/SaveSmsParameter")
	@ResponseBody
    public Response<String>  SaveSmsParameter(SmsParameterFactor smsParameterFactor){
		if(null==smsParameterFactor.getId()){
			Response<String> saveSmsFactor = smsParameterService.saveSmsFactor(smsParameterFactor);
			return saveSmsFactor;
		}else{
			Response<String> updateSmsFactor = smsParameterService.updateSmsFactor(smsParameterFactor);
			return updateSmsFactor;
		}
		
	}
	public static void main(String[] args) {
		String property = System.getProperty("os.name");
		System.err.println(property);
	}
	
}
