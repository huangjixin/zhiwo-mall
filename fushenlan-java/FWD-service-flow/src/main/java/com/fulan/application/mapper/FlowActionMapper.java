package com.fulan.application.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.flow.domain.FlowAction;
import com.fulan.api.flow.vo.FlowActionVo;

/**
 * <p>
 * 流程执行 Mapper 接口
 * </p>
 *
 * @author chenzhuang123
 * @since 2018-01-24
 */
@Mapper
public interface FlowActionMapper extends BaseMapper<FlowAction> {
	int insertList(List<FlowAction> list);
	
	List<FlowActionVo> getFlowActionVoList(FlowActionVo flowActionVo);
	
	Integer insertSelective(FlowAction record);
	
	Integer insert(FlowAction record);
	
	FlowAction getFlowActionByOrgIdAndStep(Map searchMap);
}
