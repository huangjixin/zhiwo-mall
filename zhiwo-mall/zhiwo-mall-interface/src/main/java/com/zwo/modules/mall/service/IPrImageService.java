/**
 * 
 */
package com.zwo.modules.mall.service;

import com.zwo.modules.mall.domain.PrImage;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface IPrImageService extends IBaseService<PrImage> {
	/**
	 * 根据商品ID删除对象。
	 * @param productId
	 * @return
	 */
	int deletePrImageByProductId(String productId);
}
