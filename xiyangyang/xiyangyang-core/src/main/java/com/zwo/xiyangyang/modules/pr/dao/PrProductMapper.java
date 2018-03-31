package com.zwo.xiyangyang.modules.pr.dao;

import org.apache.ibatis.annotations.Param;

import com.zwo.xiyangyang.modules.pr.domain.PrProduct;
import tk.mybatis.mapper.common.Mapper;

public interface PrProductMapper extends Mapper<PrProduct> {
	/**
	 * 根据Id进行联查。
	 * @param id
	 * @return
	 */
	PrProduct selectById(@Param("id")String id );
}