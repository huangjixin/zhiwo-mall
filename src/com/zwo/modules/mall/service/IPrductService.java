/**
 * 
 */
package com.zwo.modules.mall.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zwo.modules.mall.domain.PrImage;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.domain.PrProductCriteria;
import com.zwo.modules.mall.domain.PrProductWithBLOBs;
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
//	void sendCreateProductTopic(final String msg);
	
	int countByExample(PrProductCriteria example);

    int insert(PrProductWithBLOBs record);

    int insertSelective(PrProductWithBLOBs record);

    PrProductWithBLOBs selectByPrimKey(String id);

    int updateByExampleSelective(PrProductWithBLOBs record,PrProductCriteria example);

    int updateByExampleWithBLOBs(PrProductWithBLOBs record, PrProductCriteria example);
   
    int updateByExample(PrProduct record,PrProductCriteria example);

    int updateByPrimaryKeySelective(PrProductWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PrProductWithBLOBs record);
    
    /**
     * 根据商品ID查询产品图片。
     * @param productId
     * @return
     */
//    List<PrImage> selectByProductId(String productId,boolean isDefault);
    
    
    /**
     * 根据商铺ID查询商品。
     * @param id
     * @return
     */
    List<PrProduct> selectPrProductsByShopId(String shopId);
    
    /**
     * 根据商铺ID查询商品数量。
     * @param id
     * @return
     */
    Integer selectPrProductsCountByShopId(String shopId);
    
    /**
     * 根据种类进行商品分页查询。
     * @param categoryId
     * @param pageInfo
     * @return
     */
    PageInfo<PrProduct> selectByCategoryIdPageInfo(String categoryId,PageInfo<PrProduct> pageInfo);
    
    /**
     * 查询全部上架商品。
     * @return
     */
    List<PrProduct> selectAllByStatus(String status);
    
    /**
     * 根据上架商品分页查询。
     * @param categoryId
     * @param pageInfo
     * @return
     */
    PageInfo<PrProduct> selectAllByStatus(String status,PageInfo<PrProduct> pageInfo);
    
    /**
     * 首页查询商品。
     * @param pageInfo
     * @return
     */
    PageInfo<PrProduct> selectIndex(PageInfo<PrProduct> pageInfo);
}
