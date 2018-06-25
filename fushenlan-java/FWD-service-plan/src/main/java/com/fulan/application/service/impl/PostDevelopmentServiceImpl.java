package com.fulan.application.service.impl;

import com.fulan.api.plan.domain.ExpiredAlarm;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.api.plan.domain.PlanCourseDto;
import com.fulan.api.plan.domain.PostDevelopment;
import com.fulan.api.plan.vo.ClassPlanFwVo;
import com.fulan.api.plan.vo.DevelopmentFwVo;
import com.fulan.api.plan.vo.PlanCourseVo;
import com.fulan.api.plan.vo.PostDevelopmentVo;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.orm.id.Idfactory;
import com.fulan.application.service.PlanCourseService;
import com.fulan.application.service.PostDevelopmentService;
import com.fulan.application.util.domain.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fulan.application.context.CommenConstant;
import com.fulan.application.manage.controller.PostDevelopmentController;
import com.fulan.application.mapper.ClassPlanMapper;
import com.fulan.application.mapper.ExpiredAlarmMapper;
import com.fulan.application.mapper.PlanCourseMapper;
import com.fulan.application.mapper.PostDevelopmentMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fulan.api.course.service.CourseService;

import java.util.*;

@Service
@Transactional
public class PostDevelopmentServiceImpl implements PostDevelopmentService{
    @Autowired
    private PostDevelopmentMapper postDevelopmentMapper;
    @Autowired
    private PlanCourseMapper planCourseMapper;
    
	@Autowired
	private ExpiredAlarmMapper expiredAlarmMapper;
    @Autowired
    private ClassPlanMapper classPlanMapper;
    
    @Autowired
	private PlanCourseService planCourseService;
    
    @Autowired
    private CourseService courseService;
    @Autowired
    private IdGenerator idGenerator;
    
    @Autowired
    private AttachmentService attachmentService;
    
    private Logger logger = LoggerFactory.getLogger(PostDevelopmentController.class);
    @Override
    public String insertPostDevelopment(PostDevelopmentVo PostDevelopment) {
    		logger.info("---------------------新增岗位Impl---------------------");
    		List<PlanCourseVo> plist=PostDevelopment.getPlanCourses();
        //当无ID的时候进行添加操作
        	if( PostDevelopment.getId()==null|| PostDevelopment.getId().equals(""))
        	{
           		postDevelopmentMapper.insert(PostDevelopment);
            	//循环添加ID
            	if(plist.size()>0){

            		try {
            			for (int i = 0; i < plist.size(); i++) {
                    		PostDevelopment.setId(idGenerator.generate());
                    	}

                        	//循环添加ID
                    		for (PlanCourseVo planCourse:plist) {
                    			planCourse.setId(idGenerator.generate());
                    			planCourse.setPlanId(PostDevelopment.getId());

                    			//设置计划类型
                    			planCourse.setCourseType(CommenConstant.EL_COURSE_TYPE_POSTDEVELOPMENT);
                    			//关联类型
                    			planCourse.setAssociateType(CommenConstant.EL_ASSOCIATE_TYPE_ONLINECOURSE);
                    			//创建时间
                    			planCourse.setGmtCreate(new Date());
                    		/*	//是否必修
                    			planCourse.setIsCompulsory();
                    			//所属阶段
                    			planCourse.setStage();
                    			//排序
                    			//开始时间
                    			planCourse.setStartDate();
                    			//截止时间
                    			planCourse.setEndDate();
                    			//创建人
                    			planCourse.setCreateUser();

                    			//修改人
                    			planCourse.setModifyUser();
                    			//修改时间
                    			planCourse.setGmtModified();*/
            			}
                    	postDevelopmentMapper.batchSavePlanCourseVo(plist);
                    	String success=new Response<String>(Response.SUCCESS, Response.SUCCESS_MESSAGE).getMsg();
                    	return success;
					} catch (Exception e) {
						logger.error(e.getMessage());
						String error=new Response<>(Response.ERROR, Response.ERROR_MESSAGE).getMsg();
						return error;
					}
            	}
        	}

        	//当有计划ID的时候进行插入操作
        	else {

        		try {
        		System.err.println("++++++++++++++++++++++++++++++++++++++++"+PostDevelopment.getId());
        			postDevelopmentMapper.updateById(PostDevelopment);
            		//插入中间表  1.先根据岗位计划ID删除中间表的数据  2。然后添加数据
            		postDevelopmentMapper.deletPlanCourse(PostDevelopment.getId());

            		if(plist.size()>0){
            		//添加到中间表
                	//循环添加ID
            		for (PlanCourseVo planCourse:plist) {
            			planCourse.setId(idGenerator.generate());
            			planCourse.setPlanId(PostDevelopment.getId());
            			//设置计划类型
            			planCourse.setCourseType(CommenConstant.EL_COURSE_TYPE_POSTDEVELOPMENT);
            			//关联类型
            			planCourse.setAssociateType(CommenConstant.EL_ASSOCIATE_TYPE_ONLINECOURSE);
            			//创建时间
            			planCourse.setGmtCreate(new Date());
            		/*	//是否必修
            			planCourse.setIsCompulsory();
            			//所属阶段
            			planCourse.setStage();
            			//排序
            			//开始时间
            			planCourse.setStartDate();
            			//截止时间
            			planCourse.setEndDate();
            			//创建人
            			planCourse.setCreateUser();

            			//修改人
            			planCourse.setModifyUser();
            			//修改时间
            			planCourse.setGmtModified();*/
        			}
            		}
                	postDevelopmentMapper.batchSavePlanCourseVo(plist);
                	String success=new Response<String>(Response.SUCCESS, Response.SUCCESS_MESSAGE).getMsg();
                	System.err.println(success);
                	return success;
				} catch (Exception e) {
					logger.error(e.getMessage());
					String error=new Response<>(Response.ERROR, Response.ERROR_MESSAGE).getMsg();
					System.err.println(error);
					return error;

        	}
            	}
        	return null;
        	}




    @Override
    public PostDevelopmentVo getOnePostDevelopment(Long id) {
        return postDevelopmentMapper.getPostDevelopmentInfo(id);
    }




	@Override
	public PostDevelopmentVo getPostDevelopment(Long id) {

		return postDevelopmentMapper.selectById(id);
	}



	@Override
	public Response<String> insertDevelopmentOrupdate(PostDevelopment postDevelopment, PlanCourseDto[] planCourseDtos,
			ExpiredAlarm[] expiredAlarms, Long userId,String levelId,String levelName, Long fileId) {
		Response<String> resp = new Response<>(Response.SUCCESS,"保存数据成功");
		List<PlanCourse> planCourses = new ArrayList<PlanCourse>();
		Long id = postDevelopment.getId();
		postDevelopment.setGmtModified(new Date());
		for (PlanCourseDto dto : planCourseDtos) {
			if(null == dto.getAssociateId()){
				continue;
			}
			PlanCourse course = new PlanCourse();
			//设置计划ID
			course.setPlanId(postDevelopment.getId());
			//设置计划类型
			course.setCourseType(CommenConstant.EL_COURSE_TYPE_POSTDEVELOPMENT);
			//设置基础课程编号
			course.setAssociateId(dto.getAssociateId());
			//关联类别
			course.setAssociateType(dto.getAssociateType());
			//是否必修
			course.setIsCompulsory(dto.getIsCompulsory());
			//顺序
			course.setSeq(dto.getSeq());
			//所属阶段
			course.setStage(dto.getStage());
			//阶段周期
			course.setStartTime(dto.getStartTime());
			course.setEndTime(dto.getEndTime());
			//创建人
			course.setCreateUser(userId);
			//签到地点
			course.setSignAddress(dto.getSignAddress());
			//创建时间
			course.setGmtCreate(new Date());
			planCourses.add(course);
		}
		
		
		
		if(null == id){
		    id = idGenerator.generate();
			postDevelopment.setId(id);
			postDevelopment.setGmtCreate(new Date());//创建时间
			Long postLevel=null;
			if(null!=levelId && levelId!=""){
				postLevel=Long.valueOf(levelId);
			}
			postDevelopment.setPostLevel(postLevel);
			postDevelopment.setName(levelName);
			postDevelopmentMapper.insertDevelopment(postDevelopment);
		}else{
			
			postDevelopment.setModifyUser(userId);
			postDevelopment.setGmtModified(new Date());
			postDevelopmentMapper.updateDevelopmentById(postDevelopment);
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
		
		//批量插入计划课程
		if(planCourses.size()>0){
			PlanCourse planCourse = new PlanCourse();
			planCourse.setPlanId(postDevelopment.getId());
			EntityWrapper<PlanCourse> wrapper = new EntityWrapper<>(planCourse);
			planCourseService.delete(wrapper);
			for(PlanCourse plan : planCourses){
				plan.setPlanId(postDevelopment.getId());
			}
			planCourseService.insertBatch(planCourses);
		}

		if(null != expiredAlarms){
			ExpiredAlarm ea = new ExpiredAlarm();
				ea.setCourseId(postDevelopment.getId());
			
			EntityWrapper<ExpiredAlarm> wrapper = new EntityWrapper<>(ea);
			expiredAlarmMapper.delete(wrapper);
			for(ExpiredAlarm expiredAlarm : expiredAlarms){
				expiredAlarm.setId(Idfactory.generate());
				expiredAlarm.setCourseType(CommenConstant.EL_COURSE_TYPE_POSTDEVELOPMENT);
				expiredAlarm.setCourseId(postDevelopment.getId());
				expiredAlarmMapper.insert(expiredAlarm);
			}
		}
				
				
		
		return resp;
	}




	@Override
	public Map<String, Object> findOneDevelopmentFW(String id) {
		Map<String,Object> returnMap = new HashMap<>();
		PostDevelopment postDevelopment=postDevelopmentMapper.selectDeveById(id);
		Map<String,Object> map = new HashMap<>();
		Long pId=null;
		if(null!=postDevelopment){
			 pId=postDevelopment.getId();
		}
		if(null!=pId){
			map.put("id", pId);
			List<DevelopmentFwVo> list = postDevelopmentMapper.seleStageDevelopmentById(map);
			returnMap.put("list", list);
			ExpiredAlarm expiredAlarm = new ExpiredAlarm();
			expiredAlarm.setCourseId(pId);
			EntityWrapper<ExpiredAlarm> wrapperEA = new EntityWrapper<>(expiredAlarm);
			List<ExpiredAlarm> expiredAlarmList = new ArrayList<ExpiredAlarm>();
			expiredAlarmList=expiredAlarmMapper.selectList(wrapperEA);
			returnMap.put("expiredAlarmList", expiredAlarmList);
		}
		returnMap.put("postDevelopment", postDevelopment);
		return returnMap;
	}

	
}
