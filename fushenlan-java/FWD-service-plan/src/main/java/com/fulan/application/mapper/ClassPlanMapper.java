package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.course.domain.Course;
import com.fulan.api.plan.domain.ClassPlan;
import com.fulan.api.plan.vo.ClassPlanDto;
import com.fulan.api.plan.vo.ClassPlanFwVo;
import com.fulan.api.plan.vo.ClassPlanVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 班级计划 Mapper 接口
 * </p>
 *
 * @author yangzexu
 * @since 2018-01-18
 */
@Mapper
public interface ClassPlanMapper extends BaseMapper<ClassPlan> {

	/**
	 * 查询所有班级计划
	 * @return
	 */
	@Select("select findAllElClassPlan()")
	public List<ClassPlan> findAllElClassPlan();

	/**
	 * 查看详情
	 * @param id
	 * @return
	 */
	public ClassPlan findClassPlanDetailById(@Param("id") Long id);
	
	/**
	 * manage list 分页
	 * @param page
	 * @param name
	 * @param code
	 * @param tagId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<ClassPlanVo> classPlanFM(Page<ClassPlanVo> page,
			@Param("name") String name,
            @Param("code") String code,
            @Param("tagId") String tagId,
            @Param("pageNo") int pageNo,
            @Param("pageSize") int pageSize);
	
	/**
	 * manage 记录数
	 * @param name
	 * @param code
	 * @param tagId
	 * @return
	 */
	Integer classPlanFMCount( @Param("name") String name,
            @Param("code") String code,
            @Param("tagId") String tagId);

	/**
	 * 根据条件查询班级计划信息
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public List<ClassPlanDto>  searchClassPlan(Page<ClassPlanDto> page, Map<String,Object> paramMap);

	/**
	 * 保存classPlan
	 */
	Integer insertSelective(ClassPlan classPlan);
	
	List<Course> seleCourseById(Long id);
	
	ClassPlan selectByIdFW(Long id);
	
	List<ClassPlanFwVo> seleStageById(Map<String,Object> map);
	
	
}
