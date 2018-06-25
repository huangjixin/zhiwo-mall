package com.fulan.api.plan.service;

import com.fulan.api.plan.vo.ClassPlanVo;
import com.fulan.application.util.domain.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient("plan")
public interface ClassPlanEnterService {

    /**
     * 获取班级报名情况
     * @return
     */
    @PostMapping(value = "/customer/base/getEnterDetail")
    public Response<List<ClassPlanVo>> getEnterDetail();
}
