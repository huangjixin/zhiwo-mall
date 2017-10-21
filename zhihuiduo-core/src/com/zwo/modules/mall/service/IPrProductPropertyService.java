/**
 * 
 */
package com.zwo.modules.mall.service;

import java.util.List;

import com.zwo.modules.mall.domain.PrProductProperty;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface IPrProductPropertyService extends IBaseService<PrProductProperty> {
	/**
	 * 查询所有商品属性。
	 * @return
	 */
	List<PrProductProperty> listAll();
}
