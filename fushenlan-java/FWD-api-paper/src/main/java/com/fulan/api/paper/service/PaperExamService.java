package com.fulan.api.paper.service;

import com.fulan.api.paper.vo.ExamPaperVo;
import com.fulan.application.util.domain.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("paper")
public interface PaperExamService {

    @GetMapping(value = "/customer/exam/getPaper")
    public Response<ExamPaperVo> getPaper();
}
