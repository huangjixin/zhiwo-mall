package com.fulan.api.flow.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.flow.domain.FlowAction;
import com.fulan.api.flow.vo.FlowActionVo;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 流程执行 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@FeignClient(name = "flow")
public interface FlowActionService  {
	@GetMapping("/manage/flow/getFlowAction")
	Response<List<FlowActionVo>> getFlowAction(@RequestParam(name = "flowId") long flowId);
	
	@GetMapping("/customer/interviewFlow/getFlowActionById")
	FlowAction getFlowActionById(@RequestParam(name = "id") long id);
	
	@GetMapping("/customer/interviewFlow/getFlowActionByOrgIdAndStep")
	FlowAction getFlowActionByOrgIdAndStep(@RequestParam(name = "orgId") String orgId,@RequestParam(name = "step") int step);
}
