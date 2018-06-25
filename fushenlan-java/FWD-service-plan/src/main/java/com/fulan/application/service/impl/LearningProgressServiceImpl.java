package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.plan.domain.LearningProgress;
import com.fulan.api.security.domain.Account;
import com.fulan.application.mapper.LearningProgressMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.LearningProgressService;
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
 * @since 2018-02-01
 */
@Service
public class LearningProgressServiceImpl extends ServiceImpl<LearningProgressMapper, LearningProgress> implements LearningProgressService {

    @Autowired
    private LearningProgressMapper learningProgressMapper;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional
    public Response<Boolean> insertLearningProgress(LearningProgress learningProgress) {
        Response<Boolean> res =new Response<Boolean>();
        try {
            if(null!=learningProgress.getCourseId()&&null!=learningProgress.getPlanId()){
                learningProgress.setGmtModified(new Date());
                learningProgress.setGmtCreate(new Date());
                learningProgress.setId(idGenerator.generate());
                Account account = (Account)redisUtil.getUserInfo();
                learningProgress.setUserId(account.getId());
                learningProgress.setUserName(account.getAccountName());
                learningProgressMapper.insert(learningProgress);
                res.setCode(Response.SUCCESS);
                res.setMsg(Response.SUCCESS_MESSAGE);
            }
        } catch (Exception e) {
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
        }
        return res;
    }
}
