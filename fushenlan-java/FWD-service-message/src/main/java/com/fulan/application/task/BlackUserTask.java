package com.fulan.application.task;

import com.fulan.api.message.domain.SmsBlackUser;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.system.SmsBlackUserService;
import com.fulan.application.util.spring.SpringUtil;

import java.util.List;

/**
 * @Description: 批量删除添加手机号码黑名单到缓存
 * @author: guiyang
 * @date: 2018/3/13 12:49
 */
public class BlackUserTask implements Runnable{

    public enum Type{
        SAVE,DELETE
    }

    private final Type type;
    private final RedisUtil redisUtil;
    private final SmsBlackUserService blackUserService;
    private List<SmsBlackUser> smsBlackUsers;
    private List<Long> ids;

    public BlackUserTask(Type type, List<SmsBlackUser> smsBlackUsers,List<Long> ids) {
        this.type = type;
        this.ids = ids;
        this.smsBlackUsers = smsBlackUsers;
        this.redisUtil = SpringUtil.getBean(RedisUtil.class);
        this.blackUserService = SpringUtil.getBean(SmsBlackUserService.class);
    }

    @Override
    public void run() {

        if (Type.SAVE.equals(type)){//添加到缓存
            if (smsBlackUsers==null) {
                smsBlackUsers = blackUserService.selectBlackUsers();
            }
            if (smsBlackUsers!=null&&smsBlackUsers.size()>0) {
                for (SmsBlackUser user : smsBlackUsers) {
                    redisUtil.set(user.getType() + user.getPhone(), user);
                }
            }
        }else {//从缓存删除
            if (ids!=null) {
                smsBlackUsers = blackUserService.selectBlackUsers(ids);
                if (smsBlackUsers!=null&&smsBlackUsers.size()>0) {
                    for (SmsBlackUser user : smsBlackUsers) {
                        redisUtil.remove(user.getType() + user.getPhone());
                    }
                }
            }
        }

    }
}