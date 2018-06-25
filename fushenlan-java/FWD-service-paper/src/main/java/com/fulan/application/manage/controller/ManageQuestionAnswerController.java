package com.fulan.application.manage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.api.paper.domain.el.QuestionAnswer;
import com.fulan.application.service.QuestionAnswerService;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 试卷答案manage控制器
 * </p>
 *
 * @author Hedge
 * @since 2018-01-23
 */
@RestController
@RequestMapping(value ="/manage/questionAnswer")
public class ManageQuestionAnswerController {
	
	@Autowired
	private QuestionAnswerService questionAnswerService;
	
	private static final Logger logger = LoggerFactory.getLogger(ManageQuestionAnswerController.class);

	/**
	 * 试题保存
	 * 接收Question
	 */
	@PostMapping("/save")
	public Response<String> save(@RequestBody QuestionAnswer questionAnswer){
		try {
			return questionAnswerService.saveForManage(questionAnswer);
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
			return questionAnswerService.deleteForManage(id);
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
			return questionAnswerService.deleteBatchIdsForManage(ids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"试题删除失败");
		}
	}
	
	/**
	 * 试题更新
	 */
	@PostMapping("/update")
	public Response<String> update(@RequestBody QuestionAnswer questionAnswer){
		try {
			return questionAnswerService.updateForManage(questionAnswer);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"试题更新失败");
		}
	}
	
	
	/**
	 * 根据Id查询试题
	 */
	@PostMapping("/seleById")
	public Response<QuestionAnswer> seleById(@RequestParam("id") Long id){
		try {
			return questionAnswerService.seleByIdForManage(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<QuestionAnswer>(Response.ERROR,"试题新增失败");
		}
		
	}
	
	/**
	 * 记录数
	 */
	@PostMapping("/seleCount")
	public Response<Integer> seleCount(@RequestBody QuestionAnswer questionanswer){
		try {
			return questionAnswerService.seleCountForManage(questionanswer); 
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Integer>(Response.ERROR,"试题新增失败");
		}
	}
}
