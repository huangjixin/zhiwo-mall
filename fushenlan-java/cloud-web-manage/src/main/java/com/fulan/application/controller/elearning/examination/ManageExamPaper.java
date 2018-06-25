package com.fulan.application.controller.elearning.examination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletRequest;

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

import com.fulan.api.material.vo.MaterialCourseVo;
import com.fulan.api.paper.domain.el.Question;
import com.fulan.api.paper.domain.el.UserExam;
import com.fulan.api.paper.service.ElPaperManageService;
import com.fulan.api.paper.service.QuestionService;
import com.fulan.api.paper.vo.ElPaperVo;
import com.fulan.api.paper.vo.QuestionVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;

@Controller
@RequestMapping("/manage/paper")
public class ManageExamPaper {
	
	@Autowired
	private ElPaperManageService elPaperManageService;
	
	/**
	 * 查询已考试试卷
	 *
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetExamPaper")
	@GetMapping("/GetExamPaper")
    public String getExamPaper(
    		 Model model,
    		 @RequestParam(value="name",required=false) String name,
    		 @RequestParam(value="paperState",required=false) String paperState,
             @RequestParam(value="userName",required=false) String userName,
             @RequestParam(value="groupId",required=false) String groupId,
             @RequestParam(value="tagId",required=false) String tagId,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="10") int pageSize
    		){
		PageInfo<ElPaperVo> data = elPaperManageService.waitForReviewForManage(name, paperState, userName, groupId, tagId, pageNo, pageSize);
		model.addAttribute("data",data);
		model.addAttribute("name",name);
		model.addAttribute("paperState",paperState);
		model.addAttribute("userName",userName);
		model.addAttribute("groupId",groupId);
		model.addAttribute("tagId",tagId);
		
		return "elearning/examination/read_paper";
	}
	/**
	 * ajax异步刷新
	 *
	 * @return
	 */
	@RequiresPermissions("/manage/paper/GetExamPaper")
	@RequestMapping("/ajaxExamPaper")
	@ResponseBody
    public Response<PageInfo<ElPaperVo>> ajaxExamPaper(
    		 @RequestParam(value="name",required=false) String name,
    		 @RequestParam(value="paperState",required=false) String paperState,
             @RequestParam(value="userName",required=false) String userName,
             @RequestParam(value="groupId",required=false) String groupId,
             @RequestParam(value="tagId",required=false) String tagId,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="10") int pageSize
    		){
		 PageInfo<ElPaperVo> data = elPaperManageService.waitForReviewForManage(name, paperState, userName, groupId, tagId, pageNo, pageSize);
		 Response<PageInfo<ElPaperVo>> response = new Response<>(Response.SUCCESS, null);
		 response.setData(data);
		 return response;
		
	}
	
	/**
	 * 查询已考试单张试卷
	 */
	@RequiresPermissions("/manage/paper/GetExamPaper")
	@RequestMapping("/selectExamPaperById")
	public String selectExamPaperById(Model model,
			  @RequestParam(value="id",required=false) String id,
			  @RequestParam(value="paperState",required=false) String paperState,
			  @RequestParam(value="userId",required=false) String userId,
			  @RequestParam(value="examNum",required=false) String examNum,
			  @RequestParam(value="planCourseId",required=false) String planCourseId
			){
		ElPaperVo elPaperVo = elPaperManageService.seleByIdWaitFor(id,userId,examNum,planCourseId);
		
		
		/*List<QuestionVo> qList= elPaperVo.getQuestionVo();
		List<QuestionVo> newList= new ArrayList<>();
		
		
		String str =""; //记录 content
		String str2 =""; //记录answer
		for(QuestionVo questionVo : qList){
				if(questionVo.getType()==1){ //多选题
					 str  += questionVo.getContent()+",";
					 str2 += questionVo.getAnswer()+",";
				}else{
					newList.add(questionVo);
				}
		}
		
		String arr2[] = str2.split(",");
		
		String arr1[] = str.split(",");
		
		List<String> list = new ArrayList<>();   //去重
		list.add(arr1[0]);  
		for(int i=1;i<arr1.length;i++){  
		    if(list.toString().indexOf(arr1[i]) == -1){  
		        list.add(arr1[i]);  
		    }  
		}  
		
		int otherCount =0;
		for(String strContent : list){
			QuestionVo qo= new QuestionVo();
			int count =0;
			String strNew ="";
			for(int i=0;i<arr1.length;i++){
				if(strContent.equals(arr1[i])){
					count++;
					otherCount++;
				}
			}
			if(otherCount == count){
				for(int j=0;j<count;j++){
					strNew += arr2[j];
				}
			}else{
				for(int k =(otherCount-count);k<count;k++){
					strNew += arr2[k];
				}
			}
			
			qo.setAnswer(strNew);
			newList.add(qo);
			
		}
        
        
        
		*/
		
		model.addAttribute("elPaperVo", elPaperVo);
		model.addAttribute("id", id);
		model.addAttribute("paperState", paperState);
		return "elearning/examination/exam_paper_view";
	}
	
	/**
	 * 批改已考试试卷
	 */
	@RequiresPermissions("/manage/paper/GetExamPaper")
	@PostMapping(value="/checkExamination" )
	@ResponseBody
   public Response<String> checkExamination(@RequestParam("questionId") String questionId ,@RequestParam("scores") String scores,@RequestParam("paperId") String paperId,@RequestParam("eueId") String eueId){
		List<UserExam> userExam= new ArrayList<>();
		String[] questionIds= questionId.split(",");
		String[] sc = scores.split(",");
		String[] eueIds = eueId.split(",");
		if(sc==null || sc.length==0){
			Response<String> response = new Response<String>("0", "分数不能为空");
			return response;
		}
		if(questionIds==null || questionIds.length==0){
			Response<String> response = new Response<String>("0", "试题id不能为空");
			return response;
		}
		if(StringUtil.isEmpty(paperId)){
			Response<String> response = new Response<String>("0", "试卷id不能为空");
			return response;
		}
		if(eueIds==null || eueIds.length==0){
			Response<String> response = new Response<String>("0", "主键id不能为空");
			return response;
		}
		
		for(int i=0;i<questionIds.length;i++){
			UserExam use=new UserExam();
			Long id=null;
			Long paper=null;
			Long eue=null;
			Integer score=null;
			
			if(!StringUtil.isEmpty(questionIds[i])){
				id=Long.valueOf(questionIds[i]);
			}
			if(!StringUtil.isEmpty(sc[i])){
				score=Integer.valueOf(sc[i]);
			}
			if(!StringUtil.isEmpty(eueIds[i])){
				eue=Long.valueOf(eueIds[i]);
			}
			if(!StringUtil.isEmpty(paperId)){
				paper=Long.valueOf(paperId);
			}
			use.setQuestionId(id);
			use.setScore(score);
			use.setPaperId(paper);
			use.setId(eue);
			userExam.add(use);
		}
		Response<String> response = elPaperManageService.saveByIdUEU(userExam);
		
		return response;
	}
	
	
		
	}
	
	
	


