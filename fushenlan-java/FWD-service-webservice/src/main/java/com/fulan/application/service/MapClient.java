package com.fulan.application.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName:MapClient
 * Date:    2018年04月11日 14:23
 *Reason:高德地图API
 * @author Lycol
 * @version 1.0
 * @since JDK 1.8
 */
@FeignClient(name = "WebService", url = "${url.gaode.map}")
public interface MapClient {


    @RequestMapping(value = "geocode/geo?output=JSON", method = RequestMethod.GET)
    String getPointJsonInfo(@RequestParam("key") String key,@RequestParam("address") String address);

}
