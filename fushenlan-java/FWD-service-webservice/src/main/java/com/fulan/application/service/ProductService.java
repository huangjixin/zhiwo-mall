package com.fulan.application.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fulan.api.security.domain.Account;
import com.fulan.application.util.domain.Response;

/**
 * ClassName:ProductService
 * Date:    2018年04月12日 11:17
 * Reason :产品中心服务Service
 *
 * @author Lycol
 * @version 1.0
 * @since JDK 1.8
 */
@Service("productService")
public class ProductService {
   
	   @Autowired
	    ProductClient productClient;
	 /**
     * 获取产品类型列表
     * @return 产品类型JSON数据
     */
   public  String getProductTypes() {
        try {
            return productClient.searchProductTypeList();
        } catch (Exception e) {
            return "{\n" +
                    "  \"code\": \"-1\",\n" +
                    "  \"message\": " + e.getMessage() + ",\n" +
                    "  \"data\": []\n" +
                    "}";
        }

    }
   
   /**
    * 根据产品类型获取产品详情列表
    * @return 产品类型JSON数据
    */
   public String getProductDetailList(String agentCode,String planCategory,String channel) {
       try {
           return productClient.searchProductDetailList(agentCode,planCategory,channel);
       } catch (Exception e) {
           return "{\n" +
                   "  \"code\": \"-1\",\n" +
                   "  \"message\": " + e.getMessage() + ",\n" +
                   "  \"data\": []\n" +
                   "}";
       }

   }
    /**
     * 查询客户历史保单
     * @param customId (客户编号)
     * @param policyID(保单号)
     * @param productName(产品名称)
     * @param policyStatus(保单状态)
     * @return
     */
    public Response<String> getProductDetail(String agentCode, 
				  String customId,
				  String policyID,
				  String productName,
				  String policyStatus) {
    	/*try {
    		return productClient.getProductList(agentCode,customId, policyID, productName, policyStatus);
    	} catch (Exception e) {
    		return "{\n" +
    				"  \"code\": \"-1\",\n" +
    				"  \"message\": " + e.getMessage() + ",\n" +
    				"  \"data\": []\n" +
    				"}";
    	}*/
    	Response<String> resp = new Response<String>(Response.SUCCESS, "获得客户历史保单");
		resp.setData(JSONObject.parseObject(readJson("policy.json")).toJSONString());
		resp.setCode("1");
		return  resp;	
    }
    
    public Response<String> getProductList() {
	Response<String> resp = new Response<String>(Response.SUCCESS, "查询保单list");
	resp.setData(JSONObject.parseObject(readJson("policyList.json")).toJSONString());
	resp.setCode("1");
	return  resp;	
}
    
    public Response<String> getPolicyDetail() {
    	
	Response<String> resp = new Response<String>(Response.SUCCESS, "查询保单详情");
	resp.setData(JSONObject.parseObject(readJson("policyDetail.json")).toJSONString());
	resp.setCode("1");
	return  resp;	
}
    String readJson(String fileName) {
  		try {

  			InputStream stream = getClass().getClassLoader().getResourceAsStream("jsonpage/"+fileName);
  			byte []bts=new byte[1024*24];
  			stream.read(bts);
  			return new String(bts,"UTF-8");

  		} catch (Exception e) {
  			JSONObject jsonObject=new JSONObject();
  			jsonObject.put("code",0);
  			jsonObject.put("data","{}");
  			jsonObject.put("erro",e.getMessage());
  			e.printStackTrace();
  			return jsonObject.toJSONString();

  		}

  	}
}
