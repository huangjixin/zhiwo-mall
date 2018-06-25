/**
 * Project Name:FWD-service-webservice
 * File Name:CustomerClient.java
 * Package Name:com.fulan.application.service
 * Date:2018年4月9日上午9:58:19
 * Copyright (c) 上海复深蓝软件股份有限公司.
 */

package com.fulan.application.service;

import com.fulan.api.agent.vo.CustomerSearchParm;
import com.fulan.api.agent.vo.CustomerVo;
import com.fulan.api.agent.vo.VTag;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:FlagClient
 * Date:     2018年4月9日 上午9:58:19
 * Description: 标签服务 WebService
 * Reason : 标签服务外部Service
 * @author LyCol
 * @version
 * @since JDK 1.8
 */
@FeignClient(name = "WebService", url = "${url.flag}")
@RequestMapping(value = "/FwCustom", produces = {"application/json;charset=UTF-8"})
@ResponseBody
public interface FlagClient {

    @RequestMapping(value = "/insure/custom/label/list/iris?pagesize=1000&pagecount=0", method = RequestMethod.GET)
    String serachFlags(@RequestParam("agentCode") String agentCode);

    @RequestMapping(value = "/insure/custom/label/addOrUpdate/iris", method = RequestMethod.GET)
    String addFlag(@RequestParam("agentCode") String agentCode,
                   @RequestParam("name")String name,
                   @RequestParam("rule")String ruleJson);

    @RequestMapping(value = "insure/custom/label/delTagById", method = RequestMethod.GET)
    String delFlag(@RequestParam("tagId") String tagId);
    
}

