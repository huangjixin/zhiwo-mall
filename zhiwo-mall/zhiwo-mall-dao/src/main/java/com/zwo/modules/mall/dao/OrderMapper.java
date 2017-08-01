package com.zwo.modules.mall.dao;

import com.zwo.modules.mall.domain.Order;
import com.zwo.modules.mall.domain.OrderCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface OrderMapper extends Mapper<Order> {
    int countByExample(OrderCriteria example);

    int deleteByExample(OrderCriteria example);

    List<Order> selectByExample(OrderCriteria example);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderCriteria example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderCriteria example);
}