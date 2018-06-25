package com.fulan.application.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.course.domain.CourseMaterial;

/**
 * <p>
 *  基础课程资料中间表 接口
 * </p>
 *
 * @author Hedge
 * @since 2018-01-19
 */
@Mapper
public interface CourseMaterialMapper extends BaseMapper<CourseMaterial> {


}
