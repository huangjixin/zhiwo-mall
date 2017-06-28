/**
 * 
 */
package com.zwo.modules.mall.service;

import com.zwo.modules.mall.domain.PrProduct;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface IPrductService extends IBaseService<PrProduct> {
	/**
	 * 发送创建产品Topic。
	 * @param msg
	 */
	void sendCreateProductTopic(final String msg);
}
