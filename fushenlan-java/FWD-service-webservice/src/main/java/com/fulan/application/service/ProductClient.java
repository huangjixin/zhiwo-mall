package com.fulan.application.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * ClassName:ProductClient
 * Date:    2018年04月12日 11:00
 * Reason :产品中心外部Service
 * @author Lycol
 * @version 1.0
 * @since JDK 1.8
 */
@SuppressWarnings("ALL")
@FeignClient(name = "WebService", url = "${url.product}")
@RequestMapping(value = "/ProductCenter", produces = {"application/json;charset=UTF-8"})
@ResponseBody
public interface ProductClient {

    @RequestMapping(value = "/productForOut/planCategory", method = RequestMethod.GET)
    String searchProductTypeList();

    @RequestMapping(value = "/plan/getProductPlanList", method = RequestMethod.GET)
    String searchProductDetailList(@RequestParam("agentCode") String agentCode,@RequestParam("planCategory") String planCategory,@RequestParam("channel") String channel );
    
    //查询保单
    @RequestMapping(value = "/productForOut/getProductDetail", method = RequestMethod.GET)
    String getProductList( @RequestParam("agentCode") String agentCode,
    		 					@RequestParam("customId") String customId,
    		 					@RequestParam("policyID") String policyID,
    							@RequestParam("productName") String productName,
    							@RequestParam("policyStatus") String policyStatus);
    
    /*@RequestMapping(value = "/productForOut/getProductDetail", method = RequestMethod.GET)
    String getProductDetail (@RequestParam("agentCode") String agentCode,
		@RequestParam("customId") String customId,
		@RequestParam("policyID") String policyID);*/
	
}
