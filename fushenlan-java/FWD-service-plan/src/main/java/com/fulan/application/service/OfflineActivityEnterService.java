package com.fulan.application.service;

import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.plan.domain.OfflineActivityEnter;
import com.fulan.api.plan.vo.ActivityVO;
import com.fulan.api.plan.vo.PageResultVO;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 线下活动报名表 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-24
 */
public interface OfflineActivityEnterService extends IService<OfflineActivityEnter> {

    Response<String> save(ActivityVO activityVo);

    Integer countCourse(Long id);


}
