package com.fulan.application.custom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.paper.vo.PaperDetailVo;
import com.fulan.api.paper.vo.PaperVo;
import com.fulan.api.personnel.domain.Personnel;
import com.fulan.api.personnel.service.PersonnelService;
import com.fulan.application.service.PaperService;
import com.fulan.application.util.domain.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api(tags = "PaperApi", description = "试题相关接口")
@Controller
@RequestMapping(value ="/customer/paper")
public class PaperCustomerController {
	
	@Autowired
	PaperService paperService;
	
	@Autowired
	PersonnelService personnelService;
	
	@ApiOperation(value = "获取试题详细", notes = "面试试题", response = Response.class)
	@PostMapping("/getPaperDetailVo")
	@ResponseBody
	//@ApiParam(name = "id", value = "面试人id", required = true) @RequestParam(name = "id", required = true)Long id
	public Response<List<PaperDetailVo>> getPaperVo(@RequestParam Map<String,Object> map){
		return paperService.selectPaperDetailList(map);
	}
	
	@ApiOperation(value = "获取试题列表", notes = "面试试题", response = Response.class)
	@PostMapping("/getPaperByPaperType")
	@ResponseBody
	public Response<List<PaperVo>> getPaperByPaperType(@ApiParam(name = "paperType", value = "面试试题类型", required = false)
											@RequestParam(name = "paperType", required = false)
											String paperType,
											@ApiParam(name = "flowItemId", value = "流程节点id", required = false)
											@RequestParam(name = "flowItemId", required = false)
											String flowItemId,
											@ApiParam(name = "personnelId", value = "增员人员id", required = false)
											@RequestParam(name = "personnelId", required = false)
											String personnelId,
											@ApiParam(name = "orgId", value = "分公司id", required = false)
											@RequestParam(name = "orgId", required = false)
											String orgId){
		//paperType:1 flowItemId:3
		Response<Personnel> personnel1 = personnelService.getPersonnel(personnelId);
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap.put("paperType", paperType);
		searchMap.put("flowItemId", flowItemId);
		searchMap.put("personnelId", personnelId);
		searchMap.put("orgId", personnel1.getData().getOrgId());
		return paperService.getPaperByPaperType(searchMap);
	}
	
	
}
