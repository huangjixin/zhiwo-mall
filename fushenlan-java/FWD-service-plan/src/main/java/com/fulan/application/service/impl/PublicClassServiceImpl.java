package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.codingapi.tx.annotation.TxTransaction;
import com.fulan.api.course.domain.Course;
import com.fulan.api.course.service.CourseService;
import com.fulan.api.material.vo.MaterialAttachDto;
import com.fulan.api.plan.domain.*;
import com.fulan.api.plan.vo.*;
import com.fulan.api.plan.vo.PlanCourseDto;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.service.AccountService;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.*;
import com.fulan.application.orm.id.Idfactory;
import com.fulan.application.service.PublicClassService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * <p>
 * 公开课 服务实现类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-25
 */
@Service
@Transactional
public class PublicClassServiceImpl extends ServiceImpl<PublicClassMapper, PublicClass> implements PublicClassService{



    @Autowired
    PublicClassMapper publicClassMapper;
    @Autowired
    PlanCourseMapper planCourseMapper;
    @Autowired
    StudyPlanMapper studyPlanMapper;
    @Autowired
    private CourseService courseService;
    @Autowired
    private OfflineActivityMapper offlineActivityMapper;
    @Autowired
    private PlanAuthorityMapper planAuthorityMapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    private LearningProgressMapper learningProgressMapper;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private OfflineActivityEnterMapper offlineActivityEnterMapper;
    @Autowired
    private ClassPlanEnterMapper classPlanEnterMapper;

    @Override
	@TxTransaction
    @Transactional
    public String handPubClass(PublicClass pubclass,Map<String,String[]> param,List<PlanAuthority> planAuthorityList
    		,String studyPlanName,String studyPlanDescription, Long fileId) {
        try{
            Long id = pubclass.getId();
            if(null != id){ //修改
            	if(null != planAuthorityList){
            		planAuthorityMapper.deleteByCourseId(id);
					for(PlanAuthority planAuthority  :planAuthorityList ){
						if( null !=planAuthority.getAuthorityType()){
							planAuthority.setId(Idfactory.generate());
							planAuthority.setCourseType(1);
							planAuthority.setCourseId(pubclass.getId());
							planAuthority.setGmtCreate(new Date());
							planAuthorityMapper.insert(planAuthority);
						}
					}
				}
                publicClassMapper.updateById(pubclass);
            }else{
                Long publicClassId=Idfactory.generate();
                id=publicClassId;
                pubclass.setId(publicClassId);
                Long planId = null;
                if(param.containsKey(CommenConstant.EL_ASSOCIATE_TYPE_ONLINECOURSE.toString())){// 线上课程
                    //线上课程  需将课程打包成学习计划 ,并且将课程与计划关联
                    String[] value = param.get(CommenConstant.EL_ASSOCIATE_TYPE_ONLINECOURSE.toString());
                    //List list =Arrays.asList(value);
                    planId = Idfactory.generate();//学习计划公开课关联ID
                    for (int i = 0; i < value.length; i++) {
						
					
                    Course couser =	courseService.selectCourseOne(value[i]);
	                    if(null != couser){
	                        PlanCourse planCourse = new PlanCourse();
	                        planCourse.setId(Idfactory.generate());
	                        planCourse.setPlanId(planId);
	                        planCourse.setStage(CommenConstant.VALUE_ONE);
	                        planCourse.setAssociateId(couser.getId());
	                        planCourse.setAssociateType(CommenConstant.EL_ASSOCIATE_TYPE_ONLINECOURSE);
	                        planCourse.setCourseType(CommenConstant.EL_COURSE_TYPE_STUDYPLAN);
	                        planCourse.setIsCompulsory(CommenConstant.VALUE_YES);
	                        planCourse.setSeq(CommenConstant.VALUE_ONE);
	                        planCourse.setCreateUser(pubclass.getCreateUser());
	                        planCourse.setGmtCreate(new Date());
	                        planCourseMapper.insert(planCourse);
	                        
	                    }
                    }
                    StudyPlan studyPlan = new StudyPlan();
                    studyPlan.setId(planId);
                    studyPlan.setCode(""+Idfactory.generate());
                    studyPlan.setName(studyPlanName);
                    studyPlan.setStageNum(CommenConstant.VALUE_ONE);
                    studyPlan.setCourseNum(CommenConstant.VALUE_ONE);
                    studyPlan.setState(CommenConstant.VALUE_YES);
                    studyPlan.setDescription(studyPlanDescription);
                    studyPlan.setGmtCreate(new Date());
                    studyPlan.setCreateUser(pubclass.getCreateUser());
                    studyPlanMapper.insert(studyPlan);
                    
                }else if(param.containsKey(CommenConstant.EL_ASSOCIATE_TYPE_CLASSPLAN.toString())){ //学习计划
                    planId = Long.valueOf(param.get(CommenConstant.EL_ASSOCIATE_TYPE_CLASSPLAN.toString())[0]);

                }else{
                    return null;
                }
                PlanCourse planCourse = new PlanCourse();

                planCourse.setId(Idfactory.generate());
                planCourse.setPlanId(publicClassId);
                planCourse.setAssociateId(planId);
                planCourse.setStage(CommenConstant.VALUE_ONE);
                planCourse.setAssociateType(CommenConstant.EL_ASSOCIATE_TYPE_CLASSPLAN);
                planCourse.setCourseType(CommenConstant.EL_COURSE_TYPE_PUBLICCLASS);
                planCourse.setIsCompulsory(CommenConstant.VALUE_YES);
                planCourse.setSeq(CommenConstant.VALUE_ONE);
                planCourse.setCreateUser(pubclass.getCreateUser());
                planCourse.setGmtCreate(new Date());
                planCourse.setIsCompulsory(CommenConstant.VALUE_YES);
                planCourseMapper.insert(planCourse);
                
                pubclass.setPlanId(planId);
                pubclass.setIsSticky(CommenConstant.VALUE_NO);
                /*Course couser =	new Course();
                courseService.handCourse(couser);*/
                pubclass.setState(CommenConstant.INTEGER_NOSTATE_PUBLICCLASS);
                
                if(null != planAuthorityList){
					for(PlanAuthority planAuthority  :planAuthorityList ){
						if( null !=planAuthority.getAuthorityType()){
							planAuthority.setId(Idfactory.generate());
							planAuthority.setCourseType(1);
							planAuthority.setCourseId(pubclass.getId());
							planAuthority.setGmtCreate(new Date());
							planAuthorityMapper.insert(planAuthority);
						}
					}
				}
                publicClassMapper.insertAllColumn(pubclass);
                
                
                
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
            return id+"";
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    @Override
    public PageInfo<PublicCourseDto> manageListByPage(Page<PublicCourseDto> page, Map<String, Object> map) {
        PageInfo<PublicCourseDto> pageInfo = new PageInfo<>();
        pageInfo.setRecords(publicClassMapper.listByPage(page, map));
        int total = publicClassMapper.listByPageCount(map);
        pageInfo.setPageNo(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setPageTotal(PageUtil.getPages(page.getSize(),total));
        pageInfo.setPageRecords(page.getTotal());
        return pageInfo;
    }
    /**
     * 公开课 线上课程选择  限制条件：线上课程，未打包成学习计划
     预留map 其他未知条件
     */
    @Override
    public List<Course> courseList(Map<String,Object> map) {
        List<Course> course = publicClassMapper.selectCourseForPub(map);
        return course;
    }
    @Override
    public PublicClassVo publicClassVoInFo(String id) {
        return publicClassMapper.publicClassVoInFo(id);
    }


    @Override
    public Page<PublicCourseDto> searchPublicClass(Map<String,Object> paramMap ) {
        Page<PublicCourseDto> page = new Page<PublicCourseDto>((Integer)paramMap.get("pageNo"), (Integer)paramMap.get("pageSize"));
        page.setRecords(publicClassMapper.searchPublicClass(page,paramMap));
        return page;
    }

    @Override
    public PlanDetailDto getPlanDetailDto(Long planId, Long courseId, Integer planType,Long userId) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        PlanDetailDto planDetailDto =new PlanDetailDto();
        paramMap.put("planId",planId);
        paramMap.put("userId",userId);
        //查看是否有权限(线下活动无须处理)
        Integer hasAuthority =0;
        if(planType==6){
            Page<OfflineActivityDto> page = new Page<OfflineActivityDto>(0, 1);
            List<OfflineActivityDto> offlineActivityDtoList= offlineActivityMapper.searchOfflineActivity(page,paramMap);
            if(offlineActivityDtoList!=null&&offlineActivityDtoList.size()>0){
                planDetailDto.setOfflineActivityDto(offlineActivityDtoList.get(0));
            }
            hasAuthority= planAuthorityMapper.hasAuthority(paramMap);
        } else {
            paramMap.put("planType",planType);
            planDetailDto =  planCourseMapper.getPlanDescById(paramMap);
            hasAuthority= planAuthorityMapper.hasAuthority(paramMap);
            //班级计划的权限需另外考虑
            if(planType==5&&hasAuthority==0){
               paramMap.remove("planType");
               List<ClassPlanEnter> classPlanEnterList = classPlanEnterMapper.findClassPlanEnterByMap(paramMap);
               if(classPlanEnterList!=null&&classPlanEnterList.size()>0){
                   hasAuthority=1;
               }
               paramMap.put("planType",planType);
            }

        }
        if(planDetailDto!=null&&hasAuthority!=null&&hasAuthority==1){
            //如果courseId为空且有权限查看课程，则返回当前人最新观看的记录和其信息给用户
            if(courseId!=null&&courseId!=0){
                paramMap.put("courseId",courseId);
            }
            LearningProgress learningProgress =   learningProgressMapper.getLearningProgress(paramMap);
            if(learningProgress!=null){
                courseId =  learningProgress.getCourseId();
                planDetailDto.setLearningProgress(learningProgress);
            }
            paramMap.remove("courseId");
            if(courseId!=null){
                Course course = courseService.selectCourseOne(String.valueOf(courseId));
                planDetailDto.setCourse(course);
                paramMap.put("courseId",courseId);
                PlanCourse planCourse = planCourseMapper.getPlanCourse(paramMap);
                paramMap.remove("courseId");
                //获取资料列表
                List<MaterialAttachDto> materialList = publicClassMapper.getMaterialListByCourseId(courseId);
                planDetailDto.setMaterialList(materialList);
                //处理资料附件
                for(MaterialAttachDto materialAttachDto:materialList){
                    String filePath = attachmentService.findFileBytypeAndHostId(CommenConstant.EL_MATERIAL_ATTACHMENT, materialAttachDto.getId());
                    materialAttachDto.setFilePath(filePath);
                }
                //获取讲师列表(//当前课程Id)
                List<Long> accountId = planCourseMapper.getLecturerList(paramMap);
                if(accountId!=null&&accountId.size()>0){
                    List<Account> lecturerList =  accountService.findByAccountId(accountId);
                    planDetailDto.setLecturerList(lecturerList);
                }
            }else{
                //如果courseId为空且有权限查看课程，但用户没有观看记录，则返回该计划的第一个课程
                if(planType!=null&&planType==1){
                    PublicClass publicClass = publicClassMapper.selectById(planId);
                    paramMap.put("planId",publicClass.getPlanId());
                    paramMap.put("planType","2");
                }
                PlanCourse planCourse = planCourseMapper.getFirstPlanCourse(paramMap);
                planDetailDto.setFirstPlanCourse(planCourse);
                if(planCourse.getAssociateType()!=null&&planCourse.getAssociateType()!=1){
                    Course course = courseService.selectCourseOne(String.valueOf(planCourse.getAssociateId()));
                    planDetailDto.setCourse(course);
                }
            }
            //处理课程课件问题
            if(planDetailDto!=null&&planDetailDto.getCourse()!=null){
                String playPath = attachmentService.findFileBytypeAndHostId(CommenConstant.EL_LESSON_COURSEWARE, planDetailDto.getCourse().getId());
                planDetailDto.setPlayPath(playPath);
            }
            //获取排名前三的公开课列表
            if(planType!=CommenConstant.EL_COURSE_TYPE_OFFLINEACTIVITY) {
                paramMap.put("viewFlag",1);
                paramMap.put("pageSortFiled","viewsNum");
                paramMap.put("pageSortType","desc");
                paramMap.put("pageNo",0);
                paramMap.put("pageSize",3);
                Page<PublicCourseDto> publicCourseDtoPage = this.searchPublicClass(paramMap);
                planDetailDto.setPublicCourseDtoList(publicCourseDtoPage.getRecords());
            }else{
                //线下课程相关课程列表
                Page<OfflineActivityDto> page = new Page<OfflineActivityDto>(0, 3);
                paramMap.remove("planId");
                planDetailDto.setOfflineActivityDtoList(offlineActivityMapper.searchOfflineActivity(page,paramMap));
            }
        }

        return planDetailDto;
    }
    @Override
    public PageInfo<PublicCourseDto> managePublicListByPage(Page<PublicCourseDto> page, Map<String, Object> map) {
        PageInfo<PublicCourseDto> pageInfo = new PageInfo<>();
        pageInfo.setRecords(publicClassMapper.PublicClassListByPage(page, map));
        int total = publicClassMapper.PublicClassListByPageCount(map);
        pageInfo.setPageNo(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setPageTotal(PageUtil.getPages(page.getSize(),total));
        pageInfo.setPageRecords(page.getTotal());
        return pageInfo;
    }
    @Override
    public Response<Boolean> batchShelves(String[] publicClassIds, String state) {
        Response<Boolean> res = new Response<>();
        PublicClass publicClass=new PublicClass();
        List <String> list =Arrays.asList(publicClassIds);
        Integer states=Integer.parseInt(state);
        try {
            if(state=="1"){
                //上架
                for (String string : list) {
                    publicClass.setId(Long.parseLong(string));
                    publicClass.setState(states);
                    publicClassMapper.updateStateById(publicClass);
                }
            }else{
                //下架
                for (String string : list) {
                    publicClass.setId(Long.parseLong(string));
                    publicClass.setState(states);
                    publicClassMapper.updateStateById(publicClass);
                }
            }
            res.setData(true);
        } catch (Exception e) {
            res.setData(false);
        }
        return res;
    }
    @Override
    public String updatePublicClass(PublicClass pubclass) {
        Integer result=publicClassMapper.updateById(pubclass);
        if(result>0){
            return "修改成功";
        }
        return "修改失败";
    }
    
	@Override
	public PublicClass selectOne(String id) {
		
		return publicClassMapper.selectById(id);
	}
}
