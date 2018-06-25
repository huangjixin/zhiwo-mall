package com.fulan.api.plan.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.domain.PublicClass;
import com.fulan.api.plan.vo.PublicClassInsertVo;
import com.fulan.api.plan.vo.PublicClassVo;
import com.fulan.api.plan.vo.PublicCourseDto;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

@FeignClient(name = "plan")
public interface ManagePublicClassService {

	@PostMapping("/manage/publicClass/pageList")
	PageInfo<PublicCourseDto> pageList(@RequestBody Map<String,Object> map);

	@PostMapping("/manage/publicClass/publicClassListByPage")
	PageInfo<PublicCourseDto> publicClassListByPage(@RequestParam(value="map")Map<String, Object> map, @RequestParam(value="page")Page<PublicCourseDto> page);

	@PostMapping(value = "/manage/publicClass/batchShelves")
	Response<Boolean> theBatchShelvesPublicClasss(
				@RequestParam("publicClassIds")String[] publicClassIds,
			    @RequestParam("state")String state);

	@GetMapping(value = "/manage/publicClass/checkInfo")
	PublicClassVo getPublicClassInfo(@RequestParam(value="id")String id);

	@PostMapping(value = "/manage/publicClass/listQuestByPage")
	PageInfo<Map<String, Object>> listQuestionByPage(@RequestBody Map<String, Object> map);

	@PostMapping(value = "/manage/publicClass/listCommentByPage")
	PageInfo<Map<String, Object>> listCommentByPage(@RequestBody Map<String, Object> map);

	@PostMapping(value = "/manage/publicClass/hideOrOpenAll")
	Response<Boolean> theBatchQuestionOrComment(@RequestParam(value="id")String[] ids,
							@RequestParam(value="type")String type,
							@RequestParam(value="isOpen")String state);
	
	@PostMapping(value = "/manage/publicClass/creatPubClass")
	String submitPublicClass(@RequestBody PublicClassInsertVo publicClassInsertVo,
	        @RequestParam(name="fileId",required=false)Long fileId);

	@PostMapping(value = "/manage/publicClass/updatePublicClass")
	String updatePublicClass(@RequestBody PublicClass publicClass);
	
	@PostMapping(value = "/manage/publicClass/updatePubClass")
	String updatePubClass(@RequestBody PublicClassInsertVo publicClassInsertVo, 
	        @RequestParam(name="fileId",required=false)Long fileId);
	
	@GetMapping(value = "/manage/publicClass/selectByClassId")
	PublicClass selectByClassId(@RequestParam(value="id")String id);
	
}
