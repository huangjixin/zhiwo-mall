package com.fulan.api.agent.service;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:ProductService
 * Date:   2018年04月13日 15:15
 * Reason :产品Service
 * @author Lycol
 * @version 1.0
 * @since JDK 1.8
 */
@FeignClient("productService")
public interface ProductService {

    /**
     * 从产品中心获取产品类型
     * <p>
     * 直接返回外部接口Response JSON串
     * 网络异常返回自定义JSON串{"code":-1,"message":"失败",data:[]}
     * </p>
     * @return
     */
    @GetMapping("/productServiceFromProCenter/getProductTypes")
    String getProductTypes();

    /**
     * 从产品中心根据产品类型获取产品详情列表
     * <p>
     * 直接返回外部接口Response JSON串
     * 网络异常返回自定义JSON串{"code":-1,"message":"失败",data:[]}
     * </p>
     * @return
     */
    @GetMapping("/productServiceFromProCenter/getProductDetailList")
    String getProductDetailList( @RequestParam("productIdArray") String productIdArray);
    
    /**
     * 
     * 查询客户历史保单
     * @param customId (客户编号)
     * @param policyID(保单号)
     * @param productName(产品名称)
     * @param policyStatus(保单状态)
     *  <p>
     * 直接返回外部接口Response JSON串
     * 网络异常返回自定义JSON串{"code":-1,"message":"失败",data:[]}
     * </p>
     * @return
     */
    @GetMapping("/productServiceFromProCenter/getProductDetail")
    String getProductDetail( @RequestParam("customId") String customId,
			@RequestParam("policyID") String policyID,
			@RequestParam("productName") String productName,
			@RequestParam("policyStatus") String policyStatus);


}
