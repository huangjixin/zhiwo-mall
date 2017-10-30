package com.zwo.modules.mall.dao;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.zwo.modules.mall.domain.PrProduct;

public interface PrProductMapper extends Mapper<PrProduct> {
    
    
    /**
     * 查询会员分销的商品。
     * @param memberId
     * @return
     */
    List<PrProduct> selectByMemberId(String memberId);
    
    /**
     * 查询没有大文本数据的字段。
     * @param id
     * @return
     */
    PrProduct selectByKey(String id);
}