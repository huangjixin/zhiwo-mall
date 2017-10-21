/**
 * 
 */
package com.zwo.modules.mall.service;

import java.util.List;

import com.zwo.modules.mall.domain.PrProductPropertyValue;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface IPrProductPropertyValueService extends IBaseService<PrProductPropertyValue> {
	/**
	 * 根据商品ID进行打包价查询。
	 * @param pId
	 * @return
	 */
	List<PrProductPropertyValue> selectByProductId(String pId);
	/**
	 * 根据商品ID删除。
	 * @param pId
	 * @return
	 */
	void deleteByProductId(String pId);
}
