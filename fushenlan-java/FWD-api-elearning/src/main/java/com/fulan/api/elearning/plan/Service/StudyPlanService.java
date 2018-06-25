package com.fulan.api.elearning.plan.Service;

import java.util.Dictionary;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.elearning.plan.domain.StudyPlan;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * <p>
 * 学习计划
 * </p>
 *
 * @author yangzexu
 * @since 2018-01-22
 */
@FeignClient(name = "elearning")
public interface StudyPlanService {

    @RequestMapping("/studyPlan/insert")
    Response<String> insert(@RequestBody StudyPlan studyPlan,@RequestParam(name="userId",defaultValue="0") Long userId);

    @PostMapping("/studyPlan/batch/delete")
    Boolean deleteBacth(@RequestParam(name = "planIds",defaultValue = "0") Long[] planIds);

    @PostMapping(value = "/studyPlan/batch/update/status")
    Response<String> updateStatus(@RequestBody StudyPlan[] studyPlans);
    
    @PostMapping(value = "/studyPlan/update")
    Response<String> update(@RequestBody StudyPlan studyPlan);

    @RequestMapping(value = "/studyPlan/list")
    Page<StudyPlan> list(
    		@RequestParam(name="code",required = false) String code,
    		@RequestParam(name="name",required = false) String name,
    		@RequestParam(name="tagId",required = false) String tagId,
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    );

}
