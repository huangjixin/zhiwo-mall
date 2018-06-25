package com.fulan.application.service;

import java.util.List;

import com.fulan.api.flow.domain.FlowItem;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

public interface FlowItemService {
	/**
	 * 流程基础管理列表
	 * @param flowItem
	 * @param page
	 * @return Page<FlowItem>
	 * **/
	Response<PageInfo<FlowItem>> listByPage(FlowItem flowItem,int pageNo, int pageSize, String pageSortFiled, String pageSortType);
	
	Response<Integer>  insertOrUpdateRecord(FlowItem record);
	
	Response<FlowItem>  getFlowItem(long flowItemId);
	
	Response<Integer> deleteFlowItem(long flowItemId);
	
	Response<List<FlowItem>> selectFlowItemList();
}
