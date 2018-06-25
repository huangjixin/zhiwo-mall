package com.fulan.application.login.service;
import com.fulan.api.agent.vo.ValidateUserReq;
import com.fulan.application.util.domain.Response;

/**
 * @Description: 登录业务层接口
 * @date: 2018/3/28
 */
public interface DmsLoginService {
	
    Response<String> login(String userId,String token, String systemId,  ValidateUserReq req);
}