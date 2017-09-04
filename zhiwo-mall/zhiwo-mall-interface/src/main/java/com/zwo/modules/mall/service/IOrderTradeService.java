/**
 * 
 */
package com.zwo.modules.mall.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zwo.modules.mall.domain.OrderTrade;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface IOrderTradeService extends IBaseService<OrderTrade> {
	/**
	 * 查询我的订单。
	 * @param userId
	 * @param status
	 * @return
	 */
	PageInfo<OrderTrade> selectByUserId(String userId,String status, PageInfo<OrderTrade> pageInfo);
}
