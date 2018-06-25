package com.fulan.application.oa.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.application.oa.domain.FwdOaAddress;
import com.fulan.application.oa.service.IAddressService;

/**
 * 地址控制层
 * 
 * @author 曾文明
 *
 */
@RestController
@RequestMapping("/address")
public class AddressController {

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
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	private List<FwdOaAddress> selectAll() {
		List<FwdOaAddress> addresses = addressService.selectAll();
		return addresses;
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
