package com.fulan.application.mapper;

import java.util.List;
import java.util.Map;

import com.fulan.api.material.domain.Material;
import com.fulan.api.material.vo.MaterialAttachDto;
import com.fulan.api.material.vo.MaterialDTO;
import com.fulan.api.plan.vo.PlanDetailDto;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.course.domain.Course;
import com.fulan.api.plan.domain.PublicClass;
import com.fulan.api.plan.vo.PublicClassVo;
import com.fulan.api.plan.vo.PublicCourseDto;


/**
 *  <p>
 * 公开课  Mapper 接口
 * </p>
 *
 * @author Hedge
 * @since 2018-01-24
 */
@Mapper
public interface PublicClassMapper extends BaseMapper<PublicClass> {
    /**
     * 根据条件查询公开课信息
     * @param paramMap
     * @return
     */
    public List<PublicCourseDto>  searchPublicClass(Page<PublicCourseDto> page,Map<String,Object> paramMap);

    /**
     * 后台公共课分页，及总数
     * @param page
     * @param map
     * @return
     */
    List<PublicCourseDto> listByPage(Page<PublicCourseDto> page,Map<String,Object> map);
    int listByPageCount(Map<String,Object> map);
	/**
	 * 公开课 线上课程选择  限制条件：线上课程，未打包成学习计划
	 	预留map 其他未知条件
	 * @param map
	 * @return
	 */
    List<Course> selectCourseForPub(Map<String, Object> map);
    /**
     * 后台公开课 详情
     * @param id
     * @return
     */
    PublicClassVo publicClassVoInFo(String id);

    /**
     * 根据课程Id获取资料列表
     * @param courseId
     * @return
     */
    public List<MaterialAttachDto> getMaterialListByCourseId(Long courseId);

	public List<PublicCourseDto> PublicClassListByPage(Page<PublicCourseDto> page, Map<String, Object> map);

	public int PublicClassListByPageCount(Map<String, Object> map);

	public void updateStateById(PublicClass publicClass);


    
    
}
