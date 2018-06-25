package com.fulan.application.service;

import com.fulan.application.util.util.JsonMsgBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 查询家庭树
 * @author: guiyang
 * @date: 2018/4/13 15:55
 */
@FeignClient(name = "WebService", url = "${url.smart}")
@ResponseBody
public interface CustomerFamilyService {

    @RequestMapping(value = "/insure/custom/family/list", method = RequestMethod.POST)
    JsonMsgBean selectCustomerFamily(@RequestParam("customerId") String customerId);

}