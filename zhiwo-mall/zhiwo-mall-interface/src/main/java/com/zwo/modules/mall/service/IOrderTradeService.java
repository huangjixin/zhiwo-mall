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
	
	/**
	 * 查询商品销售数量。
	 * @param productId
	 * @param status
	 * @return
	 */
	Integer selectCountByProductId(String productId,String status);
	
	/**
	 * 查询商铺交易的订单数量。
	 * @param shopId
	 * @param status
	 * @return
	 */
	Integer selectCountByShopId(String shopId,String status);
	
	/**
	 * 更新拼团的订单状态。
	 * @param groupId
	 * @param isFormSucc
	 * @return
	 */
	int updateOrderTradeByGroupId(String groupId,boolean isFormSucc);
	
}
