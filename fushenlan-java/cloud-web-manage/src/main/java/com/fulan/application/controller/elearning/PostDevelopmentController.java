package com.fulan.application.controller.elearning;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.fulan.api.course.domain.Course;
import com.fulan.api.course.domain.CourseLecturer;
import com.fulan.api.course.service.CourseLecturerService;
import com.fulan.api.course.service.CourseService;
import com.fulan.api.course.vo.CourseAndPlanVo;
import com.fulan.api.paper.service.ElPaperManageService;
import com.fulan.api.paper.vo.ElPaperVo;
import com.fulan.api.plan.domain.ClassPlan;
import com.fulan.api.plan.domain.CompulsoryCplan;
import com.fulan.api.plan.domain.PostDevelopment;
import com.fulan.api.plan.domain.StudyPlan;
import com.fulan.api.plan.service.ClassPlanService;
import com.fulan.api.plan.service.CompulsoryCplanService;
import com.fulan.api.plan.service.PostDevelopmentService;
import com.fulan.api.plan.service.StudyPlanService;
import com.fulan.api.plan.vo.ClassPlanFwVo;
import com.fulan.api.plan.vo.DevelopmentFwVo;
import com.fulan.api.plan.vo.PlanCourseDtoFMVo;
import com.fulan.api.plan.vo.PostDevelopmentVo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.domain.Level;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.api.system.service.LevelService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthStyle;

import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manage/development")
public class PostDevelopmentController {
    @Autowired
    PostDevelopmentService postDevelopmentService;
    
    @Autowired
	private ElPaperManageService elPaperManageService;
    
    @Autowired(required=false)
	private CourseService courseService;
    
    @Autowired(required=false)
	private CourseLecturerService courseLecturerService;
    
    @Autowired(required=false)
	private CompulsoryCplanService compulsoryCplanService;

	@Autowired(required=false)
	private StudyPlanService studyPlanService;
	
	@Autowired(required=false)
	private ClassPlanService classPlanService;
    
    @Autowired
    private LevelService levelService;
    
    @Autowired
    private AttachmentService attachmentService;

    /**
     * 加载等级表
     * @param model
     * @return
     */
    @RequiresPermissions("/manage/development/getDevelopment")
    @GetMapping("/getDevelopment")
    public String getDevelopment(Model model){
    	//List<PostDevelopment> list=postDevelopmentService.selectListDevelopment();
    	Long type=1l;
    	List<Level> levels =levelService.getLevelListDeve(type);
    	type=2l;
    	List<Level> list =levelService.getLevelListDeve(type);
    	type=3l;
    	List<Level> listOne =levelService.getLevelListDeve(type);
    	Level level =listOne.get(0);
    	model.addAttribute("lev", level);
    	model.addAttribute("list", list);
    	model.addAttribute("levels", levels);
        return "elearning/plan/development/development";
    }
    
    @RequiresPermissions("/manage/development/getDevelopment")
    @GetMapping("/addDevelopment")
    public String addDevelopment(Model model){
    	
        return "elearning/plan/development/development_edit";
    }
    
    @RequiresPermissions("/manage/development/getDevelopment")
    @PostMapping("save")
	@ResponseBody
	/*@RequestParam(name = "planAuthorityDtos",required=false) PlanAuthorityDto[] planAuthorityDtos,*/
	public Response<String>  saveDevelopment(DevelopmentFwVo DevelopmentFwVo, Long fileId){
		Long userId = SessionContextUtils.getLoginUserId();
		//classPlanService.insertClassPlan(classPlanFwVo,userId)
		if(null==userId){
			userId=1l;
		}
		
		return postDevelopmentService.insertDevelopmentOrupdate(DevelopmentFwVo,userId,fileId); 
	}

    /**
     * 根据职级id查询岗位计划
     * @param model
     * @param id
     * @return
     */
    @RequiresPermissions("/manage/development/getDevelopment")
    @RequestMapping("/selectDeveById")
    public String selectDeveById(Model model,@RequestParam(value="id",required=false) String id,@RequestParam(value="levelName",required=false) String levelName){
    	
    		if(StringUtils.isNotEmpty(id)){
    			Map<String,Object> map = postDevelopmentService.findOneDevelopmentFW(id);
    			if(null!=map.get("postDevelopment")){
    				model.addAttribute("postDevelopment", map.get("postDevelopment"));
    			}
    			List<PlanCourseDtoFMVo> onLineList = null;
    			if(null!=map.get("list")){
    				onLineList=(List<PlanCourseDtoFMVo>) map.get("list");
    			}
    			if(null!=map.get("list")){
    				model.addAttribute("classDevelopmentList", map.get("list"));
    			}
    			String markType = "stage";
    			if(null!=onLineList && onLineList.size()==1){
    				markType= "ordinary";
    				model.addAttribute("OrdinaryLineList", onLineList.get(0));
    			}
    			model.addAttribute("markType", markType);
    			if(null!=map.get("expiredAlarmList")){
    				model.addAttribute("expiredAlarmList", map.get("expiredAlarmList"));
    			}
    			model.addAttribute("id", id);
    		}
    		model.addAttribute("levelName", levelName);
    		 return "elearning/plan/development/development_edit";
    }
    
    
    
    /**
     * 根据职级id查询岗位计划
     * @param model
     * @param id
     * @return
     */
    @RequiresPermissions("/manage/development/getDevelopment")
    @RequestMapping("/saveDeveById")
    public String saveDeveById(Model model,@RequestParam(value="id",required=false) String id,@RequestParam(value="levelName",required=false) String levelName){
    	

		if(StringUtils.isNotEmpty(id)){
			Map<String,Object> map = postDevelopmentService.findOneDevelopmentFW(id);
			if(null!=map.get("postDevelopment")){
				model.addAttribute("postDevelopment", map.get("postDevelopment"));
				JSONObject jsonObject = JSONObject.fromObject(map.get("postDevelopment"));
                //岗位发展计划缩略图
                List<Attachment> attachments = attachmentService.findbyparms(CommenConstant.EL_THUMBNAIL,
                        Long.parseLong((String) jsonObject.get("id")));  
                if (attachments != null && attachments.size() > 0) {
                    model.addAttribute("smallImage",attachments.get(0));
                }
				
			}
			List<PlanCourseDtoFMVo> onLineList = null;
			if(null!=map.get("list")){
				onLineList=(List<PlanCourseDtoFMVo>) map.get("list");
			}
			if(null!=map.get("list")){
				model.addAttribute("classDevelopmentList", map.get("list"));
			}
			String markType = "stage";
			if(null!=onLineList && onLineList.size()==1){
				markType= "ordinary";
				model.addAttribute("OrdinaryLineList", onLineList.get(0));
			}
			model.addAttribute("markType", markType);
			if(null!=map.get("expiredAlarmList")){
				model.addAttribute("expiredAlarmList", map.get("expiredAlarmList"));
			}
			model.addAttribute("id", id);
			
		}
		
	 		model.addAttribute("levelName", levelName);
    		 return "elearning/plan/development/development_add";
    }
    
    
    /**
	 * 根据id查看试卷详情
	 */
    @RequiresPermissions("/manage/development/getDevelopment")
	@RequestMapping("/selectPaper")
	public ModelAndView selectPaper(Model model,
			  @RequestParam(value="id",required=false) String id){
		ModelAndView mv = new ModelAndView();
		List<ElPaperVo> data = elPaperManageService.seleByIdPublic(id);
		ElPaperVo elPaperVo=null;
		if(null!=data && data.size()>0){
			 elPaperVo=data.get(0);
		}
		
		mv.addObject("elPaperVo", elPaperVo);
		mv.setViewName("elearning/plan/development/paper_view");
		return mv;
	}
    
    
	/**
	 * 查看详情
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
    @RequiresPermissions("/manage/development/getDevelopment")
	@RequestMapping("/selectCourse")
	public ModelAndView selectCourseById(Model model, @RequestParam(value="id",required=false) String id){
		ModelAndView mv = new ModelAndView();
		Course courseOne = courseService.selectCourseOne(id);
		List<CourseAndPlanVo> list = courseService.findElClassPlanByCourseId(new Long(id));
		List<StudyPlan> studyPlanList1 = new ArrayList<>();//公开课集合
		List<StudyPlan> studyPlanList2 = new ArrayList<>();//学习计划集合
		List<ClassPlan> classPlanList = new ArrayList<>();//班级计划集合
		List<CompulsoryCplan> compulsoryCplanList = new ArrayList<>();//班级计划集合
		
		for (CourseAndPlanVo courseAndPlanVo : list) {
			if(courseAndPlanVo.getCourseType()==1) {//公开课和学习计划公用名字
				Response<StudyPlan> planDetailById = studyPlanService.PlanDetailById(courseAndPlanVo.getPlanId());
				StudyPlan studyPlan = planDetailById.getData();
				studyPlanList1.add(studyPlan);
				//System.err.println("进入公开课");
			}else if(courseAndPlanVo.getCourseType()==2) {//学习计划
				Response<StudyPlan> planDetailById = studyPlanService.PlanDetailById(courseAndPlanVo.getPlanId());
				StudyPlan studyPlan = planDetailById.getData();
				studyPlanList2.add(studyPlan);
				//System.err.println(planDetailById.getData());
			}else if(courseAndPlanVo.getCourseType()==3) {//岗位发展
				//模块接口还没写好，暂时这样输出
				System.err.println("进入岗位发展");
			}else if(courseAndPlanVo.getCourseType()==4) {//必修任务 
				Response<CompulsoryCplan> compulsoryCplanById = compulsoryCplanService.compulsoryCplanById(courseAndPlanVo.getPlanId());
				CompulsoryCplan compulsoryCplan = compulsoryCplanById.getData();
				compulsoryCplanList.add(compulsoryCplan);
				
			}else if(courseAndPlanVo.getCourseType()==5) {//班级计划
				Response<ClassPlan> classPlanById = classPlanService.classPlanById(courseAndPlanVo.getPlanId());
				ClassPlan classPlan = classPlanById.getData();
				classPlanList.add(classPlan);
				//System.err.println("进入班级计划");
			}
		}
		mv.addObject("studyPlanList1", studyPlanList1);
		mv.addObject("studyPlanList2", studyPlanList2);
		mv.addObject("classPlanList", classPlanList);
		mv.addObject("compulsoryCplanList", compulsoryCplanList);
		mv.addObject("course", courseOne);
		mv.setViewName("elearning/plan/development/Cours");
		return mv;
	}
	
	
	
}
