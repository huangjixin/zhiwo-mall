package com.fulan.application.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.information.vo.NewsManageVo;
import com.fulan.application.service.IRisNewsService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

@RestController
@RequestMapping(value ="/manage/News")
public class NewsController {
	@Autowired
	private IRisNewsService iRisNewsService;
	
	 /**
		 * 查咨询列表
		 */
		@RequestMapping(value="/ListNew",method=RequestMethod.GET)
		@ResponseBody
		public PageInfo<NewsManageVo> ListNew(
	            @RequestParam(value="title",required=false) String title,
	            @RequestParam(value="type",required=false) String type,
	            @RequestParam(name="status",required = false) String status,
				 @RequestParam(value="satrtTime",required=false) String satrtTime,
	    		 @RequestParam(value="endTime",required=false) String endTime,
	            @RequestParam(value="pageNo",defaultValue="1") int pageNo,
	            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
			Page<NewsManageVo> page = new Page<NewsManageVo>(pageNo, pageSize);
			PageInfo<NewsManageVo> pageInfo = iRisNewsService.ListNew(page, title,type,status,satrtTime,endTime, pageNo, pageSize);
			return pageInfo;
		}
		
		 /**
		 * 咨询发布，撤回
		 */
		@RequestMapping(value="/updateStatus",method=RequestMethod.GET)
		@ResponseBody
		public Response<String> updateStatus(
				@RequestParam(name="id" ,required = false)String id,
				@RequestParam(name="status" ,required = false)String status){
			Response<String> response = iRisNewsService.updateStatus(id,status);
			return response;
		}
		
		/**
		 * 删除咨询
		 */
		@RequestMapping(value="/deleteNews",method=RequestMethod.GET)
		@ResponseBody
		public Response<String> deleteNews(
				@RequestParam(name="id" ,required = false)String id){
			Response<String> response = iRisNewsService.deleteNews(id);
			return response;
		}
		
		/**
		 * 新增咨询
		 */
		@PostMapping(value="/insertNews")
		@ResponseBody
		public Response<String> insertNews(@RequestBody NewsManageVo newsManageVo,
				@RequestParam(name="pathId" ,required = false)String pathId,
				@RequestParam(name="FilePathId" ,required = false)String FilePathId){
			Response<String> response = iRisNewsService.insertNews(newsManageVo,pathId,FilePathId);
			return response;
		}
		
		/**
		 * 修改咨询
		 */
		@PostMapping(value="/updateNews")
		@ResponseBody
		public Response<String> updateNews(@RequestBody NewsManageVo newsManageVo,
				@RequestParam(name="pathId" ,required = false)String pathId,
				@RequestParam(name="FilePathId" ,required = false)String FilePathId){
			Response<String> response = iRisNewsService.updateNews(newsManageVo,pathId,FilePathId);
			return response;
		}
		
		
		 /**
		 * 通过id查询咨询对象
		 */
		@RequestMapping(value="/selectOneNewsById",method=RequestMethod.GET)
		@ResponseBody
		public NewsManageVo selectOneNewsById(@RequestParam(value="id",required=false) String id){
			NewsManageVo newsManageVo =iRisNewsService.selectOneNewsById(id);
			return newsManageVo;
		}
}
