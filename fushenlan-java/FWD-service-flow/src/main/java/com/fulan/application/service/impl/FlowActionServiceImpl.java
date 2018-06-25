package com.fulan.application.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.flow.domain.FlowAction;
import com.fulan.api.flow.vo.FlowActionVo;
import com.fulan.application.mapper.FlowActionMapper;
import com.fulan.application.service.FlowActionService;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 流程执行 服务实现类
 * </p>
 *
 * @author chenzhuang123
 * @since 2018-01-24
 */
@Service
@Transactional
public class FlowActionServiceImpl extends ServiceImpl<FlowActionMapper, FlowAction> implements FlowActionService {

	@Autowired
	FlowActionMapper flowActionMapper;
	
	@Override
	public Response<List<FlowActionVo>> selectFlowActionList(long flowId) {
		// TODO Auto-generated method stub
		Response<List<FlowActionVo>> resp=new Response<List<FlowActionVo>>(Response.SUCCESS, "获取流程节点信息列表");
		FlowActionVo flowActionVo = new FlowActionVo();
		flowActionVo.setFlowId(flowId);
		List<FlowActionVo> list = flowActionMapper.getFlowActionVoList(flowActionVo);
		resp.setData(list);
		return resp;
	}

}
