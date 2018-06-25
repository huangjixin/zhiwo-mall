package com.fulan.api.plan.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.plan.domain.PlanQuestion;
import com.fulan.api.plan.domain.PublicClass;
import com.fulan.application.util.domain.Response;

@FeignClient(name = "plan")
public interface ManagePlanQuestionService {

	@GetMapping(value = "/manage/publicClass/selectQuestionById")
	PlanQuestion selectQuestionById(@RequestParam(value="questionId")String questionId);

	@PostMapping(value = "/manage/publicClass/updateQues")
	Response<Boolean> updateQuestion(@RequestBody PlanQuestion planQuestion);

}
