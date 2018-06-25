package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.plan.domain.PostDevelopment;
import com.fulan.api.plan.vo.DevelopmentFwVo;
import com.fulan.api.plan.vo.PlanCourseVo;
import com.fulan.api.plan.vo.PostDevelopmentVo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostDevelopmentMapper extends BaseMapper<PostDevelopmentVo> {
	PostDevelopmentVo getPostDevelopmentInfo(@Param("Id")Long id);
     
     void batchSavePlanCourseVo(List<PlanCourseVo> list);
     
     void deletPlanCourse(@Param("Id")Long id);

     PostDevelopment selectDeveById(@Param("id") String id);

	List<DevelopmentFwVo> seleStageDevelopmentById(Map<String, Object> map);

	Integer insertDevelopment(PostDevelopment postDevelopment);

	Integer updateDevelopmentById(PostDevelopment postDevelopment);


     
}
