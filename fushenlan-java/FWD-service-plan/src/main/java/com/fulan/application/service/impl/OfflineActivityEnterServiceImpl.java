package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.plan.domain.OfflineActivityEnter;
import com.fulan.api.plan.vo.ActivityVO;
import com.fulan.api.security.domain.Account;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.CourseCollectMapper;
import com.fulan.application.mapper.OfflineActivityEnterMapper;
import com.fulan.application.mapper.PlanAuthorityMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.OfflineActivityEnterService;
import com.fulan.application.util.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 线下活动报名表 服务实现类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-24
 */
@Service
public class OfflineActivityEnterServiceImpl extends ServiceImpl<OfflineActivityEnterMapper, OfflineActivityEnter>
        implements OfflineActivityEnterService {


    @Autowired
    private OfflineActivityEnterMapper offlineActivityEnterMapper;

    @Autowired
    private CourseCollectMapper courseCollectMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private PlanAuthorityMapper planAuthorityMapper;

    @Override
    public Response<String> save(ActivityVO activityVo) {
        Response<String> resp = new Response<>(Response.SUCCESS,"报名成功");
        Account account = (Account) redisUtil.getUserInfo();
        if (null != activityVo.getActivityId() ) {
            Integer signNumber=offlineActivityEnterMapper.selectSignNumber(activityVo.getActivityId());
            Integer countNumber=offlineActivityEnterMapper.selectEnterCount(activityVo.getActivityId());
            if(signNumber>=countNumber){
                resp.setCode(Response.ERROR);
                resp.setMsg("报名人数已满");
                return resp;
            }
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("course_id",activityVo.getActivityId());
            paramMap.put("user_id",account.getId());
            List<OfflineActivityEnter> offlineActivityEnterList=offlineActivityEnterMapper.selectByMap(paramMap);
            if(null!=offlineActivityEnterList&&offlineActivityEnterList.size()>0){
                resp.setCode(Response.ERROR);
                resp.setMsg("已经报过名");
                return resp;
            }
            paramMap.remove("course_id");
            paramMap.remove("user_id");
            paramMap.put("planId",activityVo.getActivityId());
            paramMap.put("userId",account.getId());
            paramMap.put("planType", CommenConstant.EL_COURSE_TYPE_OFFLINEACTIVITY);
            Integer hasAuthority= planAuthorityMapper.hasAuthority(paramMap);
            if(hasAuthority!=null&&hasAuthority==1){
                OfflineActivityEnter oat = new OfflineActivityEnter();
                oat.setId(idGenerator.generate());
                oat.setCourseId(activityVo.getActivityId());
                oat.setUserId(account.getId());
                oat.setUserName(account.getAccountName());//根据用户id去数据库查询
                oat.setGmtCreate(new Date());
                oat.setGmtModified(new Date());
                offlineActivityEnterMapper.insert(oat);
                resp.setCode(Response.SUCCESS);
                resp.setMsg(Response.SUCCESS_MESSAGE);
            }else{
                resp.setCode(Response.ERROR);
                resp.setMsg("无权限");
            }
            return resp;

        }else{
            resp.setCode(Response.ERROR);
            resp.setMsg("参数不合法");
            return resp;
        }
    }

    @Override
    public Integer countCourse(Long id) {
        return offlineActivityEnterMapper.countCourse(id);
    }

}
