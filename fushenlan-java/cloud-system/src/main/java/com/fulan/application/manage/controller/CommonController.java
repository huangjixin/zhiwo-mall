/**
 * Project Name:cloud-system
 * File Name:CommonController.java
 * Package Name:com.fulan.application.manage.controller
 * Date:2018年3月6日上午9:24:47
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.application.manage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.application.util.domain.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ClassName:CommonController
 * Reason:	 TODO ADD REASON
 * Date:     2018年3月6日 上午9:24:47 
 * @author   chen.zhuang
 * @version  
 * @since    JDK 1.8 
 */
@Api(tags = "CommonController", description = "通用接口")
@RestController
@RequestMapping(value = "/manage/common")
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	
	@ApiOperation(value = "获取系统时间", notes = "获取系统时间", response = Response.class)
	@RequestMapping(value="systime",method=RequestMethod.POST)
	@ResponseBody
	public Response<Long> systime(){
		try {
			Response<Long> resp = new Response<Long>(Response.SUCCESS, "获取系统时间成功");
			resp.setCode("1");
			resp.setData(System.currentTimeMillis());
			return resp;
		}catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Long>(Response.ERROR, "获取系统时间失败");
		}
		
	}
	
	
}

