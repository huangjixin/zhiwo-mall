package com.fulan.application.service;

import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.plan.domain.History;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 浏览记录 服务类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-30
 */
public interface HistoryService extends IService<History> {

    /**
     * 新增一条浏览记录
     * @param history
     * @return
     */
    public int insertHistory(History history);
}
