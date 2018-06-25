package com.fulan.application.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.course.domain.Course;
import com.fulan.api.course.vo.CourseManageVo;
import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.paper.vo.PaperManageMinVo;
import com.fulan.api.plan.domain.CompulsoryCplan;
import com.fulan.api.plan.domain.StudyPlan;
import com.fulan.api.plan.vo.CompulsoryCplanManageVo;
import com.fulan.api.plan.vo.CompulsoryCplanVo;
import com.fulan.application.util.page.PageInfo;

public interface CompulsoryCplanService extends IService< CompulsoryCplan>{
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	PageInfo<CompulsoryCplanVo> listByPage(Page<CompulsoryCplanVo> page ,String name,String tagId,String state ,int pageNo, int pageSize);
	
	String deleteCompulsoryCplan(String ids);
	
	String disAbleCompulsoryCplan(String ids);
	
	String enAbleCompulsoryCplan(String ids);
	
	String saveCompulsoryCplan(CompulsoryCplanManageVo compulsoryCplanManageVo,String fileId ,String fOtherId);
	
	CompulsoryCplanManageVo selectById (String id);
	
	List<Course> selectByElspId(String type); 
	
	List<Paper> selectAllPaper();
	
	List<CourseManageVo> selectByCId(String cId);
	
	List<PaperManageMinVo> selectBycId(String cId);
	
	CompulsoryCplan compulsoryCplanById(Long id);
	
	String selectMaxByPlanId(String id);
	
	
	
}
