package com.fulan.application.service;

import com.fulan.application.util.util.JsonMsgBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 根据代理人查询建议书记录
 * @author: guiyang
 * @date: 2018/4/13 15:29
 */
@FeignClient(name = "WebService", url = "${url.smart}")
@ResponseBody
public interface ProposalService {

    @RequestMapping(value = "/insure/custom/recommendation/proposalList", method = RequestMethod.POST)
    JsonMsgBean selectCustomerProposal(@RequestParam("customerId") String customerId,
                                       @RequestParam("agentCode")String agentCode);

}