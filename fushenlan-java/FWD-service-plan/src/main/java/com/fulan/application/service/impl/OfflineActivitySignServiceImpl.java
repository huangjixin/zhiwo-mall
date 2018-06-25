package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.plan.domain.OfflineActivitySign;
import com.fulan.api.plan.vo.ActivityVO;
import com.fulan.api.plan.vo.SignTypeDto;
import com.fulan.api.security.domain.Account;
import com.fulan.application.mapper.OfflineActivityEnterMapper;
import com.fulan.application.mapper.OfflineActivitySignMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.OfflineActivitySignService;
import com.fulan.application.util.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 线下活动签到表 服务实现类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-24
 */
@Service
public class OfflineActivitySignServiceImpl extends ServiceImpl<OfflineActivitySignMapper, OfflineActivitySign> implements
        OfflineActivitySignService {

    @Autowired
    private OfflineActivitySignMapper offlineActivitySignMapper;


    @Autowired
    private OfflineActivityEnterMapper offlineActivityEnterMapper;

    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public Response<String> save(ActivityVO activityVo) {
        Response<String> resp = new Response<>(Response.SUCCESS,"签到成功");
        Long activityId = activityVo.getActivityId();
        Account account = (Account) redisUtil.getUserInfo();
        if(null!=account  && null!=activityId){
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("userId",account.getId());
            paramMap.put("courseId",activityVo.getActivityId());
            Long signId = offlineActivitySignMapper.hasSignd(paramMap);
            if(null!=signId){
                resp.setCode(Response.ERROR);
                resp.setMsg("已经签过了");
                return resp;
            }

            SignTypeDto sign=offlineActivityEnterMapper.selectSignType(activityId);
            OfflineActivitySign oAySign = new OfflineActivitySign();
            if(1==sign.getIsNeedEnter() && 0==sign.getIsNeedSign() && 0==sign.getIsNoneedEnter()){
                return getEnterSign(resp, activityId, account.getId(), oAySign);
            }else if(1==sign.getIsNeedEnter() && 1==sign.getIsNeedSign() && 0==sign.getIsNoneedEnter()){
                return getEnterSign(resp, activityId, account.getId(), oAySign);
            }else if(0==sign.getIsNeedEnter() && 1==sign.getIsNeedSign() && 0==sign.getIsNoneedEnter()){
                return getSignType(resp,activityId,account.getId(),oAySign);
            }else if(1==sign.getIsNeedEnter() && 1==sign.getIsNeedSign() && 1==sign.getIsNoneedEnter())
                return getSignType(resp,activityId,account.getId(),oAySign);
        }
        resp.setCode(Response.ERROR);
        resp.setMsg("参数错误");
        return resp;
    }

    private Response<String> getSignType(Response<String> resp,Long activityId,Long userId,
                                         OfflineActivitySign oAySign){
        oAySign.setCourseId(activityId);
        // TODO: 用户名id待处理
        oAySign.setUserId(userId);
        oAySign.setUserName("fw");
        oAySign.setIsEnter(0);
        oAySign.setEnterTime(new Date());
        oAySign.setSignTime(new Date());
        oAySign.setGmtCreate(new Date());
        oAySign.setGmtModified(new Date());
        offlineActivitySignMapper.insert(oAySign);
        return resp;
    }


    private Response<String>  getEnterSign(Response<String> resp,Long activityId,Long userId,
                                              OfflineActivitySign oAySign){
//        Long enterId=offlineActivityEnterMapper.selectEnter(userId);
//        if(null==enterId){
//            resp.setCode(Response.ERROR);
//            resp.setMsg("你还未报名");
//            return resp;
//        }
//        oAySign.setId(idGenerator.generate());
//        oAySign.setCourseId(activityId);
//        // TODO: 用户id，name待处理
//        oAySign.setUserId(1l);
//        oAySign.setUserName("fw");
//        oAySign.setIsEnter(1);
//        oAySign.setSignTime(new Date());
//        Date enterDate = offlineActivityEnterMapper.selectEnterTime(userId);
//        oAySign.setEnterTime(enterDate);
//        oAySign.setGmtCreate(new Date());
//        oAySign.setGmtModified(new Date());
//        offlineActivitySignMapper.insert(oAySign);
        return resp;
    }
}
