package com.fulan.application.manage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.paper.domain.er.Paper;
import com.fulan.api.paper.vo.ExamAccountVo;
import com.fulan.api.paper.vo.PaperDetailVo;
import com.fulan.api.paper.vo.PaperManageVo;
import com.fulan.api.paper.vo.PaperVo;
import com.fulan.application.service.PaperExamService;
import com.fulan.application.service.PaperService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value ="/manage/paper")
public class PaperController {
	
	@Autowired
	PaperService paperService;
	
	@Autowired
	private PaperExamService paperExamService;
	
	
	
	/**
	 * 学员考试列表
	 * @param userName
	 * @param questionType
	 * @param paperId
	 * @return
	 */
	@RequestMapping(value="/getExamAccountVo",method=RequestMethod.GET)
	public @ResponseBody List<ExamAccountVo> getExamAccountVo(
			@RequestParam(name="userName",required = false) String userName , 
 			@RequestParam(name="questionType",required = false) String questionType,
			@RequestParam(name="id",required = false) String id){
		return paperExamService.getExamAccountVo(userName, questionType, id);
	}
	
	@RequestMapping(value="/getExamAccountOtherVo",method=RequestMethod.GET)
	public @ResponseBody List<ExamAccountVo> getExamAccountOtherVo(
			@RequestParam(name="userName",required = false) String userName
 			){
		return paperExamService.getExamAccountOtherVo(userName);
	}
	
	
	@RequestMapping(value="/paperCheck",method=RequestMethod.POST)
	public @ResponseBody PaperVo getPaperVo(@RequestBody Paper paper){
		return paperService.paperCheck(paper);
	}
	
	@RequestMapping(value="/addPaperVo",method=RequestMethod.POST)
	public String addPaperVo(@RequestBody PaperVo paperVo){
		String str2 = paperService.paperHandle(paperVo);
		return str2;
	}
	@RequestMapping(value="/paperManagePage",method=RequestMethod.GET)
	public  @ResponseBody PageInfo<PaperManageVo> listByPage(  
			@RequestParam String keyWord , 
			@RequestParam String orgId , 
			@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize){
		    Page<PaperManageVo> page = new Page<PaperManageVo>(pageNo, pageSize);
		return	paperService.listByPage(page, keyWord,orgId, pageNo, pageSize);
	}
	
	@RequestMapping(value="/deletePaperVo",method=RequestMethod.POST)
	public @ResponseBody boolean deletePaperVo(@RequestParam String id){
		return paperService.deletePaperVo(id);
	}
	
	
	@PostMapping("/getPaperDetailVo")
	@ResponseBody
	public Response<List<PaperDetailVo>> getPaperVo(@RequestBody Map<String,Object> map){
		return paperService.selectPaperDetailList(map);
	}
	
	
	
}
