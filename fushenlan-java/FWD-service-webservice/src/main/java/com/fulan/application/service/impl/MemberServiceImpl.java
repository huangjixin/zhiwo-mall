package com.fulan.application.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fulan.api.agent.vo.ValidateUserReq;
import com.fulan.application.service.MemberClient;
import com.fulan.application.service.MemberService;
import com.fulan.application.util.domain.Response;

import net.sf.json.JSONObject;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
    private MemberClient memberClient;
	/**
	 * DMS登录接口
	 */
	@Override
	public Response<Boolean> validateUser(String userId, String loginType, String token, ValidateUserReq req) {
		Response<Boolean> resp = new Response<Boolean>(Response.SUCCESS, "dms用户校验成功");
    	String validateUserResult = memberClient.validateUser(userId,loginType,token,req);
    	
    	if(JSONObject.fromObject(validateUserResult).getString("status").equals("fail")){
    		resp.setData(Boolean.valueOf("false"));
    	}else{
    		String booleanValue = JSONObject.fromObject(validateUserResult).getString("data");
    		resp.setData(Boolean.valueOf(booleanValue)); 	
    	}
    	resp.setCode(Response.SUCCESS);
		return resp;
	}
}
