package com.fulan.api.calendar.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fulan.application.util.page.PageInfo;

@FeignClient(name = "calendar")
public interface ManagePublicClassService {



	@PostMapping(value = "/manage/publicClass/listCommentByPage")
	PageInfo<Map<String, Object>> listCommentByPage(@RequestBody Map<String, Object> map);
}
