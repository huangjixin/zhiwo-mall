package com.fulan.api.agent.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "WebService")
public interface MyJsonService {

    @PostMapping("/perform/getPerform")
    public String getPerform(@RequestParam(value = "agentCode") Long agentCode, @RequestParam(value = "month") Integer month, @RequestParam(value = "type") Integer type);
}
