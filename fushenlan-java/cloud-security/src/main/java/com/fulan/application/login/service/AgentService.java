package com.fulan.application.login.service;

import com.fulan.application.util.util.JsonMsgBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: 获取外部DMS系统代理人信息
 * @author: guiyang
 * @date: 2018/1/24 20:14
 */
@FeignClient(name = "agent")
public interface AgentService {

    @PostMapping(value = "/customer/agent/getAgent")
    JsonMsgBean getAgent(@RequestParam("agentId") String agentId);

}