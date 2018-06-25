package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.domain.IntegralDetail;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.api.plan.vo.*;
import com.fulan.api.security.domain.Account;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.IndexBaseMapper;
import com.fulan.application.mapper.IntegralDetailMapper;
import com.fulan.application.mapper.StudyCenterMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.IndexBaseService;
import com.fulan.application.service.StudyCenterService;
import com.fulan.application.util.json.JsonUtil;
import freemarker.template.SimpleDate;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 我的计划 服务实现类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-18
 */
@Service
@Transactional
public class StudyCenterServiceImpl implements StudyCenterService {

	private Logger logger = LoggerFactory.getLogger(StudyCenterServiceImpl.class);
	@Autowired
	private StudyCenterMapper studyCenterMapper;

	@Autowired
	private IndexBaseMapper indexBaseMapper;

	@Autowired
	private IndexBaseService indexBaseService;

	@Autowired
	private IntegralDetailMapper integralDetailMapper;

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private IdGenerator idGenerator;

	@Override
	public Page<StudyPlanVo> findMyClassPlanById(Map<String,Object> pageMap) {
		logger.info("-----------------------获取我的计划--------------------------");
		Page<StudyPlanVo> page = new Page<StudyPlanVo>(Integer.parseInt(pageMap.get("pageNo").toString()),Integer.parseInt(pageMap.get("pageSize").toString()));
		List<StudyPlanVo> list = studyCenterMapper.findMyClassPlanById(page,pageMap);
		String learnProgress = "0";
		for (StudyPlanVo vo : list) {
			pageMap.put("courseType", CommenConstant.EL_COURSE_TYPE_CLASSPLAN);
			ClassPlanTopVo topVo = indexBaseService.findClassPlanByAccount(pageMap);
			if(topVo != null && topVo.getClassPlanVos() != null && topVo.getClassPlanVos().size() > 0) {
				for (ClassPlanVo planVo : topVo.getClassPlanVos()) {
					if (planVo.getId().equals(vo.getId())) {
						learnProgress = planVo.getLearningProgress();
					}
				}
			}
			if(vo.getEndDate() != null){
				vo.setEndTime(vo.getEndDate().getTime()+"");
			}
			vo.setCurrentTime(new Date().getTime());
			vo.setLearningProgress(learnProgress);
			vo.setPlanType(vo.getPlanType());
			}
		page.setRecords(list);
		return page;
	}

	@Override
	public Page<StudyPlanVo> findMyPostPlanById(Map<String, Object> pageMap) {
		logger.info("-----------------------获取我的计划--------------------------");
		Page<StudyPlanVo> page = new Page<StudyPlanVo>(Integer.parseInt(pageMap.get("pageNo").toString()),Integer.parseInt(pageMap.get("pageSize").toString()));
		List<StudyPlanVo> list = studyCenterMapper.findMyPostPlanById(page,pageMap);
		String learnProgress = "0";
		if(list != null && list.size() > 0){
			for (StudyPlanVo vo : list) {
				pageMap.put("courseType",CommenConstant.EL_COURSE_TYPE_POSTDEVELOPMENT);
				PostDevelopmentVo postVo = indexBaseService.findPostDevelopmentByAccount(pageMap);
				if(postVo != null){
					learnProgress = postVo.getLearningProgress();
				}
				if(vo.getEndDate() != null){
					vo.setEndTime(vo.getEndDate().getTime()+"");
				}
				vo.setCurrentTime(new Date().getTime());
				vo.setLearningProgress(learnProgress);
				vo.setPlanType(CommenConstant.EL_COURSE_TYPE_POSTDEVELOPMENT);
			}
		}
		page.setRecords(list);
		return page;
	}

	@Override
	public Page<StudyPlanVo> findMyCompPlanById(Map<String, Object> pageMap) {
		logger.info("-----------------------获取我的计划--------------------------");
		Page<StudyPlanVo> page = new Page<StudyPlanVo>(Integer.parseInt(pageMap.get("pageNo").toString()),Integer.parseInt(pageMap.get("pageSize").toString()));
		List<StudyPlanVo> list = studyCenterMapper.findMyPostPlanById(page,pageMap);
		String learnProgress = "0";
		if(list != null && list.size() > 0){
			for (StudyPlanVo vo : list) {
				pageMap.put("courseType", CommenConstant.EL_COURSE_TYPE_COMPULSORYCPLAN);
				CompulsoryPlanTopVo topVo = indexBaseService.findCompulsoryCplanByAccount(pageMap);
				if(topVo != null && topVo.getCompulsoryPlanVos() != null && topVo.getCompulsoryPlanVos().size() > 0){
					for (CompulsoryPlanVo planVo : topVo.getCompulsoryPlanVos()) {
						if(planVo.getId().equals(vo.getId())){
							learnProgress = planVo.getLearningProgress();
						}
					}
				}
				if(vo.getEndDate() != null){
					vo.setEndTime(vo.getEndDate().getTime()+"");
				}
				vo.setCurrentTime(new Date().getTime());
				vo.setLearningProgress(learnProgress);
				vo.setPlanType(CommenConstant.EL_COURSE_TYPE_POSTDEVELOPMENT);
			}
		}
		page.setRecords(list);
		return page;
	}

	@Override
	public SignIntegralVo getIntegral(Long id) {
		logger.info("------------------------我的积分----------------------------");
		return studyCenterMapper.getIntegral(id);
	}

	@Override
	public Object getMyCourse(Map<String, Object> map) {
		logger.info("------------------------我的课程----------------------------");
		Object obj = new Object();
		if(Integer.parseInt(map.get("courseType").toString()) == CommenConstant.EL_COURSE_TYPE_POSTDEVELOPMENT){
			PostDevelopmentVo vo = indexBaseService.findPostDevelopmentByAccount(map);
			List<PostDevelopmentVo> postList = new ArrayList<PostDevelopmentVo>();
			PostDevelopmentTopVo topVo = new PostDevelopmentTopVo();
			if(vo != null){
				topVo.setIsFinished(vo.getIsFinished());
				postList.add(vo);
				topVo.setPostDevelopmentVos(postList);
			}
			obj = topVo;
		}else if(Integer.parseInt(map.get("courseType").toString()) == CommenConstant.EL_COURSE_TYPE_COMPULSORYCPLAN){
			CompulsoryPlanTopVo vo = indexBaseService.findCompulsoryCplanByAccount(map);
			if(vo != null){
				if(vo.getCompulsoryPlanVos() != null && vo.getCompulsoryPlanVos().size() > 0){
					for (CompulsoryPlanVo comp : vo.getCompulsoryPlanVos()) {
						if(Long.parseLong(map.get("planId").toString()) != comp.getId()){
							vo.getCompulsoryPlanVos().remove(comp);
						}
					}
				}
			}
			obj = vo;
		}else if(Integer.parseInt(map.get("courseType").toString())  ==  (CommenConstant.EL_COURSE_TYPE_CLASSPLAN)){
			ClassPlanTopVo vo = indexBaseService.findClassPlanByAccount(map);
			if(vo != null){
				if(vo.getClassPlanVos() != null && vo.getClassPlanVos().size() > 0){
					for (ClassPlanVo plan : vo.getClassPlanVos()) {
						if(Long.parseLong(map.get("planId").toString()) != plan.getId()){
							vo.getClassPlanVos().remove(plan);
						}
					}
				}
			}
			obj = vo;
		}
		return obj;
	}

	@Override
	public Page<ActivityCollectVo> getActivityCollectByAccountId(ActivityVO activityVO) {
		Long id = (Long) redisUtil.getUserId();
		activityVO.setUserId(id);
		Page<ActivityCollectVo> page = new Page<ActivityCollectVo>(activityVO.getCurrentPage(),activityVO.getPageSize());
		List<ActivityCollectVo> list = studyCenterMapper.getActivityCollectByAccountId(page,activityVO);
		for (ActivityCollectVo vo : list) {
			if(vo.getStartDate() != null){
				vo.setStartTime(vo.getStartDate().getTime());
			}
			if(vo.getEndDate() != null){
				vo.setEndTime(vo.getEndDate().getTime());
			}
		}
		page.setRecords(list);
//		Map<String,Object> map = new HashMap<>();
//		map.put("accountId",id);
//		Integer count = studyCenterMapper.queryActivityCollectCount(map);
		return page;
	}

	@Override
	public Page<PublicClassCollectVo> getPublicClassCollectByAccountId(Map<String, Object> paramMap) {
		Page<PublicClassCollectVo> page = new Page<PublicClassCollectVo>((Integer)paramMap.get("pageNo"), (Integer)paramMap.get("pageSize"));
		Long accountId = (Long) redisUtil.getUserId();
		paramMap.put("accountId",accountId);
		List<PublicClassCollectVo>  list = studyCenterMapper.getPublicClassCollectByAccountId(page,paramMap);
		List<String> types = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		BigDecimal finishProgress;
		if(list != null && list.size() > 0){
			for (PublicClassCollectVo vo : list) {
				int count = 0;
				map.put("classId",vo.getId());
				List<CourseVo> courses = studyCenterMapper.getCourseIdByPC(map);
				if(courses != null && courses.size() > 0){
					for (CourseVo course : courses) {
						if(!types.contains(course.getType())){
							types.add(course.getType());
						}
						map.put("courseId",course.getId());
						map.put("planId",vo.getPlanId());
						map.put("accountId",accountId);
						Integer learnStatus = studyCenterMapper.getLearningProgress(map);
						if(learnStatus == CommenConstant.LEARNING_PROGRESS_FINISHED){
							count++;
						}
					}
					finishProgress = BigDecimal.valueOf((double)count/(courses.size()));
					vo.setLearningProgress(finishProgress);
				}else{
					vo.setLearningProgress(BigDecimal.valueOf(0));
				}
				vo.setCourseTypes(types);
			}
		}
		page.setRecords(list);
		return page;
	}

	@Override
	public void deleteCollectById(Long id) {
		studyCenterMapper.deleteCollectById(id);
	}

	@Override
	public Page<HistoryVo> getHistoryRecord(Map<String, Object> paramMap) {
		Page<HistoryVo> page = new Page<HistoryVo>((Integer)paramMap.get("pageNo"), (Integer)paramMap.get("pageSize"));
		Long accountId = (Long) redisUtil.getUserId();
		paramMap.put("accountId",accountId);
		List<HistoryVo> list = studyCenterMapper.getHistoryRecord(page,paramMap);
		String learnProgress = "";
		if(list != null && list.size() > 0){
			for(HistoryVo history : list){
				if(history.getPlanType() != CommenConstant.EL_COURSE_TYPE_OFFLINEACTIVITY){
					List<CourseVo> resultCourse = new ArrayList<>();
					List<String> types = new ArrayList<>();
					List<CourseVo> courseVos = studyCenterMapper.getCourses(history.getPlanId());
					if(courseVos != null && courseVos.size() > 0){
						for (CourseVo vo : courseVos) {
							if( !types.contains(vo.getType())){
								types.add(vo.getType());
								resultCourse.add(vo);
							}
						}
					}
					history.setCourseVos(resultCourse);
				}
				if(history.getPlanType() == CommenConstant.EL_COURSE_TYPE_POSTDEVELOPMENT){
					paramMap.put("planId",history.getPlanId());
					paramMap.put("id",accountId);
					paramMap.put("courseType",history.getPlanType());
					learnProgress = indexBaseMapper.getLearningTime(paramMap);
				}else if(history.getPlanType()  == CommenConstant.EL_COURSE_TYPE_COMPULSORYCPLAN){
					paramMap.put("planId",history.getPlanId());
					paramMap.put("id",accountId);
					paramMap.put("courseType",history.getPlanType());
					learnProgress = indexBaseMapper.getLearningTime(paramMap);
				}else if(history.getPlanType() == CommenConstant.EL_COURSE_TYPE_PUBLICCLASS){
					paramMap.put("planId",history.getPlanId());
					paramMap.put("id",accountId);
					paramMap.put("courseType",history.getPlanType());
					learnProgress = indexBaseMapper.getLearningTime(paramMap);
				}else if(history.getPlanType()  == CommenConstant.EL_COURSE_TYPE_CLASSPLAN){
					paramMap.put("planId",history.getPlanId());
					paramMap.put("id",accountId);
					paramMap.put("courseType",history.getPlanType());
					learnProgress = indexBaseMapper.getLearningTime(paramMap);
				}else if(history.getPlanType()  == CommenConstant.EL_COURSE_TYPE_OFFLINEACTIVITY){
					if(history.getStartDate() != null){
						history.setStartTime(history.getStartDate().getTime());
					}
					if(history.getEndDate() != null){
						history.setEndTime(history.getEndDate().getTime());
					}
					if(history.getEnterStartDate() != null){
						history.setEnterStartTime(history.getEnterStartDate().getTime());
					}
					if(history.getEnterEndDate() != null){
						history.setEnterEndTime(history.getEnterEndDate().getTime());
					}
				}
				history.setLearningProgress(learnProgress);
			}
		}
		page.setRecords(list);
		return page;
	}

	@Override
	public void deleteHistoryByIds(List<Long> ids) {
		studyCenterMapper.deleteHistoryByIds(ids);
	}

	@Override
	public void deleteCollectByIds(List<Long> ids) {
		studyCenterMapper.deleteCollectByIds(ids);
	}

	@Override
	public SignIntegralVo sign() {
		IntegralDetail integralDetail = new IntegralDetail();
		Account account = (Account) redisUtil.getUserInfo();
		//自动生成id
		integralDetail.setId(idGenerator.generate());
		//设置用户ID
		integralDetail.setUserId(account.getId());
		//设置用户名
		integralDetail.setUserName(account.getAccountName());
		//设置获得途经 获取途径 1签到/2计划/3任务',
		integralDetail.setObtainType(1);
		//设置积分类型   积分类型 1获得/2消费
		integralDetail.setIntegralType(1);
		//设置积分数量
		integralDetail.setIntegralNumber(5);
		//设置计划ID
		integralDetail.setHostId(1L);
		//设置计划名称
		integralDetail.setHostName("签到");
		//创建时间
		integralDetail.setGmtCreate(new Date());

		integralDetailMapper.insert(integralDetail);
		SignIntegralVo integral = studyCenterMapper.getIntegral(account.getId());
		return integral;
	}

}
