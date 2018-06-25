package com.fulan.application.service;

import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.course.domain.CourseMaterial;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 基础课程资料中间表服务类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-22
 */
public interface CourseMaterialService extends IService<CourseMaterial> {

    /**
     * 更新基础课程资料中间表记录
     * @param courseMaterial
     * @return
     */
    Response<Boolean> updateCourseMaterial(CourseMaterial courseMaterial);

    /**
     * 删除基础课程资料中间表记录
     * @param id
     * @return
     */
    Response<Boolean> deleteCourseMaterial(Long id);

}
