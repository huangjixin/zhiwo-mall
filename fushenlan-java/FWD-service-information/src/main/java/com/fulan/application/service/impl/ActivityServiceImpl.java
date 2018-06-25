package com.fulan.application.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.information.domain.Activity;
import com.fulan.api.information.domain.ActivityMemberMapping;
import com.fulan.api.information.domain.ApplyDetail;
import com.fulan.api.information.vo.ActivityVo;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.application.mapper.ActivityMapper;
import com.fulan.application.orm.id.Idfactory;
import com.fulan.application.service.ActivityService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;

@Service
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	private ActivityMapper activityMapper;
	
	@Autowired
	private AttachmentService attachmentService;

	@Override
	public PageInfo<ActivityVo> listactivity(Page<ActivityVo> page, String title, String type, String status,
			String actStartDate, String actEndDate, int pageNo, int pageSize) {
		
		PageInfo<ActivityVo> pageInfo = new PageInfo<>();
        int total = activityMapper.listForManageCount(title,type,status,actStartDate,actEndDate);
        List<ActivityVo> listForManage = activityMapper.listForManage(page,title,type,status,actStartDate,actEndDate, pageNo, pageSize);
        ArrayList<ActivityVo> list = new ArrayList<ActivityVo>();
        for (ActivityVo activityVo : listForManage) {
    	  Long num= activityMapper.selectActivityCount(activityVo.getId());
    	  activityVo.setActivityCount(num);
    	  list.add(activityVo);
	}
        pageInfo.setRecords(list);
        pageInfo.setPageNo(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
        pageInfo.setPageRecords((int) page.getTotal());
		return pageInfo;
	}

	@Override
	public Response<String> updateStatus(String id, String status) {
		Response<String> response = new Response<>(Response.SUCCESS, "修改成功");
		Date date = null;
		if(status.equals("2")){
			date=new Date();
		}
		Integer updateStatus = activityMapper.updateStatus(id,status,date);
		if(null !=updateStatus && updateStatus==0){
			response.setCode(Response.ERROR);
			response.setMsg("修改失败");
		}
		return response;
	}

	@Override
	public Response<String> deleteActivity(String id) {
		Response<String> response = new Response<>(Response.SUCCESS, "删除成功");
		Integer num=activityMapper.deleteActivity(id);
		if(null !=num && num==0){
			response.setCode(Response.ERROR);
			response.setMsg("删除失败");
		}
		return response;
	}

	@Override
	public Response<String> insertActivity(ActivityVo activityVo, String pathId, String FilePathId) {
		Response<String> response = new Response<String>(Response.SUCCESS,"咨询新增成功");
		Long id = Idfactory.generate();
		if(null !=pathId && ""!=pathId){
			Attachment	attachment=	attachmentService.selectById(Long.parseLong(pathId));
			attachment.setHostId(id);
			attachmentService.updateById(attachment);
		}
		if(null !=pathId && ""!=FilePathId){
			Attachment	attachment=	attachmentService.selectById(Long.parseLong(FilePathId));
			attachment.setHostId(id);
			attachmentService.updateById(attachment);
		}
		activityVo.setId(id);
		activityVo.setGmtCreate(new Date());
		activityVo.setGmtModified(new Date());
		int count = activityMapper.insertActivity(activityVo);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("咨询新增失败"); 
		}else{
			if(null != activityVo.getActivityMemberMapping() && activityVo.getActivityMemberMapping().size()>0 ){
				for (ActivityMemberMapping qa : activityVo.getActivityMemberMapping()) {
					qa.setId(Idfactory.generate());
					qa.setTargetType(activityVo.getSpecifyType());
					qa.setActivityId(id);
					qa.setTargetType(activityVo.getStatus());
					activityMapper.insertActivityMemberMapping(qa);
				}
			}
		}
		return response;
	}

	@Override
	public Response<String> updateActivity(ActivityVo activityVo, String pathId, String FilePathId) {
		Response<String> response = new Response<String>(Response.SUCCESS,"活动修改成功");
		activityVo.setGmtModified(new Date());
		int count = activityMapper.updateActivity(activityVo);
		if(null !=pathId && ""!=pathId){
			Attachment	attachment=	attachmentService.selectById(Long.parseLong(pathId));
			attachment.setHostId(activityVo.getId());
			attachmentService.updateById(attachment);
		}
		if(null !=pathId && ""!=FilePathId){
			Attachment	attachment=	attachmentService.selectById(Long.parseLong(FilePathId));
			attachment.setHostId(activityVo.getId());
			attachmentService.updateById(attachment);
		}
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("活动失败"); 
		}else{
			activityMapper.deleActivityMemberMapping(activityVo.getId());
			if(null != activityVo.getActivityMemberMapping() && activityVo.getActivityMemberMapping().size()>0 ){
				for (ActivityMemberMapping qa : activityVo.getActivityMemberMapping()) {
					qa.setId(Idfactory.generate());
					qa.setActivityId(activityVo.getId());
					qa.setTargetType(activityVo.getStatus());
					activityMapper.insertActivityMemberMapping(qa);
				}
			}
		}
		return response;
	}

	@Override
	public ActivityVo selectOneActivityById(String id) {
		ActivityVo activityVo=new ActivityVo();
		Activity activity=activityMapper.selectOneActivityById(id);
		List<ActivityMemberMapping> data=activityMapper.selectActivityMemberMapping(id);
		activityVo.setActivityMemberMapping(data);
		activityVo.setActivity(activity);
		return activityVo;
	}

	@Override
	public PageInfo<ApplyDetail> activityApplyList(Page<ApplyDetail> page, String acounyName, int pageNo,
			int pageSize,String id,String signUpSet) {
		
		PageInfo<ApplyDetail> pageInfo = new PageInfo<>();
        int  total= 0;
        List<ApplyDetail> listForManage  = null;
        ArrayList<ApplyDetail> list=new ArrayList<ApplyDetail>();
        List<ApplyDetail> list1=null;
        if(signUpSet.equals("3")){
        	total=activityMapper.selectActivityMamerCount(acounyName,id,signUpSet);
        	list1=activityMapper.selectActivityMamerForManage(page,acounyName, pageNo, pageSize,id,signUpSet);
        	listForManage=activityMapper.activityApplyListForManage(page,acounyName, pageNo, pageSize,id);
        	if(null != listForManage){
        	for (ApplyDetail applyDetail1 : list1) {
        		if(null !=listForManage && listForManage.size()>0){
        			for (ApplyDetail applyDetail : listForManage) {
        				if(applyDetail.getAgentCode().equals(applyDetail1.getAgentCode())){
        					applyDetail1.setStatus("1");
        					applyDetail1.setApplyDate(applyDetail.getApplyDate());
        				}
        				list.add(applyDetail1);
					}
        		}else{
        			list.add(applyDetail1);
        		}
        		
				}
        	}
        }else{
        	total=activityMapper.activityApplyListCount(acounyName,id);
        	listForManage=activityMapper.activityApplyListForManage(page,acounyName, pageNo, pageSize,id);
        	if(null !=listForManage){
        		for (ApplyDetail applyDetail : listForManage) {
        			applyDetail.setStatus("1");
        			list.add(applyDetail);
				}
        	}
        }
        pageInfo.setRecords(list);
        pageInfo.setPageNo(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
        pageInfo.setPageRecords((int) page.getTotal());
		return pageInfo;
	}
	
	
	
	
}
