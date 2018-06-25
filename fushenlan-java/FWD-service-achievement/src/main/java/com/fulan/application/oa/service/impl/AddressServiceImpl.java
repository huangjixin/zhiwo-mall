/**
 * 
 */
package com.fulan.application.oa.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fulan.application.oa.domain.FwdOaAddress;
import com.fulan.application.oa.mapper.FwdOaAddressMapper;
import com.fulan.application.oa.service.IAddressService;

/**
 * @author 黄记新 Tony
 *
 */
@Service
@Transactional
public class AddressServiceImpl implements IAddressService {

	private static String BASE_MESSAGE = "地址业务类AddressServiceImpl";
	private static Logger logger = Logger.getLogger(AddressServiceImpl.class);

	@Autowired
	private FwdOaAddressMapper addressMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fulan.application.oa.service.IAddressService#save(com.fulan.application.
	 * oa.domain.FwdOaAddress)
	 */
	@Override
	public int save(FwdOaAddress address) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"保存地址开始，传入的参数是："+address.toString());
		}
		int result = addressMapper.insertSelective(address);
		if (logger.isInfoEnabled()) {
			String msg = result==1?"成功":"失败";
			logger.info(BASE_MESSAGE+"保存地址结束，结果是："+msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.IAddressService#delete(int)
	 */
	@Override
	public int delete(int id) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"删除地址开始，传入的参数id是："+id);
		}
		int result = addressMapper.deleteByPrimaryKey(id);
		if (logger.isInfoEnabled()) {
			String msg = result==1?"成功":"失败";
			logger.info(BASE_MESSAGE+"删除地址结束术，结果是："+msg);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fulan.application.oa.service.IAddressService#update(com.fulan.application
	 * .oa.domain.FwdOaAddress)
	 */
	@Override
	public int update(FwdOaAddress address) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"更新地址开始，传入的参数是："+address.toString());
		}
		int result = addressMapper.updateByPrimaryKeySelective(address);
		if (logger.isInfoEnabled()) {
			String msg = result==1?"成功":"失败";
			logger.info(BASE_MESSAGE+"更新地址结束，结果是："+msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.IAddressService#selectAll()
	 */
	@Override
	public List<FwdOaAddress> selectAll() {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"查询所有地址开始");
		}
		List<FwdOaAddress> addresses = addressMapper.selectByExample(null);
		if (logger.isInfoEnabled()) {
			String msg = addresses.size()+"";
			logger.info(BASE_MESSAGE+"保存地址结束，结果的条目数是："+msg);
		}
		return addresses;
	}

	@Override
	public FwdOaAddress selectById(int id) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"查询根据id查询地址开始");
		}
		FwdOaAddress oaAddress = addressMapper.selectByPrimaryKey(id);
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"根据id查询地址数据，结果是："+JSONObject.toJSONString(oaAddress));
		}
		return oaAddress;
	}
}
