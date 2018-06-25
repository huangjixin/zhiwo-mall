/**
 * Project Name:FWD-service-webservice
 * File Name:CustomerServiceImpl.java
 * Package Name:com.fulan.application.service.impl
 * Date:2018年4月9日上午10:09:06
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.application.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fulan.api.agent.vo.CustomerSearchParm;
import com.fulan.api.agent.vo.CustomerVo;
import com.fulan.application.service.CustomerClient;
import com.fulan.application.service.CustomerService;
import com.fulan.application.util.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * ClassName:CustomerServiceImpl Reason: TODO ADD REASON Date: 2018年4月9日
 * 上午10:09:06
 * 
 * @author chen.zhuang
 * @version
 * @since JDK 1.8
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerClient customerClient;

	@Override
	public Response<String> customerSearch(String userId, String systemId, String token, CustomerSearchParm req) {
		Response<String> resp = new Response<String>(Response.SUCCESS, "查询成功！");
		//String s = customerClient.customerSearch(userId, systemId, token, req);
		String s = JSONObject.parseObject(readJson("cus_detail.json")).toJSONString();
		resp.setData(s);
		resp.setCode("1");
		return resp;

	}

	@Override
	public Response<String> customerCreate(String userId, String systemId, String token, CustomerVo req) {
		
		Response<String> resp = new Response<String>(Response.SUCCESS, "插入成功！");
		String s = customerClient.customerCreate(userId, systemId, token, req);
		resp.setData(s);
		resp.setCode("1");
		return resp;
	}


	@Override
	public String getAgentCustomerGroupList(Integer customerType, String agentCode) {

		return JSONObject.parseObject(readJson("cus_group_list.json")).toJSONString();

	}


	@Override
	public String getCustomerMapGroupList(String agentCode) {
		return  JSONObject.parseObject(readJson("cus_map.json")).toJSONString();

	}

	@Override
	public Response<String> getCustomerProposal(String filePath) {
		// TODO Auto-generated method stub
		Response<String> resp = new Response<String>(Response.SUCCESS, "获得建议书");
		resp.setData(JSONObject.parseObject(readJson(filePath)).toJSONString());
		resp.setCode("1");
		return  resp;
	}

	@Override
	public Response<String> getCustomerFamily(String filePath) {
		// TODO Auto-generated method stub
		Response<String> resp = new Response<String>(Response.SUCCESS, "获得家庭树");
		resp.setData(JSONObject.parseObject(readJson(filePath)).toJSONString());
		resp.setCode("1");
		return  resp;	
		}
	/**
	 * Read Json from file
	 * @param fileName
	 * @return
	 */
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

	@Override
	public Response<String> customerUpdate(String userId, String systemId, String token, CustomerVo req) {
		Response<String> resp = new Response<String>(Response.SUCCESS, "插入成功！");
		String s = customerClient.customerUpdate(userId, systemId, token, req);
		resp.setData(s);
		resp.setCode("1");
		return resp;
	}


}
