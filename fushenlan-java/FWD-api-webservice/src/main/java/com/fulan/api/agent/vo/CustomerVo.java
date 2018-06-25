/**
 * Project Name:FWD-api-webservice
 * File Name:CustomerVo.java
 * Package Name:com.fulan.api.agent.vo
 * Date:2018年4月9日下午1:32:48
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.api.agent.vo;

import com.fulan.api.agent.domain.Customer;
import io.swagger.annotations.Api;

/**
 * ClassName:CustomerVo
 * Reason:	 TODO ADD REASON
 * Date:     2018年4月9日 下午1:32:48 
 * @author   chen.zhuang
 * @version  
 * @since    JDK 1.8 
 */
public class CustomerVo {

	private  Customer Customer;
	
	private  String  tempCustomerId;

	public Customer getCustomer() {
		return Customer;
	}

	public void setCustomer(Customer customer) {
		Customer = customer;
	}

	public String getTempCustomerId() {
		return tempCustomerId;
	}

	public void setTempCustomerId(String tempCustomerId) {
		this.tempCustomerId = tempCustomerId;
	}
	
	
	
	
}

