package com.fulan.application.service;


import java.util.List;

import com.fulan.api.flow.vo.FlowActionVo;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 流程执行 服务类
 * </p>
 *
 * @author chenzhuang123
 * @since 2018-01-24
 */
public interface FlowActionService  {
	

	Response<List<FlowActionVo>> selectFlowActionList(long flowId);
}
