package com.fulan.api.flow.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.flow.domain.Flow;
import com.fulan.api.flow.vo.FlowAcceptVo;
import com.fulan.api.flow.vo.PersonnelAddVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 流程执行 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@FeignClient(name = "flow")
public interface FlowService  {
	/**
	 * 
	 * @author 
	 * @param paperId
	 * @return
	 */
	@GetMapping("/manage/flow/flowList")
	Response<PageInfo<Flow>> flowList(@RequestBody  Flow flow,
			 	@ApiParam(name = "pageNo", value = "要跳转的页数") @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
	            @ApiParam(name = "pageSize", value = "每页条数，默认：10", required = false) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
	            @ApiParam(name = "pageSortFiled", value = "排序字段名，默认：sort", required = false) @RequestParam(name = "pageSortFiled", defaultValue = "create_time") String pageSortFiled,
	            @ApiParam(name = "pageSortType", value = "顺序：asc,倒叙：desc，默认：asc", required = false) @RequestParam(name = "pageSortType", defaultValue = "asc") String pageSortType);
	
	@GetMapping("/manage/flow/addFlowAction")
	Response<Integer> addFlow(@RequestBody  FlowAcceptVo flowAcceptVo); 
	
	
	@GetMapping("/manage/flow/deleteFlow")
	Response<Integer> deleteFlow(@RequestParam(name = "flowId") long flowId); 
	
	@GetMapping("/manage/flow/selectFlowById")
	Response<Flow> selectFlowById(@RequestBody Flow flow); 
	
	@GetMapping("/customer/interviewFlow/findPersonnelbyagentCode")
	Response<Page<PersonnelAddVo>> findPersonnelbyagentCode(@ApiParam(name = "agentCode", value = "代理人code") @RequestParam(name = "agentCode",required = true) String agentCode,
			@ApiParam(name = "orgId", value = "分公司ID") @RequestParam(name="orgId",required = true) String orgId,
			@ApiParam(name = "keyWord", value = "关键词查询") @RequestParam(name="keyWord",defaultValue = "") String keyWord,
			@ApiParam(name = "pageNo", value = "要跳转的页数") @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@ApiParam(name = "pageSize", value = "每页条数，默认：10", required = false) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize);
	
	

}
