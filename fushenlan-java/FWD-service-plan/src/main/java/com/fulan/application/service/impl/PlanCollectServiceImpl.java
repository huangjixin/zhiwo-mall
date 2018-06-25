package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.plan.domain.PlanCollect;
import com.fulan.api.security.domain.Account;
import com.fulan.application.mapper.PlanCollectMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.PlanCollectService;
import com.fulan.application.util.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * <p>
 * 收藏 服务实现类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-25
 */
@Service
@Transactional
public class PlanCollectServiceImpl extends ServiceImpl<PlanCollectMapper, PlanCollect> implements PlanCollectService{

    @Autowired
    private PlanCollectMapper planCollectMapper;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public Response<PlanCollect> insertPlanCollect(PlanCollect planCollect) {
        Response<PlanCollect> res =new Response<PlanCollect>();
        try {
            Account account = (Account) redisUtil.getUserInfo();
            planCollect.setUserId(account.getId());
            EntityWrapper<PlanCollect> ew = new EntityWrapper<PlanCollect>(planCollect);
            List<PlanCollect>  planCollectList = planCollectMapper.selectList(ew);
            if(planCollectList==null||planCollectList.size()<=0){
                planCollect.setGmtCreate(new Date());
                planCollect.setUserName(account.getAccountName());
                planCollect.setGmtModified(new Date());
                planCollect.setId(idGenerator.generate());
                planCollectMapper.insert(planCollect);
                res.setCode(Response.SUCCESS);
                res.setData(planCollect);
                res.setMsg(Response.SUCCESS_MESSAGE);
            }
        } catch (Exception e) {
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
        }
        return res;
    }

    @Override
    public Response<Boolean> deletePlanCollect(Long planCollectId) {
        Response<Boolean> res =new Response<Boolean>();
        try {
            planCollectMapper.deleteById(planCollectId);
            res.setCode(Response.SUCCESS);
            res.setMsg(Response.SUCCESS_MESSAGE);
        } catch (Exception e) {
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
        }
        return res;
    }
}
