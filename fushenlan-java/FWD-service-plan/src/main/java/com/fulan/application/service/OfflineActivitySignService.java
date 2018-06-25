package com.fulan.application.service;

import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.plan.domain.OfflineActivitySign;
import com.fulan.api.plan.vo.ActivityVO;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 线下活动签到表 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-24
 */
public interface OfflineActivitySignService extends IService<OfflineActivitySign> {

    Response<String> save(ActivityVO activityVo);
}
