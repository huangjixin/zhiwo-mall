package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.course.vo.CourseManageVo;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.api.plan.domain.PublicClass;
import com.fulan.api.plan.vo.PlanCourseDtoFMVo;
import com.fulan.api.plan.vo.PlanCoursePaperDto;
import com.fulan.application.mapper.OfflineActivitySignMapper;
import com.fulan.application.mapper.PlanCourseMapper;
import com.fulan.application.mapper.PublicClassMapper;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.PlanCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 计划与课程中间表 服务实现类
 * </p>
 *
 * @author yangzexu
 * @since 2018-01-19
 */
@Service
@Transactional
public class PlanCourseServiceImpl extends ServiceImpl<PlanCourseMapper, PlanCourse> implements PlanCourseService {

	@Autowired
	private PlanCourseMapper planCourseMapper;

	@Autowired
	private PublicClassMapper publicClassMapper;
	@Autowired
	private OfflineActivitySignMapper offlineActivitySignMapper;

	@Autowired
	private RedisUtil redisUtil;


	@Override
	public void insertPlanCourse(PlanCourse planCourse) {
//		planCourse.setId(idGenerator.generate());
		planCourseMapper.insert(planCourse);
	}

	@Override
	public Map<String,List<PlanCoursePaperDto>>  getCoursePaperListByPlan(Map<String, Object> paramMap) {
		Map<String,List<PlanCoursePaperDto>> coursePaperMap = new HashMap<String,List<PlanCoursePaperDto>>();
		Integer isSeq = (Integer)paramMap.get("isSeq");
		//处理公开课跟课程关联是通过学习计划的问题
		if(1==(Integer)paramMap.get("courseType")){
			PublicClass publicClass = publicClassMapper.selectById((Long)paramMap.get("planId"));
			paramMap.put("planId2",publicClass.getPlanId());
			paramMap.put("courseType2","2");
		}else{
			paramMap.put("planId2",paramMap.get("planId"));
			paramMap.put("courseType2",paramMap.get("courseType"));
		}
		Integer stageNum = planCourseMapper.getPlanStageNum(paramMap);
		if(stageNum==null||stageNum==0L){
			stageNum=1;
		}
		Integer compulsoryNum = 0;//该计划的必修课程数（包含试卷）
		Integer finishedCompulsoryNum = 0;//该计划已完成的必修课程数（包含试卷，线下）
		Integer stageProStatus = 1;//上一个阶段是否完成
		//设置canExam
		PlanCoursePaperDto planCoursePaperDtoPro = new PlanCoursePaperDto();
		//获取每个阶段的课程大纲
		for(int i=1;i<=stageNum;i++){
			paramMap.put("stage",i);
			List<PlanCoursePaperDto> planCoursePaperDtoList = planCourseMapper.getCoursePaperListByPlan(paramMap);
			if(planCoursePaperDtoList!=null&&planCoursePaperDtoList.size()>0){

				for(int j=0;j<planCoursePaperDtoList.size();j++){
					PlanCoursePaperDto planCoursePaperDto =planCoursePaperDtoList.get(j);
					//处理每个阶段的第一个课程状态
					if(1==stageProStatus){
						planCoursePaperDtoList.get(0).setCanExam(1);
					}
					//为了处理课程百分比
					if(1==planCoursePaperDtoList.get(j).getIsCompulsory()){
						compulsoryNum++;
						if(1==planCoursePaperDtoList.get(j).getFinishFlag()&&planCoursePaperDtoList.get(j).getCourseType()!=3){
							finishedCompulsoryNum++;
						}else if(planCoursePaperDtoList.get(j).getCourseType()==3){
							paramMap.put("userId",redisUtil.getUserId());
							Long hasSignd = offlineActivitySignMapper.hasSignd(paramMap);
							if(1==hasSignd){
								finishedCompulsoryNum++;
							}
						}
					}
					//设置是否允许学习（需要根据是否需要按照学习判断）
					if(isSeq==null||0!=isSeq){
						if(1==planCoursePaperDto.getIsCompulsory()) {
							if (planCoursePaperDtoPro!=null&&planCoursePaperDtoPro.getFinishFlag()!=null&&1==planCoursePaperDtoPro.getFinishFlag()) {
								planCoursePaperDto.setCanExam(1);
							} else {
								planCoursePaperDto.setCanExam(2);
							}
						}
					}else{
						if(1==stageProStatus) {
							planCoursePaperDto.setCanExam(1);
						}
					}
					planCoursePaperDtoPro= planCoursePaperDto;
				}
				//判断该阶段是否已完成stageProStatus：0：未完成 1：已完成
				if(compulsoryNum==finishedCompulsoryNum){
					stageProStatus=1;
				}else{
					stageProStatus=0;
				}
			}
			coursePaperMap.put(String.valueOf(i),planCoursePaperDtoList);
		}
		return coursePaperMap;
	}
	
	/**
     * 查询线下课程
     * @param planId
     * @return
     */
	@Override
	public List<PlanCourseDtoFMVo> seleByPlanIdLine(Long planId) {
		return planCourseMapper.seleByPlanIdLine(planId);
	}
	
	@Override
	public List<CourseManageVo> selectByPlanId(String id) {
		return planCourseMapper.selectByPlanId(id);
	}

	@Override
	public List<CourseManageVo> selectByPlanOtherId(String id) {
		return planCourseMapper.selectByPlanOtherId(id);
	}
	
	@Override
	public PlanCourse selectByPlanIdOff(String planId) {
		return planCourseMapper.selectByPlanIdOff(planId);
	}

	@Override
	public PlanCourse getFirstPlanCourse(Long planId,Integer planType) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if(planType!=null&&planType==1){
			PublicClass publicClass = publicClassMapper.selectById(planId);
			paramMap.put("planId",publicClass.getPlanId());
			paramMap.put("courseType","2");
		}else{
			paramMap.put("planId",planId);
			paramMap.put("courseType",planType);
		}
		return planCourseMapper.getFirstPlanCourse(paramMap);
	}
}
