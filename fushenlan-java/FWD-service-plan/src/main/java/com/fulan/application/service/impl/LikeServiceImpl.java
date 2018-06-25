package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.plan.domain.Like;
import com.fulan.api.security.domain.Account;
import com.fulan.application.mapper.LikeMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.LikeService;
import com.fulan.application.util.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-25
 */
@Service
@Transactional
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements LikeService{
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public Response<Like> insertLike(Like like) {
        Response<Like> res =new Response<Like>();
        try {
            Account account = (Account) redisUtil.getUserInfo();
            like.setId(idGenerator.generate());
            like.setGmtCreate(new Date());
            like.setGmtModified(new Date());
            like.setUserName(account.getAccountName());
            like.setUserId(account.getId());
            likeMapper.insert(like);
            res.setCode(Response.SUCCESS);
            res.setData(like);
            res.setMsg(Response.SUCCESS_MESSAGE);
        } catch (Exception e) {
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
        }
        return res;
    }

    @Override
    public Response<Boolean> deleteLike(Long likeId) {
        Response<Boolean> res =new Response<Boolean>();
        try {
            likeMapper.deleteById(likeId);
            res.setCode(Response.SUCCESS);
            res.setMsg(Response.SUCCESS_MESSAGE);
        } catch (Exception e) {
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
        }
        return res;
    }
}
