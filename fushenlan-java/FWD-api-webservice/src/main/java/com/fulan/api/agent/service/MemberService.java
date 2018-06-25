package com.fulan.api.agent.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.fulan.api.agent.vo.ValidateUserReq;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * dms登录
 * </p>
 *
 * @since 2018-03-27
 */
@FeignClient(name = "WebService")
public interface MemberService {
	/**
	 * 登录接口
	 * @param agentInfoVo
	 * @return
	 */
	@PostMapping("/member/validateUser")
	Response<Boolean> validateUser(@RequestParam("userId")String userId,
								   @RequestParam("loginType")String loginType,
								   @RequestParam("token")String token,
								   @RequestBody ValidateUserReq req);
}
