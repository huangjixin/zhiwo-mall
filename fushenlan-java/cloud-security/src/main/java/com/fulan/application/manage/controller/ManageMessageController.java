/**
 * Project Name:cloud-security
 * File Name:MessageController.java
 * Package Name:com.fulan.application.manage.controller
 * Date:2018年1月23日下午7:01:41
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.application.manage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.security.domain.Message;
import com.fulan.application.service.MessageService;
import com.fulan.application.util.domain.Response;
import com.fulan.core.monitoring.log.annotation.NoLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ClassName:MessageController
 * Reason:	 TODO ADD REASON
 * Date:     2018年1月23日 下午7:01:41 
 * @author   chen.zhuang
 * @version  
 * @since    JDK 1.8 
 */
@NoLog
@Api(tags = "MessageApi", description = "消息后台")
@RestController
@RequestMapping(value = "/mamage/messageService")
public class ManageMessageController {

	 private static final Logger LOG = LoggerFactory.getLogger(ManageMessageController.class);

	
	@Autowired
	MessageService messageService;
	
	@ApiOperation(value = "分页查询账号角色列表", notes = "分页查询账号角色列表", response = Response.class)
	@RequestMapping(value = "/findbyaccountId", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Page<Message> listByPage(@RequestParam(required=true) Long accountId,
	        @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
   ) {	
		 try {
			 return messageService.findbyaccountId(accountId, pageNo, pageSize);	
	        } catch (Exception e) {
	            LOG.error("", e);
	            throw e;
	        }
		
	}
	
	@ApiOperation(value = "添加相关操作消息", notes = "消息", response = Response.class)
	@RequestMapping(value = "/addMessage", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<Integer> addMessage(@RequestParam(required=true) long accountId,
	        @RequestParam(name = "content", defaultValue = "") String content,
            @RequestParam(name = "type", defaultValue = "") String type,@RequestParam(required=true)Long personnelId) {	
		 	try {
			 return messageService.addMessage(accountId, content, type,personnelId);	
	        } catch (Exception e) {
	            LOG.error("", e);
	            throw e;
	        }
		
	}
	
}

