package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.domain.PlanQuestion;
import com.fulan.api.plan.vo.PlanQuestionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 提问与回复表 Mapper 接口
 * </p>
 *
 * @author Hedge
 * @since 2018-01-25
 */
@Mapper
public interface PlanQuestionMapper extends BaseMapper<PlanQuestion> {

	List<Map<String,Object>> manageListByPage(Page<Map<String,Object>> page,Map<String,String> param);
	int manageListByPageCount(Map<String,String> param);
	int hideOrOpenAll(@Param("list")List<String> list, @Param("type")String type, @Param("isOpen")String isOpen);

	/**
	 * 根据条件查询咨询列表
	 * @param page
	 * @param paramMap
	 * @return
	 */
	public List<PlanQuestionDto>  searchPlanQuestion(Page<PlanQuestionDto> page, Map<String,Object> paramMap);



}
