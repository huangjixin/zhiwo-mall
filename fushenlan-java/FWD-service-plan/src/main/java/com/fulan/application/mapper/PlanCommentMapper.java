package com.fulan.application.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.domain.PlanComment;
import com.fulan.api.plan.vo.PlanCommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程评论 Mapper 接口
 * </p>
 *
 * @author Hedge
 * @since 2018-01-25
 */
@Mapper
public interface PlanCommentMapper extends BaseMapper<PlanComment> {
	List<Map<String,Object>> manageListByPage(Page<Map<String,Object>> page,Map<String,String> param);
	int manageListByPageCount(Map<String,String> param);


	/**
	 * 根据条件查询评论列表
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public List<PlanCommentDto>  searchPlanComment(Page<PlanCommentDto> page, Map<String,Object> paramMap);


}
