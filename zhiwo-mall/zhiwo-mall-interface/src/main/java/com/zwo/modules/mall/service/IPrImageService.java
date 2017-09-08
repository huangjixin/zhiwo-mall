/**
 * 
 */
package com.zwo.modules.mall.service;

import java.util.List;

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
	
	/**
     * 根据商品ID和图片的类型查询产品图片。
     * @param productId
     * @return
     */
    List<PrImage> selectByProductId(String productId,String type);
    
    /**
     * 更新商品图片为真实的外键。
     * @param productId
     * @return
     */
    int updatePrImageRealPId(String productId);
}
