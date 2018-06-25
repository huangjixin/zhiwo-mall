package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.plan.domain.History;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 浏览记录 Mapper 接口
 * </p>
 *
 * @author Hedge
 * @since 2018-01-30
 */
@Mapper
public interface HistoryMapper extends BaseMapper<History>{

}
