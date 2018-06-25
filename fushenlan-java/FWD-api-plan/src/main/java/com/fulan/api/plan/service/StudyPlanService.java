package com.fulan.api.plan.service;


import com.fulan.api.plan.domain.StudyPlan;
import com.fulan.api.plan.domain.StudyPlanCopy;
import com.fulan.api.plan.vo.StudyPlanVvo;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
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
@FeignClient(name = "plan")
public interface StudyPlanService {

    @RequestMapping("/studyPlan/insert")
    Response<String> insert(@RequestBody StudyPlan studyPlan, @RequestParam(name = "userId", defaultValue = "0") Long userId);
    
   
   
    @PostMapping("/studyPlan/batch/delete")
    Boolean deleteBacth(@RequestParam(name = "planIds", defaultValue = "0") Long[] planIds);

    @PostMapping(value = "/studyPlan/batch/update/status")
    Response<String> updateStatus(@RequestBody StudyPlan[] studyPlans);
    
    @PostMapping(value = "/studyPlan/update")
    Response<String> update(@RequestBody StudyPlan studyPlan);

    @RequestMapping(value = "/studyPlan/list")
    Page<StudyPlan> list(
            @RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "tagId", required = false) String tagId,
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    );
    
    
   //------------------------------------------------------------------------- 
    
    
    @PostMapping("/manage/studyPlan/insert")
    Response<String> insert(@RequestBody StudyPlanCopy studyPlan);
    
    @PostMapping("/manage/studyPlan/update")
    public Response<String> updateStudyPlan(@RequestBody StudyPlan studyPlan);
    
    @PostMapping("/manage/studyPlan/batch/delete")
    public Response<String> delete(@RequestParam(name = "planIds", defaultValue = "0") Long[] planIds);
    
    @PostMapping("/manage/studyPlan/batch/update/status")
    public Response<String> updateStudyPlanStatus(@RequestBody StudyPlan[] studyPlans);
    
    @GetMapping("/manage/studyPlan/list")
    Page<StudyPlan> listStudyPlan(@RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "tagId", required = false) String tagId,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize);
    
    
    @GetMapping("/manage/studyPlan/detail")
    public Response<String> studyPlantDetail(@RequestParam(name = "id", defaultValue = "1") Long id);
    
    @GetMapping("/manage/studyPlan/studyPlanList")
    public Response<List> studyLists(@RequestParam(name="code") String code,@RequestParam(name="name") String name,@RequestParam(name="tagId") Long tagId,@RequestParam(name="pageNo") Integer pageNo,@RequestParam(name="pageSize") Integer pageSize);
    
    
    @GetMapping("/manage/studyPlan/studyPlanById")
    public Response<StudyPlan> PlanDetailById(@RequestParam(name="id") Long id);
    
    @PostMapping("/manage/studyPlan/enabledStudyPlan")
    public Response<String> enabledPlan(@RequestParam(name="id") Long id);
    
    @PostMapping("/manage/studyPlan/disabledStudyPlan")
    public Response<String> disabledPlan(@RequestParam(name="id") Long id);
    
    
    @GetMapping("/manage/studyPlan/batch/deletePlans")
    public Response<String> deletePlanStudy(@RequestParam(name="ids") List<Long> ids);
    
    

    
    @GetMapping("/manage/studyPlan/searchStudyPlans")
    public Response<List> lookupStudyPlans(@RequestParam(name="code",required = false) String code,
    		@RequestParam(name="name",required = false) String name,
    		@RequestParam(name="tagId",required = false) Long tagId);
    
    @RequestMapping(value = "/manage/studyPlan/planList")
    List<StudyPlan> planList(
    		@RequestParam(value="keyWord",required=false) String keyWord,//计划关键字
            @RequestParam(name = "code", required = false) String code,//计划代码
            @RequestParam(name = "tagId", required = false) String tagId,//二级分类
            @RequestParam(value="uploadTimeBegin",required=false)   String uploadTimeBegin,//上传时间
			@RequestParam(value="uploadTimeEnd",required=false)   String uploadTimeEnd//结束时间
    );
    
    @GetMapping("/manage/studyPlan/searchStudyPlans")
    public Response<List> serchStudyPlans(@RequestParam(name="code",required = false) String code,
    		@RequestParam(name="name",required = false) String name,
    		@RequestParam(name="tagId",required = false) Long tagId);
    //分页
    @GetMapping("/manage/studyPlan/studyPlanByPage")
    public PageInfo<StudyPlanVvo> listByPage(@RequestParam("keyWord") String keyWord,
    		@RequestParam("pageNo") int pageNo,
    		@RequestParam("pageSize") int pageSize);
       
    @GetMapping(value = "/manage/studyPlan/listPlan")
    PageInfo<StudyPlanVvo> listPlan(
    		@RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "tagId", required = false) String tagId,
            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    );
    
    
}
