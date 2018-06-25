package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.plan.domain.Like;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 点赞 Mapper 接口
 * </p>
 *
 * @author Hedge
 * @since 2018-01-25
 */
@Mapper
public interface LikeMapper extends BaseMapper<Like> {
}
