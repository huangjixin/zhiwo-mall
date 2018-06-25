package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.plan.domain.OfflineActivity;
import com.fulan.api.plan.domain.PlanAuthority;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.api.plan.vo.OfflineActivityDto;
import com.fulan.api.plan.vo.OfflineActivityVOPC;
import com.fulan.api.plan.vo.OfflineActivityVoFw;
import com.fulan.api.security.service.AccountService;
import com.fulan.api.security.vo.AccountOffVo;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.OfflineActivityMapper;
import com.fulan.application.mapper.PlanAuthorityMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.orm.id.Idfactory;
import com.fulan.application.service.OfflineActivityService;
import com.fulan.application.service.PlanCourseService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OfflineActivityServiceImpl extends ServiceImpl<OfflineActivityMapper, OfflineActivity> implements OfflineActivityService {

	@Autowired
	private OfflineActivityMapper offlineActivityMapper;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
    private IdGenerator idGenerator;
	
	@Autowired
	private PlanCourseService planCourseService;
	
	@Autowired
	private PlanAuthorityMapper planAuthorityMapper;
	
	@Autowired
    private AttachmentService attachmentService;
	
	@Override
	public PageInfo<OfflineActivityVoFw> pageList(Page<OfflineActivityVoFw> page, String name, String state,String enterStartDate,String enterEndDate,int pageNo,
			int pageSize) {
		PageInfo<OfflineActivityVoFw> pageInfo = new PageInfo<OfflineActivityVoFw>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = null;
		Date endTime = null;
		try {
			if(StringUtils.isNotEmpty(enterStartDate)){
				startTime = sdf.parse(enterStartDate);
			}
			if(StringUtils.isNotEmpty(enterEndDate)){
				endTime = sdf.parse(enterEndDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int total = offlineActivityMapper.pageListCount(name, state,startTime, endTime);
		pageInfo.setRecords(offlineActivityMapper.pageList(page, name, state, startTime, endTime, pageNo, pageSize));
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}

	@Override
	public Response<String> updateOrDele(String type,String state,String ids) {
		Response<String>  response = new Response<String>(Response.SUCCESS,"操作数据成功");
		String idArr[] = ids.split(",");
		List<Long> idList = new ArrayList<>();
		List<OfflineActivity> offlineActivityList = new ArrayList<>();
		for(String id : idArr){
			OfflineActivity offlineActivity = new OfflineActivity();
			offlineActivity.setId(Long.valueOf(id));
			if(StringUtils.isNotEmpty(state)){
				offlineActivity.setState(Integer.valueOf(state));
			}
			offlineActivityList.add(offlineActivity);
			idList.add(Long.valueOf(id));
		}
		if(CollectionUtils.isNotEmpty(offlineActivityList)){
			if("dele".equals(type)){
				this.deleteBatchIds(idList);
			}else{
				this.updateBatchById(offlineActivityList);
			}
		}else{
			response.setCode(Response.ERROR);
			response.setMsg("操作数据失败：id为空");
		}
		return response;
	}
	

	@Override
	public Response<String> insertOrUpdateFW(OfflineActivityVOPC offlineActivityVOPC, Long fileId) {
		OfflineActivity offlineActivity = offlineActivityVOPC.getOfflineActivity();
		List<PlanAuthority> planAuthorityList = offlineActivityVOPC.getPlanAuthorityList();
		Response<String> response = new Response<>(Response.SUCCESS,"保存数据成功");
		PlanCourse planCourse = new PlanCourse();//上课的开始与结束时间
		planCourse.setEndTime(offlineActivityVOPC.getStartTime());
		planCourse.setStartTime(offlineActivityVOPC.getEndTime());
		Long id = offlineActivity.getId();
		if(null == id){
			id = idGenerator.generate();
			offlineActivity.setId(id);
			offlineActivity.setGmtCreate(new Date());
			planCourse.setPlanId(id);
			planCourse.setAssociateId(offlineActivity.getCourseId());
			planCourse.setStage(1);
			planCourse.setCourseType(CommenConstant.EL_COURSE_TYPE_OFFLINEACTIVITY);
			planCourse.setAssociateType(CommenConstant.EL_COURSE_TYPE_OFFLINEACTIVITY);
			planCourse.setCreateUser(offlineActivity.getCreateUser());
			planCourse.setGmtCreate(offlineActivity.getGmtCreate());
			offlineActivityMapper.insertSelective(offlineActivity);
			planCourseService.insertPlanCourse(planCourse);
		}else{
			planCourse.setId(offlineActivityVOPC.getPlanCourseId());
			offlineActivity.setGmtModified(new Date());
			this.updateById(offlineActivity);
			planCourseService.updateById(planCourse);
		}
		
		//关联附件表
        Attachment am = null;
        if (fileId != null && !"".equals(fileId)) {
            am =  attachmentService.selectById(fileId);
        }
        //更新附件hostId字段
        if (am != null) {
            am.setHostId(id);
            attachmentService.updateById(am);
         }
		if(CollectionUtils.isNotEmpty(planAuthorityList)){
			for(PlanAuthority planAuthority:planAuthorityList){
				if(null !=planAuthority.getAuthorityType()){
					planAuthority.setId(Idfactory.generate());
					planAuthority.setCourseType(CommenConstant.EL_COURSE_TYPE_OFFLINEACTIVITY);
					planAuthority.setCourseId(offlineActivity.getId());
					planAuthority.setGmtCreate(new Date());
					planAuthorityMapper.insert(planAuthority);
				}
			}
		}
		return response;
	}

	/*
	 * 查看或修改线下活动
	 * id 主键id
	 * type :"check"为查看   
	 * name 报名用户名 (type为check 需要传值)
	 */
	@Override
	public Map<String, Object> findByIdFW(String id,String name,String isSign,String type,int pageNo,int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offlineActivity", this.selectById(id));
		PlanCourse planCourse = planCourseService.selectByPlanIdOff(id);
		map.put("planCourse", planCourse);
		if("check".equals(type)){
			List<Long> idList = new ArrayList<>();
			List<Long> userIdList = new ArrayList<>();
			Map<String,Object> mapHash = new HashMap<>();
			mapHash.put("id", id);
			mapHash.put("isSign", isSign);
			idList = offlineActivityMapper.seleByIdList(mapHash);

			if(CollectionUtils.isNotEmpty(idList)){
			PageInfo<AccountOffVo> page = accountService.selectByNameOff(idList,name, pageNo, pageSize);
				if(CollectionUtils.isNotEmpty(page.getRecords())){
					map.put("page", page);
					for(AccountOffVo accountOffVo :page.getRecords()){
						userIdList.add(accountOffVo.getId());
					}
					List<OfflineActivityVoFw> list = offlineActivityMapper.seleByCourseIdIsSign(id, userIdList);
					Map<String, Object> mapIsign = new HashMap<>();
					for(OfflineActivityVoFw offlineActivityVoFw:list){
						mapIsign.put(String.valueOf(offlineActivityVoFw.getUserId()), offlineActivityVoFw);
					}
					map.put("mapIsign", mapIsign);
				}
			}

		}
		List<PlanAuthority> planAuthorityList = planAuthorityMapper.selectByAssociateId(id);
		map.put("planAuthorityList", planAuthorityList);
		return map;
	}

	@Override
	public Page<OfflineActivityDto> searchOfflineActivity(Map<String, Object> paramMap) {
		Page<OfflineActivityDto> page = new Page<OfflineActivityDto>((Integer)paramMap.get("pageNo"), (Integer)paramMap.get("pageSize"));
		page.setRecords(offlineActivityMapper.searchOfflineActivity(page,paramMap));
		return page;
	}


}
