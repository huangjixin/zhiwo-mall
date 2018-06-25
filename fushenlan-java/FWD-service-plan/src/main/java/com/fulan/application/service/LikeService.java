package com.fulan.application.service;

import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.plan.domain.Like;
import com.fulan.application.util.domain.Response;


/**
 * <p>
 * 点赞 服务类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-25
 */
public interface LikeService extends IService<Like> {


    /**
     * 新增一条点赞
     * @param like
     * @return
     */
    public Response<Like> insertLike(Like like);

    /**
     *
     * @param likeId
     * @return
     */
    public Response<Boolean> deleteLike(Long likeId);
}
