package com.fulan.application.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.course.domain.Course;
import com.fulan.api.course.vo.CourseManageVo;
import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.paper.vo.PaperManageMinVo;
import com.fulan.api.plan.domain.CompulsoryCplan;
import com.fulan.api.plan.domain.StudyPlan;
import com.fulan.api.plan.vo.CompulsoryCplanManageVo;
import com.fulan.api.plan.vo.CompulsoryCplanVo;
import com.fulan.application.service.CompulsoryCplanService;
import com.fulan.application.service.PlanCourseService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
/**
 * 必修任务  控制器
 * @author kang
 *
 */
@RestController
@RequestMapping("/manage/compulsoryCplan")
public class CompulsoryCplanController {
	 @Autowired
     private CompulsoryCplanService compulsoryCplanService;
	 
	 @Autowired
	 private PlanCourseService planCourseService;
     
     @RequestMapping(value="/compulsoryCplanManagePage",method=RequestMethod.GET)
 	public  @ResponseBody PageInfo<CompulsoryCplanVo> listByPage(  
 			@RequestParam(name="name") String name , 
 			@RequestParam(name="tagId") String tagId,
			@RequestParam(name="state") String state,
 			@RequestParam("pageNo") int pageNo,
 			@RequestParam("pageSize") int pageSize){
    	    Map<String,Object> map = new HashMap<>();
    	    map.put("name", name);
    	    map.put("tagId", tagId);
    	    map.put("state", state);
 		    Page<CompulsoryCplanVo> page = new Page<CompulsoryCplanVo>(pageNo, pageSize);
 		return	compulsoryCplanService.listByPage(page, name,tagId,state, pageNo, pageSize);
 	}
     
     @RequestMapping(value="/deleteCompulsoryCplan",method=RequestMethod.POST)
     public @ResponseBody String deleteCompulsoryCplan(@RequestParam String ids){
    	 return compulsoryCplanService.deleteCompulsoryCplan(ids);
     }
     
     @RequestMapping(value="/disAbleCompulsoryCplan",method=RequestMethod.POST)
     public @ResponseBody String disAbleCompulsoryCplan(@RequestParam String ids){
    	 return compulsoryCplanService.disAbleCompulsoryCplan(ids);
     }
     
     @RequestMapping(value="/enAbleCompulsoryCplan",method=RequestMethod.POST)
     public @ResponseBody String enAbleCompulsoryCplan(@RequestParam String ids){
    	 return compulsoryCplanService.enAbleCompulsoryCplan(ids);
     }
     
     @RequestMapping(value="/addCompulsoryCplanManageVo",method=RequestMethod.POST)
 	 public String addCompulsoryCplan(@RequestBody CompulsoryCplanManageVo compulsoryCplanManageVo,@RequestParam String fileId,@RequestParam String fOtherId){
 		String str2 = compulsoryCplanService.saveCompulsoryCplan(compulsoryCplanManageVo,fileId,fOtherId);
 		return str2;
 	}
     
    @RequestMapping(value="/cplanManageCheck",method=RequestMethod.GET)
 	public @ResponseBody CompulsoryCplanManageVo getCompulsoryCplanManageVo(@RequestParam String id){
 		return compulsoryCplanService.selectById(id);
 	}
    @RequestMapping(value="/selectByElspId",method=RequestMethod.GET)
    public @ResponseBody List<Course> selectByElspId(@RequestParam String type){
		return compulsoryCplanService.selectByElspId(type);
    }
    
    @RequestMapping(value="/selectByAllList",method=RequestMethod.GET)
    public @ResponseBody List<Paper> selectByAllList(){
		return compulsoryCplanService.selectAllPaper();
    }
    
    @RequestMapping(value="/selectByCId",method=RequestMethod.GET)
    public @ResponseBody List<CourseManageVo> selectByCId(@RequestParam(name="id") String cId ){
		return compulsoryCplanService.selectByCId(cId);
    }
    
    @RequestMapping(value="/selectBycId",method=RequestMethod.GET)
    public @ResponseBody List<PaperManageMinVo> selectBycId(@RequestParam(name="id") String cId ){
		return compulsoryCplanService.selectBycId(cId);
    }
    
    @RequestMapping(value="/selectByPlanId",method=RequestMethod.GET)
    public @ResponseBody List<CourseManageVo> selectByPlanId(@RequestParam(name="id") String id ){
		return planCourseService.selectByPlanId(id);
    }
    
    @RequestMapping(value="/selectByPlanOtherId",method=RequestMethod.GET)
    public @ResponseBody List<CourseManageVo> selectByPlanOtherId(@RequestParam(name="id") String id ){
		return planCourseService.selectByPlanOtherId(id);
    }
    /**
     * 查询最大的stage
     * @param id
     * @return
     */
    @RequestMapping(value="/selectMaxByPlanId",method=RequestMethod.GET)
    public @ResponseBody String selectMaxByPlanId(@RequestParam(name="id") String id ){
		return compulsoryCplanService.selectMaxByPlanId(id);
    }
    
    @RequestMapping(value="/compulsoryCplanById",method=RequestMethod.GET)
    Response<CompulsoryCplan> compulsoryCplanById(@RequestParam(name="id") Long id){
    	Response<CompulsoryCplan> res = new Response<CompulsoryCplan>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
    	try {
    		CompulsoryCplan compulsoryCplan = compulsoryCplanService.compulsoryCplanById(id);
			res.setData(compulsoryCplan);
			return res;
		} catch (Exception e) {
			return new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
		}
    }
}