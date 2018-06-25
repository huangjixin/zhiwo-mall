package com.fulan.application.service;
import com.fulan.api.agent.vo.ValidateUserReq;
import com.fulan.application.util.domain.Response;

public interface MemberService {
	/**
	 * dms登录
	 * @param userId
	 * @param loginType
	 * @param token
	 * @param req
	 * @return
	 */
	Response<Boolean> validateUser(String userId,String loginType,String token,ValidateUserReq req);
}

