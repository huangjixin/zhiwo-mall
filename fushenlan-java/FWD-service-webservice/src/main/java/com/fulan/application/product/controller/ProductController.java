package com.fulan.application.product.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.ProductService;
import com.fulan.application.util.domain.Response;
import com.fulan.core.monitoring.log.annotation.NoLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * ClassName:ProductController
 * Date:    2018年04月12日 11:32
 * Reason :产品信息中心(调用产品中心外部接口)
 * @author Lycol
 * @version 1.0
 * @since JDK 1.8
 */

@NoLog
@Api(tags = "product", description = "产品中心信息查询")
@RestController
@RequestMapping(value ="/productServiceFromProCenter",produces = { "application/json;charset=utf-8" })
public class ProductController {

    @Autowired
    ProductService productService;
    
    @ApiOperation(value = "获取产品类型列表", notes = "获取产品类型列表", response = Object.class)
    @GetMapping("/getProductTypes")
    @ResponseBody
    public Response<String> getProductTypes() {
    	JSONObject jsStr = JSONObject.parseObject(productService.getProductTypes());
    	Response<String> resp = new Response<String>(Response.SUCCESS, "获取产品类型列表");
		resp.setData(jsStr.get("data").toString());
		resp.setCode("1");
        return resp;
    }
    
    @ApiOperation(value = "根据产品类型获取产品详情列表", notes = "根据产品类型获取产品详情列表", response = Object.class)
    @GetMapping( "/getProductDetailList")
    @ResponseBody
    public Response<String> getProductDetailList(
            @ApiParam(name = "agentCode",value = "代理人code", example = "代理人code")
            @RequestParam("agentCode") String agentCode,
            @ApiParam(name = "planCategory",value = "险种ID", example = "险种ID")
            @RequestParam("planCategory") String planCategory,
            @ApiParam(name = "channel",value = "渠道", example = "渠道",defaultValue="0")
            @RequestParam("channel") String channel) {
    	JSONObject jsStr = JSONObject.parseObject(productService.getProductDetailList(agentCode,planCategory,channel));
    	Response<String> resp = new Response<String>(Response.SUCCESS, "获取产品类型列表");
		resp.setData(jsStr.get("data").toString());
		resp.setCode("1");
        return resp;

    }
    @ApiOperation(value = "获得客户历史保单", notes = "获得客户历史保单", response = Object.class)
    @GetMapping( "/getProductDetail")
    @ResponseBody
    public Response<String> getProductDetail(
    		@ApiParam(name="agentCode",value="代理人编号(必传)")
            @RequestParam(value="agentCode",required = true) String agentCode,
            @ApiParam(name="customId",value="客户ID(必传)")
    		@RequestParam(value="customId",required = true) String customId,
    		@ApiParam(name="policyID",value="保单号")
            @RequestParam(value="policyID",required = false) String policyID, 
    		@ApiParam(name="productName",value="产品名称")
            @RequestParam(value="productName",required = false) String productName, 
    		@ApiParam(name="policyStatus",value="保单状态")
            @RequestParam(value="policyStatus",required = false) String policyStatus) {

    	return productService.getProductDetail(agentCode, customId, policyID, productName, policyStatus);
    }
    
    
    @ApiOperation(value = "查询保单list", notes = "查询保单list", response = Object.class)
    @GetMapping( "/getProductList")
    @ResponseBody
    public Response<String> getProductList(
    	@ApiParam(name="agentCode",value="代理人编号(必传)")
	    @RequestParam(value="agentCode",required = true) String agentCode,
	    @ApiParam(name="customId",value="客户ID(必传)")
		@RequestParam(value="customId",required = true) String customId,
		@ApiParam(name="startTime",value="生效日期")
	    @RequestParam(value="startTime",required = false) String startTime, 
		@ApiParam(name="endTime",value="结束日期")
	    @RequestParam(value="endTime",required = false) String endTime, 
	    @ApiParam(name="applicant",value="投保人")
	    @RequestParam(value="applicant",required = false) String applicant, 
		@ApiParam(name="policyStatus",value="保单状态")
	    @RequestParam(value="policyStatus",required = false) String policyStatus) {

    	 return productService.getProductList();

    }
    
    @ApiOperation(value = "查询保单详情", notes = "查询保单详情", response = Object.class)
    @GetMapping( "/getPolicyDetail")
    @ResponseBody
    public Response<String> getPolicyDetail(
    		@ApiParam(name="agentCode",value="代理人编号(必传)")
            @RequestParam(value="agentCode",required = true) String agentCode,
            @ApiParam(name="customId",value="客户ID(必传)")
    		@RequestParam(value="customId",required = true) String customId,
    		@ApiParam(name="policyID",value="保单号")
            @RequestParam(value="policyID",required = false) String policyID
    		) {
    		return productService.getPolicyDetail();
    }
}
