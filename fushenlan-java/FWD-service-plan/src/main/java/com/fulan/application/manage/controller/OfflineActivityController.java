package com.fulan.application.manage.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.domain.OfflineActivity;
import com.fulan.api.plan.vo.OfflineActivityVOPC;
import com.fulan.api.plan.vo.OfflineActivityVoFw;
import com.fulan.application.service.OfflineActivityService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

@RestController
@RequestMapping("/manage/offlineActivity")
public class OfflineActivityController {
	
	@Autowired
	private OfflineActivityService offlineActivityService;
	
	private static final Logger logger = LoggerFactory.getLogger(OfflineActivityController.class);
	
	@RequestMapping(value ="/pageList",method=RequestMethod.GET)
	public PageInfo<OfflineActivityVoFw> pageList(
			@RequestParam(value="name",required=false) String name, 
			@RequestParam(value="state",required=false) String state,
			@RequestParam(value="enterStartDate",required=false) String enterStartDate,
			@RequestParam(value="enterEndDate",required=false) String enterEndDate,
			@RequestParam(value="pageNo",defaultValue="0")int pageNo,
			@RequestParam(value="pageSize",defaultValue="10")int pageSize){
		Page<OfflineActivityVoFw> page = new Page<OfflineActivityVoFw>(pageNo, pageSize);
		PageInfo<OfflineActivityVoFw> pageInfo = offlineActivityService.pageList(page, name, state, enterStartDate, enterEndDate, pageNo, pageSize);
		return pageInfo;
	}
	
	@RequestMapping(value ="/updateOrDele",method=RequestMethod.POST)
    public Response<String> updateOrDele(
    		@RequestParam(value="type",required=false)String type,
    		@RequestParam(value="state",required=false)String state,
    		@RequestParam(value="ids",required=false)String ids){
    	try {
			return offlineActivityService.updateOrDele(type, state, ids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"操作数据失败");
		}
    }
    
	@RequestMapping(value ="/insertOrUpdateFW",method=RequestMethod.POST)
    public Response<String> insertOrUpdateFW(@RequestBody OfflineActivityVOPC offlineActivityVOPC,
            @RequestParam(value="fileId",required=false) Long fileId){
    	try {
			return offlineActivityService.insertOrUpdateFW(offlineActivityVOPC, fileId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"操作数据失败");
		}
    }
    
	@RequestMapping(value ="/findByIdFW",method=RequestMethod.GET)
    public Map<String,Object> findByIdFW(
    		@RequestParam(value="id",required=false) String id,
    		@RequestParam(value="accountName",required=false) String accountName,
    		@RequestParam(value="isSign",required=false) String isSign,
    		@RequestParam(value="type",required=false) String type,
    		@RequestParam(value="pageNo",defaultValue="0") int pageNo,
    		@RequestParam(value="pageSize",defaultValue="10") int pageSize){
    	return offlineActivityService.findByIdFW(id, accountName, isSign, type, pageNo, pageSize);
    }

}
