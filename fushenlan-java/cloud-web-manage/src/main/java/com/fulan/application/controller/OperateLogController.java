package com.fulan.application.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.security.domain.OperateLog;
import com.fulan.api.security.service.OperateLogOtherService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;

@Controller
public class OperateLogController {
     
	@Autowired
    private OperateLogOtherService operateLogOtherService;
	
	@GetMapping("/operateLogList")
    public String demo4(Model model,String requestIp,String requestUrl,String pageNo,String pageSize ){
    	
    	if(""==pageNo || null ==pageNo){
    		pageNo="1";
    	}
    	if(""==pageSize || null ==pageSize){
    		pageSize="10";
    	}
    	if(""==requestIp || null ==requestIp){
    		requestIp="";
    	}
    	if(""==requestUrl || null ==requestUrl){
    		requestUrl="";
    	}
		PageInfo<OperateLog> page = operateLogOtherService.selectByPage(requestIp,requestUrl,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		model.addAttribute("page", page);
    	model.addAttribute("requestIp",requestIp);
    	model.addAttribute("requestUrl",requestUrl);
    	return "operateLog/operateLog";
    }
	
	/**
	 * 分页获取内容
	 * @param requestIp
	 * @param requestUrl
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/listByPageAjax")
	@ResponseBody
	public Response<PageInfo<OperateLog>> listByPageAjax(String requestIp,String requestUrl,String pageNo,String pageSize){
		pageNo = StringUtil.isEmpty(pageNo)?"1":pageNo;
		pageSize =  StringUtil.isEmpty(pageNo)?"10":pageSize;
		PageInfo<OperateLog> operateLogList = operateLogOtherService.selectByPage(requestIp,requestUrl,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		Response<PageInfo<OperateLog>> response = new Response<>(Response.SUCCESS, null);
		response.setData(operateLogList);
		return response;
		
	}
}
