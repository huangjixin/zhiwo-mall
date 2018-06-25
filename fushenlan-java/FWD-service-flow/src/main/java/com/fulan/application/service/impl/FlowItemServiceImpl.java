package com.fulan.application.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.flow.domain.FlowItem;
import com.fulan.application.mapper.FlowItemMapper;
import com.fulan.application.orm.id.Idfactory;
import com.fulan.application.service.FlowItemService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
@Service
@Transactional
public class FlowItemServiceImpl extends ServiceImpl<FlowItemMapper, FlowItem> implements FlowItemService{
	@Autowired
	FlowItemMapper flowItemMapper;
	@Override
	public Response<PageInfo<FlowItem>> listByPage(FlowItem flowItem,
			int pageNo, int pageSize, String pageSortFiled, String pageSortType) {
		// TODO Auto-generated method stub
		 // 组装page，页数、条数、排序字段、排序方式
		FlowItem searchFlowItem = new FlowItem();
		if(flowItem != null&& flowItem.getBusinessName() != null&&!"".equals(flowItem.getBusinessName())){
			searchFlowItem.setFlowItemName(flowItem.getBusinessName());
		}
		if(flowItem != null&& flowItem.getMoudleName() != null&&!"".equals(flowItem.getMoudleName())){
			searchFlowItem.setMoudleName(flowItem.getMoudleName());
		}
		if(flowItem != null&& flowItem.getSystemName() != null&&!"".equals(flowItem.getSystemName())){
			searchFlowItem.setSystemName(flowItem.getSystemName());
		}
        Page<FlowItem> page = new Page<FlowItem>(pageNo, pageSize);
        page.setOrderByField(pageSortFiled);
        page.setAsc(pageSortType.equals("asc") ? true : false);
        EntityWrapper<FlowItem> ew = new EntityWrapper<FlowItem>(searchFlowItem);
        if (StringUtils.isNotEmpty(flowItem.getFlowItemName())) {
            ew.like("flow_item_name",flowItem.getFlowItemName() );
        }
        Response<PageInfo<FlowItem>> resp=new Response<PageInfo<FlowItem>>(Response.SUCCESS, "获取流程信息列表");
        PageInfo<FlowItem> pageInfo = new PageInfo<FlowItem>();
        Page<FlowItem> flowPage = this.selectPage(page, ew);
        pageInfo.setRecords(flowPage.getRecords());
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageTotal(flowPage.getPages());
        pageInfo.setPageNo(flowPage.getCurrent());
        resp.setData(pageInfo);
        return resp;
	}

	@Override
	public Response<Integer> insertOrUpdateRecord(FlowItem record) {
		// TODO Auto-generated method stub
		Response<Integer> resp=new Response<Integer>(Response.SUCCESS, "插入流程基础信息");
		long id = Idfactory.generate();
		if(record.getId()==null){
			record.setCreateTime(new Date());
			record.setCreateUser((long)201882300);
			record.setId(id);
		}
		record.setUpdateTime(new Date());
		record.setUpdateUser((long)201882300);
		
		boolean result = this.insertOrUpdate(record);
		resp.setData(1);
		return resp;
	}

	@Override
	public Response<FlowItem> getFlowItem(long flowItemId) {
		// TODO Auto-generated method stub
		Response<FlowItem> resp=new Response<FlowItem>(Response.SUCCESS, "获取基础信息");
		FlowItem flowItem = new FlowItem();
		flowItem.setId(flowItemId);
		resp.setData(this.selectById(flowItem));
		return resp;
	}

	@Override
	public Response<Integer> deleteFlowItem(long flowItemId) {
		// TODO Auto-generated method stub
		Response<Integer> resp=new Response<Integer>(Response.SUCCESS, "删除流程");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", flowItemId);
		this.deleteByMap(map);
		resp.setData(1);
		return resp;

	}

	@Override
	public Response<List<FlowItem>> selectFlowItemList() {
		// TODO Auto-generated method stub
		Response<List<FlowItem>> resp=new Response<List<FlowItem>>(Response.SUCCESS, "查询基础流程成功");
		resp.setData(flowItemMapper.selectFlowItemList());
		return resp;
	}
	
}
