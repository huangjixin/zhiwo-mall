package com.zwo.modules.mall.dao;

import com.zwo.modules.mall.domain.OrderDelivery;
import com.zwo.modules.mall.domain.OrderDeliveryCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface OrderDeliveryMapper extends Mapper<OrderDelivery> {
    int countByExample(OrderDeliveryCriteria example);

    int deleteByExample(OrderDeliveryCriteria example);

    List<OrderDelivery> selectByExample(OrderDeliveryCriteria example);

    int updateByExampleSelective(@Param("record") OrderDelivery record, @Param("example") OrderDeliveryCriteria example);

    int updateByExample(@Param("record") OrderDelivery record, @Param("example") OrderDeliveryCriteria example);
}