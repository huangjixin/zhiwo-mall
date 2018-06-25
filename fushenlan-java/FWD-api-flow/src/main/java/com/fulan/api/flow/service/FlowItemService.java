package com.fulan.api.flow.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.flow.domain.FlowItem;
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
public interface FlowItemService  {
	/**
	 * 
	 * @author kang
	 * @param paperId
	 * @return
	 */
	@GetMapping("/manage/flow/flowItemList")
	Response<PageInfo<FlowItem>> flowItemList(@RequestBody  FlowItem flowItem,
			 	@ApiParam(name = "pageNo", value = "要跳转的页数") @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
	            @ApiParam(name = "pageSize", value = "每页条数，默认：10", required = false) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
	            @ApiParam(name = "pageSortFiled", value = "排序字段名，默认：sort", required = false) @RequestParam(name = "pageSortFiled", defaultValue = "create_time") String pageSortFiled,
	            @ApiParam(name = "pageSortType", value = "顺序：asc,倒叙：desc，默认：asc", required = false) @RequestParam(name = "pageSortType", defaultValue = "asc") String pageSortType);
	
	@GetMapping("/manage/flow/addFlowItem")
	Response<Integer> addFlowItem(@RequestBody  FlowItem flowItem); 
	
	@GetMapping("/manage/flow/getFlowItem")
	Response<FlowItem> getFlowItem(@RequestParam(name = "flowItemId") long flowItemId); 
	
	@GetMapping("/manage/flow/deleteFlowItem")
	Response<Integer> deleteFlowItem(@RequestParam(name = "flowItemId") long flowItemId); 
	
	@GetMapping("/manage/flow/getItemFlowList")
	Response<List<FlowItem>> getItemFlowList(); 
	
	
	
	
	

}
