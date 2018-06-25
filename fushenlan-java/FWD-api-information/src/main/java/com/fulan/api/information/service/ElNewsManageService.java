package com.fulan.api.information.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.information.vo.NewsManageVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

@FeignClient(name = "information")
public interface ElNewsManageService {
	
	/**
	 * 分页查询咨询
	 * 
	 */
	@GetMapping("/manage/News/ListNew")
	PageInfo<NewsManageVo> ListNew(
			@RequestParam(name="title" ,required = false)String title,
			@RequestParam(name="type",required = false) String type,
			@RequestParam(name="status",required = false) String status,
			 @RequestParam(value="satrtTime",required=false) String satrtTime,
    		 @RequestParam(value="endTime",required=false) String endTime,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
			);
	
	/**
	 * 咨询发布，撤回
	 * 
	 */
	@GetMapping("/manage/News/updateStatus")
	Response<String> updateStatus(
			@RequestParam(name="id" ,required = false)String id,
			@RequestParam(name="status" ,required = false)String status
			);
	
	/**
	 * 删除咨询
	 * 
	 */
	@GetMapping("/manage/News/deleteNews")
	Response<String> deleteNews(
			@RequestParam(name="id" ,required = false)String id
			);
	

	/**
	 * 新增咨询
	 * 
	 */
	@PostMapping("/manage/News/insertNews")
	Response<String> insertNews(@RequestBody NewsManageVo newsManageVo,
					@RequestParam(name="pathId" ,required = false)String pathId,
					@RequestParam(name="FilePathId" ,required = false)String FilePathId
			);
	
	
	
	/**
	 * 修改咨询
	 * 
	 */
	@PostMapping("/manage/News/updateNews")
	Response<String> updateNews(@RequestBody NewsManageVo newsManageVo,
			@RequestParam(name="pathId" ,required = false)String pathId,
			@RequestParam(name="FilePathId" ,required = false)String FilePathId);
	
	

	/**
	 * 通过id查询咨询对象
	 * 
	 */
	@GetMapping("/manage/News/selectOneNewsById")
	NewsManageVo selectOneNewsById(@RequestParam(name="id" ,required = false)String id);
}
