package com.fulan.application.manage.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.paper.domain.el.PaperQuestion;
import com.fulan.api.paper.domain.el.UserExam;
import com.fulan.api.paper.vo.ElPaperVo;
import com.fulan.api.paper.vo.PaperManageMinVo;
import com.fulan.api.paper.vo.PaperPlanNameVo;
import com.fulan.api.paper.vo.PaperQuestionVo;
import com.fulan.application.service.ElPaperService;
import com.fulan.application.service.PaperQuestionService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

@RestController
@RequestMapping(value ="/manage/elPaper")
public class ElPaperController {
	@Autowired
	private ElPaperService elPaperService;
	
	@Autowired
	private PaperQuestionService paperQuestionService;
	
	private static final Logger logger = LoggerFactory.getLogger(ElPaperController.class);

	
	/**
	 * 试题保存
	 * 接收Question
	 */
	@PostMapping("/save")
	public Response<String> save(@RequestBody PaperQuestionVo paperQuestionVo){
		try {
			return elPaperService.saveForManage(paperQuestionVo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"试题新增失败");
		}
	}
	
	/**
	 * 根据id删除试题
	 */
	@PostMapping("/delete")
	public Response<String> deleteById(@RequestParam("id") Long id){
		try {
			return elPaperService.deleteForManage(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"试题删除失败");
		}
	}
	
	/**
	 * 批量删除试题
	 */
	@PostMapping("/deleteBatchIds")
	public Response<String> deleteBatchIds(@RequestParam("ids") String ids){
		try {
			return elPaperService.deleteBatchIdsForManage(ids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"试题删除失败");
		}
	}
	
	/**
	 * 试题更新
	 */
	@PostMapping("/update")
	public Response<String> update(@RequestBody PaperQuestionVo paperQuestionVo){
		try {
			return elPaperService.updateForManage(paperQuestionVo);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"试题更新失败");
		}
	}
	
	/**
	 * 查询试题列表
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo<ElPaperVo> list(
            @RequestParam(value="type",required=false) String type,
            @RequestParam(value="pubType",required=false) String pubType,
            @RequestParam(value="submitter",required=false) String submitter,
            @RequestParam(value="createUser",required=false) String createUser,
            @RequestParam(value="name",required=false) String name,
            @RequestParam(value="groupId",required=false) String groupId,
            @RequestParam(value="tagId",required=false) String tagId,
            @RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		Page<ElPaperVo> page = new Page<ElPaperVo>(pageNo, pageSize);
		PageInfo<ElPaperVo> pageInfo = elPaperService.listForManage(page, type, pubType, submitter, createUser, name, groupId, tagId, pageNo, pageSize);
		return pageInfo;
	}
	
	/**
	 * 查询试题列表
	 */
	@RequestMapping(value="/waitForReview",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo<ElPaperVo> waitForReview(
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="paperState",required=false) String paperState,
			@RequestParam(value="userName",required=false) String userName,
			@RequestParam(value="groupId",required=false) String groupId,
			@RequestParam(value="tagId",required=false) String tagId,
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
			@RequestParam(value="pageSize",defaultValue="10") int pageSize){
		Page<ElPaperVo> page = new Page<ElPaperVo>(pageNo, pageSize);
		PageInfo<ElPaperVo> pageInfo = elPaperService.waitForReview(page, name, paperState, userName, groupId, tagId, pageNo, pageSize);
		return pageInfo;
	}
	
	/**
	 * 根据Id查询试题
	 */
	@PostMapping("/seleById")
	public Response<Paper> seleById(@RequestParam("id") Long id){
		try {
			return elPaperService.seleByIdForManage(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Paper>(Response.ERROR,"试题新增失败");
		}
		
	}
	
	@PostMapping("/delectBatchPQIds")
	public Response<String> delectBatchPQIds(@RequestParam("ids") String ids){
		try {
			return paperQuestionService.delectBatchIds(ids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"删除数据失败");
		}
		
	}
	
	@PostMapping("/savePQ")
	public Response<String> savePQ(@RequestParam("paperQuestion") List<PaperQuestion> paperQuestion){
		try {
			return paperQuestionService.save(paperQuestion);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"新增数据失败");
		}
	}
	
	@PostMapping("/delectPQ")
	public Response<String> delectPQ(@RequestParam("paperId") String paperId,@RequestParam("questionId") String questionId){
		try {
			return paperQuestionService.delect(paperId,questionId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"删除数据失败");
		}
	}
	
	@PostMapping("/seleByIdVo")
	public List<ElPaperVo> seleByIdVo(String id) {
		return elPaperService.seleByIdVo(id);
	}
	
	@PostMapping("/saveUEU")
	public Response<String> saveUEU(@RequestBody List<UserExam> userExam) {
		try {
			return elPaperService.saveUEU(userExam);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"更新数据失败");
		}
	}
	
	@PostMapping("/listObject")
	public Response<List<Paper>> listObject(@RequestBody Paper paper){
		try {
			return elPaperService.listObject(paper);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<List<Paper>>(Response.ERROR,"访问数据失败");
		}
	}
	
	
	@RequestMapping(value="/selectByAllPaper",method=RequestMethod.GET)
	@ResponseBody
	public List<Paper> paperList(){
		return elPaperService.paperList();
	}
	
	@RequestMapping(value="/seleByIdPublic",method=RequestMethod.POST)
	@ResponseBody
	public List<ElPaperVo> seleByIdPublic(String id) {
		return elPaperService.seleByIdPublic(id);
	}
	@RequestMapping(value="/seleByIdWaitFor",method=RequestMethod.GET)
	@ResponseBody
	public ElPaperVo seleByIdWaitFor(@RequestParam(value="id")String id,
			@RequestParam(value="userId") String userId,
			@RequestParam(value="examNum") String examNum,
			@RequestParam(value="planCourseId") String planCourseId) {
		return elPaperService.seleByIdWaitFor(id,userId,examNum, planCourseId);
	}
	
	
	@RequestMapping(value="selectByCId",method=RequestMethod.GET)
	public @ResponseBody List<PaperManageMinVo> selectByCId(@RequestParam String cId){
		return elPaperService.seleBycId(cId);
	}
	
	@RequestMapping(value="seleByGroupId",method=RequestMethod.GET)
	public @ResponseBody Response<List<Paper>> seleByGroupId(@RequestParam(value="submitter",required=false) Integer submitter ,
			@RequestParam(value = "name" , required = false) String name,
			@RequestParam(value = "type" , required = false) Integer type){
		try {
			return elPaperService.seleByGroupId(submitter, name, type);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<List<Paper>>(Response.ERROR,"访问数据失败");
		}
	}
	/**
	 * 根据id 查询是否关联计划
	 */
	@PostMapping("/selectPlanName")
    public List<PaperPlanNameVo> selectPlanName(@RequestParam("ids") String ids){
		List<PaperPlanNameVo> list = new ArrayList<PaperPlanNameVo>();
		list = elPaperService.selectPlanName(ids);
		return list;
	}
    
	
	//分享
		@RequestMapping("sharePaper")
		public @ResponseBody Response<Boolean> sharePaper(String[] groupIds,String[] paperIds){
			Response<Boolean> res = new Response<>();
			res.setData(elPaperService.sharePaper(groupIds, paperIds));	
			return res;
		}
}
