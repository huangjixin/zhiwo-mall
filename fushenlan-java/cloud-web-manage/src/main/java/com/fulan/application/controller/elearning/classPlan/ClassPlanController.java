package com.fulan.application.controller.elearning.classPlan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.fulan.api.course.domain.Course;
import com.fulan.api.course.service.CourseService;
import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.plan.domain.ClassPlan;
import com.fulan.api.plan.service.ClassPlanService;
import com.fulan.api.plan.service.CompulsoryCplanService;
import com.fulan.api.plan.vo.ClassPlanFwVo;
import com.fulan.api.plan.vo.ClassPlanVo;
import com.fulan.api.plan.vo.PlanCourseDtoFMVo;
import com.fulan.api.plan.vo.StudyPlanVvo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.service.AccountService;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.json.JsonUtil;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;

@Controller
@RequestMapping("/manage/classPlan")
public class ClassPlanController {
	
	@Autowired
	private ClassPlanService classPlanService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private CompulsoryCplanService compulsoryCplanService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@RequiresPermissions("/manage/classPlan/index")
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(Model model,
			@RequestParam(value="name",required=false)String name ,
			@RequestParam(value="code",required=false)String code,
			@RequestParam(value="tagId",required=false)String tagId,
			@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="10")String pageSize
			){
		PageInfo<ClassPlanVo> pageInfo = classPlanService.listPage(name, code, tagId, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("name",name);
		model.addAttribute("code",code);
		model.addAttribute("tagId",tagId);
		model.addAttribute("pageInfo",pageInfo);
		return "elearning/plan/classPlan/classPlanList";
	}
	
	/**
	 * ajax 获取分页内容
	 * @param name
	 * @param code
	 * @param tagId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("listByPageAjax")
	@ResponseBody
	public Response<PageInfo<ClassPlanVo>> listByPageAjax(String name,String code,String tagId, String pageNo,String pageSize){
		pageNo = StringUtil.isEmpty(pageNo)?"1":pageNo;
		pageSize =  StringUtil.isEmpty(pageNo)?"10":pageSize;
		PageInfo<ClassPlanVo> pageInfo = classPlanService.listPage(name, code, tagId, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		Response<PageInfo<ClassPlanVo>> response = new Response<>(Response.SUCCESS, null);
		response.setData(pageInfo);
		return response;
	}	
	
	@RequestMapping("editOrDele")
	@ResponseBody
	public Response<String> edit(String ids,String state,Model model){
		return classPlanService.updateOrDele(ids, state);
	}
	
	@RequestMapping("save")
	@ResponseBody
	/*@RequestParam(name = "planAuthorityDtos",required=false) PlanAuthorityDto[] planAuthorityDtos,*/
	public Response<String>  saveClassPlan(
			ClassPlanFwVo classPlanFwVo,Long fileId
			){
		Long userId = SessionContextUtils.getLoginUserId();
		return classPlanService.insertClassPlan(classPlanFwVo,userId,fileId);
	}
	
	@RequiresPermissions("/manage/classPlan/index")
	@RequestMapping("saveView")
	@SuppressWarnings("unchecked")
	public String  saveClassPlan(Model model , String type, String id){
		if(StringUtils.isNotEmpty(id)){
			Map<String,Object> map = classPlanService.findOnePlansFW(id);
			model.addAttribute("classPlan", map.get("classPlan"));
			List<PlanCourseDtoFMVo> onLineList = (List<PlanCourseDtoFMVo>) map.get("list");
			model.addAttribute("classPlanList", map.get("list"));
			model.addAttribute("planAuthorityList", map.get("planAuthorityList"));
			String markType = "stage";
			if(CollectionUtils.isNotEmpty(onLineList) && onLineList.size()==1){
				markType= "ordinary";
				model.addAttribute("OrdinaryLineList", onLineList.get(0));
			}
			model.addAttribute("markType", markType);
			model.addAttribute("classPlanManagers", map.get("classPlanManagers"));
			model.addAttribute("expiredAlarmList", map.get("expiredAlarmList"));
			/*model.addAttribute("lineList", map.get("lineList"));*/
			String jsonStr = JsonUtil.object2Json(map.get("accountList"));
			List<Account> accountList = JsonUtil.json2List(jsonStr, Account.class);
			String accountName = "";
			for(int i = 0 ; i < accountList.size() ; i++ ){
				accountName+= accountList.get(i).getAccountName()+",";
			}
			if(!"".equals(accountName)){
				model.addAttribute("accountId", map.get("accountId"));
				model.addAttribute("accountName", accountName.substring(0, accountName.length()-1));
			}
			model.addAttribute("authorityName", map.get("accountName"));
			/*List<Course> list = map.get("course");
			model.addAttribute("course", );*/
			
			//班级计划缩略图
	        List<Attachment> attachments = attachmentService.findbyparms(CommenConstant.EL_THUMBNAIL, Long.parseLong(id));  
	        if (attachments != null && attachments.size() > 0) {
	            model.addAttribute("smallImage",attachments.get(0));
	        }
		}
		return "elearning/plan/classPlan/classPlan_edit";
	}
	
	@RequestMapping("lecturerList")
	public String lecturerList(Model model,HttpServletRequest request,@RequestParam(value="name",required=false)String name){
		//暂时设置为955997244559982592 表示讲师
		HttpSession session = request.getSession();
		String type = "讲师";
		Response<List<Account>> accountList = accountService.findByRoleType(type, Long.valueOf(CommenConstant.FWD_ELEARNING), name);
		model.addAttribute("accountList", accountList.getData());
		return "elearning/plan/classPlan/lecturer_list";
	}
	@RequestMapping("lecturerListAjax")
	@ResponseBody
	public List<Account> lecturerListAjax(@RequestParam(value="name",required=false)String name,HttpServletRequest request){
		//暂时设置为955997244559982592 表示讲师
		HttpSession session = request.getSession();
		String type = "讲师";
		Response<List<Account>> accountList = accountService.findByRoleType(type, Long.valueOf((String)session.getAttribute("resourcePID")), name);
		return accountList.getData();
	}
	
	@RequestMapping("courseList")
	public String courseList(Model model,String isOnline){
		Map<String,Object> map = new HashMap<>();
		map.put("is_online", isOnline);
		List<Course> courseList = courseService.selectCourseByMap(map);
		model.addAttribute("isOnline", isOnline);
		model.addAttribute("courseList", courseList);
		return "elearning/plan/classPlan/course_list";
	}
	
	@GetMapping("paperList")
	public ModelAndView selectByAllList(){
		ModelAndView mv = new ModelAndView();
		List<Paper> paperList = compulsoryCplanService.selectByAllPaperList();
		mv.addObject("paperList", paperList);
		mv.setViewName("elearning/plan/classPlan/paper_list");
		return mv ;
	}
	
	@GetMapping("choosePerson")
	public ModelAndView selectCertificate(@RequestParam(name="accountName",required = false )String accountName,
			@RequestParam(name="mobile",required = false)String mobile,@RequestParam(name="personIds",required = false )String personIds){
		ModelAndView mv = new ModelAndView();
		List<Account> accountList = accountService.selectByAll(accountName, mobile);
		mv.addObject("accountList", accountList);
		mv.addObject("personIds", personIds);
		mv.setViewName("elearning/compulsoryCplan/personnel_restrictions");
		return mv;
	}
	
	@PostMapping("previewAjax")
	@ResponseBody
	public Map<String,Object> previewAjax(ClassPlanFwVo classPlanFwVo,String managerName,String stageNum,String fileId){
		Map<String,Object> map = new HashMap<>();
		map.put("classPlanFwVo", classPlanFwVo);
		map.put("planCourseDto", classPlanFwVo.getPlanCourseDto());
		map.put("classPlan", classPlanFwVo.getClassPlan());
		map.put("managerName", managerName);
		map.put("expiredAlarm", classPlanFwVo.getExpiredAlarmList());
		
		String markType = "stage";
		if("1".equals(stageNum)){
			markType= "ordinary";
		}
		map.put("markType", markType);
		map.put("stageNum", stageNum);
		map.put("fileId", fileId);
		
		return map;
	}
	@RequestMapping(value="preview",method=RequestMethod.GET)
	public ModelAndView preview(ClassPlan classPlan,String managerName){
		ModelAndView mv = new ModelAndView();
		mv.addObject("classPlan", classPlan);
		mv.addObject("managerName", managerName);
		mv.setViewName("elearning/plan/classPlan/classPlan_preview");
		/*map.put("markType", markType);
		map.put("stageNum", stageNum);*/
		
		return mv;
	}
	
}
