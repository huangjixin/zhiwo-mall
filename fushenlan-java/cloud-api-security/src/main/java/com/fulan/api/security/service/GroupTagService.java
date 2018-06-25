package com.fulan.api.security.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.security.domain.TagTarget;
import com.fulan.application.util.domain.Response;

@FeignClient(name = "security")
public interface GroupTagService {

	@GetMapping("/customer/grouptag/addGroupTag")
	Response<String> addGroupTag(TagTarget tagtarget);
	
	@GetMapping("/customer/grouptag/listByHostIdAndType")
	List<TagTarget> listByHostIdAndType(@RequestParam(value="hostId", required=true) Long hostId, 
	        @RequestParam(value="type", required=false) Integer type);
	
	@GetMapping("/customer/grouptag/findByTagId")
	TagTarget findByTagId(@RequestParam(value="tagId", required=true) Long tagId);
	
	/**
	 * map中的key值为数据库中的表字段名(不是实体类字段名)
	 * @param map
	 * @return
	 */
	@PostMapping("/customer/grouptag/deleteByMap")
	int deleteByMap(@RequestParam(value="hostId", required=false) Long hostId);
}
