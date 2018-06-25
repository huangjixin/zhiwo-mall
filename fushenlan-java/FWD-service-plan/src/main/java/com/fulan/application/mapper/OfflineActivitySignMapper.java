package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.plan.domain.OfflineActivitySign;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * <p>
 * 线下活动签到表 Mapper 接口
 * </p>
 *
 * @author fulan123
 * @since 2018-01-24
 */
@Mapper
public interface OfflineActivitySignMapper extends BaseMapper<OfflineActivitySign> {


    Long hasSignd(Map<String,Object> paramMap);
}
