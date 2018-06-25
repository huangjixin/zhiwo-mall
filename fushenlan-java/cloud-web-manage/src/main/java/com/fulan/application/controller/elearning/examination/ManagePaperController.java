package com.fulan.application.controller.elearning.examination;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.material.vo.MaterialCourseVo;
import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.paper.domain.el.Question;
import com.fulan.api.paper.service.ElPaperManageService;
import com.fulan.api.paper.service.PaperManageService;
import com.fulan.api.paper.vo.ElPaperVo;
import com.fulan.api.paper.vo.ExamAccountVo;
import com.fulan.api.security.domain.Account;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;

@Controller
@RequestMapping("/manage/paper")
public class ManagePaperController {
	@Autowired
	private ElPaperManageService elPaperManageService;
	
	@Autowired
	private PaperManageService paperManageService;
	
	/**
	 * 查询公共试卷
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
	@RequestMapping(value="/GetPublicPaper")
    public String getPaper(
    		 Model model,
    		 @RequestParam(value="type",required=false) String type,
    		 @RequestParam(value="pubType",required=false) String pubType,
             @RequestParam(value="submitter",required=false) String submitter,
             @RequestParam(value="createUser",required=false) String createUser,
             @RequestParam(value="name",required=false) String name,
             @RequestParam(value="groupId",required=false) String groupId,
             @RequestParam(value="tagId",required=false) String tagId,
             @RequestParam(value="pageNo",defaultValue="1") int pageNo,
             @RequestParam(value="pageSize",defaultValue="10") int pageSize,
             @RequestParam(value="otherType",required=false) String otherType
    		){
		if("0".equals(otherType) || null ==otherType){
			submitter="1";
			PageInfo<ElPaperVo> data = elPaperManageService.listForManage(type, pubType, submitter, createUser, name, groupId, tagId, pageNo, pageSize);
			String pType="1";
			model.addAttribute("pType",pType);
			model.addAttribute("data",data);
			model.addAttribute("name",name);
			model.addAttribute("type",type);
			model.addAttribute("pubType",pubType);
			model.addAttribute("submitter",submitter);
			model.addAttribute("createUser",createUser);
			model.addAttribute("groupId",groupId);
			model.addAttribute("tagId",tagId);
		}else if("1".equals(otherType)){
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
		}
		
		return "elearning/examination/public_paper";
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
	@RequestMapping(value="/AjaxPaper")
	@ResponseBody
    public Response<PageInfo<ElPaperVo>> AjaxPaper(
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
		
		
		submitter="1";
		
		PageInfo<ElPaperVo> data = elPaperManageService.listForManage(type, pubType, submitter, createUser, name, groupId, tagId, pageNo, pageSize);
		Response<PageInfo<ElPaperVo>> response = new Response<>(Response.SUCCESS, null);
		response.setData(data);
		return response;
	}
	
	/**
	 * 查询单张试卷
	 */

	@RequiresPermissions("/manage/paper/GetPublicPaper")
	@RequestMapping("/selectPaper")
	public String selectPaper(Model model,
			  @RequestParam(value="id",required=false) String id,
			  @RequestParam(value="userName" ,required=false) String userName,
			  @RequestParam(value="questionType" ,required=false) String questionType){
		List<ElPaperVo> data = elPaperManageService.seleByIdPublic(id);
		ElPaperVo elPaperVo=data.get(0);
		List<ExamAccountVo> eList = paperManageService.getExamAccountVo(userName, questionType, id);
		String sel="A,B,C,D,E,F,G,H,I,G,K,L,M,N";
		String[] arr= sel.split(",");
		model.addAttribute("elPaperVo", elPaperVo);
		model.addAttribute("eList", eList);
		model.addAttribute("id", id);
		model.addAttribute("arr", arr);
		return "elearning/examination/paper_view";
	}
	
	
	@RequestMapping("/selectOtherPaper")
	@ResponseBody
	 public Response<List<ExamAccountVo>> selectOtherPaper(Model model,String userName,String questionType,String id){
		 /*String userName = request.getParameter("userName");
		 String questionType = request.getParameter("questionType");
		 String id = request.getParameter("id");*/
	    	Response<List<ExamAccountVo>> res = new Response<List<ExamAccountVo>>();
	    	List<ExamAccountVo> eList = paperManageService.getExamAccountVo(userName, questionType, id);
	    	res.setData(eList);
	    	return  res;
	 }
	
	
	@RequestMapping("/checkInfo")
	public String checkInfo(Model model, @RequestParam(value="userName" ,required=false) String userName){
		List<ExamAccountVo> eList = paperManageService.getExamAccountOtherVo(userName);
/*		List<ExamAccountVo> aList = new ArrayList<>();//单选
		List<ExamAccountVo> bList = new ArrayList<>();//多选
		List<ExamAccountVo> cList = new ArrayList<>();//判断
		List<ExamAccountVo> dList = new ArrayList<>();//问答
		if(null!=eList){
			for(ExamAccountVo evo : eList) {
				String type = evo.getQuestionType().toString();
				if("0".equals(type)){
					aList.add(evo);
				}else if("1".equals(type)){
					bList.add(evo);
				}else if("2".equals(type)){
					cList.add(evo);
				}else if("3".equals(type)){
					dList.add(evo);
				}
			}
		}
		model.addAttribute("aList", aList);
		model.addAttribute("bList", bList);
		model.addAttribute("cList", cList);
		model.addAttribute("dList", dList);*/
		model.addAttribute("eList", eList);
		model.addAttribute("eavo", eList.get(0));
		return "elearning/examination/exam_person_view";
		
	}
	
	
}
