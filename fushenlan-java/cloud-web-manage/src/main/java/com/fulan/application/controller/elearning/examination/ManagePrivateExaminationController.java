package com.fulan.application.controller.elearning.examination;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.course.domain.CourseShare;
import com.fulan.api.course.service.CourseService;
import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.paper.domain.el.Question;
import com.fulan.api.paper.domain.el.QuestionAnswer;
import com.fulan.api.paper.service.PaperQuesShareService;
import com.fulan.api.paper.service.QuestionService;
import com.fulan.api.paper.vo.QuestionVo;
import com.fulan.api.security.domain.UserGroup;
import com.fulan.api.security.service.GroupService;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;

@Controller
@RequestMapping("/manage/question")
public class ManagePrivateExaminationController {
	final Logger logger = org.slf4j.LoggerFactory.getLogger(ManagePrivateExaminationController.class);
	@Autowired
	private QuestionService questionService;
	@Autowired
	private PaperQuesShareService paperQuesShareService;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private CourseService courseService;
	
	
	/**
	 * 跳转私人试题页面
	 * @param model
	 * @param type
	 * @param pubType
	 * @param submitter
	 * @param createUser
	 * @param content
	 * @param groupId
	 * @param tagId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequiresPermissions("/manage/question/GetPrivateQuestion")
	@RequestMapping(value="/GetPrivateQuestion")
    public String getPriateQuestion(
    		 Model model,
    		 @RequestParam(value="type",required=false) String type,
             @RequestParam(value="pubType",required=false) String pubType,
             @RequestParam(value="submitter",required=false) String submitter,
             @RequestParam(value="createUser",required=false) String createUser,
             @RequestParam(value="content",required=false) String content,
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
		PageInfo<Question> data=questionService.listForManage(type, pubType, submitter, createUser, content, groupId, tagId, pageNo, pageSize);
		String pType="2";
		model.addAttribute("pType",pType);
		model.addAttribute("page", data);
		model.addAttribute("type",type);
		model.addAttribute("pubType",pubType);
		model.addAttribute("submitter",submitter);
		model.addAttribute("createUser",createUser);
		model.addAttribute("content",content);
		model.addAttribute("groupId",groupId);
		model.addAttribute("tagId",tagId);
		
		return "elearning/examination/public_question";
		/*return "elearning/examination/private_question";*/
	}
	

	/**
	 * ajax异步刷新
	 * @param model
	 * @param type
	 * @param pubType
	 * @param submitter
	 * @param createUser
	 * @param content
	 * @param groupId
	 * @param tagId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/ajaxPriateQuestion")
	@ResponseBody
    public Response<PageInfo<Question>> ajaxPriateQuestion(
    		 Model model,
    		 @RequestParam(value="type",required=false) String type,
             @RequestParam(value="pubType",required=false) String pubType,
             @RequestParam(value="submitter",required=false) String submitter,
             @RequestParam(value="createUser",required=false) String createUser,
             @RequestParam(value="content",required=false) String content,
             @RequestParam(value="groupId",required=false) String groupId,
             @RequestParam(value="tagId",required=false) String tagId,
             @RequestParam(value="pageNo",required=false) String pageNo,
             @RequestParam(value="pageSize",required=false) String pageSize
    		){
		
		if(StringUtil.isEmpty(pubType)){
			pubType="1";
		}
		if(StringUtil.isEmpty(submitter)){
			submitter="1";
			
		}
		pageNo = StringUtil.isEmpty(pageNo)?"1":pageNo;
		pageSize =  StringUtil.isEmpty(pageSize)?"10":pageSize;
		PageInfo<Question> data=questionService.listForManage(type, pubType, submitter, createUser, content, groupId, tagId, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		Response<PageInfo<Question>> response = new Response<>(Response.SUCCESS, null);
		response.setData(data);
		return response;
		
	}
	
	/**
	 * 跳转增加试题页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/addQuestion")
    public String addQuestion(Model model){
		return "elearning/examination/addQuestion";
	}
	
	/*@RequiresPermissions("/manage/question/GetPrivateQuestion")*/
	@RequestMapping(value="/addQuestionOther")
    public String addQuestionOther(Model model){
		return "elearning/examination/addQuestionOther";
	}
	
	/**
	 * 批量删除试题
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/deleteBatchQuestion")
	@ResponseBody
    public Response<String> deleteBatchQuestion(Model model,@RequestParam(value="ids")String ids){
		
		
		if(ids.substring(ids.length()-1, ids.length()).equals(",")){
			ids=ids.substring(0,ids.length() - 1);
		}
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		 list=questionService.isNotRelation(ids);
		if(list !=null && list.size()!=0){
			Response<String> resonse=new Response<>(Response.ERROR, "存在关联试卷，删除失败");
			
		return resonse;
		}
		Response<String> data=questionService.deleteBatchIdsForManage(ids);
		
		return  data;
	}
	
	/**
	 * 批量删除试题
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/verify")
	@ResponseBody
    public Response<String> deleteQuestionverify(Model model,@RequestParam(value="ids")String ids){
		String pe="";
		Response<String> resonse=new Response<>(Response.SUCCESS, "是否确认删除？");
		List<String> listPaper = new ArrayList<String>();
		if(ids.substring(ids.length()-1, ids.length()).equals(",")){
			ids=ids.substring(0,ids.length() - 1);
		}
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		 list=questionService.isNotRelation(ids);
		 List<String> selectPaperName = questionService.selectPaperName(ids);
		  Set set = new  HashSet(); 
	         List<String> newList = new  ArrayList<String>(); 
	         for (String cd:selectPaperName) {
	            if(set.add(cd)){
	                newList.add(cd);
	            }
	        }
	         for (String s : newList) {
	        	 pe=pe+s+",";
			}
	         
		if(list !=null && list.size()!=0){
			resonse.setCode(Response.ERROR);
			resonse.setMsg("存在关联试卷:"+pe+"不能删除");
		return resonse;
		}
		
		return  resonse;
	}
	
	
	
	/**
	 * 跳转修改试题页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/updateQuestion")
    public String updateQuestion(Model model ,@RequestParam("id") String id){
	      QuestionVo questionVo = questionService.questionAndAnswer(id);
	      String sel="A,B,C,D,E,F,G,H,I,G,K,L,M,N";
	    String[] arr= sel.split(",");
		model.addAttribute(questionVo);
		model.addAttribute("arr", arr);
		return "elearning/examination/srtk_edit";
	}

	/**
	 * 跳转单个分享试题页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/shareQuestion")
	public String shareQuestion(Model model,
			@RequestParam(value="questionIds")String[] questionIds,
			@RequestParam(value="questionContents")String[] questionNames){
		List<Question> questions=new ArrayList<Question>();
		
		//用户组
        List <UserGroup> groupList = groupService.listAllGroups();
        model.addAttribute("groupList", groupList);
		
        if (questionIds.length > 0) {
            String courseIdStr = "";
            for(int i=0;i<questionIds.length;i++){
            	Question question=new Question();
            	question.setId(Long.parseLong(questionIds[i]));
            	question.setContent(questionNames[i]);
            	questions.add(question);
            	courseIdStr += questionIds[i];
                if (i != questionIds.length -1) {
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
		model.addAttribute("questions", questions);
		return "elearning/examination/srtk_share";
	}
	
	/**
	 * 跳转批量分享试题页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/shareBatchQuestion" )
    public String shareBatchQuestion(Model model,@RequestParam("ids") String[] ids ,
    		@RequestParam("contents") String[] contents,
    		@RequestParam("isShare") String isShare
    		){
		List <Question> questions=new ArrayList<>();
		for(int i=0;i<ids.length;i++){
			Question ques=new Question();
			ques.setId(Long.parseLong(ids[i]));
			ques.setContent(contents[i]);
			questions.add(ques);
		}
		model.addAttribute("questions", questions);
		model.addAttribute("isShare", isShare);
		return "elearning/examination/batchShare_question";
	}
	
	
	
	/**
	 * 分享试题
	 * @param model
	 * @param content
	 * @param tagId
	 * @param analysis
	 * @param questionId
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping(value="/saveShareQuestion" )
	public @ResponseBody Response<Boolean> doSharePapers(Model model,
			@RequestParam(value="groupIds[]")String[] groupIds, 
			@RequestParam(value="questionIds[]")String[] questionIds){
	Response<Boolean> doShareCourses = questionService.shareQuestion(groupIds, questionIds);
	
	return doShareCourses;
} 
	
	/**
	 * 修改或新增试题
	 * @param model
	 * @param content
	 * @param tagId
	 * @param analysis
	 * @param questionId
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@PostMapping(value="/updateOrSavePrivateQuestion" )
	@ResponseBody
    public Response<String> updateOrSavePrivateQuestion(Model model,
    		 @RequestParam(value="content",required=false) String content,
             @RequestParam(value="tagId",required=false) String tagId,
             @RequestParam(value="analysis",required=false) String analysis,
             @RequestParam(value="questionId",required=false) String questionId,
             @RequestParam(value="selects[]",required=false) String[] selects,
             @RequestParam(value="answers[]",required=false) String[] answers,
             @RequestParam(value="answerIds[]",required=false) String[] answerIds,
             @RequestParam(value="question_type",required=false) String question_type,
             @RequestParam(value="answerSin",required=false) String answerSin,
             @RequestParam(value="isRead",required=false) String isRead,
             @RequestParam(value="answerId",required=false) String answerId
    		){
		 String sel="A,B,C,D,E,F,G,H,I,G,K,L,M,N";
		    String[] arr= sel.split(",");
		if(!StringUtil.isEmpty(questionId)){  //试题id不为空，修改试题
			
			QuestionVo questionVo = new QuestionVo();
			questionVo.setId(Long.valueOf(questionId));
			questionVo.setContent(content);
			if(!StringUtil.isEmpty(tagId)){
				questionVo.setTagId(Long.valueOf(tagId));
			}
			questionVo.setAnalysis(analysis);
			List<QuestionAnswer> list=new ArrayList<>();
			
			if(!StringUtil.isEmpty(answerSin)){
				QuestionAnswer questionAnswer=new QuestionAnswer();
				questionAnswer.setAnswer(answerSin);
				if(!StringUtil.isEmpty(answerId)){
					questionAnswer.setId(Long.valueOf(answerId));
				}
				
				list.add(questionAnswer);
			}
			
			
			
			if(!StringUtil.isEmpty(isRead)){
				QuestionAnswer questionAnswer=new QuestionAnswer();
				questionAnswer.setId(Long.valueOf(answerId));
				questionAnswer.setIsRight(Integer.parseInt(isRead));
				list.add(questionAnswer);
				
			}
			
			
			if(selects!=null && selects.length>0 ){
				for(int i=0;i<selects.length;i++){
					QuestionAnswer questionAnswer=new QuestionAnswer();
					questionAnswer.setIsRight(Integer.parseInt(selects[i]));
					questionAnswer.setOption(arr[i]);
					if(answers!=null){
						questionAnswer.setAnswer(answers[i]);
						questionAnswer.setSeq(i);
					}
					/*if(answerIds[i]!=null){
						questionAnswer.setId(Long.valueOf(answerIds[i]));
					}*/
					questionAnswer.setQuestionId(Long.valueOf(questionId));
					questionAnswer.setQuestionType(Integer.parseInt(question_type));
					list.add(questionAnswer);
				}
			}
			questionVo.setQuestionAnswer(list);
			questionVo.setGmtModified(new Date());
			questionVo.setModifyUser(SessionContextUtils.getLoginUserId());
			Response<String> data = questionService.updateForManage(questionVo);
		
			return data;
			
		}else{//新增试题
			QuestionVo questionVo = new QuestionVo();
			questionVo.setContent(content);
			questionVo.setType(Integer.parseInt(question_type));
			if(!StringUtil.isEmpty(tagId)){
				questionVo.setTagId(Long.valueOf(tagId));
			}
			
			questionVo.setAnalysis(analysis);
			List<QuestionAnswer> list=new ArrayList<>();
			
			if(!StringUtil.isEmpty(answerSin)){
				QuestionAnswer questionAnswer=new QuestionAnswer();
				questionAnswer.setAnswer(answerSin);
				list.add(questionAnswer);
			}
			
			if(!StringUtil.isEmpty(isRead)){
				QuestionAnswer questionAnswer=new QuestionAnswer();
				questionAnswer.setIsRight(Integer.parseInt(isRead));
				list.add(questionAnswer);
				
			}
			
			if(selects!=null && selects.length>0 ){
				for(int i=0;i<selects.length;i++){
					QuestionAnswer questionAnswer=new QuestionAnswer();
					questionAnswer.setIsRight(Integer.parseInt(selects[i]));
					questionAnswer.setOption(arr[i]);
					if(answers!=null && answers.length>0){
						questionAnswer.setAnswer(answers[i]);
						questionAnswer.setSeq(i);
					}
					/*questionAnswer.setQuestionType(Integer.parseInt(question_type));*/
					list.add(questionAnswer);
				}
			}
			questionVo.setQuestionAnswer(list);
			long groupId=1;
			questionVo.setGroupId(groupId);
			questionVo.setCreateUser(SessionContextUtils.getLoginUserId());
			questionVo.setGmtCreate(new Date());
			questionVo.setGmtModified(new Date());
			Response<String> data = questionService.saveForManage(questionVo);
		
			return data;
			
			
		}
		
		
		
		
		
		
	
	}
	
	
	
}
