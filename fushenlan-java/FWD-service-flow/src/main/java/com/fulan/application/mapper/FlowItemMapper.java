package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.flow.domain.FlowItem;
@Mapper
public interface FlowItemMapper  extends BaseMapper<FlowItem>{
	
	List<FlowItem> selectByPage(Page<FlowItem> page,FlowItem flowItem);
	
	List<FlowItem> selectFlowItemList();
	
	
}
