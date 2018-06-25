package com.fulan.application.controller.elearning.examination;

import java.util.List;

import javax.servlet.ServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fulan.api.material.vo.MaterialCourseVo;
import com.fulan.api.paper.domain.el.Question;
import com.fulan.api.paper.service.QuestionService;
import com.fulan.api.paper.vo.QuestionVo;
import com.fulan.api.plan.vo.StudyPlanVvo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;

@Controller
@RequestMapping("/manage/question")
public class ManageExaminationController {
	
	@Autowired
	private QuestionService questionService;
	 
	@RequiresPermissions("/manage/question/GetPulicQuestion")
	@RequestMapping(value="/GetPulicQuestion")
    public String getPulicQuestion(
    		 Model model,
    		 @RequestParam(value="type",required=false) String type,
             @RequestParam(value="pubType",required=false) String pubType,
             @RequestParam(value="submitter",required=false) String submitter,
             @RequestParam(value="createUser",required=false) String createUser,
             @RequestParam(value="content",required=false) String content,
             @RequestParam(value="groupId",required=false) String groupId,
             @RequestParam(value="tagId",required=false) String tagId,
             @RequestParam(value="pageNo",defaultValue="1") String pageNo,
             @RequestParam(value="pageSize",defaultValue="10") String pageSize,
             @RequestParam(value="otherType",required=false) String otherType
    		){
		
		if("0".equals(otherType) || null == otherType){
			submitter="1";
			pageNo = StringUtil.isEmpty(pageNo)?"1":pageNo;
			pageSize =  StringUtil.isEmpty(pageNo)?"10":pageSize;
			PageInfo<Question> data=questionService.listForManage(type, pubType, submitter, createUser, content, groupId, tagId, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
			String pType="1";
			model.addAttribute("pType",pType);
			model.addAttribute("page", data);
			model.addAttribute("type",type);
			model.addAttribute("pubType",pubType);
			model.addAttribute("submitter",submitter);
			model.addAttribute("createUser",createUser);
			model.addAttribute("content",content);
			model.addAttribute("groupId",groupId);
			model.addAttribute("tagId",tagId);
		  }else if("1".equals(otherType)){

			if(StringUtil.isEmpty(pubType)){
				pubType="1";
			}
			if(StringUtil.isEmpty(submitter)){
				submitter="1";
				
			}
			PageInfo<Question> data=questionService.listForManage(type, pubType, submitter, createUser, content, groupId, tagId, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
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
		}
		
		return "elearning/examination/public_question";
	}
	
	/**
	 * ajax异部刷新
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
	@RequiresPermissions("/manage/question/GetPulicQuestion")
	@RequestMapping(value="/ajaxPulicQuestion")
	@ResponseBody
    public Response<PageInfo<Question>> ajaxPulicQuestion(
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
		
		
		submitter="1";
		
		pageNo = StringUtil.isEmpty(pageNo)?"1":pageNo;
		pageSize =  StringUtil.isEmpty(pageSize)?"10":pageSize;
		PageInfo<Question> data=questionService.listForManage(type, pubType, submitter, createUser, content, groupId, tagId, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		Response<PageInfo<Question>> response = new Response<>(Response.SUCCESS, null);
		response.setData(data);
		return response;
		
	}
	
	/**
	 * 查询questionById
	 * @param model
	 * @param request
	 * @return
	 */
	@RequiresPermissions("/manage/question/GetPulicQuestion")
	@RequestMapping("/selectQuestionById")
	public String selectQuestionById(Model model,ServletRequest request){
		String id=request.getParameter("id");
		
		QuestionVo qv=questionService.questionAndAnswer(id);
		model.addAttribute("questionVo",qv);
		return "elearning/examination/ggtk_view";
	}
	
	
	
	
		
	}
	
	
	


