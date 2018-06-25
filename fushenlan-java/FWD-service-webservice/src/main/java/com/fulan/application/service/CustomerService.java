/**
 * Project Name:FWD-service-webservice
 * File Name:CustomerService.java
 * Package Name:com.fulan.application.service
 * Date:2018年4月9日上午10:03:46
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.application.service;

import com.fulan.api.agent.vo.CustomerSearchParm;
import com.fulan.api.agent.vo.CustomerVo;
import com.fulan.application.util.domain.Response;

/**
 * ClassName:CustomerService
 * Reason:	客户服务Service
 * Date:     2018年4月9日 上午10:03:46 
 * @author   chen.zhuang
 * @version  
 * @since    JDK 1.8 
 */
public interface CustomerService {

	
	/**
	 * customerSearch
	 * 根据条件查找信息
	 * @warn(注意事项 – 可选)
	 * @param userId
	 * @param systemId
	 * @param token
	 * @param req
	 * @return
	 */
	Response<String> customerSearch( String userId,String systemId, String token,CustomerSearchParm req);
	
	/**
	 * customerCreate
	        创建个人信息
	 * @warn(注意事项 – 可选)
	 * @param userId
	 * @param systemId
	 * @param token
	 * @param req
	 * @return
	 */
	Response<String> customerCreate( String userId,String systemId, String token,CustomerVo req);

	
	/**
	 * customerUpdate
	 * 跟新个人信息
	 * @warn(注意事项 – 可选)
	 * @param userId
	 * @param systemId
	 * @param token
	 * @param req
	 * @return
	 */
	Response<String> customerUpdate( String userId,String systemId, String token,CustomerVo req);
	
	
	
	
	/**
	 *获取代理人的列表模式的客户信息
	 * @param agentCode    代理人编号
	 * @param customerType 客户类型
	 * @return
	 */
	String getAgentCustomerGroupList(Integer customerType, String agentCode);


	/**
	 * 获取代理人的地图模式客户信息
	 * @param agentCode
	 * @return
	 */
	String getCustomerMapGroupList(String agentCode);
	
	Response<String> getCustomerProposal(String filePath);
	
	Response<String> getCustomerFamily(String filePath);


}

