package com.fulan.application.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.fulan.application.util.json.JsonUtil;
import com.fulan.application.util.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fulan.api.security.domain.Account;
import com.fulan.api.security.vo.AccountVO;
import com.fulan.application.login.service.HandlerLoginService;
import com.fulan.application.login.service.LoginService;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.session.SessionUtil;
import com.fulan.application.token.TokenHelper;
import com.fulan.application.util.constant.GlobalConstant;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.spring.SpringUtil;
import com.fulan.core.monitoring.cat.constant.Constant;

/**
 * @Description: 获取登录业务操作
 * @author: guiyang
 * @date: 2018/1/24 19:26
 */
@Service
@Transactional
public class HandlerLoginServiceImpl implements HandlerLoginService {

    private Logger logger = LoggerFactory.getLogger(HandlerLoginServiceImpl.class);

    private static final String ACCOUNT = "account";
    private static final String DEFAULT_INSTANCE= "LOCAL_SYSTEM";

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    TokenHelper tokenHelper;

    /**
     * 获取登录实例
     * @param accountVO
     * @return
     */
    @Override
    public Response<String> login(AccountVO accountVO) {
        try {
            LoginService loginService;

            if (accountVO.getSystemType() == null){
                loginService = SpringUtil.getBean(DEFAULT_INSTANCE, LoginService.class);
            }else{
                loginService =  SpringUtil.getBean(accountVO.getSystemType(), LoginService.class);
            }

            if (loginService == null){
                loginService = SpringUtil.getBean(DEFAULT_INSTANCE, LoginService.class);
            }

            return loginService.login(accountVO);
        }catch (Exception e){
            logger.error("{-----获取登录实例失败-----}");
            e.printStackTrace();
            return new Response<>(Response.ERROR,  "获取登录实例失败,参数类型:"+accountVO.getSystemType());
        }
    }

    /**
     * 处理登录业务逻辑
     * @param account
     * @return
     */
    @Override
    public Response<String> handler(Account account) {

        try {
            //将account账户信息放入session中和redis中
            SessionUtil.setAttribute(Constant.LOGIN_ACCOUNT,account.getId());
            redisUtil.set(Constant.LOGIN_ACCOUNT + account.getId(), account);
        }catch (Exception e){
            logger.error("{-----账户信息放入缓存异常-----}");
            e.printStackTrace();
            return new Response<>(Response.ERROR, "账户信息放入缓存异常");
        }

            Map<String, Object> info = new HashMap<>();
            info.put(GlobalConstant.TENANT_ID, account.getCompanyId());
            info.put(GlobalConstant.USER_ID, account.getId());
            info.put(GlobalConstant.USER_ACCOUNT, account.getAccountName());

       try {
    	   
            //创建token
            String token = tokenHelper.createToken(ACCOUNT, info);
            Response<String> resp = new Response<>(Response.SUCCESS, "用户验证成功");
            info.clear();
            info.put("account", account);
            info.put("token",token);
            resp.setData(JsonUtils.objectToJson(info));
            logger.info("{-----用户验证成功-----}");
            return resp;
        }catch (Exception e){
            logger.error("{-----用户验证异常-----}");
            e.printStackTrace();
            return new Response<>(Response.ERROR, "用户验证异常");
        }
    }
}