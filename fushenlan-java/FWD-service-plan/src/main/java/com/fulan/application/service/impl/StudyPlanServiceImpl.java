package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.api.plan.domain.StudyPlan;
import com.fulan.api.plan.domain.StudyPlanCopy;
import com.fulan.api.plan.vo.ClassPlanVo;
import com.fulan.api.plan.vo.PlanCourseVoQ;
import com.fulan.api.plan.vo.StudyPlanVvo;
import com.fulan.application.mapper.PlanCourseMapper;
import com.fulan.application.mapper.StudyPlanMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.PlanAuthorityService;
import com.fulan.application.service.PlanCourseService;
import com.fulan.application.service.StudyPlanService;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 学习计划 服务实现类
 * </p>
 *
 * @author yangzexu
 * @since 2018-01-18
 */
@Service
@Transactional
public class StudyPlanServiceImpl extends ServiceImpl<StudyPlanMapper, StudyPlan> implements StudyPlanService {

	private Logger logger = LoggerFactory.getLogger(StudyPlanServiceImpl.class);

	@Autowired
	private StudyPlanMapper studyPlanMapper;

	@Autowired
	private PlanCourseMapper planCourseMapper;

	@Autowired
	private PlanAuthorityService planAuthorityService;
	
	@Autowired
	private PlanCourseService planCourseService;

	@Autowired
	private IdGenerator idGenerator;

	/**
	 * 添加和修改修改计划，同时进行
	 * @param studyPlan
     */
//	@Override
//	public void insertElStudyPlan(StudyPlanCopy studyPlan) {
//		StudyPlan studuy = new StudyPlan();
//		studuy.setId(studyPlan.getId());
//		studuy.setCode(studyPlan.getCode());
//		studuy.setDescription(studyPlan.getDescription());
//		studuy.setName(studyPlan.getName());
//		studuy.setTagId(studyPlan.getTagId());
//		studuy.setStageNum(studyPlan.getStageNum());
//		if (studuy.getId()!=null){
//			//修改学习计划
//			studyPlanMapper.updateAllColumnById(studuy);
//		}else{
//			//插入学习计划
//			studuy.setId(idGenerator.generate());
//		}
//		List<PlanCourse> savePlanCourses = new ArrayList<>();
//		List<PlanCourse> updatePlanCourses = new ArrayList<>();
//		for (PlanCourse planCourse : studyPlan.getPlanCourses()) {
//			if (planCourse.getId()!=null){
//				updatePlanCourses.add(planCourse);
//				continue;
//			}
//			planCourse.setId(idGenerator.generate());
//			//关联的计划id
//			planCourse.setPlanId(studuy.getId());
//			//设置创建人
//			planCourse.setCreateUser(idGenerator.generate());
//			//设置创建时间
//			planCourse.setGmtCreate(new Date());
//			savePlanCourses.add(planCourse);
//		}
//	/*	//计划权限表
//		PlanAuthority planAuthority = new PlanAuthority();
//		//自动生成id
//		planAuthority.setId(idGenerator.generate());
//		//任务类型
//		planAuthority.setCourseType(1);
//		//任务编号
//		planAuthority.setCourseId(studyPlan.getId());
//		//创建人
//		planAuthority.setCreateUser(idGenerator.generate());
//		//创建时间
//		planAuthority.setGmtCreate(new Date());
//		//插入课程权限
//		planAuthorityService.insertPlanAuthority(planAuthority);*/
//
//		//插入学习计划表
//		studuy.setCourseNum(savePlanCourses.size()+updatePlanCourses.size());
//		studyPlanMapper.insertAllColumn(studuy);
//
//		//批量插入计划课程中间表
//		if (savePlanCourses.size()>0) {
//			planCourseService.insertBatch(savePlanCourses);
//		}
//		//批量修改计划课程中间表
//		if (updatePlanCourses.size()>0) {
//			planCourseService.updateBatchById(updatePlanCourses);
//		}
//		//批量删除计划课程中间表
//		Set<Long> planCourseIds = studyPlan.getPlanCourseIds();
//		if (planCourseIds!=null&&planCourseIds.size()>0) {
//			planCourseService.deleteBatchIds(planCourseIds);
//		}
//	}
//	
	
	//新增或者修改学习计划
	@Override
	public void insertElStudyPlan(StudyPlanCopy studyPlan) {
		Long id=studyPlan.getId();
		List<Long> planIds = new ArrayList<Long>();
		StudyPlan studuy = new StudyPlan();
		studuy.setCode(studyPlan.getCode());
		studuy.setDescription(studyPlan.getDescription());
		studuy.setName(studyPlan.getName());
		studuy.setTagId(studyPlan.getTagId());
		studuy.setStageNum(studyPlan.getStageNum());
		studuy.setCourseNum(studyPlan.getCourseNum());
		studuy.setPlanType(studyPlan.getPlanType());
		if(null!=id){//若存在则修改学习计划(先删除后插入)
			studuy.setId(id);
			planIds.add(id);
			studyPlanMapper.deletePlanCourseByIds(planIds);
			studyPlanMapper.deletePlanStudyByIds(planIds);
		}else{//新增学习计划
 			studuy.setId(idGenerator.generate());
		}
		List<PlanCourse> savePlanCourses = new ArrayList<>();
		for (PlanCourse planCourse : studyPlan.getPlanCourses()) {
			//关联的计划id
			planCourse.setPlanId(studuy.getId());
			if(null!=id){//设置创建人
				planCourse.setModifyUser(studyPlan.getModifyUser());
				//设置创建时间
				planCourse.setGmtCreate(new Date());
			}else{//修改时间
				planCourse.setCreateUser(studyPlan.getCreateUser());
				planCourse.setGmtModified(new Date());
			}
			
			savePlanCourses.add(planCourse);
		}

		//插入学习计划表
		studyPlanMapper.insertAllColumn(studuy);

		//批量插入计划课程中间表
		if (savePlanCourses.size()>0) {
			planCourseService.insertBatch(savePlanCourses);
		}
		}
			
	
	@Override
	public Page<StudyPlan> findPlanByPage(String code, String name, String tagId, int pageNo, int pageSize) {
		Page<StudyPlan> page = new Page<StudyPlan>(pageNo, pageSize);
		List<StudyPlan> plans =  studyPlanMapper.findPlanByPage(page,code,name,tagId,pageNo,pageSize);
		page.setRecords(plans);
		return page;
	}

	@Override
	@Transactional
	public void deletePlanBatchByIds(Long[] planIds) {
		studyPlanMapper.deletePlanBatchByIds(planIds);
	}

	@Override
	public StudyPlan findPlanDetailById(Long id) {
		return studyPlanMapper.findPlanDetailById(id);
	}

	public StudyPlanCopy selectPlanAndCourse(Long id) {
		StudyPlan studyPlan = selectById(id);
		List<PlanCourseVoQ> planCourses = planCourseMapper.selectPlanAndCourse(id);
		StudyPlanCopy copy = new StudyPlanCopy();
		try {
			PropertyUtils.copyProperties(copy,studyPlan);
		} catch (Exception e) {
            e.printStackTrace();
		}
		copy.setPlanCourseVoQs(planCourses);
		return copy;
	}

	@Override
	@Transactional
	public List<StudyPlan> studyPlanList(StudyPlan studyPlan) {
	return	studyPlanMapper.studyPlanList(studyPlan);
	}

	@Override
	@Transactional
	public StudyPlan selectPlanById(Long id) {
		return studyPlanMapper.selectPlanById(id);
	}

	@Override
	@Transactional
	public void updateStudyPlan(StudyPlan studyPlan) {
		 studyPlanMapper.updateStudyPlan(studyPlan);
		
	}
	
	/**
	 * 批量删除学习计划
	 * 
	 * 
	 */
	@Override
	@Transactional
	public void deleteBetchPlan(List<Long> ids) {
	studyPlanMapper.deletePlanCourseByIds(ids);
	studyPlanMapper.deletePlanStudyByIds(ids);
		
	}

	@Override
	public List<StudyPlan> serachFind(StudyPlan studyPlan) {
	return 	studyPlanMapper.studyPlanCollection(studyPlan);
	}

	@Override
	public Integer findAssociated(Long id) {
	return studyPlanMapper.planCourseSerach(id);
	}

	
	@Override
	public List<StudyPlan> planList(String keyWord, String code, String tagId, String uploadTimeBegin,
			String uploadTimeEnd) {
		return studyPlanMapper.planList(keyWord, code, tagId, uploadTimeBegin, uploadTimeEnd);
	}
	
	private  int getPages(int size,int total) {
        int pages = 0;
		if (size == 0) {
            return 0;
        }
        pages =total / size;
        if (total % size != 0) {
            pages++;
        }
        return pages;
    }

	@Override
	public PageInfo<StudyPlanVvo> listByPage(Page<StudyPlanVvo> page, String keyWord, int pageNo, int pageSize) {
		PageInfo<StudyPlanVvo> pageInfo = new PageInfo<StudyPlanVvo>();
		pageInfo.setRecords(studyPlanMapper.studyPlanManageSearch(page,keyWord,pageNo,pageSize));
		int total = studyPlanMapper.studyPlanManageSearchCount(keyWord);
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(getPages(pageSize,total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}


	@Override
	public PageInfo<StudyPlanVvo> listPlan(Page<StudyPlanVvo> page, String name, String code, String tagId, int pageNo,
			int pageSize) {
		PageInfo<StudyPlanVvo> pageInfo = new PageInfo<StudyPlanVvo>();
		int total = studyPlanMapper.stuPlanNum(name, code, tagId);
		pageInfo.setRecords(studyPlanMapper.studyPlansSearch(page, name, code, tagId, pageNo, pageSize));
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}


}
