package com.fulan.api.plan.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.domain.ClassPlanEnter;
import com.fulan.api.plan.vo.ClassPlanDto;
import com.fulan.api.plan.vo.PlanRequestDto;
import com.fulan.application.util.domain.Response;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 班级计划
 * </p>
 *
 * @author chailaoer
 * @since 2018-01-22
 */
@FeignClient(name = "plan")
public interface PublicClassService{

    @PostMapping("customer/classPlan/list")
    Response<Page<ClassPlanDto>> listByPage(
            @ApiParam("userId:用户Id<br>pageNo:要跳转的页数<br>pageSize:每页条数")
            @RequestBody PlanRequestDto planRequestDto);

    @PostMapping("customer/classPlan/enter")
    Response<Boolean> enter(
            @ApiParam("userId:用户Id,required<br>planId:计划Id,required<br>")
            @RequestBody ClassPlanEnter classPlanEnter);

}

