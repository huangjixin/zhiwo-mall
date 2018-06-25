package com.fulan.application.manage.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.fulan.api.paper.domain.el.Question;
import com.fulan.api.paper.vo.QuestionVo;
import com.fulan.application.service.QuestionService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;


/**
 * <p>
 * 试卷前端控制器
 * </p>
 *
 * @author Hedge
 * @since 2018-01-23
 */
@RestController
@RequestMapping(value ="/manage/question")
public class ManageQuestionController {
	
	@Autowired
	QuestionService questionService;
	
	private static final Logger logger = LoggerFactory.getLogger(ManageQuestionController.class);

	
	/**
	 * 试题保存
	 * 接收Question
	 */
	@PostMapping("/save")
	public Response<String> save(@RequestBody QuestionVo questionVo){
		try {
			return questionService.saveForManage(questionVo);
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
			return questionService.deleById(id);
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
			return questionService.deleteBatchIdsForManage(ids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"试题删除失败");
		}
	}
	
	/**
	 * 试题更新
	 */
	@PostMapping("/update")
	public Response<String> update(@RequestBody QuestionVo questionVo){
		try {
			return questionService.updateForManage(questionVo);
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
	public PageInfo<Question> list(
            @RequestParam(value="type",required=false) String type,
            @RequestParam(value="pubType",required=false) String pubType,
            @RequestParam(value="submitter",required=false) String submitter,
            @RequestParam(value="createUser",required=false) String createUser,
            @RequestParam(value="content",required=false) String content,
            @RequestParam(value="groupId",required=false) String groupId,
            @RequestParam(value="tagId",required=false) String tagId,
            @RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		Page<Question> page = new Page<Question>(pageNo, pageSize);
		PageInfo<Question> pageInfo = questionService.listForManage(page, type, pubType, submitter, createUser, content, groupId, tagId, pageNo, pageSize);
		return pageInfo;
	}
	
	
	
	/**
	 * 查询试题列表
	 */
	@RequestMapping(value="/listOther",method=RequestMethod.GET)
	@ResponseBody
	public List<Question> listOther(
            @RequestParam(value="type",required=false) String type,
            @RequestParam(value="tagId",required=false) String tagId,
            @RequestParam(value="keyWord",required=false)String keyWord,
			@RequestParam(value="createUser",required=false)String createUser){
		return questionService.listForOtherManage(type, tagId,keyWord,createUser);
	}
	
	/**
	 * 根据Id查询试题
	 */
	@PostMapping("/seleById")
	public Response<Question> seleById(@RequestParam("id") Long id){
		try {
			return questionService.seleByIdForManage(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Question>(Response.ERROR,"试题新增失败");
		}
		
	}
	
	/**
	 * 记录数
	 */
	@PostMapping("/seleCount")
	public Response<Integer> seleCount(@RequestBody Question question){
		try {
			return questionService.seleCountForManage(question); 
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Integer>(Response.ERROR,"试题新增失败");
		}
	}
	
	/**
	 * 随机选题
	 * @param list
	 * @return
	 */
	@PostMapping("/seleRandom")
	public Response<List<Question>> seleRandom(@RequestBody List<Map<String,Object>> mapList){
		try {
			return questionService.seleRandomForManage(mapList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<List<Question>>(Response.ERROR,"访问数据失败");
		}
		
	}
	
	@PostMapping("/questionAndAnswer")
	public QuestionVo questionAndAnswer(@RequestParam("id") String id) {
		return questionService.questionAndAnswer(id);
	}
	
	/**
	 * 根据id 查询是否关联试卷
	 */
	@PostMapping("/isNotRelation")
    public List<Map<String,String>> isNotRelation(@RequestParam("ids") String ids){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		list = questionService.isNotRelation(ids);
		return list;
	}
	/**
	 * 根据id 查询是否关联试卷
	 */
	@PostMapping("/selectPaperName")
    public List<String> selectPaperName(@RequestParam("ids") String ids){
		List<String> list = new ArrayList<String>();
		list = questionService.selectPaperName(ids);
		return list;
	}
	
	@PostMapping("/seleByIdList")
	public List<QuestionVo> seleByIdList(@RequestParam("idList") List<Long> idList){
		return questionService.seleByIdList(idList);
	}
	
	
	//分享
	@RequestMapping("shareQuestion")
	public @ResponseBody Response<Boolean> sharePaper(String[] groupIds,String[] questionIds){
		Response<Boolean> res = new Response<>();
		res.setData(questionService.shareQuestion(groupIds, questionIds));	
		return res;
	}
}
