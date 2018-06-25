package com.fulan.application.service.impl;

import com.fulan.api.plan.domain.*;
import com.fulan.api.plan.vo.*;
import com.fulan.api.security.domain.Account;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.*;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.IndexBaseService;
import com.fulan.application.util.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 首页 服务实现类
 * </p>
 *
 * @author kevin.yang
 * @since 2018-01-19
 */
@Service
@Transactional
public class IndexBaseServiceImpl implements IndexBaseService {

	private Logger logger = LoggerFactory.getLogger(IndexBaseServiceImpl.class);

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private IndexBaseMapper indexBaseMapper;

	@Autowired
	private IntegralDetailMapper integralDetailMapper;

	@Autowired
	private QualificationDetailMapper qualificationDetailMapper;

	@Autowired
	private CertificateDetailMapper certificateDetailMapper;

	@Autowired
	private IdGenerator idGenerator;

	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private PlanCourseMapper planCourseMapper;


	@Autowired
	private OfflineActivitySignMapper offlineActivitySignMapper;


	@Override
	public ClassPlanTopVo findClassPlanByAccount(Map<String, Object> map) {
		ClassPlanTopVo classPlanTopVo = new ClassPlanTopVo();
		//获取当前用户报名的班级计划id列表
		List<Long> planIds = indexBaseMapper.getPlanIdByAccountId(map);
		List<ClassPlanVo> classPlans = new ArrayList<>();
		if(planIds != null && planIds.size() > 0){
			for(Long planId : planIds ) {
				List<CourseVo> vos = new ArrayList<>();
				map.put("planId",planId);
				//获取班级计划
				ClassPlanVo plan =indexBaseMapper.findClassPlan(planId);
				//获取计划的阶段数
				Integer stageNum = planCourseMapper.getPlanStageNum(map);
				if(stageNum==null||stageNum==0L){
					stageNum=1;
				}
				Integer compulsoryNum = 0;//该计划的必修课程数（包含试卷）
				Integer finishedCompulsoryNum = 0;//该计划已完成的必修课程数（包含试卷，线下）
				//获取每个阶段的课程大纲
				CourseVo courseVoPro = new CourseVo();
				Integer stageProStatus =1;//上一个阶段是否完成
				List<PlanCourseVo> planCourseVoList = new ArrayList<>();
				for(int i=1;i<=stageNum;i++) {
					PlanCourseVo planCourseVo = new PlanCourseVo();
					map.put("stage", i);
					List<CourseVo> courseVoList = indexBaseMapper.getCoursePaperListByPlan(map);
					//设置isAllowStudy
					if(courseVoList!=null&&courseVoList.size()>0){
						//判断该阶段是否未开始courseStatus：0：未开始 1：进行中
						Integer startFlag = indexBaseMapper.getStartFlag(map);
						planCourseVo.setCourseStatus(startFlag);
						for(int j=0;j<courseVoList.size();j++){
							CourseVo courseVo =courseVoList.get(j);
							//处理每个阶段的第一个课程状态
							if(1==stageProStatus){
								courseVoList.get(0).setIsAllowStudy(1);
							}
							//为了处理课程百分比
							if(1==courseVoList.get(j).getIsCompulsory()) {
								compulsoryNum++;
								if (2 == courseVoList.get(j).getLearningStatus() && courseVoList.get(j).getCourseType() != 3) {
									finishedCompulsoryNum++;
								} else if (courseVoList.get(j).getCourseType() == 3) {
									map.put("userId", redisUtil.getUserId());
									Long hasSignd = offlineActivitySignMapper.hasSignd(map);
									if (1 == hasSignd) {
										finishedCompulsoryNum++;
									}
								}
							}
							//设置是否允许学习（需要根据是否需要按照学习判断）
							if(plan.getIsSeq()==null||0!=plan.getIsSeq()){
								if(courseVoPro!=null&&courseVoPro.getIsCompulsory()!=null&&1==courseVoPro.getIsCompulsory()) {
									if (2 == courseVoPro.getLearningStatus()) {
										courseVo.setIsAllowStudy(1);
									} else {
										courseVo.setIsAllowStudy(0);
									}
								}
							}else{
								if(1==stageProStatus) {
									courseVo.setIsAllowStudy(1);
								}
							}
							//处理阶段开始时间和结束时间
							if(j==courseVoList.size()-1){
								//设置阶段时间
								if(courseVo.getStartTime()!=null){
									planCourseVo.setSTime(courseVo.getStartTime().getTime());
								}
								if(courseVo.getEndTime()!=null) {
									planCourseVo.setETime(courseVo.getEndTime().getTime());
								}
							}
							courseVoPro= courseVo;
						}
					}
					//判断该阶段是否已完成courseStatus：0：未开始 1：进行中 2：已完成
					if(compulsoryNum==finishedCompulsoryNum){
						planCourseVo.setCourseStatus(2);
						stageProStatus=1;
					}else{
						stageProStatus=0;
					}
					planCourseVo.setCourses(courseVoList);
					planCourseVoList.add(planCourseVo);
				}
				BigDecimal learningProgress =new BigDecimal(0);
				if(compulsoryNum>0){
					learningProgress=new BigDecimal(finishedCompulsoryNum).divide(new BigDecimal(compulsoryNum), 2, RoundingMode.HALF_UP);
				}
				plan.setPlanCourses(planCourseVoList);
				plan.setLearningProgress(String.valueOf(learningProgress));
				plan.setPlanType(CommenConstant.EL_COURSE_TYPE_CLASSPLAN);
				//判断整个计划是否已完成
				if(plan.getPlanCourses()!=null&&plan.getPlanCourses().size()>0&&plan.getPlanCourses().get(plan.getPlanCourses().size()-1).getCourseStatus()!=null&&2==plan.getPlanCourses().get(plan.getPlanCourses().size()-1).getCourseStatus()){
					plan.setIsFinished(1);
				}
				classPlans.add(plan);
			}
		}
		classPlanTopVo.setClassPlanVos(classPlans);
		return classPlanTopVo;
	}

	@Override
	public PostDevelopmentVo findPostDevelopmentByAccount(Map<String, Object> map) {
		PostDevelopmentVo plan  = indexBaseMapper.findPostDevelopment(map);
		//如果没有相应的岗位发展，则返回空
		if(plan == null){
			return null;
		}
		List<CourseVo> vos = new ArrayList<>();
        map.put("planId",plan.getId());
		//获取计划的阶段数
		Integer stageNum = planCourseMapper.getPlanStageNum(map);
		if(stageNum==null||stageNum==0L){
			stageNum=1;
		}
		Integer compulsoryNum = 0;//该计划的必修课程数（包含试卷）
		Integer finishedCompulsoryNum = 0;//该计划已完成的必修课程数（包含试卷，线下）
		//获取每个阶段的课程大纲
		CourseVo courseVoPro = new CourseVo();
		Integer stageProStatus = 1;//上一个阶段是否完成
		List<PlanCourseVo> planCourseVoList = new ArrayList<>();
		for(int i=1;i<=stageNum;i++) {
			PlanCourseVo planCourseVo = new PlanCourseVo();
			map.put("stage", i);
			List<CourseVo> courseVoList = indexBaseMapper.getCoursePaperListByPlan(map);
			//设置isAllowStudy
			if(courseVoList!=null&&courseVoList.size()>0){
				//处理每个阶段的第一个课程状态
				if(courseVoPro!=null&&courseVoPro.getId()!=null&&2==courseVoPro.getLearningStatus()){
					courseVoList.get(0).setIsAllowStudy(1);
				}

				//判断该阶段是否未开始courseStatus：0：未开始 1：进行中
				Integer startFlag = indexBaseMapper.getStartFlag(map);
				planCourseVo.setCourseStatus(startFlag);
				for(int j=0;j<courseVoList.size();j++){
					CourseVo courseVo =courseVoList.get(j);
					//处理每个阶段的第一个课程状态
					if(1==stageProStatus){
						courseVoList.get(0).setIsAllowStudy(1);
					}
					//为了处理课程百分比
					if(1==courseVoList.get(j).getIsCompulsory()){
						compulsoryNum++;
						if(2==courseVoList.get(j).getLearningStatus()&&courseVoList.get(j).getCourseType()!=3){
							finishedCompulsoryNum++;
						}else if(courseVoList.get(j).getCourseType()==3){
							map.put("userId",redisUtil.getUserId());
							Long hasSignd = offlineActivitySignMapper.hasSignd(map);
							if(1==hasSignd){
								finishedCompulsoryNum++;
							}
						}
					}
					//设置是否允许学习（需要根据是否需要按照学习判断）
					if(plan.getIsSeq()==null||0!=plan.getIsSeq()){
						if(courseVoPro!=null&&courseVoPro.getIsCompulsory()!=null&&1==courseVoPro.getIsCompulsory()) {
							if (2 == courseVoPro.getLearningStatus()) {
								courseVo.setIsAllowStudy(1);
							} else {
								courseVo.setIsAllowStudy(0);
							}
						}
					}else{
						if(1==stageProStatus) {
							courseVo.setIsAllowStudy(1);
						}
					}
					if(j==courseVoList.size()-1){
						//设置阶段时间
						if(courseVo.getStartTime()!=null){
							planCourseVo.setSTime(courseVo.getStartTime().getTime());
						}
						if(courseVo.getEndTime()!=null) {
							planCourseVo.setETime(courseVo.getEndTime().getTime());
						}
					}
					courseVoPro =courseVo;
				}
			}
			//判断该阶段是否已完成courseStatus：0：未开始 1：进行中 2：已完成
			if(compulsoryNum==finishedCompulsoryNum){
				planCourseVo.setCourseStatus(2);
				stageProStatus=1;
			}else{
				stageProStatus=0;
			}
			planCourseVo.setCourses(courseVoList);
			planCourseVoList.add(planCourseVo);
		}
		BigDecimal learningProgress =new BigDecimal(0);
		if(compulsoryNum>0){
			learningProgress = new BigDecimal(finishedCompulsoryNum).divide(new BigDecimal(compulsoryNum), 2, RoundingMode.HALF_UP);
		}
		plan.setPlanCourses(planCourseVoList);
		plan.setLearningProgress(String.valueOf(learningProgress));
		plan.setPlanType(CommenConstant.EL_COURSE_TYPE_POSTDEVELOPMENT);
		//判断整个计划是否已完成
		if(plan.getPlanCourses()!=null&&plan.getPlanCourses().size()>0&&plan.getPlanCourses().get(plan.getPlanCourses().size()-1).getCourseStatus()!=null&&2==plan.getPlanCourses().get(plan.getPlanCourses().size()-1).getCourseStatus()){
			plan.setIsFinished(1);
		}
		return plan;





	}

	@Override
	public ClassPlanTopVo findClassPlanByAccountId(Map<String, Object> paramMap) {
		logger.info("---------------------------------前端首页班级计划------------------------------");
		ClassPlanTopVo classPlanTopVo = new ClassPlanTopVo();
		//获取当前用户报名的班级计划id列表
		List<Long> planIds = indexBaseMapper.getPlanIdByAccountId(paramMap);
		List<ClassPlanVo> classPlans = new ArrayList<>();
		///////////
		if(planIds != null && planIds.size() > 0){
			boolean planFinishedFlag = false;
			for(Long planId : planIds ) {
				boolean singlePlanFinishedFlag = false;
				List<CourseVo> vos = new ArrayList<>();
				paramMap.put("planId",planId);
				//获取班级计划
				ClassPlanVo plan = indexBaseMapper.findClassPlanByAccountId(paramMap);
				//整理考试通过状态  以及阶段课程学习完成进度
				Map<String,Object> coursesmap = new HashMap<String,Object>();
				coursesmap.put("courseType",CommenConstant.EL_COURSE_TYPE_CLASSPLAN);
				coursesmap.put("id",paramMap.get("accountId"));//用户ID
				///////////////////////
				if(plan != null && plan.getPlanCourses() != null){
					plan.setPlanType(CommenConstant.EL_COURSE_TYPE_CLASSPLAN);
					coursesmap.put("planId",plan.getId());
					coursesmap.put("tableName","el_class_plan");
					/////////////////////阶段
					int stageNum = 1;
					for (PlanCourseVo pc : plan.getPlanCourses()) {
						coursesmap.put("stage",pc.getStage());
						pc.setCurrentTime(new Date().getTime());
						if(pc.getEndTime() != null){
							pc.setETime(pc.getEndTime().getTime());
						}
						if(pc.getStartTime() != null){
							pc.setSTime(pc.getStartTime().getTime());
						}
						//////////////////////课程
						for (int i = 0;i<pc.getCourses().size();i++) {
							boolean courseFinish = false;
							CourseVo vo = pc.getCourses().get(i);
							//课程状态为2时，表示该课程是试卷
							if(vo.getCourseType() == CommenConstant.PAPER){
								//查询阶段考试状态
								coursesmap.put("paperId",vo.getId());
								Integer paperStatus = indexBaseMapper.getPaperStatus(coursesmap);//当前考试是否通过   0:未通过   1：通过
								Integer examNum = indexBaseMapper.getExamNum(coursesmap);//用户测试次数     1：已达到最大次数    0：未达到最大次数
								if(paperStatus != null){
									if(paperStatus == 0){
										planFinishedFlag = true;
										singlePlanFinishedFlag = true;
									}
									vo.setIsExam(CommenConstant.IS_EXAM);
									vo.setLearningStatus(paperStatus);
								}else{//未开始
									planFinishedFlag = true;
									singlePlanFinishedFlag = true;
									vo.setIsExam(CommenConstant.IS_NOT_EXAM);
									vo.setLearningStatus(CommenConstant.PAPER_UN_EXAM);
								}
								int courseNum = 0;//必修课程
								int paperNum = 0;//试卷数量
								boolean paperPassFlag = true; //试卷通过状态
								boolean coursePassFlag = true; //课程学完状态
								//只有一个阶段
								if(plan.getPlanCourses().size() == 1){
									//当前计划只有一张试卷的情况下
									if(pc.getCourses().size() == 1){
										vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
									}else {

										for (CourseVo cv : vos) {
											//试卷前面所有课程均为完成状态才可以考试
											if(cv.getLearningStatus() != null){
												if ((cv.getCourseType().equals(CommenConstant.COURSE) && !cv.getLearningStatus().equals(CommenConstant.COURSE_PASS) && cv.getIsCompulsory().equals(1)) ) {
													coursePassFlag = false;
												} else if(cv.getCourseType().equals(CommenConstant.PAPER) && !cv.getLearningStatus().equals(CommenConstant.EXAM_PASS)){
													paperPassFlag = false;
												}
											}
											if(  cv.getCourseType() == CommenConstant.PAPER){
												paperNum++;
											}
											if(cv.getIsCompulsory() == 1 && cv.getCourseType() == CommenConstant.COURSE){
												courseNum++;
											}
										}
										if(courseNum == 0 && paperNum == 0){
											vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
										}else if(courseNum > 0 && paperNum == 0){
											if(coursePassFlag){
												vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
											}
										}else if(courseNum > 0 && paperNum > 0){
											if(coursePassFlag && paperPassFlag){
												vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
											}
										}
									}
								}else{
									if(pc.getCourses().size() == 1){
										vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
									}else {
										for (CourseVo cv : vos) {
											//试卷前面所有课程均为完成状态才可以考试
											if(cv.getLearningStatus() != null){
												if ((cv.getCourseType().equals(CommenConstant.COURSE) && !cv.getLearningStatus().equals(CommenConstant.COURSE_PASS) && cv.getIsCompulsory().equals(1)) ) {
													coursePassFlag = false;
												} else if(cv.getCourseType().equals(CommenConstant.PAPER) && !cv.getLearningStatus().equals(CommenConstant.EXAM_PASS)){
													paperPassFlag = false;
												}
											}
											if(  cv.getCourseType() == CommenConstant.PAPER){
												paperNum++;
											}
											if(cv.getIsCompulsory() == 1 && cv.getCourseType() == CommenConstant.COURSE){
												courseNum++;
											}
										}
										if(courseNum == 0 && paperNum == 0){
											vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
										}else if(courseNum > 0 && paperNum == 0){
											if(coursePassFlag){
												vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
											}
										}else if(courseNum > 0 && paperNum > 0){
											if(coursePassFlag && paperPassFlag){
												vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
											}
										}
									}
								}
								if(paperStatus != null){
									if(paperStatus == CommenConstant.PAPER_FAILED && examNum.equals(CommenConstant.IS_NOT_MAX_EXAM)){
										vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
									}else if(paperStatus == CommenConstant.PAPER_PASS || examNum.equals(CommenConstant.IS_MAX_EXAM)){
										vo.setIsAllowExam(CommenConstant.NOT_ALLOW_EXAM);
									}
								}
								Integer paperState = indexBaseMapper.getPaperState(coursesmap);//审核状态 0：未开始考试  1：已阅卷  2：待阅卷
								vo.setPaperState(paperState);
							}else if(vo.getCourseType() == 1){//表示课程
								coursesmap.put("courseId",vo.getId());
								//获取课程状态   0：w未开始 1：正在学习   2：已完成
								Integer courseStatus = indexBaseMapper.getCourseStatus(coursesmap);
								if(courseStatus != null){
									if((courseStatus == 1 || courseStatus == 0) && vo.getIsCompulsory() == 1 ){
										planFinishedFlag = true;
										singlePlanFinishedFlag = true;
									}
									vo.setLearningStatus(courseStatus);
								}else{//如果为空  说明没有学习进度   默认学习状态为0
									vo.setLearningStatus(CommenConstant.COURSE_FAILED);
									if(vo.getIsCompulsory() == 1){
										planFinishedFlag = true;
										singlePlanFinishedFlag = true;
									}
								}
								int comNum = 0;//当前课程前面必修课程数量
								int paperNum = 0;
								boolean paperPassFlag = true; //试卷通过状态
								boolean coursePassFlag = true; //课程学完状态
								//只有一个阶段
								if(plan.getPlanCourses().size() == 1){
									//当前计划只有一个课程的情况下
									if(pc.getCourses().size() == 1){
										vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
									}else {
										if(i == 0){
											vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
										}else{
											if(vo.getIsCompulsory() == 1){

												for (CourseVo cv : vos) {
													if(cv.getLearningStatus() != null){
														if ((cv.getCourseType().equals(CommenConstant.COURSE) && !cv.getLearningStatus().equals(CommenConstant.COURSE_PASS) && cv.getIsCompulsory().equals(1)) ) {
															coursePassFlag = false;
														} else if(cv.getCourseType().equals(CommenConstant.PAPER) && !cv.getLearningStatus().equals(CommenConstant.EXAM_PASS)){
															paperPassFlag = false;
														}
													}
													if(cv.getCourseType().equals(CommenConstant.COURSE) && cv.getIsCompulsory() == 1){
														comNum++;
													}
													if(cv.getCourseType().equals(CommenConstant.PAPER)){
														paperNum++;
													}
												}
												if(comNum == 0 && paperNum == 0){
													vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
												}else if(comNum > 0 && paperNum == 0){
													if(coursePassFlag){
														vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
													}
												}else if(comNum > 0 && paperNum > 0){
													if(coursePassFlag && paperPassFlag){
														vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
													}
												}

											}else{
												vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
											}
										}

									}
								}else{
									if(i == 0 && stageNum == 1){
										vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
									} else {
										if(vo.getIsCompulsory() == 1){
											for (CourseVo cv : vos) {
												if(cv.getLearningStatus() != null){
													if ((cv.getCourseType().equals(CommenConstant.COURSE) && !cv.getLearningStatus().equals(CommenConstant.COURSE_PASS) && cv.getIsCompulsory().equals(1)) ) {
														coursePassFlag = false;
													} else if(cv.getCourseType().equals(CommenConstant.PAPER) && !cv.getLearningStatus().equals(CommenConstant.EXAM_PASS)){
														paperPassFlag = false;
													}
												}
												if(cv.getCourseType().equals(CommenConstant.COURSE) && cv.getIsCompulsory() == 1){
													comNum++;
												}
												if(cv.getCourseType().equals(CommenConstant.PAPER)){
													paperNum++;
												}
											}
											if(comNum == 0 && paperNum == 0){
												vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
											}else if(comNum > 0 && paperNum == 0){
												if(coursePassFlag){
													vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
												}
											}else if(comNum > 0 && paperNum > 0){
												if(coursePassFlag && paperPassFlag){
													vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
												}
											}
										}else{
											vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
										}
									}
								}

							}
							vos.add(vo);
						}
						int comNum = 0;//当前课程前面必修课程数量
						int paperNum = 0;
						boolean failedFlag = true;
						boolean learningFlag = true;
						boolean paperPassFlag = true; //试卷通过状态
						boolean coursePassFlag = true; //课程学完状态
						for (CourseVo vo : pc.getCourses()) {
							if(vo.getCourseType().equals(CommenConstant.COURSE) && vo.getIsCompulsory() == 1){
								comNum++;
							}
							if(vo.getCourseType().equals(CommenConstant.PAPER)){
								paperNum++;
							}
							if((vo.getCourseType().equals(CommenConstant.COURSE) && vo.getLearningStatus().equals(CommenConstant.COURSE_FAILED))
									|| (vo.getCourseType().equals(CommenConstant.PAPER) && vo.getLearningStatus().equals(CommenConstant.PAPER_UN_EXAM))){
								failedFlag = false;
							}else if((vo.getCourseType().equals(CommenConstant.COURSE) && vo.getLearningStatus().equals(CommenConstant.COURSE_LEARNING))
									|| (vo.getCourseType().equals(CommenConstant.PAPER) && !vo.getLearningStatus().equals(CommenConstant.PAPER_UN_EXAM))){
								learningFlag = false;
							}
							if(vo.getCourseType().equals(CommenConstant.COURSE) && !vo.getLearningStatus().equals(CommenConstant.COURSE_PASS)){
								coursePassFlag = false;
							}else if(vo.getCourseType().equals(CommenConstant.PAPER) && !vo.getLearningStatus().equals(CommenConstant.PAPER_PASS)){
								paperPassFlag = false;
							}
						}

						if(failedFlag){
							pc.setCourseStatus(CommenConstant.COURSE_FAILED);
						}
						if(learningFlag){
							pc.setCourseStatus(CommenConstant.COURSE_LEARNING);
						}
						if(comNum > 0 && paperNum == 0){
							if(coursePassFlag){
								pc.setCourseStatus(CommenConstant.COURSE_PASS);
							}
						}else if(comNum == 0 && paperNum > 0){
							if(paperPassFlag){
								pc.setCourseStatus(CommenConstant.COURSE_PASS);
							}
						}else if(comNum > 0 && paperNum > 0){
							if(coursePassFlag && paperPassFlag){
								pc.setCourseStatus(CommenConstant.COURSE_PASS);
							}
							if(stageNum == 1){
								if(coursePassFlag && !paperPassFlag){
									pc.setCourseStatus(CommenConstant.COURSE_LEARNING);
								}
							}
						}
						if(plan.getPlanCourses().size() > 1 && stageNum > 1){
							PlanCourseVo planCourseVo = plan.getPlanCourses().get(stageNum - 2);
							if(planCourseVo.getCourseStatus() == CommenConstant.COURSE_PASS && pc.getCourseStatus() != CommenConstant.COURSE_PASS){
								pc.setCourseStatus(CommenConstant.COURSE_LEARNING);
							}
						}

						stageNum++;
					}

					String lTime = indexBaseMapper.getLearningTime(coursesmap);
					if(lTime != null){
						plan.setLearningProgress(lTime);
					}else{
						plan.setLearningProgress(CommenConstant.LEARNING_PROGRESS_UNFINISHED.toString());
					}
					if(singlePlanFinishedFlag){
						plan.setIsFinished(CommenConstant.PLAN_UNFINISHED);
					}else{
						plan.setIsFinished(CommenConstant.PLAN_FINISHED);
					}
					classPlans.add(plan);
					classPlanTopVo.setClassName(plan.getName());
				}
			}
			if(planFinishedFlag){
				classPlanTopVo.setIsFinished(CommenConstant.PLAN_UNFINISHED);
			}else{
				classPlanTopVo.setIsFinished(CommenConstant.PLAN_FINISHED);
			}

		}
		Collections.sort(classPlans);
		classPlanTopVo.setClassPlanVos(classPlans);

		return classPlanTopVo;
	}

	@Override
	public PostDevelopmentVo findPostDevelopmentByAccountId(Map<String, Object> paramMap) {
		logger.info("---------------------------------前端首页岗位计划------------------------------");
		Long postId = indexBaseMapper.getPostIdByPostType(paramMap);
		List<CourseVo> vos = new ArrayList<>();
		if(postId != null){
			paramMap.put("planId",postId);
			PostDevelopmentVo plan = indexBaseMapper.findPostDevelopmentByAccountId(paramMap);
			Map<String,Object> postmap = new HashMap<String,Object>();
			postmap.put("id",paramMap.get("accountId"));//用户ID
			postmap.put("courseType",CommenConstant.EL_COURSE_TYPE_POSTDEVELOPMENT);
			boolean planFinishedFlag = false;//计划完成标识
			if(plan != null && plan.getPlanCourses() != null){
				plan.setPlanType(CommenConstant.EL_COURSE_TYPE_POSTDEVELOPMENT);
				postmap.put("planId",plan.getId());
				postmap.put("tableName","el_post_development");
				///////////////////////////////////////阶段
				int stageNum = 1;
				for (PlanCourseVo pc : plan.getPlanCourses()) {
					postmap.put("stage",pc.getStage());
					if(pc.getEndTime() != null){
						pc.setETime(pc.getEndTime().getTime());
					}
					if(pc.getStartTime() != null){
						pc.setSTime(pc.getStartTime().getTime());
					}
					pc.setCurrentTime(new Date().getTime());
					//////////////////////课程
					for (int i = 0;i<pc.getCourses().size();i++) {
						boolean courseFinish = false;
						CourseVo vo = pc.getCourses().get(i);
						//课程状态为2时，表示该课程是试卷
						if(vo.getCourseType() == CommenConstant.PAPER){
							//查询阶段考试状态
							postmap.put("paperId",vo.getId());
							Integer paperStatus = indexBaseMapper.getPaperStatus(postmap);//当前考试是否通过   0:未通过   1：通过
							Integer examNum = indexBaseMapper.getExamNum(postmap);//用户测试次数     1：已达到最大次数    0：未达到最大次数
							if(paperStatus != null){
								if(paperStatus == 0){
									planFinishedFlag = true;
								}
								vo.setIsExam(CommenConstant.IS_EXAM);
								vo.setLearningStatus(paperStatus);
							}else{//未开始
								planFinishedFlag = true;
								vo.setIsExam(CommenConstant.IS_NOT_EXAM);
								vo.setLearningStatus(CommenConstant.PAPER_UN_EXAM);
							}
							int courseNum = 0;//必修课程
							int paperNum = 0;//试卷数量
							boolean paperPassFlag = true; //试卷通过状态
							boolean coursePassFlag = true; //课程学完状态
							//只有一个阶段
							if(plan.getPlanCourses().size() == 1){
								//当前计划只有一张试卷的情况下
								if(pc.getCourses().size() == 1){
									vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
								}else {

									for (CourseVo cv : vos) {
										//试卷前面所有课程均为完成状态才可以考试
										if(cv.getLearningStatus() != null){
											if ((cv.getCourseType().equals(CommenConstant.COURSE) && !cv.getLearningStatus().equals(CommenConstant.COURSE_PASS) && cv.getIsCompulsory().equals(1)) ) {
												coursePassFlag = false;
											} else if(cv.getCourseType().equals(CommenConstant.PAPER) && !cv.getLearningStatus().equals(CommenConstant.EXAM_PASS)){
												paperPassFlag = false;
											}
										}
										if(  cv.getCourseType() == CommenConstant.PAPER){
											paperNum++;
										}
										if(cv.getIsCompulsory() == 1 && cv.getCourseType() == CommenConstant.COURSE){
											courseNum++;
										}
									}
									if(courseNum == 0 && paperNum == 0){
										vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
									}else if(courseNum > 0 && paperNum == 0){
										if(coursePassFlag){
											vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
										}
									}else if(courseNum > 0 && paperNum > 0){
										if(coursePassFlag && paperPassFlag){
											vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
										}
									}
								}
							}else{
								if(pc.getCourses().size() == 1){
									vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
								}else {
									for (CourseVo cv : vos) {
										//试卷前面所有课程均为完成状态才可以考试
										if(cv.getLearningStatus() != null){
											if ((cv.getCourseType().equals(CommenConstant.COURSE) && !cv.getLearningStatus().equals(CommenConstant.COURSE_PASS) && cv.getIsCompulsory().equals(1)) ) {
												coursePassFlag = false;
											} else if(cv.getCourseType().equals(CommenConstant.PAPER) && !cv.getLearningStatus().equals(CommenConstant.EXAM_PASS)){
												paperPassFlag = false;
											}
										}
										if(  cv.getCourseType() == CommenConstant.PAPER){
											paperNum++;
										}
										if(cv.getIsCompulsory() == 1 && cv.getCourseType() == CommenConstant.COURSE){
											courseNum++;
										}
									}
									if(courseNum == 0 && paperNum == 0){
										vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
									}else if(courseNum > 0 && paperNum == 0){
										if(coursePassFlag){
											vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
										}
									}else if(courseNum > 0 && paperNum > 0){
										if(coursePassFlag && paperPassFlag){
											vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
										}
									}
								}
							}
							if(paperStatus != null){
								if(paperStatus == CommenConstant.PAPER_FAILED && examNum.equals(CommenConstant.IS_NOT_MAX_EXAM)){
									vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
								}else if(paperStatus == CommenConstant.PAPER_PASS || examNum.equals(CommenConstant.IS_MAX_EXAM)){
									vo.setIsAllowExam(CommenConstant.NOT_ALLOW_EXAM);
								}
							}
							Integer paperState = indexBaseMapper.getPaperState(postmap);//审核状态 0：未开始考试  1：已阅卷  2：待阅卷
							vo.setPaperState(paperState);
						}else if(vo.getCourseType() == 1){//表示课程
							postmap.put("courseId",vo.getId());
							//获取课程状态   0：w未开始 1：正在学习   2：已完成
							Integer courseStatus = indexBaseMapper.getCourseStatus(postmap);
							if(courseStatus != null){
								if((courseStatus == 1 || courseStatus == 0) && vo.getIsCompulsory() == 1 ){
									planFinishedFlag = true;
								}
								vo.setLearningStatus(courseStatus);
							}else{//如果为空  说明没有学习进度   默认学习状态为0
								vo.setLearningStatus(CommenConstant.COURSE_FAILED);
								if(vo.getIsCompulsory() == 1){
									planFinishedFlag = true;
								}
							}
							int comNum = 0;//当前课程前面必修课程数量
							int paperNum = 0;
							boolean paperPassFlag = true; //试卷通过状态
							boolean coursePassFlag = true; //课程学完状态
							//只有一个阶段
							if(plan.getPlanCourses().size() == 1){
								//当前计划只有一个课程的情况下
								if(pc.getCourses().size() == 1){
									vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
								}else {
									if(i == 0){
										vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
									}else{
										if(vo.getIsCompulsory() == 1){

											for (CourseVo cv : vos) {
												if(cv.getLearningStatus() != null){
													if ((cv.getCourseType().equals(CommenConstant.COURSE) && !cv.getLearningStatus().equals(CommenConstant.COURSE_PASS) && cv.getIsCompulsory().equals(1)) ) {
														coursePassFlag = false;
													} else if(cv.getCourseType().equals(CommenConstant.PAPER) && !cv.getLearningStatus().equals(CommenConstant.EXAM_PASS)){
														paperPassFlag = false;
													}
												}
												if(cv.getCourseType().equals(CommenConstant.COURSE) && cv.getIsCompulsory() == 1){
													comNum++;
												}
												if(cv.getCourseType().equals(CommenConstant.PAPER)){
													paperNum++;
												}
											}
											if(comNum == 0 && paperNum == 0){
												vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
											}else if(comNum > 0 && paperNum == 0){
												if(coursePassFlag){
													vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
												}
											}else if(comNum > 0 && paperNum > 0){
												if(coursePassFlag && paperPassFlag){
													vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
												}
											}

										}else{
											vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
										}
									}

								}
							}else{
								if(i == 0 && stageNum == 1){
									vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
								} else {
									if(vo.getIsCompulsory() == 1){
										for (CourseVo cv : vos) {
											if(cv.getLearningStatus() != null){
												if ((cv.getCourseType().equals(CommenConstant.COURSE) && !cv.getLearningStatus().equals(CommenConstant.COURSE_PASS) && cv.getIsCompulsory().equals(1)) ) {
													coursePassFlag = false;
												} else if(cv.getCourseType().equals(CommenConstant.PAPER) && !cv.getLearningStatus().equals(CommenConstant.EXAM_PASS)){
													paperPassFlag = false;
												}
											}
											if(cv.getCourseType().equals(CommenConstant.COURSE) && cv.getIsCompulsory() == 1){
												comNum++;
											}
											if(cv.getCourseType().equals(CommenConstant.PAPER)){
												paperNum++;
											}
										}
										if(comNum == 0 && paperNum == 0){
											vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
										}else if(comNum > 0 && paperNum == 0){
											if(coursePassFlag){
												vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
											}
										}else if(comNum > 0 && paperNum > 0){
											if(coursePassFlag && paperPassFlag){
												vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
											}
										}
									}else{
										vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
									}
								}
							}

						}
						vos.add(vo);
					}
					int comNum = 0;//当前课程前面必修课程数量
					int paperNum = 0;
					boolean failedFlag = true;
					boolean learningFlag = true;
					boolean paperPassFlag = true; //试卷通过状态
					boolean coursePassFlag = true; //课程学完状态
					for (CourseVo vo : pc.getCourses()) {
						if(vo.getCourseType().equals(CommenConstant.COURSE) && vo.getIsCompulsory() == 1){
							comNum++;
						}
						if(vo.getCourseType().equals(CommenConstant.PAPER)){
							paperNum++;
						}
						if((vo.getCourseType().equals(CommenConstant.COURSE) && vo.getLearningStatus().equals(CommenConstant.COURSE_FAILED))
								|| (vo.getCourseType().equals(CommenConstant.PAPER) && vo.getLearningStatus().equals(CommenConstant.PAPER_UN_EXAM))){
							failedFlag = false;
						}else if((vo.getCourseType().equals(CommenConstant.COURSE) && vo.getLearningStatus().equals(CommenConstant.COURSE_LEARNING))
								|| (vo.getCourseType().equals(CommenConstant.PAPER) && !vo.getLearningStatus().equals(CommenConstant.PAPER_UN_EXAM))){
							learningFlag = false;
						}
						if(vo.getCourseType().equals(CommenConstant.COURSE) && !vo.getLearningStatus().equals(CommenConstant.COURSE_PASS)){
							coursePassFlag = false;
						}else if(vo.getCourseType().equals(CommenConstant.PAPER) && !vo.getLearningStatus().equals(CommenConstant.PAPER_PASS)){
							paperPassFlag = false;
						}
					}

					if(failedFlag){
						pc.setCourseStatus(CommenConstant.COURSE_FAILED);
					}
					if(learningFlag){
						pc.setCourseStatus(CommenConstant.COURSE_LEARNING);
					}
					if(comNum > 0 && paperNum == 0){
						if(coursePassFlag){
							pc.setCourseStatus(CommenConstant.COURSE_PASS);
						}
					}else if(comNum == 0 && paperNum > 0){
						if(paperPassFlag){
							pc.setCourseStatus(CommenConstant.COURSE_PASS);
						}
					}else if(comNum > 0 && paperNum > 0){
						if(coursePassFlag && paperPassFlag){
							pc.setCourseStatus(CommenConstant.COURSE_PASS);
						}
						if(stageNum == 1){
							if(coursePassFlag && !paperPassFlag){
								pc.setCourseStatus(CommenConstant.COURSE_LEARNING);
							}
						}
					}
					if(plan.getPlanCourses().size() > 1 && stageNum > 1){
						PlanCourseVo planCourseVo = plan.getPlanCourses().get(stageNum - 2);
						if(planCourseVo.getCourseStatus() == CommenConstant.COURSE_PASS && pc.getCourseStatus() != CommenConstant.COURSE_PASS){
							pc.setCourseStatus(CommenConstant.COURSE_LEARNING);
						}
					}
					stageNum++;
				}

				String lTime = indexBaseMapper.getLearningTime(postmap);
				if(lTime != null){
					plan.setLearningProgress(lTime);
				}else{
					plan.setLearningProgress(CommenConstant.LEARNING_PROGRESS_UNFINISHED.toString());
				}
				if(planFinishedFlag){
					plan.setIsFinished(CommenConstant.PLAN_UNFINISHED);
				}else{
					plan.setIsFinished(CommenConstant.PLAN_FINISHED);
				}
			}
			return plan;
		}else {
			return null;
		}
	}

	@Override
	public CompulsoryPlanTopVo findCompulsoryCplanByAccountId(Map<String, Object> paramMap) {
		logger.info("----------------------查询必修任务-------------------");
		CompulsoryPlanTopVo compulsoryPlanTopVo = new CompulsoryPlanTopVo();
		List<CompulsoryPlanVo> plans = indexBaseMapper.findCompulsoryCplanByAccountId(paramMap);
		Map<String,Object> coursesmap = new HashMap<String,Object>();
		coursesmap.put("id",paramMap.get("accountId"));
		coursesmap.put("courseType",CommenConstant.EL_COURSE_TYPE_COMPULSORYCPLAN);
		if(plans != null && plans.size()>0){
			boolean planFinishedFlag = false;
			for (CompulsoryPlanVo cplan: plans) {
				boolean singlePlanFinishedFlag = false;
				List<CourseVo> vos = new ArrayList<>();
				cplan.setPlanType(CommenConstant.EL_COURSE_TYPE_COMPULSORYCPLAN);
				coursesmap.put("planId",cplan.getId());
				coursesmap.put("tableName","el_compulsory_cplan");
				int stageNum = 1;
				for (PlanCourseVo pc : cplan.getPlanCourses()) {
					coursesmap.put("stage",pc.getStage());
					pc.setCurrentTime(new Date().getTime());
					if(pc.getEndTime() != null){
						pc.setETime(pc.getEndTime().getTime());
					}
					if(pc.getStartTime() != null){
						pc.setSTime(pc.getStartTime().getTime());
					}
					//////////////////////课程
					for (int i = 0;i<pc.getCourses().size();i++) {
						CourseVo vo = pc.getCourses().get(i);
						//课程状态为2时，表示该课程是试卷
						if(vo.getCourseType() == CommenConstant.PAPER){
							//查询阶段考试状态
							coursesmap.put("paperId",vo.getId());
							Integer paperStatus = indexBaseMapper.getPaperStatus(coursesmap);//当前考试是否通过   0:未通过   1：通过
							Integer examNum = indexBaseMapper.getExamNum(coursesmap);//用户测试次数     1：已达到最大次数    0：未达到最大次数
							if(paperStatus != null){
								if(paperStatus == 0){
									planFinishedFlag = true;
									singlePlanFinishedFlag = true;
								}
								vo.setIsExam(CommenConstant.IS_EXAM);
								vo.setLearningStatus(paperStatus);
							}else{//未开始
								planFinishedFlag = true;
								singlePlanFinishedFlag = true;
								vo.setIsExam(CommenConstant.IS_NOT_EXAM);
								vo.setLearningStatus(CommenConstant.PAPER_UN_EXAM);
							}
							int courseNum = 0;//必修课程
							int paperNum = 0;//试卷数量
							boolean paperPassFlag = true; //试卷通过状态
							boolean coursePassFlag = true; //课程学完状态
							//只有一个阶段
							if(cplan.getPlanCourses().size() == 1){
								//当前计划只有一张试卷的情况下
								if(pc.getCourses().size() == 1){
									vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
								}else {

									for (CourseVo cv : vos) {
										//试卷前面所有课程均为完成状态才可以考试
										if(cv.getLearningStatus() != null){
											if ((cv.getCourseType().equals(CommenConstant.COURSE) && !cv.getLearningStatus().equals(CommenConstant.COURSE_PASS) && cv.getIsCompulsory().equals(1)) ) {
												coursePassFlag = false;
											} else if(cv.getCourseType().equals(CommenConstant.PAPER) && !cv.getLearningStatus().equals(CommenConstant.EXAM_PASS)){
												paperPassFlag = false;
											}
										}
										if(  cv.getCourseType() == CommenConstant.PAPER){
											paperNum++;
										}
										if(cv.getIsCompulsory() == 1 && cv.getCourseType() == CommenConstant.COURSE){
											courseNum++;
										}
									}
									if(courseNum == 0 && paperNum == 0){
										vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
									}else if(courseNum > 0 && paperNum == 0){
										if(coursePassFlag){
											vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
										}
									}else if(courseNum > 0 && paperNum > 0){
										if(coursePassFlag && paperPassFlag){
											vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
										}
									}
								}
							}else{
								if(pc.getCourses().size() == 1){
									vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
								}else {
									for (CourseVo cv : vos) {
										//试卷前面所有课程均为完成状态才可以考试
										if(cv.getLearningStatus() != null){
											if ((cv.getCourseType().equals(CommenConstant.COURSE) && !cv.getLearningStatus().equals(CommenConstant.COURSE_PASS) && cv.getIsCompulsory().equals(1)) ) {
												coursePassFlag = false;
											} else if(cv.getCourseType().equals(CommenConstant.PAPER) && !cv.getLearningStatus().equals(CommenConstant.EXAM_PASS)){
												paperPassFlag = false;
											}
										}
										if(  cv.getCourseType() == CommenConstant.PAPER){
											paperNum++;
										}
										if(cv.getIsCompulsory() == 1 && cv.getCourseType() == CommenConstant.COURSE){
											courseNum++;
										}
									}
									if(courseNum == 0 && paperNum == 0){
										vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
									}else if(courseNum > 0 && paperNum == 0){
										if(coursePassFlag){
											vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
										}
									}else if(courseNum > 0 && paperNum > 0){
										if(coursePassFlag && paperPassFlag){
											vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
										}
									}
								}
							}
							if(paperStatus != null){
								if(paperStatus == CommenConstant.PAPER_FAILED && examNum.equals(CommenConstant.IS_NOT_MAX_EXAM)){
									vo.setIsAllowExam(CommenConstant.ALLOW_EXAM);
								}else if(paperStatus == CommenConstant.PAPER_PASS || examNum.equals(CommenConstant.IS_MAX_EXAM)){
									vo.setIsAllowExam(CommenConstant.NOT_ALLOW_EXAM);
								}
							}
							Integer paperState = indexBaseMapper.getPaperState(coursesmap);//审核状态 0：未开始考试  1：已阅卷  2：待阅卷
							vo.setPaperState(paperState);

						}else if(vo.getCourseType() == 1){//表示课程
							coursesmap.put("courseId",vo.getId());
							//获取课程状态   0：w未开始 1：正在学习   2：已完成
							Integer courseStatus = indexBaseMapper.getCourseStatus(coursesmap);
							if(courseStatus != null){
								if((courseStatus == 1 || courseStatus == 0) && vo.getIsCompulsory() == 1 ){
									planFinishedFlag = true;
									singlePlanFinishedFlag = true;
								}
								vo.setLearningStatus(courseStatus);
							}else{//如果为空  说明没有学习进度   默认学习状态为0
								vo.setLearningStatus(CommenConstant.COURSE_FAILED);
								if(vo.getIsCompulsory() == 1){
									planFinishedFlag = true;
									singlePlanFinishedFlag = true;
								}
							}
							int comNum = 0;//当前课程前面必修课程数量
							int paperNum = 0;
							boolean paperPassFlag = true; //试卷通过状态
							boolean coursePassFlag = true; //课程学完状态
							//只有一个阶段
							if(cplan.getPlanCourses().size() == 1){
								//当前计划只有一个课程的情况下
								if(pc.getCourses().size() == 1){
									vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
								}else {
									if(i == 0){
										vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
									}else{
										if(vo.getIsCompulsory() == 1){

											for (CourseVo cv : vos) {
												if(cv.getLearningStatus() != null){
													if ((cv.getCourseType().equals(CommenConstant.COURSE) && !cv.getLearningStatus().equals(CommenConstant.COURSE_PASS) && cv.getIsCompulsory().equals(1)) ) {
														coursePassFlag = false;
													} else if(cv.getCourseType().equals(CommenConstant.PAPER) && !cv.getLearningStatus().equals(CommenConstant.EXAM_PASS)){
														paperPassFlag = false;
													}
												}
												if(cv.getCourseType().equals(CommenConstant.COURSE) && cv.getIsCompulsory() == 1){
													comNum++;
												}
												if(cv.getCourseType().equals(CommenConstant.PAPER)){
													paperNum++;
												}
											}
											if(comNum == 0 && paperNum == 0){
												vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
											}else if(comNum > 0 && paperNum == 0){
												if(coursePassFlag){
													vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
												}
											}else if(comNum > 0 && paperNum > 0){
												if(coursePassFlag && paperPassFlag){
													vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
												}
											}

										}else{
											vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
										}
									}

								}
							}else{
								if(i == 0 && stageNum == 1){
									vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
								} else {
									if(vo.getIsCompulsory() == 1){
										for (CourseVo cv : vos) {
											if(cv.getLearningStatus() != null){
												if ((cv.getCourseType().equals(CommenConstant.COURSE) && !cv.getLearningStatus().equals(CommenConstant.COURSE_PASS) && cv.getIsCompulsory().equals(1)) ) {
													coursePassFlag = false;
												} else if(cv.getCourseType().equals(CommenConstant.PAPER) && !cv.getLearningStatus().equals(CommenConstant.EXAM_PASS)){
													paperPassFlag = false;
												}
											}
											if(cv.getCourseType().equals(CommenConstant.COURSE) && cv.getIsCompulsory() == 1){
												comNum++;
											}
											if(cv.getCourseType().equals(CommenConstant.PAPER)){
												paperNum++;
											}
										}
										if(comNum == 0 && paperNum == 0){
											vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
										}else if(comNum > 0 && paperNum == 0){
											if(coursePassFlag){
												vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
											}
										}else if(comNum > 0 && paperNum > 0){
											if(coursePassFlag && paperPassFlag){
												vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
											}
										}
									}else{
										vo.setIsAllowStudy(CommenConstant.ALLOW_EXAM);
									}
								}
							}
						}
						vos.add(vo);
					}
					int comNum = 0;//当前课程前面必修课程数量
					int paperNum = 0;
					boolean failedFlag = true;
					boolean learningFlag = true;
					boolean paperPassFlag = true; //试卷通过状态
					boolean coursePassFlag = true; //课程学完状态
					for (CourseVo vo : pc.getCourses()) {
						if(vo.getCourseType().equals(CommenConstant.COURSE) && vo.getIsCompulsory() == 1){
							comNum++;
						}
						if(vo.getCourseType().equals(CommenConstant.PAPER)){
							paperNum++;
						}
						if((vo.getCourseType().equals(CommenConstant.COURSE) && vo.getLearningStatus().equals(CommenConstant.COURSE_FAILED))
								|| (vo.getCourseType().equals(CommenConstant.PAPER) && vo.getLearningStatus().equals(CommenConstant.PAPER_UN_EXAM))){
							failedFlag = false;
						}else if((vo.getCourseType().equals(CommenConstant.COURSE) && vo.getLearningStatus().equals(CommenConstant.COURSE_LEARNING))
								|| (vo.getCourseType().equals(CommenConstant.PAPER) && !vo.getLearningStatus().equals(CommenConstant.PAPER_UN_EXAM))){
							learningFlag = false;
						}
						if(vo.getCourseType().equals(CommenConstant.COURSE) && !vo.getLearningStatus().equals(CommenConstant.COURSE_PASS)){
							coursePassFlag = false;
						}else if(vo.getCourseType().equals(CommenConstant.PAPER) && !vo.getLearningStatus().equals(CommenConstant.PAPER_PASS)){
							paperPassFlag = false;
						}
					}

					if(failedFlag){
						pc.setCourseStatus(CommenConstant.COURSE_FAILED);
					}
					if(learningFlag){
						pc.setCourseStatus(CommenConstant.COURSE_LEARNING);
					}
					if(comNum > 0 && paperNum == 0){
						if(coursePassFlag){
							pc.setCourseStatus(CommenConstant.COURSE_PASS);
						}
					}else if(comNum == 0 && paperNum > 0){
						if(paperPassFlag){
							pc.setCourseStatus(CommenConstant.COURSE_PASS);
						}
					}else if(comNum > 0 && paperNum > 0){
						if(coursePassFlag && paperPassFlag){
							pc.setCourseStatus(CommenConstant.COURSE_PASS);
						}
						if(stageNum == 1){
							if(coursePassFlag && !paperPassFlag){
								pc.setCourseStatus(CommenConstant.COURSE_LEARNING);
							}
						}
					}
					if(cplan.getPlanCourses().size() > 1 && stageNum > 1){
						PlanCourseVo planCourseVo = cplan.getPlanCourses().get(stageNum - 2);
						if(planCourseVo.getCourseStatus() == CommenConstant.COURSE_PASS && pc.getCourseStatus() != CommenConstant.COURSE_PASS){
							pc.setCourseStatus(CommenConstant.COURSE_LEARNING);
						}
					}
					stageNum++;
				}

				String lTime = indexBaseMapper.getLearningTime(coursesmap);
				if(lTime != null){
					cplan.setLearningProgress(lTime);
				}else{
					cplan.setLearningProgress(CommenConstant.LEARNING_PROGRESS_UNFINISHED.toString());
				}
				if(singlePlanFinishedFlag){
					cplan.setIsFinished(CommenConstant.PLAN_UNFINISHED);
				}else{
					cplan.setIsFinished(CommenConstant.PLAN_FINISHED);
				}
			}
			if(planFinishedFlag){
				compulsoryPlanTopVo.setIsFinished(CommenConstant.PLAN_UNFINISHED);
			}else{
				compulsoryPlanTopVo.setIsFinished(CommenConstant.PLAN_FINISHED);
			}
		}
		Collections.sort(plans);
		compulsoryPlanTopVo.setCompulsoryPlanVos(plans);
		return compulsoryPlanTopVo;
	}

	@Override
	public void appraise(AppraiseDto appraiseDto) {
		logger.info("----------------------考核通过，奖励类型为-------------------");
		logger.info("---------------------奖励类型 1积分/2证书/3资格---------------------"+appraiseDto.getRewardType());
		if(appraiseDto.getRewardType().equals("1")){
			//奖励类型为积分
			integralDetailMapper.insert(transferToIntegral(appraiseDto));
		}else if(appraiseDto.getRewardType().equals("2")){
			//奖励类型为证书
			certificateDetailMapper.insert(transferToCertification(appraiseDto));
		}else if(appraiseDto.getRewardType().equals("3")){
			//奖励类型为资格
			qualificationDetailMapper.insert(transferToQualification(appraiseDto));
		}

	}

	@Override
	public List<RemindingExpireVo> getRemindingExpire(Long id) {
		Long tomorrow = new Date().getTime()-24*60*60*1000;
		Long tomorrow2 = new Date().getTime()-24*60*60*1000*2;
		Long today = new Date().getTime();
		List<RemindingExpireVo> list = indexBaseMapper.getRemindingExpire(id);
		if(list != null && list.size()>0){
			for (RemindingExpireVo vo: list ) {
				if(vo.getTimeType().equals(CommenConstant.TODAY)){
					vo.setTimeStamp(today.toString());
				}else if(vo.getTimeType().equals(CommenConstant.TOMORROW)){
					vo.setTimeStamp(tomorrow.toString());
				}else if(vo.getTimeType().equals(CommenConstant.THE_DAY_AFTER_TOMORROW)){
					vo.setTimeStamp(tomorrow2.toString());
				}
			}
		}
		return list;
	}

	@Override
	public List<HotPublicClassVo> getHotPublicClass(Long id) {
		List<HotPublicClassVo> publicClasses = indexBaseMapper.getHotPublicClass(id);
		return publicClasses;
	}

	@Override
	public List<ClassPlanVo> getEnterDetail(Long accountId) {
		return indexBaseMapper.getEnterDetail(accountId);
	}


	//转换积分对象
	private IntegralDetail transferToIntegral(AppraiseDto appraiseDto){
		//积分
		IntegralDetail integralDetail = new IntegralDetail();
		//自动生成id
		integralDetail.setId(idGenerator.generate());
		Account account = (Account) redisUtil.getUserInfo();
		//设置用户ID
		integralDetail.setUserId(account.getId());
		//设置用户名
		integralDetail.setUserName(account.getAccountName());
		//设置获得途经
		integralDetail.setObtainType(appraiseDto.getObtainType());
		//设置积分类型    1：获得
		integralDetail.setIntegralType(1);
		//设置积分数量
		integralDetail.setIntegralNumber(appraiseDto.getIntegralNumber());
		//设置计划ID
		integralDetail.setHostId(appraiseDto.getPlanId());
		//设置计划名称
		integralDetail.setHostName(appraiseDto.getPlanName());
		//创建时间
		integralDetail.setGmtCreate(new Date());
		logger.info("------------------------积分数据对象-------------------------");
		logger.info("-------------integralDetail  : "+JsonUtil.object2Json(integralDetail));
		return integralDetail;
	}

	//转换证书对象
	private CertificateDetail transferToCertification(AppraiseDto appraiseDto){
		//证书
		CertificateDetail detail = new CertificateDetail();
		//自动生成id
		detail.setId(idGenerator.generate());
		Account account = (Account) redisUtil.getUserInfo();
		//设置用户ID
		detail.setUserId(account.getId());
		//设置用户名
		detail.setUserName(account.getAccountName());
		//证书编号
		detail.setCertificateId(appraiseDto.getCertificateId());
		//设置计划ID
		detail.setHostId(appraiseDto.getPlanId());
		//设置计划名称
		detail.setHostName(appraiseDto.getPlanName());
		//创建时间
		detail.setGmtCreate(new Date());
		logger.info("------------------------证书对象-------------------------");
		logger.info("-------------CertificateDetail  : "+JsonUtil.object2Json(detail));
		return detail;
	}


	//转换资格对象
	private QualificationDetail transferToQualification(AppraiseDto appraiseDto){
		//资格
		QualificationDetail detail = new QualificationDetail();
		//自动生成id
		detail.setId(idGenerator.generate());
		Account account = (Account) redisUtil.getUserInfo();
		//设置用户ID
		detail.setUserId(account.getId());
		//设置用户名
		detail.setUserName(account.getAccountName());
		//资格编号
		detail.setQualificationId(appraiseDto.getQualificationId());
		//设置计划ID
		detail.setHostId(appraiseDto.getPlanId());
		//设置计划名称
		detail.setHostName(appraiseDto.getPlanName());
		//创建时间
		detail.setGmtCreate(new Date());
		logger.info("------------------------资格对象-------------------------");
		logger.info("-------------QualificationDetail  : "+JsonUtil.object2Json(detail));
		return detail;
	}

    @Override
    public CompulsoryPlanTopVo findCompulsoryCplanByAccount(Map<String, Object> paramMap) {
        CompulsoryPlanTopVo compulsoryPlanTopVo = new CompulsoryPlanTopVo();
        List<CompulsoryPlanVo> plans = indexBaseMapper.findCompulsoryPlan(paramMap);
        if(plans != null && plans.size()>0) {
            for (CompulsoryPlanVo cplan : plans) {
                List<CourseVo> vos = new ArrayList<>();
                paramMap.put("planId",cplan.getId());
                //获取计划的阶段数
                Integer stageNum = planCourseMapper.getPlanStageNum(paramMap);
                if(stageNum==null||stageNum==0L){
                    stageNum=1;
                }
                Integer compulsoryNum = 0;//该计划的必修课程数（包含试卷）
                Integer finishedCompulsoryNum = 0;//该计划已完成的必修课程数（包含试卷，线下）
                //获取每个阶段的课程大纲
                CourseVo courseVoPro = new CourseVo();
				Integer stageProStatus = 1;//上一个阶段是否完成
				List<PlanCourseVo> planCourseVoList = new ArrayList<>();
                for(int i=1;i<=stageNum;i++) {
                    PlanCourseVo planCourseVo = new PlanCourseVo();
                    paramMap.put("stage", i);
                    List<CourseVo> courseVoList = indexBaseMapper.getCoursePaperListByPlan(paramMap);
                    //设置isAllowStudy
                    if(courseVoList!=null&&courseVoList.size()>0){
                        //判断该阶段是否未开始courseStatus：0：未开始 1：进行中
                        Integer startFlag = indexBaseMapper.getStartFlag(paramMap);
                        planCourseVo.setCourseStatus(startFlag);
                        for(int j=0;j<courseVoList.size();j++){
                            CourseVo courseVo =courseVoList.get(j);
							//处理每个阶段的第一个课程状态
							if(stageProStatus==1){
								courseVoList.get(0).setIsAllowStudy(1);
							}
							//为了处理课程百分比
							if(1==courseVoList.get(0).getIsCompulsory()){
								compulsoryNum++;
								if(2==courseVoList.get(0).getLearningStatus()&&courseVoList.get(0).getCourseType()!=3){
									finishedCompulsoryNum++;
								}else if(courseVoList.get(0).getCourseType()==3){
									paramMap.put("userId",redisUtil.getUserId());
									Long hasSignd = offlineActivitySignMapper.hasSignd(paramMap);
									if(1==hasSignd){
										finishedCompulsoryNum++;
									}
								}
							}
							//设置是否允许学习（需要根据是否需要按照学习判断）
							if(cplan.getIsSeq()==null||0!=cplan.getIsSeq()){
								if(courseVoPro!=null&&courseVoPro.getIsCompulsory()!=null&&1==courseVoPro.getIsCompulsory()) {
									if (2 == courseVoPro.getLearningStatus()) {
										courseVo.setIsAllowStudy(1);
									} else {
										courseVo.setIsAllowStudy(0);
									}
								}
							}else{
								if(1==stageProStatus) {
									courseVo.setIsAllowStudy(1);
								}
							}
                            if(j==courseVoList.size()-1){
                                courseVoPro= courseVo;
                                //设置阶段时间
                                if(courseVo.getStartTime()!=null){
                                    planCourseVo.setSTime(courseVo.getStartTime().getTime());
                                }
                                if(courseVo.getEndTime()!=null) {
                                    planCourseVo.setETime(courseVo.getEndTime().getTime());
                                }
                            }
							courseVoPro =courseVo;
						}
                    }
					//判断该阶段是否已完成courseStatus：0：未开始 1：进行中 2：已完成
					if(compulsoryNum==finishedCompulsoryNum){
						planCourseVo.setCourseStatus(2);
						stageProStatus=1;
					}else{
						stageProStatus=0;
					}
                    planCourseVo.setCourses(courseVoList);
                    planCourseVoList.add(planCourseVo);
                }
				BigDecimal learningProgress =new BigDecimal(0);
				if(compulsoryNum>0){
					learningProgress = new BigDecimal(finishedCompulsoryNum).divide(new BigDecimal(compulsoryNum), 2, RoundingMode.HALF_UP);
				}
                cplan.setPlanCourses(planCourseVoList);
                cplan.setLearningProgress(String.valueOf(learningProgress));
                cplan.setPlanType(CommenConstant.EL_COURSE_TYPE_COMPULSORYCPLAN);
                //判断整个计划是否已完成
                if(cplan.getPlanCourses()!=null&&cplan.getPlanCourses().size()>0&&cplan.getPlanCourses().get(cplan.getPlanCourses().size()-1).getCourseStatus()!=null&&2==cplan.getPlanCourses().get(cplan.getPlanCourses().size()-1).getCourseStatus()){
                    cplan.setIsFinished(1);
                }
            }
        }
        compulsoryPlanTopVo.setCompulsoryPlanVos(plans);
        return compulsoryPlanTopVo;
    }

}
