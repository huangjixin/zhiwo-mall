package com.fulan.application.manage.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.api.flow.domain.Flow;
import com.fulan.api.flow.domain.FlowAction;
import com.fulan.api.flow.domain.FlowItem;
import com.fulan.api.flow.vo.FlowAcceptVo;
import com.fulan.api.flow.vo.FlowActionVo;
import com.fulan.application.service.FlowActionService;
import com.fulan.application.service.FlowItemService;
import com.fulan.application.service.FlowService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 面试流程 前端控制器
 * </p>
 *
 * @author chenzhuang123
 * @since 2018-01-24
 */
@RestController
@RequestMapping("/manage/flow")
public class FlowManageController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(FlowManageController.class);



	@Autowired
	private FlowItemService flowItemService;
	
	@Autowired
	private FlowService flowService;
	
	@Autowired
	private FlowActionService flowActionService;
	
	
	@RequestMapping(value = "/flowItemList", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<PageInfo<FlowItem>>  flowItemList( @RequestBody  FlowItem flowItem,
			 @ApiParam(name = "pageNo", value = "要跳转的页数") @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
	            @ApiParam(name = "pageSize", value = "每页条数，默认：10", required = false) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
	            @ApiParam(name = "pageSortFiled", value = "排序字段名，默认：sort", required = false) @RequestParam(name = "pageSortFiled", defaultValue = "create_time") String pageSortFiled,
	            @ApiParam(name = "pageSortType", value = "顺序：asc,倒叙：desc，默认：asc", required = false) @RequestParam(name = "pageSortType", defaultValue = "asc") String pageSortType) {
		try {
			return flowItemService.listByPage(flowItem, pageNo, pageSize, pageSortFiled, pageSortType);
		} catch (Exception e) {
			// TODO: handle exception
			return new Response<PageInfo<FlowItem>>(Response.ERROR,"流程列表获取失败");
		}
	}
	
	@RequestMapping(value = "/addFlowItem", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<Integer>  addFlowItem( @RequestBody  FlowItem flowItem) {
		try {
			return flowItemService.insertOrUpdateRecord(flowItem);
		} catch (Exception e) {
			// TODO: handle exception
			return new Response<Integer>(Response.ERROR,"流程列表获取失败");
		}
	}
	
	@RequestMapping(value = "/selectFlowById", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<Flow>  selectFlowById( @RequestBody Flow flow) {
		try {
			return flowService.selectFlowById(flow);
		} catch (Exception e) {
			// TODO: handle exception
			return new Response<Flow>(Response.ERROR,"流程获取失败");
		}
	}
	
	@RequestMapping(value = "/getFlowItem", produces = { "application/json;charset=utf-8" }, method = RequestMethod.GET)
	public Response<FlowItem>  getFlowItem( @RequestParam long flowItemId) {
		try {
			return flowItemService.getFlowItem(flowItemId);
		} catch (Exception e) {
			// TODO: handle exception
			return new Response<FlowItem>(Response.ERROR,"数据获取失败");
		}
	}
	
	@RequestMapping(value = "/deleteFlowItem", produces = { "application/json;charset=utf-8" }, method = RequestMethod.GET)
	public Response<Integer>  deleteFlowItem( @RequestParam long flowItemId) {
		try {
			return flowItemService.deleteFlowItem(flowItemId);
		} catch (Exception e) {
			// TODO: handle exception
			return new Response<Integer>(Response.ERROR,"数据获取失败");
		}
	}
	
	@RequestMapping(value = "/flowList", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<PageInfo<Flow>>  flowList( @RequestBody  Flow flow,
			 @ApiParam(name = "pageNo", value = "要跳转的页数") @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
	            @ApiParam(name = "pageSize", value = "每页条数，默认：10", required = false) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
	            @ApiParam(name = "pageSortFiled", value = "排序字段名，默认：sort", required = false) @RequestParam(name = "pageSortFiled", defaultValue = "create_time") String pageSortFiled,
	            @ApiParam(name = "pageSortType", value = "顺序：asc,倒叙：desc，默认：asc", required = false) @RequestParam(name = "pageSortType", defaultValue = "asc") String pageSortType) {
		try {
			return flowService.listByPage(flow, pageNo, pageSize, pageSortFiled, pageSortType);
		} catch (Exception e) {
			// TODO: handle exception
			return new Response<PageInfo<Flow>>(Response.ERROR,"流程列表获取失败");
		}
	}
	
	@RequestMapping(value = "/getItemFlowList", produces = { "application/json;charset=utf-8" }, method = RequestMethod.GET)
	public Response<List<FlowItem>>  getItemFlowList() {
		try {
			return flowItemService.selectFlowItemList();
		} catch (Exception e) {
			// TODO: handle exception
			return new Response<List<FlowItem>>(Response.ERROR,"流程列表获取失败");
		}
	}
	
	@RequestMapping(value = "/addFlowAction", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<Integer>  addFlowAction( @RequestBody  FlowAcceptVo flowAcceptVo) {
		try {
			return flowService.insertOrUpdateRecord(flowAcceptVo);
		} catch (Exception e) {
			// TODO: handle exception
			return new Response<Integer>(Response.ERROR,"流程列表获取失败");
		}
	}
	@RequestMapping(value = "/deleteFlow", produces = { "application/json;charset=utf-8" }, method = RequestMethod.GET)
	public Response<Integer>  deleteFlow( @RequestParam long flowId) {
		try {
			return flowService.deleteFlow(flowId);
		} catch (Exception e) {
			// TODO: handle exception
			return new Response<Integer>(Response.ERROR,"数据获取失败");
		}
	}
	
	@RequestMapping(value = "/getFlowAction", produces = { "application/json;charset=utf-8" }, method = RequestMethod.GET)
	public Response<List<FlowActionVo>>  getFlowAction(long flowId) {
		try {
			return flowActionService.selectFlowActionList(flowId);
		} catch (Exception e) {
			// TODO: handle exception
			return new Response<List<FlowActionVo>>(Response.ERROR,"流程列表获取失败");
		}
	}
}

