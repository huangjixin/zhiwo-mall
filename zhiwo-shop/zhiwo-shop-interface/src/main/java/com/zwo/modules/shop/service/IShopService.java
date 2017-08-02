/**
 * 
 */
package com.zwo.modules.shop.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zwo.modules.shop.domain.Shop;
import com.zwo.modules.shop.domain.ShopCriteria;
import com.zwo.modules.shop.domain.ShopWithBLOBs;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 * 店铺接口类。
 */
public interface IShopService extends IBaseService<Shop> {
	
    int countByExample(ShopCriteria example);

    int insert(ShopWithBLOBs record);

    int insertSelective(ShopWithBLOBs record);

    ShopWithBLOBs selectByPrimKey(String id);

    int updateByExampleSelective(ShopWithBLOBs record,ShopCriteria example);

    int updateByExampleWithBLOBs(ShopWithBLOBs record, ShopCriteria example);
   
    int updateByExample(Shop record,ShopCriteria example);

    int updateByPrimaryKeySelective(ShopWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ShopWithBLOBs record);

}
