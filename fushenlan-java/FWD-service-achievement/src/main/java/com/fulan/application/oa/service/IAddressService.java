/**
 * 
 */
package com.fulan.application.oa.service;

import java.util.List;

import com.fulan.application.oa.domain.FwdOaAddress;

/**
 * @author 黄记新
 * 提供对地址的增删改查
 */
public interface IAddressService {
	/**
	 * 新增一个地址
	 * @param address
	 * @return
	 */
	int save(FwdOaAddress address);
	/**
	 * 删除一个地址
	 * @param address
	 * @return
	 */
	int delete(int id);
	/**
	 * 修改
	 * @param address
	 * @return
	 */
	int update(FwdOaAddress address);
	/**
	 * 查询全部列表
	 * @param address
	 * @return
	 */
	List<FwdOaAddress> selectAll();
	
	/**
	 * 根据ID查询数据
	 * @param FwdOaAddress
	 * @return
	 */
	FwdOaAddress selectById(int id);
}
