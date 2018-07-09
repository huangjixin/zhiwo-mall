/**
 * 
 */
package com.fulan.application.oa.service;

import java.util.List;

import com.fulan.application.oa.domain.FwdOaAddress;
import com.fulan.application.oa.domain.FwdOaAddressExample;

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
	
	
	List<FwdOaAddress> findByCriteria(FwdOaAddressExample example);
	
	/**
	 * @Description:从common query 获取 代理人地址信息
	 * @param agentCode
	 * @return
	 */
	List<FwdOaAddress> getAgentAddressFromCommonQuery(String agentCode);
	
	
}
