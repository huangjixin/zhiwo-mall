package com.fulan.application.oa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.application.oa.domain.FwdOaAddress;
import com.fulan.application.oa.domain.FwdOaAddressExample;
import com.fulan.application.oa.domain.FwdOaBankCard;
import com.fulan.application.oa.service.IAddressService;
import com.fulan.application.util.domain.Response;

/**
 * 地址控制层
 * 
 * @author 曾文明
 *
 */
@RestController
@RequestMapping("/address")
public class AddressController {
	private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
	@Autowired
	private IAddressService addressService;

	/**
	 * 保存数据
	 * 
	 * @param save
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	private int save(FwdOaAddress address) {
		address.setCreateDatetime(new Date());
		address.setUpdateDatetime(new Date());
		int result = addressService.save(address);
		return result;
	}

	/**
	 * 删除数据
	 * 
	 * @param delete
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	private int delete(@PathVariable(value = "id") Integer id) {
		int result = addressService.delete(id);
		return result;
	}

	/**
	 * 修改数据
	 * 
	 * @param update
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	private int update(FwdOaAddress address) {
		address.setUpdateDatetime(new Date());
		int result = addressService.update(address);
		return result;
	}

	/**
	 * 查询数据
	 * 
	 * @param selectAll
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	private Response<List<FwdOaAddress>> findAddresses(@RequestParam("agentCode") String agentCode) {
		List<FwdOaAddress> oaBankCardList = new ArrayList<FwdOaAddress>();
		try {
			oaBankCardList = addressService.getAgentAddressFromCommonQuery(agentCode);
		} catch (Exception e) {
			logger.error("Unknow Error", e);
			Response<List<FwdOaAddress>> response = new Response<List<FwdOaAddress>>(Response.ERROR,e.getMessage());
			return response;
		}
		Response<List<FwdOaAddress>> response = new Response<List<FwdOaAddress>>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		response.setData(oaBankCardList);
		return response;
	}

	/**
	 * 根据ID查询数据
	 * 
	 * @param selectAll
	 * @return
	 */
	@RequestMapping(value = "/selectById/{id}", method = RequestMethod.GET)
	private FwdOaAddress selectById(@PathVariable(value = "id") Integer id) {
		FwdOaAddress oaAddress = addressService.selectById(id);
		return oaAddress;
	}
}
