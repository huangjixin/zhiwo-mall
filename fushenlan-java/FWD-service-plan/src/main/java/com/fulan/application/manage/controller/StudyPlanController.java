package com.fulan.application.manage.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fulan.api.plan.domain.StudyPlanCopy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.domain.StudyPlan;
import com.fulan.api.plan.vo.StudyPlanVoQ;
import com.fulan.api.plan.vo.StudyPlanVvo;
import com.fulan.application.service.StudyPlanService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.json.JsonUtil;
import com.fulan.application.util.page.PageInfo;

/**
 * <p>
 * 学习计划 后端控制器
 * </p>
 *
 * @author guiyang
 * @since 2018-01-26
 */
@RestController  
@RequestMapping("/manage/studyPlan")
public class StudyPlanController {

	private Logger logger = LoggerFactory.getLogger(StudyPlanController.class);

	@Autowired
	private StudyPlanService studyPlanService;

	/**
	 * 新增学习计划
	 *
	 * @param studyPlan
	 * @param
	 * @return
	 */
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	@ResponseBody
	public Response<String> insert(@RequestBody StudyPlanCopy studyPlan){
		try {
			if (studyPlan!=null&&studyPlan.getPlanCourses()!=null
					&&studyPlan.getPlanCourses().size()==0){
				return new Response<>(Response.ERROR, "学习计划不能为空");
			}
			studyPlanService.insertElStudyPlan(studyPlan);
			Response<String> res = new Response<String>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			return res;
		} catch (Exception e) {
			logger.error("----------------------新增学习计划失败------------------------");
			logger.error(e.getMessage());
			return new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}

	/**
	 * 更新学习计划
	 *
	 * @param studyPlan
	 * @return
	 */

	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Response<String> update(@RequestBody StudyPlan studyPlan) {
		try {
			logger.info("---------------------更新学习计划---------------------");
			studyPlanService.updateById(studyPlan);
			Response<String> res = new Response<String>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			logger.info("----------------------更新学习计划成功------------------------");
			return res;
		} catch (Exception e) {
			logger.error("----------------------更新学习计划失败------------------------");
			logger.error(e.getMessage());
			return new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}

	/**
	 * 批量删除学习计划
	 *
	 * @param planIds
	 * @return
	 */
	

	@RequestMapping(value="/batch/delete",method=RequestMethod.POST)
	@ResponseBody
	public Response<String> delete(@RequestParam(name = "planIds", defaultValue = "0") Long[] planIds) {
		try {
			logger.info("---------------------批量删除学习计划---------------------");
			studyPlanService.deletePlanBatchByIds(planIds);
			Response<String> res = new Response<String>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			logger.info("----------------------批量删除学习计划成功------------------------");
			return res;
		} catch (Exception e) {
			logger.error("----------------------批量删除学习计划失败------------------------");
			logger.error(e.getMessage());
			return new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}

	
	
	@RequestMapping(value="/batch/deletePlans",method=RequestMethod.GET)
	@ResponseBody
	public Response<String> deleted(@RequestParam(name="ids") List<Long> ids) {
		try {
		
			logger.info("---------------------批量删除学习计划---------------------");
			studyPlanService.deleteBetchPlan(ids);
			Response<String> res = new Response<String>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			logger.info("----------------------批量删除学习计划成功------------------------");
			return res;
		} catch (Exception e) {
			logger.error("----------------------批量删除学习计划失败------------------------");
			logger.error(e.getMessage());
			return new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}
	/**
	 * 批量更新学习计划状态
	 *
	 * @param studyPlans
	 * @return
	 */
	
	@RequestMapping(value="/batch/update/status",method=RequestMethod.POST)
	@ResponseBody
	public Response<String> updateStatus(@RequestBody StudyPlan[] studyPlans) {
		try {
			logger.info("---------------------批量删除学习计划---------------------");
			studyPlanService.updateBatchById(Arrays.asList(studyPlans));
			Response<String> res = new Response<String>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			logger.info("----------------------批量删除学习计划成功------------------------");
			return res;
		} catch (Exception e) {
			logger.error("----------------------批量删除学习计划失败------------------------");
			logger.error(e.getMessage());
			return new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}

	/**
	 * 批量更新学习计划状态
	 *
	 * @param code
	 * @param name
	 * @param tagId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public Page<StudyPlan> list(@RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "tagId", required = false) String tagId,
			@RequestParam(required = false, name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, name = "pageSize", defaultValue = "10") int pageSize) {
		try {
			logger.info("---------------------查询学习计划---------------------");
			Page<StudyPlan> page = studyPlanService.findPlanByPage(code, name, tagId, pageNo, pageSize);
			System.out.println(page.getSize()+"=============================================");
		logger.info("----------------------查询学习计划成功------------------------");
			return page;
		} catch (Exception e) {
			logger.error("----------------------查询学习计划失败------------------------");
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 查询学习计划详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	@ResponseBody
	public Response<String> listDetail(@RequestParam(name = "id",required = false, defaultValue = "1") Long id) {
		try {
			logger.info("---------------------查询学习计划---------------------");
			StudyPlanCopy plan = studyPlanService.selectPlanAndCourse(id);
			logger.info("----------------------查询学习计划成功------------------------");
			Response<String> res = new Response<String>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			String json = JsonUtil.object2Json(plan);
			logger.info("----------------------学习计划json------------------------ ：" + json);
			res.setData(json);
			return res;
		} catch (Exception e) {
			logger.error("----------------------查询学习计划失败------------------------");
			logger.error(e.getMessage());
			return new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}
	
	@RequestMapping(value="/studyPlanList",method=RequestMethod.GET)
	@ResponseBody
	public Response<List> studyList(@RequestParam(name="code", required = false ) String code,@RequestParam(name="name", required = false) String name,@RequestParam(name="tagId",required = false) Long tagId,@RequestParam(name="pageNo",required = false) Integer pageNo,@RequestParam(name="pageSize", required = false) Integer pageSize){
	try{
		StudyPlan studyPlan=new StudyPlan();
		studyPlan.setCode(code);
		studyPlan.setName(name);
		studyPlan.setTagId(tagId);
	    List<StudyPlan> lists=	studyPlanService.studyPlanList(studyPlan);
	    Response<List> res=new Response<>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
	    res.setData(lists);
	    return res;
	}catch(Exception e){
	
		return new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
	}
	
		
	}
	
	@RequestMapping(value="/planList",method=RequestMethod.GET)
	@ResponseBody
	public List<StudyPlan> planList(@RequestParam(value="keyWord",required=false) String keyWord,//计划关键字
            @RequestParam(name = "code", required = false) String code,//计划代码
            @RequestParam(name = "tagId", required = false) String tagId,//二级分类
            @RequestParam(value="uploadTimeBegin",required=false)   String uploadTimeBegin,//上传时间
			@RequestParam(value="uploadTimeEnd",required=false)   String uploadTimeEnd//结束时间
			){
	try{
	    List<StudyPlan> lists=	studyPlanService.planList(keyWord, code, tagId, uploadTimeBegin, uploadTimeEnd);
	    return lists;
	}catch(Exception e){
		throw e;
	}
		
	}
	

	
	
	@RequestMapping(value="/studyPlanById",method=RequestMethod.GET)
	@ResponseBody
	public Response<StudyPlan> selectStudyPlanById(@RequestParam(name="id") Long id){
		try{
		StudyPlan studyPlan=studyPlanService.selectPlanById(id);
		Response<StudyPlan> res=new Response<StudyPlan>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		res.setData(studyPlan);
		return res;
		}catch(Exception e){
			e.printStackTrace();
			return new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
		}
		
	}
	
	@RequestMapping(value="/enabledStudyPlan",method=RequestMethod.POST)
	@ResponseBody
	public Response<String> enabledPlan(@RequestParam(name="id") Long id){
		try{
			StudyPlan studyPlan=new StudyPlan();
			studyPlan.setId(id);
			studyPlan.setState(1);
			studyPlanService.updateStudyPlan(studyPlan);
			return new Response<>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		}catch(Exception e){
			e.printStackTrace();
			return new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
		}
		
	}
	
	@RequestMapping(value="/disabledStudyPlan",method=RequestMethod.POST)
	@ResponseBody
	public Response<String> disabledPlan(@RequestParam(name="id") Long id){
		try{
			StudyPlan studyPlan=new StudyPlan();
			studyPlan.setId(id);
			studyPlan.setState(0);
			studyPlanService.updateStudyPlan(studyPlan);
			return new Response<>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		}catch(Exception e){
			e.printStackTrace();
			return new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
		}

	}
	
	/**
	 * 按条件查询
	 * @param code
	 * @param name
	 * @param tagId
	 * @return
	 */
	@RequestMapping(value="/searchStudyPlans",method=RequestMethod.GET)
	@ResponseBody
	public Response<List> serchStudyPlan(@RequestParam(name="code",required = false) String code,
	@RequestParam(name="name",required = false) String name,
	@RequestParam(name="tagId",required = false) Long tagId){
		try{
			StudyPlan studyPlan=new StudyPlan();
			studyPlan.setCode(code);
			studyPlan.setName(name);
			studyPlan.setTagId(tagId);
			List<StudyPlan> studyPlans=studyPlanService.serachFind(studyPlan);
			List<StudyPlanVoQ> lists=new ArrayList<StudyPlanVoQ>();
			for (StudyPlan stu : studyPlans) {
			int count=studyPlanService.findAssociated(stu.getId());
			StudyPlanVoQ stuVo=new StudyPlanVoQ();
			if(count>0){//若count大于0则说明该学习计划被关联了
				stuVo.setId(stu.getId());
				stuVo.setCode(stu.getCode());
				stuVo.setName(stu.getName());
				stuVo.setGroupId(stu.getGroupId());
				stuVo.setTagId(stu.getTagId());
				stuVo.setAssociated(1);
				stuVo.setCourseNum(stu.getCourseNum());
				stuVo.setStageNum(stu.getStageNum());
				stuVo.setState(stu.getState());
	
			}else{//否则就没有被关联
				
				stuVo.setId(stu.getId());
				stuVo.setCode(stu.getCode());
				stuVo.setName(stu.getName());
				stuVo.setGroupId(stu.getGroupId());
				stuVo.setTagId(stu.getTagId());
				stuVo.setAssociated(0);
				stuVo.setCourseNum(stu.getCourseNum());
				stuVo.setStageNum(stu.getStageNum());
				stuVo.setState(stu.getState());
			}
			lists.add(stuVo);
			}
			Response<List> res=new Response<List>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
			res.setData(lists);
			return res;
		}catch(Exception e){
			e.printStackTrace();
			return new Response<List>(Response.ERROR, Response.ERROR_MESSAGE);
		}
		
	}
	
	//分页
	@RequestMapping(value="/listPlan",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo<StudyPlanVvo> listByPage(@RequestParam(value="name",required=false) String name,
			@RequestParam(value="code",required=false) String code,
			@RequestParam(value="tagId",required=false) String tagId,
			@RequestParam(value="pageNo",defaultValue = "1") int pageNo,
			@RequestParam(value="pageSize",defaultValue = "10") int pageSize){
      Page<StudyPlanVvo> page = new Page<>(pageNo, pageSize);
     return	studyPlanService.listPlan(page,name,code,tagId, pageNo, pageSize);
}

}
