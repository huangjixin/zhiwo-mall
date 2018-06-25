package com.fulan.application.manage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.api.paper.domain.el.PaperQuesShare;
import com.fulan.application.service.PaperQuesShareService;
import com.fulan.application.util.domain.Response;

@RestController
@RequestMapping(value ="/manage/paperQuesShare")
public class PaperQuesShareController {
	@Autowired
	private PaperQuesShareService paperQuesShareService;
	
	private static final Logger logger = LoggerFactory.getLogger(PaperQuesShareController.class);
	/**
	 * 试题保存
	 * 接收Question
	 */
	@PostMapping("/save")
	public Response<String> saveForManage(@RequestBody PaperQuesShare paperQuesShare){
		try {
			return paperQuesShareService.saveForManage(paperQuesShare);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"新增分享失败");
		}
	}
	
	/**
	 * 试题批量保存
	 * type 1试题2试卷
	 * groupIds 多个用户组Id以逗号分割
	 * hostId 试卷/试题Id
	 */
	@PostMapping("/saveBatch")
	public Response<Integer> saveBatchForManage(@RequestParam(value = "hostIds") String hostIds ,
			@RequestParam(value = "type") String type, 
			@RequestParam(value = "groupIds") String groupIds,
			@RequestParam(value = "isShare") String isShare
			){
		try {
			return paperQuesShareService.saveBatch(hostIds, type, groupIds,isShare);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Integer>(Response.ERROR,"新增分享失败");
		}
	}
	
	/**
	 * 根据id删除试题
	 */
	@PostMapping("/delete")
	Response<String> deleteByIdForManage(@RequestParam("id") Long id){
		try {
			return paperQuesShareService.deleteForManage(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"删除分享失败");
		}
	}
	
	/**
	 * 批量删除试题
	 */
	@PostMapping("/deleteBatchIds")
	Response<String> deleteBatchIdsForManage(@RequestParam("ids") String ids){
		try {
			return paperQuesShareService.deleteBatchIdsForManage(ids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"删除分享失败");
		}
	}
	
	/**
	 * 试题更新
	 */
	@PostMapping("/update")
	Response<String> updateForManage(@RequestBody PaperQuesShare paperQuesShare){
		try {
			return paperQuesShareService.updateForManage(paperQuesShare);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"更新分享失败");
		}
	}
	
	/**
	 * 查询试题列表
	 */
	@PostMapping("/list")
	Response<List<PaperQuesShare>> listForManage(@RequestBody PaperQuesShare paperQuesShare){
		try {
			return paperQuesShareService.listForManage(paperQuesShare);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<List<PaperQuesShare>>(Response.ERROR,"查询分享失败");
		}
	}
	
	/**
	 * 根据Id查询试题
	 */
	@PostMapping("/seleById")
	Response<PaperQuesShare> seleByIdForManage(@RequestParam("id") Long id){
		try {
			return paperQuesShareService.seleByIdForManage(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<PaperQuesShare>(Response.ERROR,"查询分享失败");
		}
	}
	
	/**
	 * 记录数
	 */
	@PostMapping("/seleCount")
	Response<Integer> seleCountForManage(@RequestBody PaperQuesShare paperQuesShare){
		try {
			return paperQuesShareService.seleCountForManage(paperQuesShare);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Integer>(Response.ERROR,"查询分享失败");
		}
	}
	
	
	/**
	 * 查询试题已分享的范围
	 */
	@GetMapping("/selectShareQuestion")
	List<Long>  selectShareQuestion(@RequestParam("id") String id){
		List<Long> list=paperQuesShareService.selectShareQuestion(id);
	return list;
	}
	
	

	/**
	 * 查询试卷已分享的范围
	 */
	@GetMapping("/selectSharePaper")
	List<Long>  selectSharePaper(@RequestParam("id") String id){
		List<Long> list=paperQuesShareService.selectSharePaper(id);
	return list;
	}
}
