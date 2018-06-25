package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.fulan.api.plan.domain.*;
import com.fulan.api.plan.vo.ClassPlanDto;
import com.fulan.api.plan.vo.ClassPlanFwVo;
import com.fulan.api.plan.vo.ClassPlanVo;
import com.fulan.api.plan.vo.PlanAuthorityDto;
import com.fulan.api.plan.vo.PlanCourseDtoFMVo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.service.AccountService;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.ClassPlanMapper;
import com.fulan.application.mapper.ExpiredAlarmMapper;
import com.fulan.application.mapper.PlanAuthorityMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.orm.id.Idfactory;
import com.fulan.application.service.ClassPlanManagerService;
import com.fulan.application.service.ClassPlanService;
import com.fulan.application.service.PlanAuthorityService;
import com.fulan.application.service.PlanCourseService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 班级计划 服务实现类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-18
 */
@Service
@Transactional
public class ClassPlanServiceImpl extends ServiceImpl<ClassPlanMapper, ClassPlan> implements ClassPlanService {

	@Autowired
	private ClassPlanMapper classPlanMapper;

	@Autowired
	private PlanAuthorityService planAuthorityService;

	@Autowired
	private PlanCourseService planCourseService;

	@Autowired
	private ClassPlanManagerService classPlanManagerService;
	
	@Autowired
    private IdGenerator idGenerator;
	
	@Autowired
	private ExpiredAlarmMapper expiredAlarmMapper;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PlanAuthorityMapper planAuthorityMapper;
	
	@Autowired
    private AttachmentService attachmentService;

//	@Autowired
//	private IdGenerator idGenerator;

	@Override
	public void insertClassPlan(ClassPlan classPlan, PlanCourseDto[] planCourseDto, PlanAuthorityDto[] planAuthorityDtos, Long[] managerId, Long userId) {
		List<PlanCourse> planCourses = new ArrayList<PlanCourse>();
		List<ClassPlanManager> classPlanManagers = new ArrayList<ClassPlanManager>();
		List<PlanAuthority> planAuthoritys = new ArrayList<PlanAuthority>();
		//雪花自动生成ID
//		elClassPlan.setId(idGenerator.generate());
		// **班级计划对象
		//设置创建人
		classPlan.setCreateUser(userId);
		//设置启用
		classPlan.setState(1);
		//创建时间
		classPlan.setGmtCreate(new Date());
		//**计划课程对象
		for (PlanCourseDto dto : planCourseDto) {
			PlanCourse course = new PlanCourse();
			//自动生成ID
//			course.setId(1);
			//设置计划ID
//			course.setCourseId(classPlan.getId());
			//设置计划类型
			course.setCourseType(2);
			//设置基础课程编号
			course.setAssociateId(dto.getAssociateId());
			//关联类别
			course.setAssociateType(dto.getAssociateType());
			//是否必修
			course.setIsCompulsory(dto.getIsCompulsory());
			//所属阶段
			course.setStage(dto.getStage());
			//阶段周期
			course.setStartTime(dto.getStartTime());
			course.setEndTime(dto.getEndTime());
			//创建人
			course.setCreateUser(userId);
			//创建时间
			course.setGmtCreate(new Date());
			planCourses.add(course);
		}
		// **负责人
		for (Long maId : managerId) {
			ClassPlanManager manager = new ClassPlanManager();
			//自动生成ID
//			manager.setId();
			//设置计划ID
//			manager.setPlanId(classPlan.getId());
			//设置负责人ID
			manager.setUserId(maId);
			classPlanManagers.add(manager);
		}
		// ** 新增计划权限表
		for (PlanAuthorityDto dto : planAuthorityDtos) {
			PlanAuthority planAuthority = new PlanAuthority();
			//自动生成ID
	//		planAuthority.setId();
			//设置计划ID
	//		planAuthority.setCourseId(classPlan.getId());
			//设置计划Type
			planAuthority.setCourseType(2);
			//设置权限类型
			planAuthority.setAuthorityType(dto.getAuthorityType());
			//设置关联编号
			planAuthority.setAssociateId(dto.getAuthorityId());
			//设置创建人
			planAuthority.setCreateUser(userId);
			//设置创建时间
			planAuthority.setGmtCreate(new Date());
			planAuthoritys.add(planAuthority);
		}

		//**新增班级计划
		classPlanMapper.insert(classPlan);
		//新增计划权限
		planAuthorityService.insertBatch(planAuthoritys);
		//批量插入计划课程
		planCourseService.insertBatch(planCourses);
		//批量插入负责人
		classPlanManagerService.insertBatch(classPlanManagers);

	}

	@Override
	public boolean deleteClassPlan(ClassPlan classPlan) {
		return classPlanMapper.delete(new EntityWrapper<ClassPlan>(classPlan)) == 1;
	}

	@Override
	public void deleteBatchClassPlan(List<ClassPlan> plans) {
		this.deleteBatchIds(plans);
	}

	@Override
	public boolean updateClassPlan(ClassPlan classPlan) {
		return classPlanMapper.update(classPlan, new EntityWrapper<ClassPlan>(classPlan)) == 1;
	}

	@Override
	public ClassPlan findOnePlans(ClassPlan classPlan) {
		return classPlanMapper.selectOne(classPlan);
	}

	@Override
	public List<ClassPlan> findAllClassPlan() {
		return null;
	}

	@Override
	public Page<ClassPlan> findClassPlanByPage(ClassPlan classPlan, int pageNo, int pageSize,String pageSortFiled, String pageSortType) {
		// 组装page，页数、条数、排序字段、排序方式
        Page<ClassPlan> page = new Page<ClassPlan>(pageNo, pageSize);
        page.setOrderByField(pageSortFiled);
        page.setAsc((null == pageSortType || "desc".equals(pageSortType)) ? false : true);
        EntityWrapper<ClassPlan> wrapper = new EntityWrapper<ClassPlan>(classPlan);
		return this.selectPage(page, wrapper);
	}

	@Override
	public ClassPlan findClassPlanDetailById(Long id) {
		return classPlanMapper.findClassPlanDetailById(id);
	}
	
	@Override
	public PageInfo<ClassPlanVo> listByPageFM(Page<ClassPlanVo> page, String name, String code, String tagId, int pageNo,
			int pageSize) {
		PageInfo<ClassPlanVo> pageInfo = new PageInfo<ClassPlanVo>();
		int total = classPlanMapper.classPlanFMCount(name, code, tagId);
		pageInfo.setRecords(classPlanMapper.classPlanFM(page, name, code, tagId, pageNo, pageSize));
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}

	@Override
	public Response<String> updateOrDeleForManage(String ids, String state) {
		Response<String> response = new Response<>(Response.SUCCESS,"操作数据成功");
		ClassPlan classPlan = new ClassPlan();
		String[] idArr = ids.split(",");
		int count = 0;
		for (String id : idArr) {
			if(!"dele".equals(state)){
				classPlan.setId(Long.valueOf(id));
				classPlan.setState(Integer.valueOf(state));
				count = classPlanMapper.updateById(classPlan);
			}else{
				PlanAuthority planAuthority = new PlanAuthority();
				planAuthority.setCourseId(classPlan.getId());
				EntityWrapper<PlanAuthority> wrapper = new EntityWrapper<>(planAuthority);
				planAuthorityService.delete(wrapper);
				PlanCourse planCourse = new PlanCourse();
				planCourse.setPlanId(classPlan.getId());
				EntityWrapper<PlanCourse> wrapperPC = new EntityWrapper<>(planCourse);
				planCourseService.delete(wrapperPC);
				ClassPlanManager classPlanManage = new ClassPlanManager();
				classPlanManage.setPlanId(classPlan.getId());
				EntityWrapper<ClassPlanManager> wrapperCPM = new EntityWrapper<>(classPlanManage);
				classPlanManagerService.delete(wrapperCPM);
				count = classPlanMapper.deleteById(Long.valueOf(id));
			}
		}
		if(count==0){
			response.setCode(Response.ERROR);
			response.setMsg("操作数据失败");
		}
		return response;
	}


	@Override
	public Page<ClassPlanDto> searchClassPlan(Map<String, Object> paramMap) {
		Page<ClassPlanDto> page = new Page<ClassPlanDto>((Integer)paramMap.get("pageNo"), (Integer)paramMap.get("pageSize"));
		page.setRecords(classPlanMapper.searchClassPlan(page,paramMap));
		return page;
	}
	
	@Override
	public Response<String> insertClassPlanFW(ClassPlan classPlan,
			PlanCourseDto[] planCourseDto, 
			Long[] managerId, Long userId, 
			ExpiredAlarm[] expiredAlarms,
			String isNot,PlanAuthority[] planAuthorityss,
			Long fileId
			) {
		Response<String> resp = new Response<>(Response.SUCCESS,"保存数据成功");
		List<PlanCourse> planCourses = new ArrayList<PlanCourse>();
		List<ClassPlanManager> classPlanManagers = new ArrayList<ClassPlanManager>();
		/*List<PlanAuthority> planAuthoritys = new ArrayList<PlanAuthority>();*/
		Long id = classPlan.getId();
		if(null == id){
			classPlan.setId(idGenerator.generate());
			classPlan.setGmtCreate(new Date());//创建时间
		}
		// **班级计划对象
		if("0".equals(isNot)){
			Map<String,Object> classPlanMap=new HashMap<>();
			classPlanMap.put("name", classPlan.getName());
			List<ClassPlan> classPlanList=this.selectByMap(classPlanMap);
			if(CollectionUtils.isNotEmpty(classPlanList)){
				resp.setCode(Response.ERROR);
				resp.setMsg("计划名称已存在");
				return resp;
			}
		}
		//**计划课程对象
		for (PlanCourseDto dto : planCourseDto) {
			if(null == dto.getAssociateId()){
				continue;
			}
			PlanCourse course = new PlanCourse();
			//自动生成ID
//			course.setId(1);
			//设置计划ID
//			course.setCourseId(classPlan.getId());
			//设置计划ID
			course.setPlanId(classPlan.getId());
			//设置计划类型
			course.setCourseType(CommenConstant.EL_COURSE_TYPE_CLASSPLAN);
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
		// **负责人
		for (Long maId : managerId) {
			if(null == maId){
				continue;
			}
			ClassPlanManager manager = new ClassPlanManager();
			//自动生成ID
//			manager.setId();
			//设置计划ID
			manager.setPlanId(classPlan.getId());
			//设置负责人ID
			manager.setUserId(maId);
			classPlanManagers.add(manager);
		}
		// ** 新增计划权限表
		/*for (PlanAuthorityDto dto : planAuthorityDtos) {
			if(null == dto.getAuthorityId()){
				continue;
			}
			PlanAuthority planAuthority = new PlanAuthority();
			//自动生成ID
	//		planAuthority.setId();
			//设置计划ID
			planAuthority.setCourseId(classPlan.getId());
			//设置计划Type
			planAuthority.setCourseType(CommenConstant.EL_COURSE_TYPE_CLASSPLAN);
			//设置权限类型
			planAuthority.setAuthorityType(dto.getAuthorityType());
			//设置关联编号
			planAuthority.setAssociateId(dto.getAuthorityId());
			//设置创建人
			planAuthority.setCreateUser(userId);
			//设置创建时间
			planAuthority.setGmtCreate(new Date());
			planAuthoritys.add(planAuthority);
		}*/
		if(null != expiredAlarms){
			ExpiredAlarm ea = new ExpiredAlarm();
			ea.setCourseId(classPlan.getId());
			EntityWrapper<ExpiredAlarm> wrapper = new EntityWrapper<>(ea);
			expiredAlarmMapper.delete(wrapper);
			for(ExpiredAlarm expiredAlarm : expiredAlarms){
				expiredAlarm.setId(Idfactory.generate());
				expiredAlarm.setCourseType(CommenConstant.EL_COURSE_TYPE_CLASSPLAN);
				expiredAlarm.setCourseId(classPlan.getId());
				expiredAlarmMapper.insert(expiredAlarm);
			}
		}
		//**新增班级计划
		if(null == id){
			//设置启用
			classPlan.setState(CommenConstant.EL_COURSE_TYPE_CLASSPLAN_STAGE);
			classPlan.setCreateUser(userId);
			classPlan.setGmtCreate(new Date());
			classPlanMapper.insertSelective(classPlan);
		}else{
			if(null == classPlan.getIsTrainingAgent()){
				classPlan.setIsTrainingAgent(0);
			}
			if(null == classPlan.getIsTrainingNew()){
				classPlan.setIsTrainingNew(0);
			}
			if(null == classPlan.getIsTrainingStaff()){
				classPlan.setIsTrainingStaff(0);
			}
			if(null == classPlan.getIsRewardCertification()){
				classPlan.setIsRewardCertification(0);
			}
			if(null == classPlan.getIsRewardPoint()){
				classPlan.setIsRewardPoint(0);
			}
			if(null == classPlan.getIsRewardQualification()){
				classPlan.setIsRewardQualification(0);
			}
			classPlan.setModifyUser(userId);
			classPlan.setGmtModified(new Date());
			classPlanMapper.updateById(classPlan);
		}
		//关联附件表
		Attachment am = null;
        if (fileId != null && !"".equals(fileId)) {
            am =  attachmentService.selectById(fileId);
        }
        //更新附件hostId字段
        if (am != null) {
            am.setHostId(classPlan.getId());
            attachmentService.updateById(am);
         }
        
		//新增计划权限
		if(planAuthorityss.length>0){
			if(null !=id ){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("course_id", id);
				planAuthorityMapper.deleteByMap(map);
			}
			for(PlanAuthority planAuthority:planAuthorityss){
				if(null !=planAuthority.getAuthorityType()){
					planAuthority.setId(Idfactory.generate());
					planAuthority.setCourseType(3);
					planAuthority.setCourseId(classPlan.getId());
					planAuthority.setGmtCreate(new Date());
					planAuthorityMapper.insert(planAuthority);
				}
			}
			/*PlanAuthority planAuthority = new PlanAuthority();
		if(CollectionUtils.isNotEmpty(planAuthoritys) && planAuthoritys.size()>0){
			PlanAuthority planAuthority = new PlanAuthority();
			planAuthority.setCourseId(classPlan.getId());
			EntityWrapper<PlanAuthority> wrapper = new EntityWrapper<>(planAuthority);
			planAuthorityService.delete(wrapper);
			planAuthorityService.insertBatch(planAuthoritys);*/
		}
		//批量插入计划课程
		if(CollectionUtils.isNotEmpty(planCourses) && planCourses.size()>0){
			PlanCourse planCourse = new PlanCourse();
			planCourse.setPlanId(classPlan.getId());
			EntityWrapper<PlanCourse> wrapper = new EntityWrapper<>(planCourse);
			planCourseService.delete(wrapper);
			planCourseService.insertBatch(planCourses);
		}
		//批量插入负责人
		if(CollectionUtils.isNotEmpty(classPlanManagers) && classPlanManagers.size()>0){
			ClassPlanManager classPlanManage = new ClassPlanManager();
			classPlanManage.setPlanId(classPlan.getId());
			EntityWrapper<ClassPlanManager> wrapper = new EntityWrapper<>(classPlanManage);
			classPlanManagerService.delete(wrapper);
			classPlanManagerService.insertBatch(classPlanManagers);
		}
		return resp;

	}
	
	@Override
	public Response<String> updateClassPlanFW(ClassPlan classPlan, PlanCourseDto[] planCourseDto, PlanAuthorityDto[] planAuthorityDtos, Long[] managerId, Long userId, ExpiredAlarm[] expiredAlarms) {
		Response<String> resp = new Response<>(Response.SUCCESS,"操作数据成功");
		List<PlanCourse> planCourses = new ArrayList<PlanCourse>();
		List<ClassPlanManager> classPlanManagers = new ArrayList<ClassPlanManager>();
		List<PlanAuthority> planAuthoritys = new ArrayList<PlanAuthority>();
		//创建时间
		Map<String,Object> classPlanMap=new HashMap<>();
		if(StringUtils.isNotEmpty(classPlan.getName())){
			classPlanMap.put("name", classPlan.getName());
			List<ClassPlan> classPlanList=this.selectByMap(classPlanMap);
			if(CollectionUtils.isNotEmpty(classPlanList)){
				resp.setCode(Response.ERROR);
				resp.setMsg("计划名称已存在");
				return resp;
			}
		}
		classPlan.setGmtModified(new Date());
		//**计划课程对象
		for (PlanCourseDto dto : planCourseDto) {
			if(null == dto){
				continue;
			}
			PlanCourse course = new PlanCourse();
			course.setPlanId(classPlan.getId());
			//设置计划类型
			course.setCourseType(2);
			//设置基础课程编号
			course.setAssociateId(dto.getAssociateId());
			//关联类别
			course.setAssociateType(dto.getAssociateType());
			//是否必修
			course.setIsCompulsory(dto.getIsCompulsory());
			//所属阶段
			course.setStage(dto.getStage());
			//阶段周期
			course.setStartTime(dto.getStartTime());
			course.setEndTime(dto.getEndTime());
			//创建人
			course.setCreateUser(userId);
			//创建时间
			course.setGmtCreate(new Date());
			planCourses.add(course);
		}
		// **负责人
		for (Long maId : managerId) {
			if(null == maId){
				continue;
			}
			ClassPlanManager manager = new ClassPlanManager();
			manager.setPlanId(classPlan.getId());
			//设置负责人ID
			manager.setUserId(maId);
			classPlanManagers.add(manager);
		}
		// ** 新增计划权限表
		for (PlanAuthorityDto dto : planAuthorityDtos) {
			if(null == dto){
				continue;
			}
			PlanAuthority planAuthority = new PlanAuthority();
			
			planAuthority.setCourseId(classPlan.getId());
			//设置计划Type
			planAuthority.setCourseType(2);
			//设置权限类型
			planAuthority.setAuthorityType(dto.getAuthorityType());
			//设置关联编号
			planAuthority.setAssociateId(dto.getAuthorityId());
			//设置创建人
			planAuthority.setCreateUser(userId);
			//设置创建时间
			planAuthority.setGmtCreate(new Date());
			planAuthoritys.add(planAuthority);
		}
		if(null != expiredAlarms){
			/*expiredAlarmMapper.*/
			for(ExpiredAlarm expiredAlarm : expiredAlarms){
				if(null == expiredAlarm){
					continue;
				}
				expiredAlarm.setId(Idfactory.generate());
				expiredAlarm.setCourseId(classPlan.getId());
				expiredAlarmMapper.insert(expiredAlarm);
			}
		}

		//**新增班级计划
		classPlanMapper.updateById(classPlan);
		//新增计划权限
		if(CollectionUtils.isNotEmpty(planAuthoritys) && planAuthoritys.size()>0){
			PlanAuthority planAuthority = new PlanAuthority();
			planAuthority.setCourseId(classPlan.getId());
			EntityWrapper<PlanAuthority> wrapper = new EntityWrapper<>(planAuthority);
			planAuthorityService.delete(wrapper);
			planAuthorityService.insertBatch(planAuthoritys);
		}
		//批量插入计划课程
		if(CollectionUtils.isNotEmpty(planCourses) && planCourses.size()>0){
			PlanCourse planCourse = new PlanCourse();
			planCourse.setPlanId(classPlan.getId());
			EntityWrapper<PlanCourse> wrapper = new EntityWrapper<>(planCourse);
			planCourseService.delete(wrapper);
			planCourseService.insertBatch(planCourses);
		}
		//批量插入负责人
		if(CollectionUtils.isNotEmpty(classPlanManagers) && classPlanManagers.size()>0){
			ClassPlanManager classPlanManage = new ClassPlanManager();
			classPlanManage.setPlanId(classPlan.getId());
			EntityWrapper<ClassPlanManager> wrapper = new EntityWrapper<>(classPlanManage);
			classPlanManagerService.delete(wrapper);
			classPlanManagerService.insertBatch(classPlanManagers);
		}
		return resp;
	}

	@Override
	public Map<String,Object> findOnePlansFW(String id) {
		Map<String,Object> returnMap = new HashMap<>();
		ClassPlan classPlan = classPlanMapper.selectByIdFW(Long.valueOf(id));
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		List<ClassPlanFwVo> list = classPlanMapper.seleStageById(map);
		/*List<PlanCourseDtoFMVo> lineList = planCourseService.seleByPlanIdLine(Long.valueOf(id));*/
		returnMap.put("classPlan", classPlan);
		returnMap.put("list", list);
		/*returnMap.put("lineList", lineList);*/
		ClassPlanManager classPlanManage = new ClassPlanManager();
		classPlanManage.setPlanId(classPlan.getId());
		EntityWrapper<ClassPlanManager> wrapperCPM = new EntityWrapper<>(classPlanManage);
		List<ClassPlanManager> classPlanManagers = new ArrayList<ClassPlanManager>();
		classPlanManagers = classPlanManagerService.selectList(wrapperCPM);
		List<Account> accountList =  new ArrayList<Account>();
		if(CollectionUtils.isNotEmpty(classPlanManagers)){
			returnMap.put("classPlanManagers", classPlanManagers);
			List<Long> accountId = new ArrayList<>();
			for(ClassPlanManager classPlanManager : classPlanManagers){
				accountId.add(classPlanManager.getUserId());
			}
			accountList = accountService.findByAccountId(accountId);
			returnMap.put("accountId",accountId);
		}
		returnMap.put("accountList", accountList);
		ExpiredAlarm expiredAlarm = new ExpiredAlarm();
		expiredAlarm.setCourseId(classPlan.getId());
		EntityWrapper<ExpiredAlarm> wrapperEA = new EntityWrapper<>(expiredAlarm);
		List<ExpiredAlarm> expiredAlarmList = new ArrayList<ExpiredAlarm>();
		expiredAlarmList = expiredAlarmMapper.selectList(wrapperEA);
		returnMap.put("expiredAlarmList", expiredAlarmList);
		
		List<PlanAuthority> planAuthorityList = planAuthorityMapper.selectByAssociateId(id);
		returnMap.put("planAuthorityList", planAuthorityList);
		List<Long> accountId = new ArrayList<>();
		for (PlanAuthority planAuthority : planAuthorityList) {
			accountId.add(planAuthority.getAssociateId());
		}
		List<Account> accounList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(accountId)){
			accounList =accountService.findByAccountId(accountId);
		}
		String accountName = "";
		for(Account account:accounList){
			accountName+=account.getAccountName()+",";
		}
		if(accountName != ""){
			accountName = accountName.substring(0,accountName.length()-1);
		}
		returnMap.put("accountName",accountName );
		/*List<PlanAuthority> planAuthoritys = new ArrayList<PlanAuthority>();*/
		return returnMap;
	}
}
