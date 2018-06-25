package com.fulan.api.plan.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.course.domain.Course;
import com.fulan.api.course.vo.CourseManageVo;
import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.paper.vo.PaperManageMinVo;
import com.fulan.api.plan.domain.CompulsoryCplan;
import com.fulan.api.plan.domain.StudyPlan;
import com.fulan.api.plan.vo.CompulsoryCplanManageVo;
import com.fulan.api.plan.vo.CompulsoryCplanVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

@FeignClient(name = "plan")
public interface CompulsoryCplanService {
	/**
	 * 分页
	 * @param name
	 * @param tagId
	 * @param state
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/manage/compulsoryCplan/compulsoryCplanManagePage")
	PageInfo<CompulsoryCplanVo> compulsoryCplanManageSerch(
			@RequestParam(name="name" ,required = false)String name,
			@RequestParam(name="tagId",required = false) String tagId,
			@RequestParam(name="state",required = false) String state,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
			);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@PostMapping("/manage/compulsoryCplan/deleteCompulsoryCplan")
	String deleteCompulsoryCplan(@RequestParam(name="ids" ,required = false)String ids);
	/**
	 * 批量停用
	 * @param ids
	 * @return
	 */
	@PostMapping("/manage/compulsoryCplan/disAbleCompulsoryCplan")
	String disAbleCompulsoryCplan(@RequestParam(name="ids" ,required = false)String ids);
	
	@PostMapping("/manage/compulsoryCplan/enAbleCompulsoryCplan")
	String enAbleCompulsoryCplan(@RequestParam(name="ids" ,required = false)String ids);
	/**
	 * 新增Vo
	 * @param paperVo
	 * @return
	 */
	@PostMapping("/manage/compulsoryCplan/addCompulsoryCplanManageVo")
	String saveCompulsoryCplanManageVo(@RequestBody CompulsoryCplanManageVo compulsoryCplanManageVo,@RequestParam(name="fileId" ,required = false)String fileId,@RequestParam(name="fOtherId" ,required = false)String fOtherId );
	
	
	@GetMapping("/manage/compulsoryCplan/cplanManageCheck")
	CompulsoryCplanManageVo compulsoryCplanCheck(@RequestParam(name="id" ,required = false)String id); 
	
	@GetMapping("/manage/compulsoryCplan/selectByElspId")
	List<Course> selectByElspId (@RequestParam(name="type" ,required = false)String type);
	
	@GetMapping("/manage/compulsoryCplan/selectByAllList")
	List<Paper> selectByAllPaperList();
	
	@GetMapping("/manage/compulsoryCplan/selectByCId")
	List<CourseManageVo> selectByCId(@RequestParam(name="id") String id);
	
	@GetMapping("/manage/compulsoryCplan/selectBycId")
	List<PaperManageMinVo> selectBycId(@RequestParam(name="id") String id);
	
	@GetMapping("/manage/compulsoryCplan/selectByPlanId")
	List<CourseManageVo> selectByPlanId(@RequestParam(name="id") String id);
	
	@GetMapping("/manage/compulsoryCplan/selectByPlanOtherId")
	List<CourseManageVo> selectByPlanOtherId(@RequestParam(name="id") String id);
	
	@GetMapping("/manage/compulsoryCplan/compulsoryCplanById")
    Response<CompulsoryCplan> compulsoryCplanById(@RequestParam(name="id") Long id);
	
	@GetMapping("/manage/compulsoryCplan/selectMaxByPlanId")
	String selectMaxByPlanId(@RequestParam(name="id") String id);
	
}
