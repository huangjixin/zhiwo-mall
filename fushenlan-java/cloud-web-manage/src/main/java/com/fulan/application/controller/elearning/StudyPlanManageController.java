package com.fulan.application.controller.elearning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.course.domain.Course;
import com.fulan.api.course.service.CourseService;
import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.paper.service.ElPaperManageService;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.api.plan.domain.StudyPlan;
import com.fulan.api.plan.domain.StudyPlanCopy;
import com.fulan.api.plan.service.StudyPlanService;
import com.fulan.api.plan.vo.PlanCourseVoQ;
import com.fulan.api.plan.vo.StudyPlanCourse;
import com.fulan.api.plan.vo.StudyPlanManageVo;
import com.fulan.api.plan.vo.StudyPlanVoQ;
import com.fulan.api.plan.vo.StudyPlanVvo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.domain.UserGroup;
import com.fulan.api.security.service.AccountService;
import com.fulan.api.security.service.GroupService;
import com.fulan.api.system.domain.Tag;
import com.fulan.api.system.service.TagService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.shrio.SessionContextUtils;
/*import com.fulan.application.context.CommenConstant;*/
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;
import com.mysql.fabric.xmlrpc.base.Array;
import com.netflix.client.http.HttpRequest;
@Controller
@RequestMapping("/manage/studyPlanManage")
public class StudyPlanManageController {
	@Autowired
	private StudyPlanService studyPlanService;
	@Autowired
	private TagService tagService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private ElPaperManageService elPaperManageService;
	
	@Autowired(required=false)
	private AccountService accountService;

	@RequestMapping(method=RequestMethod.GET)
	public String studyPlan(){
		return "forward:/manage/studyPlanManage/planListByPage";
	}
	

	//更新学习计划
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Response<String> updateStudyPlanManage(@RequestBody StudyPlan studyPlan){
		return studyPlanService.updateStudyPlan(studyPlan);
	}

	//批量删除学习计划
	 @RequestMapping(value="/batch/delete",method=RequestMethod.POST) 
	 public Response<String> deleteStudyPlan(@RequestParam(name = "planIds", defaultValue = "0") Long[] planIds){
		 return studyPlanService.delete(planIds);
	 }
	 
	//根据id查出详情
	 @RequestMapping("/detail")
	 public String listDetail(Long id,Model model){
		 Response<String> res= studyPlanService.studyPlantDetail(id);
	     String data=res.getData();
	     StudyPlanCopy studyPlanCopy = JSON.parseObject(data, StudyPlanCopy.class);
		 int maxStage=0;//设置记录有多少个阶段
		 List<PlanCourseVoQ>  planCourseVos=studyPlanCopy.getPlanCourseVoQs();
		 String name= studyPlanCopy.getName();
		 String description=studyPlanCopy.getDescription();
		 Long tagId=studyPlanCopy.getTagId();
		 if(null!=planCourseVos){
		 int sumCount= planCourseVos.size();//定义课程或者是试卷的数目
		 for (PlanCourseVoQ planCourseVoQ : planCourseVos) {//获取最大的阶段数
			if(planCourseVoQ.getStage()>maxStage){
				maxStage=planCourseVoQ.getStage();
			}
		 }
		 int maxCount=0;//定义所有阶段中最多的课程数
		 int[] counts=new int[maxStage+1];//定义每个阶段对应的线上课程或试卷的数目
		 int[] sum=new int[maxStage+1];
		 int s=0;
		 for(int i=0;i<counts.length;i++){//赋初始值
			 counts[i]=0;
			 sum[i]=0;
		 }
		 for(int i=0;i<counts.length;i++){
		  for(PlanCourseVoQ planCourseVoQ : planCourseVos){//得到每个阶段共有多少个课程
			  if(planCourseVoQ.getStage()==i){//遍历循环出每个阶段的课程试卷的数目
				  counts[i]=counts[i]+1;
			  }
		   }
		}
		 List<PlanCourseVoQ> planCourses=new ArrayList<>();//定义一个有序的集合
		 List<PlanCourseVoQ> tarPlanCourse=new ArrayList<>();//定义一个有序的集合
		 for(int i=1;i<=maxStage;i++){
			s=s+counts[i-1]; 
			sum[i]=s;
		 }
		 PlanCourseVoQ[] planCourseArray=new PlanCourseVoQ[sumCount];
		 List<Integer> stageCounts=new ArrayList<>();
		 if(maxStage>0){
		 for(int i=0;i<=maxStage;i++){
		   for(PlanCourseVoQ planCourseVo : planCourseVos){
				if(planCourseVo.getStage()==i){//先按阶段给排序号(0,1,2,3...)
					planCourses.add(planCourseVo);
					}
				}
			 }
		 int i=0;
		 for(PlanCourseVoQ planCourseVo : planCourses){//把集合转化为数组
			   planCourseArray[i]=planCourseVo;
			   i++;
		   }
			 for(int j=0;j<=maxStage;j++){//遍历阶段(对阶段和序号进行排序)
				 for(int k=0;k<counts[j]-1;k++){//
					for(int m=0;m<counts[j]-1-k;m++){
						PlanCourseVoQ pv1=new PlanCourseVoQ();	
                        int a1=planCourseArray[m+sum[j]].getSeq();
                        int a2=planCourseArray[m+sum[j]+1].getSeq();
                        if(a1>a2){
                        	pv1=planCourseArray[m+sum[j]];
                        	planCourseArray[m+sum[j]]=planCourseArray[m+sum[j]+1];
                        	planCourseArray[m+sum[j]+1]=pv1;
                        }
					}
				 }
			 }
		}else{//普通型
			int[] downSum=new int[2];
			downSum[0]=0;
			downSum[1]=0;
			List<PlanCourseVoQ> plans=new ArrayList<>();
			 for(PlanCourseVoQ planCourseVo : planCourseVos){
			  if(planCourseVo.getAssociateType()!=3){
				planCourses.add(planCourseVo);
				downSum[0]=downSum[0]+1;
				}else{
					plans.add(planCourseVo);
					downSum[1]=downSum[1]+1;
				}
			}
			 planCourses.addAll(plans);
			 int i=0;
			 for(PlanCourseVoQ planCourseVo : planCourses){//把集合转化为数组
				   planCourseArray[i]=planCourseVo;
				   i++;
			   }
			 int[] sums=new int[2];
			 sums[0]=0;
			 sums[1]=0+downSum[0];
		 for(int j=0;j<2;j++){//遍历阶段(对阶段和序号进行排序)
			for(int k=0;k<downSum[j]-1;k++){//
				for(int m=0;m<downSum[j]-1-k;m++){
					PlanCourseVoQ pv1=new PlanCourseVoQ();	
                       int a1=planCourseArray[m+sums[j]].getSeq();
                       int a2=planCourseArray[m+sums[j]+1].getSeq();
                       if(a1>a2){
                        pv1=planCourseArray[m+sums[j]];
                        planCourseArray[m+sums[j]]=planCourseArray[m+sums[j]+1];
                        planCourseArray[m+sums[j]+1]=pv1;
                        }
					}	 
				 }
			 }
		}
			
		 for(int k=0;k<planCourseArray.length;k++){
			 tarPlanCourse.add(planCourseArray[k]);
		 } 
		 Long userId = SessionContextUtils.getLoginUserId();
		 List<Tag> tags= tagService.listByAccountId(userId);
		 if(null!=tags)
		 model.addAttribute("tags", tags);
		 model.addAttribute("stageCounts", stageCounts);
		 model.addAttribute("name", name);
		 model.addAttribute("description", description);
		 model.addAttribute("tagId", tagId);
		 model.addAttribute("id", id);
		 model.addAttribute("maxStage", maxStage);
		 model.addAttribute("tarPlanCourse", tarPlanCourse);
		 model.addAttribute("studyPlanCopy", studyPlanCopy);
		 return "elearning/course/studyPlanManage/learnPLan_addEdit";
		 }else{//没多大意义
			 model.addAttribute("name", name);
			 model.addAttribute("description", description);
			 model.addAttribute("tagId", tagId);
			 model.addAttribute("id", id);
			 return "redirect:/manage/studyPlanManage/planListByPage"; 
		 }
	 }
	 
	 //新增
	 @RequestMapping(value="/learnPLan_addEdit")
	 public String addLearnPlan(){
		 return "elearning/course/studyPlanManage/learnPLan_addEdit";
	 }
	 

	 @RequestMapping("/onlineCourse_add")
	 public String addOnlinecourse(String stageId,String idList,String online,Model model){
		 model.addAttribute("stageId", stageId);
		 model.addAttribute("idList", idList);
		 model.addAttribute("online", online);
		 return "elearning/course/studyPlanManage/onlineCourse_add";
	 }
	 
	 
	 @RequestMapping("/offlineCourse_add")
	 public String addOfflinecourse(String stageId,String idList,String online,Model model){
		 model.addAttribute("stageId", stageId);
		 model.addAttribute("idList", idList);
		 model.addAttribute("online", online);
		 return "elearning/course/studyPlanManage/offlineCourse_add";
	 }
	 @RequestMapping("/testPaper_add")
	 public String addTestPaper(String stageId,String idList,Model model){
		 model.addAttribute("stageId", stageId);
		 model.addAttribute("idList", idList);
		 return "elearning/course/studyPlanManage/testPaper_add";
	 }
	 
	 @RequestMapping(value="/testPaper_preview",method=RequestMethod.POST)
	  public String testPaperpreview(StudyPlanCourse studyPlanCourse,Model model){
		String isStag="0";
		String description=studyPlanCourse.getStudyPlan().getDescription();
		String name=studyPlanCourse.getStudyPlan().getName();
		Long tagId=studyPlanCourse.getStudyPlan().getTagId();
		Long id=studyPlanCourse.getStudyPlan().getId();
		Integer planType = studyPlanCourse.getStudyPlan().getPlanType();
		List<PlanCourseVoQ> planCourseVoQs= studyPlanCourse.getPlanCourse();
		List<PlanCourseVoQ> planCourseVoReal=new ArrayList<PlanCourseVoQ>();
		for(PlanCourseVoQ planCourseVoQ : planCourseVoQs){//把得到的为空的去掉
			if(null!=planCourseVoQ.getStage()){
				planCourseVoReal.add(planCourseVoQ);
			}
		}
		int maxStage=1;//定义最大的阶段数变量
		int stage;
		for (PlanCourseVoQ planCourseVoQ : planCourseVoReal) {//得到最大的阶段数
			stage=planCourseVoQ.getStage();
			if(maxStage<stage){
				maxStage=stage;
			}
			if(stage==1 && null!=planCourseVoQ.getIsStage() && planCourseVoQ.getIsStage()!=""){
				isStag="1";
			}
		} 
		int sumCount= planCourseVoReal.size();//定义课程或者是试卷的数目
	    List<PlanCourseVoQ> planCourses=new ArrayList<>();//定义一个有序的集合
	    List<PlanCourseVoQ> tarPlanCourse=new ArrayList<>();//定义一个有序的集合
	    PlanCourseVoQ[] planCourseArray=new PlanCourseVoQ[sumCount];
	    if(maxStage>1){//阶段型
			 for(int i=0;i<=maxStage;i++){
			   for(PlanCourseVoQ planCourseVo : planCourseVoReal){
				   if(i==0 && planCourseVo.getAssociateType()==3){
					   planCourses.add(planCourseVo);
				   }
				   else if(planCourseVo.getStage()==i && planCourseVo.getAssociateType()!=3){//先按阶段给排序号(1,2,3...)
						planCourses.add(planCourseVo);
						}
					}
				 }
			 int i=0;
			 for(PlanCourseVoQ planCourseVo : planCourses){//把集合转化为数组
				   planCourseArray[i]=planCourseVo;
				   i++;
			   }
			 int[] counts=new int[maxStage+1];//定义每个阶段对应的线上课程或试卷的数目
			 int[] sum=new int[maxStage+1];
			 int s=0;
			 for(int a=0;a<counts.length;a++){//赋初始值
				 counts[a]=0;
				 sum[a]=0;
			 }
			 for(int a=0;a<counts.length;a++){
			  for(PlanCourseVoQ planCourseVoQ : planCourses){//得到每个阶段共有多少个课程
				  if(planCourseVoQ.getStage()==a){//遍历循环出每个阶段的课程试卷的数目
					  counts[a]=counts[a]+1;
				  }
			   }
			}
			 for(int a=1;a<=maxStage;a++){
				s=s+counts[a-1]; 
				sum[a]=s;
			 }
				 for(int j=0;j<=maxStage;j++){//遍历阶段(对阶段和序号进行排序)
					 for(int k=0;k<counts[j]-1;k++){
						for(int m=0;m<counts[j]-1-k;m++){
							PlanCourseVoQ pv1=new PlanCourseVoQ();	
	                        int a1=planCourseArray[m+sum[j]].getSeq();
	                        int a2=planCourseArray[m+sum[j]+1].getSeq();
	                        if(a1>a2){
	                        	pv1=planCourseArray[m+sum[j]];
	                        	planCourseArray[m+sum[j]]=planCourseArray[m+sum[j]+1];
	                        	planCourseArray[m+sum[j]+1]=pv1;
	                        }
						}
					 }
				 }
			}else{//普通型
				int[] downSum=new int[2];
				downSum[0]=0;
				downSum[1]=0;
				List<PlanCourseVoQ> plans=new ArrayList<>();
				 for(PlanCourseVoQ planCourseVo : planCourseVoReal){
				  if(planCourseVo.getAssociateType()!=3){
					planCourses.add(planCourseVo);
					downSum[0]=downSum[0]+1;
					}else{
						plans.add(planCourseVo);
						downSum[1]=downSum[1]+1;
					}
				}
				 planCourses.addAll(plans);
				 int i=0;
				 for(PlanCourseVoQ planCourseVo : planCourses){//把集合转化为数组
					   planCourseArray[i]=planCourseVo;
					   i++;
				   }
				 int[] sums=new int[2];
				 sums[0]=0;
				 sums[1]=0+downSum[0];
			  for(int j=0;j<2;j++){//遍历阶段(对阶段和序号进行排序)
				for(int k=0;k<downSum[j]-1;k++){//
					for(int m=0;m<downSum[j]-1-k;m++){
						PlanCourseVoQ pv1=new PlanCourseVoQ();	
	                       int a1=planCourseArray[m+sums[j]].getSeq();
	                       int a2=planCourseArray[m+sums[j]+1].getSeq();
	                       if(a1>a2){
	                        pv1=planCourseArray[m+sums[j]];
	                        planCourseArray[m+sums[j]]=planCourseArray[m+sums[j]+1];
	                        planCourseArray[m+sums[j]+1]=pv1;
	                        }
						}	 
					 }
				 }
			}
	    
	    for(int k=0;k<planCourseArray.length;k++){
			 tarPlanCourse.add(planCourseArray[k]);
		 }
			
		model.addAttribute("description", description);
		model.addAttribute("name", name);
		model.addAttribute("tagId", tagId);
		model.addAttribute("id", id);
		model.addAttribute("maxStage", maxStage);
		model.addAttribute("planCourseVoReal", tarPlanCourse);
		model.addAttribute("isStage", isStag);
		model.addAttribute("planType", planType);
		 return "elearning/course/studyPlanManage/testPaper_preview";
	 }
	 
	//保存学习计划或更新学习计划
	 @RequestMapping(value="/saveStudyPlan")
	 public String saveStudy(StudyPlanCourse studyPlanCourse){
		 String planName=studyPlanCourse.getStudyPlan().getName();
		 String planDescription=studyPlanCourse.getStudyPlan().getDescription();
		 Long tagId=studyPlanCourse.getStudyPlan().getTagId();
		 Integer planType = studyPlanCourse.getStudyPlan().getPlanType();
		 List<PlanCourseVoQ> planCourseVoQs= studyPlanCourse.getPlanCourse();
			int maxStage=0;//定义最大的阶段数变量
			int stage;
			int courseNum=0;//定义课程数
			for (PlanCourseVoQ planCourseVoQ : planCourseVoQs) {//获取阶段数
				stage=planCourseVoQ.getStage();
				if(maxStage<stage){
					maxStage=stage;
				}
				if(planCourseVoQ.getAssociateType()!=1){
					courseNum=courseNum+1;
				}
			}

			StudyPlanCopy studyPlan=new StudyPlanCopy();
			studyPlan.setName(planName);
			studyPlan.setDescription(planDescription);
			studyPlan.setTagId(tagId);
			studyPlan.setStageNum(maxStage);
			studyPlan.setId(studyPlanCourse.getStudyPlan().getId());
			studyPlan.setCourseNum(courseNum);
			studyPlan.setPlanType(planType);
			List<PlanCourse> planCourses=new ArrayList<PlanCourse>();
			for (PlanCourseVoQ planCourseVo : planCourseVoQs) {
			  PlanCourse planCourse=new PlanCourse();
			  planCourse.setAssociateId(planCourseVo.getAssociateId()); 
			  planCourse.setAssociateType(planCourseVo.getAssociateType());
			  planCourse.setStage(planCourseVo.getStage());
			  planCourse.setSeq(planCourseVo.getSeq());
			  planCourse.setIsCompulsory(planCourseVo.getIsCompulsory());
			  planCourse.setCourseType(CommenConstant.EL_COURSE_TYPE_STUDYPLAN);
			  planCourses.add(planCourse);
		    }
			studyPlan.setPlanCourses(planCourses);
			studyPlan.setCode("1111");
			Long id = SessionContextUtils.getLoginUserId();
			UserGroup userGuoup=groupService.selectByAccountId(id);
			
			if(null == studyPlan.getId() && null !=userGuoup){
				studyPlan.setCreateUser(id);
				studyPlan.setGroupId(userGuoup.getId());
			}else{
				studyPlan.setModifyUser(id);
			}
			
			studyPlanService.insert(studyPlan);
		 return "redirect:/manage/studyPlanManage";
	 }
	 
	 
		//保存学习计划或更新学习计划
		 @RequestMapping(value="/goback")
		 public String gobackStudy(StudyPlanCourse studyPlanCourse,String isStage,Model model){
			 if(null!=isStage && isStage.equals("1")){
				 isStage="1";
			 }else{
				 isStage="0";
			 }
			Long id=studyPlanCourse.getStudyPlan().getId();
			String name= studyPlanCourse.getStudyPlan().getName();
			String description=studyPlanCourse.getStudyPlan().getDescription();
			Long tagId=studyPlanCourse.getStudyPlan().getTagId();
			 List<PlanCourseVoQ> planCourseVoQs= studyPlanCourse.getPlanCourse();
				int maxStage=0;//定义最大的阶段数变量
				int stage;
				for (PlanCourseVoQ planCourseVoQ : planCourseVoQs) {//获取阶段数
					stage=planCourseVoQ.getStage();
					if(maxStage<stage){
						maxStage=stage;
					}
					planCourseVoQ.setName(planCourseVoQ.getDesName());
				}
				 model.addAttribute("maxStage", maxStage);
				 model.addAttribute("name", name);
				 model.addAttribute("description", description);
				 model.addAttribute("tagId", tagId);
				 model.addAttribute("id", id);
				 model.addAttribute("isStage", isStage);
				 model.addAttribute("tarPlanCourse", planCourseVoQs);
				 return "elearning/course/studyPlanManage/learnPLan_addEdit";

		 }
	 
	 
	 //查询出所有的数据
	 @RequestMapping("/studyLists")
	 public String studyPlanLists(String code,String name,Long tagId,Integer pageNo,Integer pageSize,Model model){
		Response<List> res= studyPlanService.studyLists(code,name,tagId,pageNo,pageSize);
		List<StudyPlan> studyPlanList=res.getData();
		model.addAttribute("studyPlanList", studyPlanList);
		return "elearning/course/studyPlanManage/learnPlan_manage";
		 
	 }
	 
	 //根据id查询出学习计划详情
	 @RequestMapping("/planDetail")
	 @ResponseBody
	 public Response<StudyPlan> selectPlanById(Long id){
		Response<StudyPlan> res= studyPlanService.PlanDetailById(id);
		return res;
	 }
	
	 //批量更新
	 @RequestMapping("/batchUpstates")
	 @ResponseBody
	 public Response<String> updatePlan(String ids){
		 List<Long> idList = new ArrayList<Long>();
			String[] idArr = ids.split(",");
			for(String id : idArr){
				idList.add(Long.valueOf(id));
				
	       }
		   int length=idList.size();		
		   Long enabled=idList.get(length-1);
		   idList.remove(length-1);
		   try{
		   //若enabled为1，则进行启用
		   if(enabled==1L){
			   for (Long id : idList) {
				   studyPlanService.enabledPlan(id);
			}
		   }else{//否则是禁用
			   for (Long id : idList) {
				   studyPlanService.disabledPlan(id);
			}
		   }
		   return new Response<>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		   }catch(Exception e){
			   e.printStackTrace();
			   return new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
		   }
	 }
	 
	 
	 //批量删除学习计划
	 @RequestMapping("/betchdeletes")
	 @ResponseBody
	 public Response<String> betchdelete(String ids){
		if(ids.equals("")){
			Response<String> res=new Response<>();
			res.setCode("0");
			return res;
		}else{
			 List<Long> idList = new ArrayList<Long>();
				String[] idArr = ids.split(",");
				for(String id : idArr){
					idList.add(Long.valueOf(id));
					
		       }
			 return studyPlanService.deletePlanStudy(idList);
		}
	 }
	 
	 //批量删除学习计划
	 @RequestMapping("/betchd")
	 @ResponseBody
	 public Response<String> betchdelete(){
		 List<Long> idList = new ArrayList<Long>();
			idList.add(2L);
		 return studyPlanService.deletePlanStudy(idList);
		 
	 }
	 
	 @RequestMapping("/findStudyPlans")
	 public String findStudyPlan(String code,String name,Long tagId,Model model){
		Response<List> res= studyPlanService.lookupStudyPlans(code, name, tagId); 
		model.addAttribute("code", code);
		model.addAttribute("name", name);
		model.addAttribute("tagId", tagId);
		List<StudyPlanVoQ>  studyPlanList=res.getData();
		model.addAttribute("studyPlanList", studyPlanList);
		return "elearning/course/studyPlanManage/learnPlan_manage";
		 
	 }
	 
	 @RequestMapping("/findPapers")
	 @ResponseBody
	 public Response<List<Paper>> findsPapers(@RequestBody Paper paper){

		 return elPaperManageService.listObject(paper);
		 
	 }

	 //查询试卷
	 @RequestMapping("/selectPapers")
	 @ResponseBody
	 public Response<List<Paper>> selectPapers(Integer submitter,String name,Integer type){
		return elPaperManageService.seleByGroupId(submitter, name, type);
	 }

	 @RequestMapping("/findCourses")
	 @ResponseBody
	 public List<Course> dlfs(
			 @RequestParam(value="keyWord",required=false) String keyWord,//课程关键字
				@RequestParam(value="type",required=false) String type,//类型
				@RequestParam(value="submitter",required=false) String submitter,//提交人
				@RequestParam(value="pubType",required=false) String pubType,
				@RequestParam(value="isOnline",required=false) String isOnline,//线上线下
				@RequestParam(value="groupId",required=false)  String groupId,//用户组/角色编号
				@RequestParam(value="tagId",required=false)   String tagId,//用户组/角色下的标签编号
				@RequestParam(value="uploadTimeBegin",required=false)   String uploadTimeBegin,//上传时间
				@RequestParam(value="uploadTimeEnd",required=false)   String uploadTimeEnd//结束时间
			 ){
		 pubType = "1";
		 return courseService.courseManageByPublic(keyWord, type, submitter, pubType, isOnline, groupId, tagId, uploadTimeBegin, uploadTimeEnd);
	 }
	 
	   /**
		 * 获取讲师列表
		 */
		@RequestMapping("/findByRoleId")
		public String findByRoleId(Model model, HttpServletRequest request, HttpServletResponse response){
			String id = request.getParameter("courseId");
			Course courseOne = courseService.selectCourseOne(id);
			//暂时设置为955997244559982592 表示讲师
			Long roleId = new Long("955997244559982592");
			List<Account> accountList = accountService.findByRoleId(roleId);
			model.addAttribute("course", courseOne);
			model.addAttribute("accountList", accountList);
			return "/elearning/course/studyPlanManage/lecturer_list";
		}
		
		@RequestMapping(value="/editCourse",method=RequestMethod.POST)
		@ResponseBody
		public Response<String> editCourse(Model model, Course course, HttpServletRequest request, HttpServletResponse response){
			String lecturerId = request.getParameter("lecturerId");
			//提交人暂时设置为0
			course.setCreateUser(new Long("0"));
			//如果没有选择课程分类，默认为全部
			if(course.getTagId()==null){
				course.setTagId(new Long("0"));
			}
			Response<String> resultData = courseService.handCourses(course,lecturerId,null,null);
			return resultData;
		}
		
	//数据列表	
	@RequestMapping(value="/planListByPage")
	public String listByPage(String name,String code,String tagId, String pageNo,String pageSize, Model model){
		if(""==pageNo || null ==pageNo){
    		pageNo="1";
    	}
    	if(""==pageSize || null ==pageSize){
    		pageSize="10";
    	}
		 PageInfo<StudyPlanVvo> stuPlanPageInfo=studyPlanService.listPlan(name, code, tagId, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		 List<StudyPlanVvo> studyPlanVvos=stuPlanPageInfo.getRecords();
		 Tag tag;
		 UserGroup userGroup;
		 for (StudyPlanVvo studyPlanVvo : studyPlanVvos) {
			 tag=tagService.findById(studyPlanVvo.getGroupId());
			 userGroup=groupService.selectById(studyPlanVvo.getGroupId());
			if(null!=tag && null !=userGroup){
				studyPlanVvo.setGroupName(userGroup.getGroupName());
				studyPlanVvo.setTagName(tag.getTagName());
			}
		 }
		 model.addAttribute("pageList", stuPlanPageInfo);
		 model.addAttribute("name", name);//用于查询之后的回显
		 model.addAttribute("code", code);//用于查询之后的回显
		 model.addAttribute("tagId", tagId);//用于查询之后的回显
		 return "elearning/course/studyPlanManage/learnPlan_manage";
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
	@RequestMapping("/listByPageAjax")
	@ResponseBody
	public Response<PageInfo<StudyPlanVvo>> listByPageAjax(String name,String code,String tagId, String pageNo,String pageSize){
		pageNo = StringUtil.isEmpty(pageNo)?"1":pageNo;
		pageSize =  StringUtil.isEmpty(pageSize)?"10":pageSize;
		PageInfo<StudyPlanVvo> stuPlanPageInfo=studyPlanService.listPlan(name, code, tagId, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		 List<StudyPlanVvo> studyPlanVvos=stuPlanPageInfo.getRecords();
		 Tag tag;
		 UserGroup userGroup;
		 for (StudyPlanVvo studyPlanVvo : studyPlanVvos) {
			 tag=tagService.findById(studyPlanVvo.getGroupId());
			 userGroup=groupService.selectById(studyPlanVvo.getGroupId());
			if(null!=tag && null !=userGroup){
				studyPlanVvo.setGroupName(userGroup.getGroupName());
				studyPlanVvo.setTagName(tag.getTagName());//注：这里面的groupName,tagName用在页面的回显，在后期页面上会有作用
			}
		 }
		Response<PageInfo<StudyPlanVvo>> response = new Response<>(Response.SUCCESS, null);
		response.setData(stuPlanPageInfo);
		return response;
	}	

}