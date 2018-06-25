package com.fulan.application.controller.elearning.PublicClass;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fulan.api.course.domain.Course;
import com.fulan.api.course.domain.CourseLecturer;
import com.fulan.api.course.service.CourseService;
import com.fulan.api.plan.domain.PlanAuthority;
import com.fulan.api.plan.domain.PlanQuestion;
import com.fulan.api.plan.domain.PublicClass;
import com.fulan.api.plan.domain.StudyPlan;
import com.fulan.api.plan.service.ManagePlanAuthorityService;
import com.fulan.api.plan.service.ManagePlanQuestionService;
import com.fulan.api.plan.service.ManagePublicClassService;
import com.fulan.api.plan.service.StudyPlanService;
import com.fulan.api.plan.vo.CourseVo;
import com.fulan.api.plan.vo.PublicClassInsertVo;
import com.fulan.api.plan.vo.PublicClassVo;
import com.fulan.api.plan.vo.PublicCourseDto;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.service.AccountService;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.domain.Tag;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.api.system.service.TagService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * @Author: chenzuyu
 * @Date: 2018/1/31 20:39
 */
@Controller
public class PublicClassController {

	@Autowired
    private ManagePublicClassService managePublicClassService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudyPlanService studyPlanService;
	
	@Autowired
	private ManagePlanQuestionService managePlanQuestionService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	ManagePlanAuthorityService managePlanAuthorityService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
    private AttachmentService attachmentService;
	
	@RequestMapping("/manage/publicClassListByPage")
	public String viewMaterial(Model model,
			@RequestParam(value="name",required=false) String name,
            @RequestParam(value="type",required=false) String type,
            @RequestParam(value="submitter",required=false) String submitter,
            @RequestParam(value="state",required=false) String state,
            @RequestParam(value="isSticky",required=false) String isSticky,
            @RequestParam(value="childTagId",required=false) String childTagId,
            @RequestParam(value="tagId",required=false) String tagId,
            @RequestParam(value="startDate",required=false) String startDate,
            @RequestParam(value="endDate",required=false) String endDate,
            @RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize
				){
		
		Map <String,Object> map= new HashMap<>();
		map.put("name", name);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("tagId", tagId);
		map.put("childTagId", childTagId);
		map.put("isSticky", isSticky);
		map.put("state", state);
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);

		//标签一级分类   parentId 默认为0  catagory为2
		Response<List<Tag>> resTag=tagService.findByparentId((long) 0, 2);
		//标签二级分类   parentId 默认为tagId  catagory为2
		if(null!=tagId&&""!=tagId){
			Response<List<Tag>> resChildTagId=tagService.findByparentId(Long.parseLong(tagId),2);
			model.addAttribute("resChildTagId",resChildTagId.getData());
		}
		model.addAttribute("resTag",resTag.getData());
		PageInfo<PublicCourseDto> pageList=managePublicClassService.pageList(map);
		model.addAttribute("page",pageList);
		model.addAttribute("map",map);
		return "/elearning/public_class/public_class";
	}
	
	@RequestMapping("/manage/theBatchShelvesPublicClass")
	@ResponseBody
	public Response<Boolean> theBatchShelvesPublicClasss(Model model,
							@RequestParam(value="publicClassIds")String[] publicClassIds,@RequestParam(value="state")String state){
		Response<Boolean> res=managePublicClassService.theBatchShelvesPublicClasss(publicClassIds,state);
		return res;
	}
	
	@RequestMapping("/manage/toAddJsp")
	public String toAddJsp(Model model,
				@RequestParam(value="courseKeyWord",required=false)String courseKeyWord,
				@RequestParam(value="courseCreateUser",required=false)String courseCreateUser,
				@RequestParam(value="courseType",required=false)String courseType,
				@RequestParam(value="courseGroupId",required=false)String courseGroupId,
				@RequestParam(value="courseTagId",required=false)String courseTagId,
				@RequestParam(value="courseUploadTimeBegin",required=false)String courseUploadTimeBegin,
				@RequestParam(value="courseUploadTimeEnd",required=false)String courseUploadTimeEnd,
				@RequestParam(value="planKeyWord",required=false)String planKeyWord,
				@RequestParam(value="planCode",required=false)String planCode,
				@RequestParam(value="planTagId",required=false)String planTagId,
				@RequestParam(value="planUploadTimeBegin",required=false)String planUploadTimeBegin,
				@RequestParam(value="planUploadTimeEnd",required=false)String planUploadTimeEnd
					){
		Map<String,Object> map=new HashMap<>();
		map.put("courseKeyWord",courseKeyWord);
		map.put("courseCreateUser",courseCreateUser);
		map.put("courseType",courseType);
		map.put("courseGroupId",courseGroupId);
		map.put("courseTagId",courseTagId);
		map.put("courseUploadTimeBegin",courseUploadTimeBegin);
		map.put("courseUploadTimeEnd",courseUploadTimeEnd);
		map.put("planKeyWord",planKeyWord);
		map.put("planCode",planCode);
		map.put("planTagId",planTagId);
		map.put("planUploadTimeBegin",planUploadTimeBegin);
		map.put("planUploadTimeEnd",planUploadTimeEnd);
		List<Course> courseList=courseService.courseManageByPublic(courseKeyWord, courseType, courseCreateUser, "1", "1", courseGroupId, courseTagId, courseUploadTimeBegin, courseUploadTimeEnd);
		List<StudyPlan> planList=studyPlanService.planList(planKeyWord, planCode, planTagId, planUploadTimeBegin, planUploadTimeEnd);
		model.addAttribute("courseList", courseList);
		model.addAttribute("planList", planList);
		model.addAttribute("map",map);
		return "/elearning/public_class/add_publicClass";
	}
	
	
	@RequestMapping("/manage/toAddJspSecond")
	public String toAddJspSecond(Model model,
			@RequestParam("planOrCourseId")String[] planOrCourseId,
			@RequestParam("type")String type){

		List<String> planOrCourseIds = Arrays.asList(planOrCourseId);
		
		model.addAttribute("planOrCourseIds", planOrCourseIds);
		model.addAttribute("type", type);
		return "/elearning/public_class/add_publicClassSecond";
	}
	
	
	
	
	@RequestMapping("/manage/questionListByPage")
	public String questionListByPage(Model model,
			@RequestParam(value="courseId",required=false)String id,//公开课id
			@RequestParam(value="userName",required=false)String userName,//用户名
			@RequestParam(value="comment",required=false)String comment,//评论内容
			@RequestParam(value="isOpen",required=false)String isOpen,//是否开启
			@RequestParam(value="beginTime",required=false)String beginTime,//开始时间
			@RequestParam(value="endTime",required=false)String endTime,//结束时间
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		Map<String,Object> map=new HashMap<>();
		map.put("courseId",id);
		map.put("userName", userName);
		map.put("comment", comment);
		map.put("isOpen", isOpen);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		PublicClassVo publicClassVo =managePublicClassService.getPublicClassInfo(id);
		//公开课查询讲师姓名
		String lecturerName="";
		if(publicClassVo!=null){
			List<CourseVo> courseList = publicClassVo.getCourse();
			if(courseList!=null&&!courseList.isEmpty()){
				Set<String>lecturerNameSet = new HashSet<String>();
				for(CourseVo courseVo:courseList){
					Long courseId = courseVo.getId();
					//通过course-lecturer中间表查询讲师list
					if(courseId!=null){
						List<CourseLecturer> lecturerList = courseService.findCourseLecturer(courseId, null);
						for(CourseLecturer courseLecturer:lecturerList){
							Long lecturerId = courseLecturer.getLecturerId();
							Response<Account> accountResponse = accountService.findById(lecturerId);
							Account account = accountResponse.getData();
							if(account!=null){
								//去重
								lecturerNameSet.add(account.getAccountName());
							}
						}
					}
				}
				Iterator<String> iterator = lecturerNameSet.iterator();
				while(iterator.hasNext()){
					lecturerName+=(iterator.next()+" ");
				}
			}
		}
		//线下活动查询讲师姓名
		if(null==publicClassVo){
			Long lecturerId = courseService.checkCourseVoInfo(id).getLecturer();
			if(lecturerId!=null){
				Response<Account> accountResponse = accountService.findById(lecturerId);
				Account account = accountResponse.getData();
				if(account!=null){
					lecturerName=account.getAccountName();
				}
			}
		}
		model.addAttribute("lecturerName",lecturerName);
		
		PageInfo<Map<String,Object>> page=managePublicClassService.listQuestionByPage(map);
		List<Map<String,Object>> list=page.getRecords();
		for (Map<String, Object> map2 : list) {
			if(""!=map2.get("gmtCreate")||null!=map2.get("gmtCreate")){
				Long ss=(Long) map2.get("gmtCreate");
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date date = new Date(ss);
				String str = sdf.format(date);
				map2.put("gmtCreate", str);
			}
			
		}
		model.addAttribute("publicClassVo",publicClassVo);
		model.addAttribute("page",page);
		model.addAttribute("map",map);
		return "/elearning/public_class/publicClass_question";
	}
	
	
	@RequestMapping("/manage/commentListByPage")
	public String commentListByPage(Model model,
			@RequestParam(value="courseId",required=false)String id,//公开课id
			@RequestParam(value="userName",required=false)String userName,//用户名
			@RequestParam(value="comment",required=false)String comment,//评论内容
			@RequestParam(value="isOpen",required=false)String isOpen,//是否开启
			@RequestParam(value="beginTime",required=false)String beginTime,//开始时间
			@RequestParam(value="endTime",required=false)String endTime,//结束时间
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize
			){
		Map<String,Object> map=new HashMap<>();
		map.put("courseId",id);
		map.put("userName", userName);
		map.put("comment", comment);
		map.put("isOpen", isOpen);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		PublicClassVo publicClassVo =managePublicClassService.getPublicClassInfo(id);
		//公开课查询讲师姓名
		String lecturerName="";
		if(publicClassVo!=null){
			List<CourseVo> courseList = publicClassVo.getCourse();
			if(courseList!=null&&!courseList.isEmpty()){
				Set<String>lecturerNameSet = new HashSet<String>();
				for(CourseVo courseVo:courseList){
					Long courseId = courseVo.getId();
					//通过course-lecturer中间表查询讲师list
					if(courseId!=null){
						List<CourseLecturer> lecturerList = courseService.findCourseLecturer(courseId, null);
						for(CourseLecturer courseLecturer:lecturerList){
							Long lecturerId = courseLecturer.getLecturerId();
							Response<Account> accountResponse = accountService.findById(lecturerId);
							Account account = accountResponse.getData();
							if(account!=null){
								//去重
								lecturerNameSet.add(account.getAccountName());
							}
						}
					}
				}
				Iterator<String> iterator = lecturerNameSet.iterator();
				while(iterator.hasNext()){
					lecturerName+=(iterator.next()+" ");
				}
			}
		}
		//线下活动查询讲师姓名
		if(null==publicClassVo){
			Long lecturerId = courseService.checkCourseVoInfo(id).getLecturer();
			if(lecturerId!=null){
				Response<Account> accountResponse = accountService.findById(lecturerId);
				Account account = accountResponse.getData();
				if(account!=null){
					lecturerName=account.getAccountName();
				}
			}
		}
		model.addAttribute("lecturerName",lecturerName);
		PageInfo<Map<String,Object>> page=managePublicClassService.listCommentByPage(map);
		List<Map<String,Object>> list=page.getRecords();
		for (Map<String, Object> map2 : list) {
			if(""!=map2.get("gmtCreate")||null!=map2.get("gmtCreate")){
				Long ss=(Long) map2.get("gmtCreate");
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date date = new Date(ss);
				String str = sdf.format(date);
				map2.put("gmtCreate", str);
			}
			
		}
		
		model.addAttribute("publicClassVo",publicClassVo);
		model.addAttribute("page",page);
		model.addAttribute("map",map);
		return "/elearning/public_class/publicClass_comment";
	}
	
	@RequestMapping("/manage/theBatchQuestionOrComment")
	@ResponseBody
	public Response<Boolean> theBatchQuestionOrComment(Model model,
							@RequestParam(value="id")String[] ids,
							@RequestParam(value="type")String type,
							@RequestParam(value="state")String state){
		Response<Boolean> res=managePublicClassService.theBatchQuestionOrComment(ids,type,state);
		return res;
	}
	
	@RequestMapping("/manage/toUpdateQuestionJsp")
	public String toUpdateQuestionJsp(Model model,
			@RequestParam(value="publicClassId")String publicClassId,
			@RequestParam(value="questionId")String questionId){
		PublicClassVo publicClassVo =managePublicClassService.getPublicClassInfo(publicClassId);
		//根据ID查询question
		PlanQuestion planQuestion=managePlanQuestionService.selectQuestionById(questionId);
		model.addAttribute("publicClassVo",publicClassVo);
		model.addAttribute("planQuestion",planQuestion);
		return "/elearning/public_class/update_question";
	}
	
	@RequestMapping("/manage/updateQuestion")
	@ResponseBody
    public Response<Boolean> updateQuestion(Model model,PlanQuestion planQuestion){
		Long userId=SessionContextUtils.getLoginUserId();
		String userName=SessionContextUtils.getLoginName();
		planQuestion.setAnswerUser(userId);
		planQuestion.setAnswerUserName(userName);
		planQuestion.setAnswerTime(new Date());
		planQuestion.setGmtModified(new Date());
		planQuestion.setModifyUser(userId);
		Response<Boolean> res=managePlanQuestionService.updateQuestion(planQuestion);
		return res;
	}
	
	
	
	@RequestMapping("/manage/submitPublicClass")
	@ResponseBody
    public Response<String> submitPublicClass(PublicClassInsertVo publicClassInsertVo,Long fileId){
		Response<String> res = new Response<>();
		Long id= publicClassInsertVo.getPublicClass().getId();
		Long userId=SessionContextUtils.getLoginUserId();
		Date date = new Date();
		PublicClass publicClass = publicClassInsertVo.getPublicClass();
		if(null==publicClass.getId()){
			publicClass.setCreateUser(userId);
			publicClass.setGmtCreate(date);
		}else{
			publicClass.setModifyUser(userId);
			publicClass.setGmtModified(date);
		}
		publicClassInsertVo.setPublicClass(publicClass);
		if(null!=id){
			String result=managePublicClassService.updatePubClass(publicClassInsertVo,fileId);
			res.setMsg(result);
			return res;
		}else{
			publicClassInsertVo.setPlanOrCourseId(publicClassInsertVo.getPlanOrCourseId());
			publicClassInsertVo.setType(publicClassInsertVo.getType());
			String result=managePublicClassService.submitPublicClass(publicClassInsertVo,fileId);
			res.setMsg(result);
			return res;
		}
	}
	
	
	@RequestMapping("/manage/updatePublicClass")
	@ResponseBody
    public String updatePublicClass(Model model,PublicClass publicClass){
		String res=managePublicClassService.updatePublicClass(publicClass);
		return res;
	}
	
	
	@RequestMapping("/manage/toUpdatePublicClass")
	public String toUpdatePublicClass(Model model,
			@RequestParam(value="id")String id){
		PublicClassVo publicClassVo =managePublicClassService.getPublicClassInfo(id);
		//标签一级分类   parentId 默认为0  catagory为2
		Response<List<Tag>> resTag=tagService.findByparentId((long) 0, 2);
		//标签二级分类   parentId 默认为第一个一级标签的id  catagory为2
				Long tagId=publicClassVo.getTagId();
				if(null!=tagId){
					Response<List<Tag>> resChildTagId=tagService.findByparentId(tagId,2);
					model.addAttribute("resChildTagId",resChildTagId.getData());
				}
		model.addAttribute("resTag",resTag.getData());
		
		List<PlanAuthority> planAuthoritys =managePlanAuthorityService.selectAuthorityByCourseId(id);
		model.addAttribute("planAuthoritys",planAuthoritys);
		//根据ID查询question
		model.addAttribute("publicClassVo",publicClassVo);
		
		//班级计划缩略图
        List<Attachment> attachments = attachmentService.findbyparms(CommenConstant.EL_THUMBNAIL, Long.parseLong(id));  
        if (attachments != null && attachments.size() > 0) {
            model.addAttribute("smallImage",attachments.get(0));
        }
		return "/elearning/public_class/update_publicClass";
	}
	
	
	//置顶
	@GetMapping("/manage/isSticky")
	public ModelAndView selectLineOne(String id,String isSticky,String stickySeq){
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.addObject("isSticky", isSticky);
		mv.addObject("stickySeq", stickySeq);
		mv.setViewName("elearning/public_class/is_sticky");
		return mv ;
	}
	
	@PostMapping("/manage/saveIsSticky")
    @ResponseBody
    public String saveIsSticky(Model model ,String id,String isSticky,String stickySeq){
		PublicClass publicClass = managePublicClassService.selectByClassId(id);
		publicClass.setIsSticky(Integer.parseInt(isSticky));
		publicClass.setStickySeq(Integer.parseInt(stickySeq));
		return managePublicClassService.updatePublicClass(publicClass);
    }
	
	
	@PostMapping("/manage/saveOtherIsSticky")
    @ResponseBody
    public String saveIsSticky(String id){
		PublicClass publicClass = managePublicClassService.selectByClassId(id);
		publicClass.setIsSticky(0);
		publicClass.setStickySeq(0);
		return managePublicClassService.updatePublicClass(publicClass);
    }
	
}
