package com.fulan.application.controller.elearning.examination;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fulan.api.course.domain.Course;
import com.fulan.api.course.domain.CourseShare;
import com.fulan.api.course.service.CourseService;
import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.paper.domain.el.PaperQuesShare;
import com.fulan.api.paper.domain.el.PaperQuestion;
import com.fulan.api.paper.domain.el.Question;
import com.fulan.api.paper.service.ElPaperManageService;
import com.fulan.api.paper.service.PaperQuesShareService;
import com.fulan.api.paper.service.QuestionService;
import com.fulan.api.paper.vo.ElPaperVo;
import com.fulan.api.paper.vo.PaperPlanNameVo;
import com.fulan.api.paper.vo.PaperQuestionVo;
import com.fulan.api.paper.vo.QuestionVo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.domain.UserGroup;
import com.fulan.api.security.service.AccountService;
import com.fulan.api.security.service.GroupService;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;
import com.jcraft.jsch.Session;

@Controller
@RequestMapping("/manage/paper")
public class ManagePrivatePaperController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ElPaperManageService elPaperManageService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private PaperQuesShareService paperQuesShareService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private GroupService groupService;
	
	/**
	 * 查询私人试卷
	 * @param model
	 * @param type
	 * @param pubType
	 * @param submitter
	 * @param createUser
	 * @param name
	 * @param groupId
	 * @param tagId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPrivatePaper")
	@RequestMapping(value="/GetPrivatePaper")
    public String getPrivatePaper(
    		 Model model,
    		 @RequestParam(value="type",required=false) String type,
    		 @RequestParam(value="pubType",required=false) String pubType,
             @RequestParam(value="submitter",required=false) String submitter,
             @RequestParam(value="createUser",required=false) String createUser,
             @RequestParam(value="name",required=false) String name,
             @RequestParam(value="groupId",required=false) String groupId,
             @RequestParam(value="tagId",required=false) String tagId,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="10") int pageSize
    		){
		if(StringUtil.isEmpty(pubType)){
			pubType="1";
		}
		if(StringUtil.isEmpty(submitter)){
			submitter="1";
		}
		createUser = String.valueOf(SessionContextUtils.getLoginUserId());
		PageInfo<ElPaperVo> data = elPaperManageService.listForManage(type, pubType, submitter, createUser, name, groupId, tagId, pageNo, pageSize);
		
		String pType="2";
		model.addAttribute("pType",pType);
		model.addAttribute("data",data);
		model.addAttribute("name",name);
		model.addAttribute("type",type);
		model.addAttribute("pubType",pubType);
		model.addAttribute("submitter",submitter);
		model.addAttribute("createUser",createUser);
		model.addAttribute("groupId",groupId);
		model.addAttribute("tagId",tagId);
		
		return "elearning/examination/public_paper";
		
		
		/*return "elearning/examination/private_paper";*/
	}
	
	/**
	 * ajax异步刷新
	 * @param model
	 * @param type
	 * @param pubType
	 * @param submitter
	 * @param createUser
	 * @param name
	 * @param groupId
	 * @param tagId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/ajaxPrivatePaper")
	@ResponseBody
    public Response<PageInfo<ElPaperVo>> ajaxPrivatePaper(
    		 @RequestParam(value="type",required=false) String type,
    		 @RequestParam(value="pubType",required=false) String pubType,
             @RequestParam(value="submitter",required=false) String submitter,
             @RequestParam(value="createUser",required=false) String createUser,
             @RequestParam(value="name",required=false) String name,
             @RequestParam(value="groupId",required=false) String groupId,
             @RequestParam(value="tagId",required=false) String tagId,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="10") int pageSize
    		){
		if(StringUtil.isEmpty(pubType)){
			pubType="1";
		}
		if(StringUtil.isEmpty(submitter)){
			submitter="1";
		}
		createUser = String.valueOf(SessionContextUtils.getLoginUserId());
		PageInfo<ElPaperVo> data = elPaperManageService.listForManage(type, pubType, submitter, createUser, name, groupId, tagId, pageNo, pageSize);
		Response<PageInfo<ElPaperVo>> response = new Response<>(Response.SUCCESS, null);
		response.setData(data);
		return response;
		
	}
	
	/**
	 * 跳转增加试卷页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/addPaper")
    public String addPaper(Model model){
		return "elearning/examination/addPaper";
	}
	
	/**
	 * 批量删除试卷
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/deleteBatchPaper")
	@ResponseBody
    public Response<String> deleteBatchPaper(Model model,@RequestParam(value="ids")String ids){
		
		
		if(ids.substring(ids.length()-1, ids.length()).equals(",")){
			ids=ids.substring(0,ids.length() - 1);
		}
		
		Response<String> data=elPaperManageService.deleteBatchIdsForManage(ids);
		
		return  data;
	}
	/**
	 * 验证试卷是否有关联
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/verify")
	@ResponseBody
    public Response<String> deletePaperverify(@RequestParam(value="ids")String ids){
		String pe="";
		Response<String> resonse=new Response<>(Response.SUCCESS, "是否确认删除？");
		if(ids.substring(ids.length()-1, ids.length()).equals(",")){
			ids=ids.substring(0,ids.length() - 1);
		}
		 List<PaperPlanNameVo> selectPlanName = elPaperManageService.selectPlanName(ids);
		 if(selectPlanName !=null && selectPlanName.size()!=0){
			 Set set = new  HashSet(); 
			 List<String> newList = new  ArrayList<String>(); 
			 for (PaperPlanNameVo cd:selectPlanName) {
				 if(null!=cd.getClassName() && cd.getClassName()!="" ){
					 if(set.add(cd.getClassName())){
			                newList.add(cd.getClassName());
			            }
				 }else if(null!=cd.getCompulsoryName() && cd.getCompulsoryName()!="" ){
					 if(set.add(cd.getCompulsoryName())){
			                newList.add(cd.getCompulsoryName());
			            }
				 }else if(null!=cd.getPostName() && cd.getPostName()!="" ){
					 if(set.add(cd.getPostName())){
			                newList.add(cd.getPostName());
			            }
				 }else if(null!=cd.getPublicName() && cd.getPublicName()!="" ){
					 if(set.add(cd.getPublicName())){
			                newList.add(cd.getPublicName());
			            }
				 }else if(null!=cd.getStudyName() && cd.getStudyName()!="" ){
					 if(set.add(cd.getStudyName())){
			                newList.add(cd.getStudyName());
			            }
				 }
		            
		        }
		         for (String s : newList) {
		        	 pe=pe+s+",";
				}
				resonse.setCode(Response.ERROR);
				resonse.setMsg("存在关联计划:"+pe+"不能删除");
			return resonse;
			}
		return  resonse;
	}
	
	//试卷分享
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/sharePaper" )
	public String sharePaper(Model model,
			@RequestParam(value="paperIds")String[] paperIds,
			@RequestParam(value="paperNames")String[] paperNames){
		List<Paper> papers=new ArrayList<Paper>();
		
		//用户组
        List <UserGroup> groupList = groupService.listAllGroups();
        model.addAttribute("groupList", groupList);
		
        if (paperIds.length > 0) {
            String courseIdStr = "";
            for(int i=0;i<paperIds.length;i++){
            	Paper paper=new Paper();
            	paper.setId(Long.parseLong(paperIds[i]));
            	paper.setName(paperNames[i]);
            	papers.add(paper);
                
            	courseIdStr += paperIds[i];
                if (i != paperIds.length -1) {
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
		model.addAttribute("papers", papers);
		return "elearning/examination/share_paper";
	}
	
	
	
	/**
	 * 跳转单个分享试卷页面
	 * @param model
	 * @return
	 */
	/*@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/sharePaper" )
    public String sharePaper(Model model,@RequestParam("id") String id ,@RequestParam("name") String name,
    		@RequestParam("isShare") String isShare){
		Long idl=null;
		if(!StringUtil.isEmpty(id)){
			idl=Long.valueOf(id);
		}
		List<Long> list = paperQuesShareService.selectSharePaper(id);
		model.addAttribute("list", list);
		model.addAttribute("isShare", isShare);
		model.addAttribute("id", idl);
		model.addAttribute("name", name);
		return "elearning/examination/share_paper";
	}*/
	
	/**
	 * 跳转批量分享试卷页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/shareBatchPaper" )
    public String shareBatchPaper(Model model,@RequestParam("ids") String[] ids ,@RequestParam("names") String[] names,
    		@RequestParam("isShare") String isShare 
    		){
		List <Paper> data=new ArrayList<>();
		for(int i=0;i<ids.length;i++){
			Paper paper= new Paper();
			paper.setId(Long.parseLong(ids[i]));
			paper.setName(names[i]);
			data.add(paper);
		}
		model.addAttribute("data", data);
		model.addAttribute("isShare", isShare);
		return "elearning/examination/batchShare_paper";
	}
	/**
	 * 分享试卷
	 * @param model
	 * @param content
	 * @param tagId
	 * @param analysis
	 * @param questionId
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/doShareOtherPaper" )
	public @ResponseBody Response<Boolean> doSharePapers(Model model,
				@RequestParam(value="groupIds[]")String[] groupIds, 
				@RequestParam(value="paperIds[]")String[] paperIds){
		Response<Boolean> doShareCourses = elPaperManageService.doSharePapers(groupIds, paperIds);
		
		return doShareCourses;
	} 
	/*@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/saveSharePaper" )
	@ResponseBody
    public Response<Integer> saveSharePaper(Model model,
    		@RequestParam("ids") String ids ,
    		@RequestParam("groupIds") String groupIds,
    		@RequestParam("isShare") String isShare
    		){
	Response<Integer> response = paperQuesShareService.saveBatchForManage(ids, "2", groupIds, isShare);
	return response;
	}*/
	
	/**
	 * 跳转试卷新增试题页面
	 * @param type
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@GetMapping("/selectQuestion")
	public ModelAndView selectQuestion(
			@RequestParam(value="type",required=false)String type,
			@RequestParam(value="tagId",required=false)String tagId,
			@RequestParam(value="keyWord",required=false)String keyWord,
			@RequestParam(value="createUser",required=false)String createUser){
		ModelAndView mv = new ModelAndView();
		List<Question> qList= questionService.listForOtherManage(type, tagId,keyWord,createUser);
		mv.addObject("qList", qList);
		mv.setViewName("elearning/examination/paper_add_question");
		return mv ;
	}
	
	/**
	 * 新增试题页面手动查询试题
	 * @param type
	 * @return
	 */
	/*@RequestMapping(value ="/manage/paper/GetPrivateOtherPaper", method = RequestMethod.POST)*/
	@PostMapping("/GetPrivateOtherPaper")
	@ResponseBody
    public Response<List<Question>> getQuestionList(
    		@RequestParam(value="type",required=false)String type,
    		@RequestParam(value="tagId",required=false)String tagId,
    		@RequestParam(value="keyWord",required=false)String keyWord,
    		@RequestParam(value="createUser",required=false)String createUser){
    	Response<List<Question>> res = new Response<List<Question>>();
    	
    	//查找私人题库
    	List<Question> qList = questionService.listForOtherManage(type, tagId,keyWord,createUser);
    	//查找分享题库
    	PaperQuesShare paperQuesShare = new PaperQuesShare();
    	//GroupId写死为1
    	paperQuesShare.setGroupId(1L);
    	List<PaperQuesShare> sList = paperQuesShareService.listForManage(paperQuesShare).getData();
    	for(PaperQuesShare paperQuesShares:sList){
    		qList.add(questionService.seleByIdForManage(paperQuesShares.getHostId()).getData());
    	}
    	res.setData(qList);
    	return res;
    }
	
	

	/**
	 * 随机生成试题
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/randomQuestion")
	@ResponseBody
    public  Response<List<Question>>  randomQuestion(@RequestParam(value="ids[]")String[] ids,
    										@RequestParam(value="nums[]")String[] nums,
    										@RequestParam(value="tagId")String tagId){
		
		List<Map<String,Object>> list=new ArrayList<>();
		for(int i=0;i<ids.length;i++){
			Map<String,Object> map =new HashMap<>();
			 map.put("type",ids[i]);
	 		 map.put("tagId",tagId);
	 		 map.put("questionNum",Integer.valueOf(nums[i]));
	 		 list.add(map);
		}
		 Response<List<Question>> response = questionService.seleRandomForManage(list);
		return  response;
	}
	
	@RequestMapping(value="/paperQuestionPreview")
    public String paperQuestionPreview(PaperQuestionVo paperQuestionVo,Model model){
		model.addAttribute("paper", paperQuestionVo.getPaper());
		List<Long> idList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(paperQuestionVo.getPaperQuestionList())){
			Map<Object,Object> mapHash = new HashMap<>();
			for (PaperQuestion paperQuestion : paperQuestionVo.getPaperQuestionList()) {
				idList.add(paperQuestion.getQuestionId());
			}
			if(CollectionUtils.isNotEmpty(idList)){
				List<QuestionVo> list = questionService.seleByIdList(idList);
				for(QuestionVo questionVo : list){
					mapHash.put(questionVo.getId(), questionVo);
				}
				model.addAttribute("questionVo", paperQuestionVo.getPaperQuestionList());
				model.addAttribute("questionVoMap", mapHash);
			}
		}
		return  "elearning/examination/private_paper_view";
	}
	
	/**
	 * 修改或新增试题
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/savePaperQuestion")
	@ResponseBody
    public Response<String> savePaperQuestion(PaperQuestionVo paperQuestionVo){
		if(null != paperQuestionVo.getPaper().getId()){
			Paper paper=paperQuestionVo.getPaper();
			paper.setModifyUser(SessionContextUtils.getLoginUserId());
			paper.setGmtModified(new Date());
			paper.setModifyUser(SessionContextUtils.getLoginUserId());
			paperQuestionVo.setPaper(paper);
		Response<String> response = elPaperManageService.updateForManage(paperQuestionVo);
		return  response;
		}
		long groupId=1;
		Paper paper=paperQuestionVo.getPaper();
		paper.setGroupId(groupId);
		paper.setCreateUser(SessionContextUtils.getLoginUserId());
		paper.setGmtCreate(new Date());
		paper.setGmtModified(new Date());
		paperQuestionVo.setPaper(paper);
		Response<String> response = elPaperManageService.saveForManage(paperQuestionVo);
		return  response;
	}
	
	/**
	 * 跳转修改试卷页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/updatePaper")
    public String updatePaper(Model model ,@RequestParam("id") String id){
	     List<ElPaperVo> response = elPaperManageService.seleByIdPublic(id);
	     ElPaperVo elPaperVo=response.get(0);
	     model.addAttribute("elPaperVo", elPaperVo);
		return "elearning/examination/updata_paper";
	}
	
	
	
}
