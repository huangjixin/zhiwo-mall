package com.fulan.application.controller.elearning;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.fulan.api.course.domain.Course;
import com.fulan.api.course.domain.CourseLecturer;
import com.fulan.api.course.domain.CourseMaterial;
import com.fulan.api.course.domain.CourseShare;
import com.fulan.api.course.service.CourseLecturerService;
import com.fulan.api.course.service.CourseService;
import com.fulan.api.course.vo.CourseAndPlanVo;
import com.fulan.api.course.vo.CourseCMSVo;
import com.fulan.api.material.domain.Material;
import com.fulan.api.material.domain.MaterialShare;
import com.fulan.api.material.service.MaterialService;
import com.fulan.api.plan.domain.ClassPlan;
import com.fulan.api.plan.domain.CompulsoryCplan;
import com.fulan.api.plan.domain.StudyPlan;
import com.fulan.api.plan.service.ClassPlanService;
import com.fulan.api.plan.service.CompulsoryCplanService;
import com.fulan.api.plan.service.StudyPlanService;
import com.fulan.api.plan.vo.CompulsoryCplanVo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.domain.UserGroup;
import com.fulan.api.security.service.AccountService;
import com.fulan.api.security.service.GroupService;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.domain.Tag;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.api.system.service.TagService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.json.JsonUtil;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;

import net.sf.json.JSONObject;


/**
 * @Author: zhouyun
 * @Date: 2018/1/30 
 */
@Controller
public class CourseController {
	
	@Autowired(required=false)
	private CourseService courseService;
	
	@Autowired(required=false)
	private AccountService accountService ;
	
	@Autowired(required=false)
	private CourseLecturerService courseLecturerService;
	
	@Autowired(required=false)
	private StudyPlanService studyPlanService;
	
	@Autowired(required=false)
	private ClassPlanService classPlanService;
	
	@Autowired(required=false)
	private CompulsoryCplanService compulsoryCplanService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private MaterialService materialService;
	
	/**
	 * 分享页面跳转
	 */
	@RequestMapping("/manage/privateShareCourses")
	public String privateShareCourses(Model model,
			@RequestParam(value="courseIds")String[] courseIds,
			@RequestParam(value="courseNames")String[] courseNames){
		List <Course> courses=new ArrayList<Course>();
		
		//用户组
        List <UserGroup> groupList = groupService.listAllGroups();
        model.addAttribute("groupList", groupList);
		
        if (courseIds.length > 0) {
            String courseIdStr = "";
            for(int i=0;i<courseIds.length;i++){
                Course course=new Course();
                course.setId(Long.parseLong(courseIds[i]));
                course.setName(courseNames[i]);
                courses.add(course);
                
                courseIdStr += courseIds[i];
                if (i != courseIds.length -1) {
                    courseIdStr += ",";
                }
            }
            
            //查询已经关联的用户组，页面回显
            List<CourseShare> csList = courseService.listCourseShareByCourseId(courseIdStr);
            if (csList != null && csList.size() > 0) {
                String groupIds = "";
                for (int j = 0; j < csList.size(); j++) {
                    groupIds += csList.get(j).getGroupId();
                    if (j != csList.size() - 1) {
                        groupIds += ",";
                    }
                }
                model.addAttribute("groupIds", groupIds);
            }
        }
		model.addAttribute("courses", courses);
		return "/elearning/course/private_share";
	}
	
	@RequestMapping(value="/manage/doShareCourses", method = RequestMethod.POST)
	public @ResponseBody Response<Boolean> doShareCourses(Model model,
				@RequestParam(value="groupIds[]")String[] groupIds, 
				@RequestParam(value="courseIds[]")String[] courseIds){
		Response<Boolean> doShareCourses = courseService.doShareCourses(groupIds, courseIds);
		
		return doShareCourses;
	} 
	
	/**
	 * 修改页面跳转
	 */
	@RequestMapping("/manage/updateCourse")
	public String updateCourse(Model model, HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		Course courseOne = courseService.selectCourseOne(id);
		//查询该课程所有讲师的id
		List<CourseLecturer> courseLecturerList = courseLecturerService.findCourseLecturer(new Long(id),null );
		List<Long> lecturerId = new ArrayList<Long>();
		for (CourseLecturer courseLecturer2 : courseLecturerList) {
			Long lecturerId2 = courseLecturer2.getLecturerId();
			lecturerId.add(lecturerId2);
		}
		if(lecturerId!=null && lecturerId.size()>0 ) {
			//根据讲师id查询讲师
			List<Account> findByAccountId = accountService.findByAccountId(lecturerId);
			String accountIds="";
			String accountNames="";
			for (Account account : findByAccountId) {
				accountIds += account.getId()+",";
				accountNames += account.getAccountName()+",";
			}
			model.addAttribute("accountId", accountIds);
			model.addAttribute("accountName", accountNames);
		}
		model.addAttribute("course", courseOne);
		
		//课程附件部分
		//课程缩略图
		List<Attachment> attachments1 = attachmentService.findbyparms(CommenConstant.EL_THUMBNAIL, courseOne.getId());  
        if (attachments1 != null && attachments1.size() > 0) {
            model.addAttribute("courseImg",attachments1.get(0));
        }
        if (courseOne.getIsOnline() == 1) {
            //课程课件 （线上课程才有课件）
            List<Attachment> attachments2 = attachmentService.findbyparms(CommenConstant.EL_LESSON_COURSEWARE, courseOne.getId());  
            if (attachments2 != null && attachments2.size() > 0) {
                model.addAttribute("courseWare",attachments2.get(0));
            }
        }
        //课程附件资料
        List<Attachment> attachments3 = attachmentService.findbyparms(CommenConstant.EL_LESSON_ATTACHMENT, courseOne.getId());  
        model.addAttribute("courseAttachement",attachments3);
        
        // 标签 
        List<Tag> tagList = tagService.listByAccountId(SessionContextUtils.getCurrentUserId());
        model.addAttribute("tagList", tagList);
        
        //查询关联的资料
        List<Material> materialList = materialService.listMaterialByCourseId(courseOne.getId());
        if (materialList != null && materialList.size() > 0) {
            String materialIds = "";
            String materialNames = "";
            for (int i = 0; i < materialList.size(); i ++) {
                materialIds += materialList.get(i).getId();
                materialNames += materialList.get(i).getName();
                if (i != materialList.size() - 1) {
                    materialIds += ",";
                    materialNames += ",";
                }
            }
            model.addAttribute("materialIds", materialIds);
            model.addAttribute("materialNames", materialNames);
        }
		return "/elearning/course/private_edit";
	}
	
	/**
	 * 获取数据
	 */
	@RequestMapping("/manage/findByRoleId")
	public String findByRoleId(Model model, HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("courseId");
		Course courseOne = courseService.selectCourseOne(id);
		if(null!=id&&""!=id){
			List<CourseLecturer> courseLecturerList=courseLecturerService.findCourseLecturer(Long.parseLong(id),null);
			model.addAttribute("courseLecturerList",courseLecturerList);
		}
		//暂时设置为955997244559982592 表示讲师
		Long roleId = new Long("955997244559982592");
		List<Account> accountList = accountService.findByRoleId(roleId);
		model.addAttribute("course", courseOne);
		model.addAttribute("accountList", accountList);
		return "/elearning/course/lecturer_list";
	}
	
	
	@RequestMapping("/manage/findByOtherRoleId")
	@ResponseBody
	public Response<List<Account>> findByOtherRoleId(HttpServletRequest request, HttpServletResponse response){
		String accountName = request.getParameter("accountName");
		String mobile = request.getParameter("mobile");
		//暂时设置为955997244559982592 表示讲师
		String roleId ="955997244559982592";
		List<Account> accountList = accountService.findByOtherRoleId(roleId, accountName, mobile);
		Response<List<Account>> res = new Response<List<Account>>();
		res.setData(accountList);
		return res;
	}
	
	/**
	 * 添加页面跳转
	 */
	@RequestMapping("/manage/addCourse")
	public String addCourse(Model model){
		 // 标签 
        List<Tag> tagList = tagService.listByAccountId(SessionContextUtils.getCurrentUserId());
        model.addAttribute("tagList", tagList);
		return "/elearning/course/private_add";
	}
	/**
	 * 新增/修改
	 */
	@RequestMapping(value="/manage/editCourse",method=RequestMethod.POST)
	@ResponseBody
	public Response<String> editCourse(Model model, Course course, HttpServletRequest request, HttpServletResponse response){
		String lecturerId = request.getParameter("lecturerId");
		String materialId = request.getParameter("materialId");
		
		//课程缩略图附件Id
		String fileId1 = request.getParameter("fileId1");
		//课程课件Id
		String fileId2 = request.getParameter("fileId2");
		//课程附件Id  多个附件 (课程资料附件)
		String muchfileIds = request.getParameter("fileId3");
		
		String fileIds = "";     //只有一个附件 （课程缩略图和课件）
		if (fileId1 != null && !"".equals(fileId1)) {
		    fileIds = fileIds + fileId1;
		}
		if (fileId2 != null && !"".equals(fileId2) ) {
		    if (fileIds != "") {
		        fileIds = fileIds + "," + fileId2;
		    } else {
		        fileIds = fileIds + fileId2;
		    }
		}
		
		System.out.println(fileIds);
		//用于关联的用户组
		Long userId = SessionContextUtils.getLoginUserId();
        UserGroup ug = groupService.selectByAccountId(userId);
        if (ug != null) {
            course.setGroupId(ug.getId());
        }
		course.setCreateUser(userId);
		course.setModifyUser(userId);
		Response<String> resultData = courseService.handCourses(course,lecturerId,materialId, fileIds,muchfileIds);
		return resultData;
		/*if(null!=materialId){
		}else{
			Response<String> resultData = courseService.handCourses(course,lecturerId, fileIds,muchfileIds);
			return resultData;
		}*/
		
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping("/manage/deleteBatchCourse")
	@ResponseBody
	public Response<List<String>> deleteBatchCourse(Model model, HttpServletRequest request, HttpServletResponse response){
		String jsonstr = request.getParameter("jsonstr");//前台传的json字符串
		JSONObject jsonObject = JSONObject.fromObject(jsonstr);;//json字符串转化为json对象
		//遍历json的键值
		Set<String> keySet = jsonObject.keySet();
		Iterator<String> iterator = keySet.iterator();
		
		List<String> courseId= new ArrayList<String>();
		String next =null;
		Object object = new Object() ;
		//将json值放入集合
		while (iterator.hasNext()) {
			next= iterator.next();
			object= jsonObject.getString(next);
			courseId.add(String.valueOf(object));
		}
		String[] courseIds = new String[courseId.size()];
		for (int i = 0; i < courseId.size(); i++) {
			courseIds[i]=courseId.get(i);
		}
		
		Response<List<String>> deleteResult = courseService.deleteBatchCourse(courseIds);
		
		return deleteResult;
	}
	/**
	 * 查看详情
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/manage/selectCourseById")
	public String selectCourseById(Model model, HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		Course courseOne = courseService.selectCourseOne(id);
		String  str ="";
		List<Material> mList = courseService.getMaterialList(id);
		if(null != mList){
			for(Material material : mList ){
				if(null!=material){
					String name = material.getName();
					if(null!=name || !("".equals(name))){
						str+=name+",";
					}
				}
			}	
		}
		if(""!=str){
			str=str.substring(0,str.length()-1);
		}
		model.addAttribute("str",str.toString());
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
		
		        //课程附件部分
				//课程缩略图
		     List<Attachment> attachments1 = attachmentService.findbyparms(CommenConstant.ER_HEAD_PHOTO, courseOne.getId());  
		     if (attachments1 != null && attachments1.size() > 0) {
		            model.addAttribute("courseImg",attachments1.get(0));
		        }
		    if (courseOne.getIsOnline() == 1) {
            //课程课件 （线上课程才有课件）
            List<Attachment> attachments2 = attachmentService.findbyparms(CommenConstant.EL_LESSON_COURSEWARE, courseOne.getId());  
            if (attachments2 != null && attachments2.size() > 0) {
                model.addAttribute("courseWareName",attachments2.get(0));
            }
        }
		model.addAttribute("studyPlanList1", studyPlanList1);//公开课
		model.addAttribute("studyPlanList2", studyPlanList2);//学习计划
		model.addAttribute("classPlanList", classPlanList);//班级计划
		model.addAttribute("compulsoryCplanList", compulsoryCplanList);//必修任务 
		model.addAttribute("course", courseOne);
		return "/elearning/course/public_view";
	}
	
	/**
	 * 管理员公共库/讲师私人库1 列表分页显示 
	 * 讲师私人库的时候需要传submitter
	 */
	@RequestMapping(value="/manage/publicCourseListByPage",method=RequestMethod.GET)
	public String publicCourseListByPage(Model model,
			@RequestParam(value="keyWord",required=false) String keyWord,//课程关键字
			@RequestParam(value="type",required=false) String type,//类型
			@RequestParam(value="submitter",required=false) String submitter,//提交人
			@RequestParam(value="pubType",required=false) String pubType,//管理员公共库/讲师公共库 1，讲师私人库2， 
			@RequestParam(value="isOnline",required=false) String isOnline,//线上线下
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="groupId",required=false)  String groupId,//用户组/角色编号
			@RequestParam(value="tagId",required=false)   String tagId,//用户组/角色下的标签编号
			@RequestParam(value="uploadTimeBegin",required=false) String uploadTimeBegin,//上传时间
			@RequestParam(value="uploadTimeEnd",required=false) String uploadTimeEnd,//结束时间
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		pubType = "1";
		if("undefined".equals(type)){
			type="";
    	}
		if("undefined".equals(submitter)){
			submitter="";
    	}
		if("undefined".equals(id)){
			id="";
    	}
		if("undefined".equals(groupId)){
			groupId="";
    	}
		PageInfo<CourseCMSVo> pageInfo = courseService.courseListByPage(keyWord, type, submitter, pubType, isOnline, groupId, tagId, uploadTimeBegin, uploadTimeEnd, pageNo, pageSize);
		pageInfo = getDate(pageInfo);
		// 标签 
        List<Tag> tagList = tagService.listByAccountId(SessionContextUtils.getCurrentUserId());
        model.addAttribute("tagList", tagList);
		model.addAttribute("page", pageInfo);
		model.addAttribute("search_keyWord",keyWord);
		model.addAttribute("search_type",type);
		model.addAttribute("search_submitter",submitter);
		model.addAttribute("search_pubType",pubType);
		model.addAttribute("search_isOnline",isOnline);
		model.addAttribute("search_id",id);
		model.addAttribute("search_groupId",groupId);
		model.addAttribute("search_tagId",tagId);
		model.addAttribute("search_uploadTimeBegin",uploadTimeBegin);
		model.addAttribute("search_uploadTimeEnd",uploadTimeEnd);
		return "/elearning/course/public_course";
	}
	/**
	 * 获取分页内容
	 * @param keyWord
	 * @param type
	 * @param submitter
	 * @param pubType
	 * @param isOnline
	 * @param id
	 * @param groupId
	 * @param tagId
	 * @param uploadTimeBegin
	 * @param uploadTimeEnd
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/manage/elearning/listByPageAjaxOther")
	@ResponseBody
	public Response<PageInfo<CourseCMSVo>> listByPageAjaxOther(
			String keyWord,//课程关键字
			String type,//类型
			String submitter,//提交人
			String pubType,//管理员公共库/讲师公共库 1，讲师私人库2， 
			String isOnline,//线上线下
			String id,
		    String groupId,//用户组/角色编号
			String tagId,//用户组/角色下的标签编号
			String uploadTimeBegin,//上传时间
			String uploadTimeEnd,//结束时间
			String pageNo,
            String pageSize){
		pageNo = StringUtil.isEmpty(pageNo)?"1":pageNo;
		pageSize =  StringUtil.isEmpty(pageNo)?"10":pageSize;
		PageInfo<CourseCMSVo> courseList = courseService.courseListByPage(keyWord, type, submitter, pubType, isOnline, groupId, tagId, uploadTimeBegin, uploadTimeEnd,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		courseList = getDate(courseList);
		Response<PageInfo<CourseCMSVo>> response = new Response<>(Response.SUCCESS, null);
		response.setData(courseList);
		return response;
		
	}
	
	
	
	
	/**
	 * 讲师公共库2 列表分页显示 
	 * 只能看到别人分享给submitter组的课程
	 */
	@RequestMapping(value="/manage/publicCourseListByPageByGroupId",method=RequestMethod.GET)
	public String publicCourseListByPageByGroupId(Model model,
			@RequestParam(value="keyWord",required=false) String keyWord,//课程关键字
			@RequestParam(value="type",required=false) String type,//类型
			@RequestParam(value="submitter",required=false) String submitter,//提交人
			@RequestParam(value="pubType",required=false) String pubType,//管理员公共库/讲师私人库 1，讲师公共库2， 
			@RequestParam(value="isOnline",required=false) String isOnline,//线上线下
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="groupId",required=false)  String groupId,//用户组/角色编号
			@RequestParam(value="tagId",required=false)   String tagId,//用户组/角色下的标签编号
			@RequestParam(value="uploadTimeBegin",required=false) String uploadTimeBegin,//上传时间
			@RequestParam(value="uploadTimeEnd",required=false) String uploadTimeEnd,//结束时间
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		pubType = "2";
		/*submitter="0";*/
		PageInfo<CourseCMSVo> pageInfo = courseService.courseListByPage(keyWord, type, submitter, pubType, isOnline, groupId, tagId, uploadTimeBegin, uploadTimeEnd, pageNo, pageSize);
		pageInfo = getDate(pageInfo);
		
		// 标签 
//        List<Tag> tagList = tagService.listByAccountId(SessionContextUtils.getCurrentUserId());
//        model.addAttribute("tagList", tagList);
		model.addAttribute("page", pageInfo);
		model.addAttribute("search_keyWord",keyWord);
		model.addAttribute("search_type",type);
		model.addAttribute("search_submitter",submitter);
		model.addAttribute("search_pubType",pubType);
		model.addAttribute("search_isOnline",isOnline);
		model.addAttribute("search_id",id);
//		model.addAttribute("search_groupId",groupId);
//		model.addAttribute("search_tagId",tagId);
		model.addAttribute("search_uploadTimeBegin",uploadTimeBegin);
		model.addAttribute("search_uploadTimeEnd",uploadTimeEnd);
		return "/elearning/course/public_course";
	}
	/**
	 * 管理员公共库/讲师私人库1 列表分页显示 
	 */
	@RequestMapping(value="/manage/privateCourseListByPage",method=RequestMethod.GET)
	public String privateCourseListByPage(Model model,
			@RequestParam(value="keyWord",required=false) String keyWord,//课程关键字
			@RequestParam(value="type",required=false) String type,//课件类型
			@RequestParam(value="pubType",required=false) String pubType,//讲师公共库 1，讲师公共库2， 
			@RequestParam(value="isOnline",required=false) String isOnline,//线上线下
			@RequestParam(value="uploadTimeBegin",required=false) String uploadTimeBegin,//上传时间
			@RequestParam(value="uploadTimeEnd",required=false) String uploadTimeEnd,//结束时间
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
	    if (pubType == null || "".equals(pubType)) {
            pubType = "1";
        }
      //当前登录用户
        Long loginUser = SessionContextUtils.getLoginUserId();  
        
        //当前用户所属用户组
        UserGroup ug = groupService.selectByAccountId(SessionContextUtils.getLoginUserId());
        Long groupId = null;
        if (ug != null) {
            groupId = ug.getId(); //管理员不关联用户组。  如果是管理员 只能在公共资料库中查看所有资料;如果是讲师，在私人资料库中查看自己新建的资料，在公共资料库中查看分享的资料
            
        }
        
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("keyWord", keyWord);
        paramMap.put("loginUser", loginUser);
        paramMap.put("groupId", groupId);
        paramMap.put("type", type);
        paramMap.put("pubType", pubType);
        paramMap.put("uploadTimeBegin", uploadTimeBegin);
        paramMap.put("uploadTimeEnd", uploadTimeEnd);
        paramMap.put("isOnline", isOnline);
        PageInfo<CourseCMSVo> pageInfo = courseService.listCourseByPage(paramMap,pageNo, pageSize);
		//PageInfo<CourseCMSVo> pageInfo = courseService.courseListByPage(keyWord, type, submitter, pubType, isOnline, groupId, tagId, uploadTimeBegin, uploadTimeEnd, pageNo, pageSize);
		//pageInfo = getDate(pageInfo);
		
		model.addAttribute("page", pageInfo);
		model.addAttribute("search_keyWord",keyWord);
		model.addAttribute("search_type",type);
		model.addAttribute("search_pubType",pubType);
		model.addAttribute("search_isOnline",isOnline);
		model.addAttribute("search_uploadTimeBegin",uploadTimeBegin);
		model.addAttribute("search_uploadTimeEnd",uploadTimeEnd);
		return "/elearning/course/private_course";
	}
	
	@GetMapping("/manage/privateCourse/listByPageAjax")
	@ResponseBody
	public Response<PageInfo<CourseCMSVo>> listByPageAjax(@RequestParam(value="keyWord",required=false) String keyWord,//课程关键字
			@RequestParam(value="type",required=false) String type,//类型
			@RequestParam(value="submitter",required=false) String submitter,//提交人
			@RequestParam(value="pubType",required=false) String pubType,//管理员公共库/讲师公共库 1，讲师私人库2， 
			@RequestParam(value="isOnline",required=false) String isOnline,//线上线下
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="groupId",required=false)  String groupId,//用户组/角色编号
			@RequestParam(value="tagId",required=false)   String tagId,//用户组/角色下的标签编号
			@RequestParam(value="uploadTimeBegin",required=false) String uploadTimeBegin,//上传时间
			@RequestParam(value="uploadTimeEnd",required=false) String uploadTimeEnd,//结束时间
			 String pageNo,
             String pageSize){
		     pubType = "1";
		     submitter=SessionContextUtils.getLoginUserId().toString();
		     pageNo = StringUtil.isEmpty(pageNo)?"1":pageNo;
		     pageSize =  StringUtil.isEmpty(pageNo)?"10":pageSize;
		     PageInfo<CourseCMSVo> courseList = courseService.courseListByPage(keyWord, type, submitter, pubType, isOnline, groupId, tagId, uploadTimeBegin, uploadTimeEnd, Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		     courseList = getDate(courseList);
		     Response<PageInfo<CourseCMSVo>> response = new Response<>(Response.SUCCESS, null);		
		     response.setData(courseList);
		     return response;
		
	}
	
	//页面展示需要
	private PageInfo<CourseCMSVo> getDate(PageInfo<CourseCMSVo> pageInfo) {
	    
	    List<CourseCMSVo> ccvList = pageInfo.getRecords();
	    if (ccvList != null && ccvList.size() > 0) {
	        for (CourseCMSVo course : ccvList) {
	        	Tag tag =null;
	        	if(null != course.getTagId()){
	        		tag=tagService.findById(course.getTagId());
	        	}
	            if (tag != null) {
	                course.setTagName(tag.getTagName());
	            }
	            UserGroup ug = groupService.selectById(course.getGroupId());
	            if (ug != null) {
	                course.setGroupName(ug.getGroupName());
	            }
	        }
	        pageInfo.setRecords(ccvList);
	    }
	    
	    return pageInfo;
	}
	
	/**
	 * 新增资料
	 * @param model
	 * @return
	 */
	@RequestMapping("/manage/ceateMaterialForCourse")
    public String insertMaterialsJsp(Model model,String form){
        // 标签 
        List<Tag> tagList = tagService.listByAccountId(SessionContextUtils.getCurrentUserId());
        model.addAttribute("tagList", tagList);
        model.addAttribute("form", form);
        return "/elearning/course/addEdit_material";
    }
}
