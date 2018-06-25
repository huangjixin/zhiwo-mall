package com.fulan.application.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.flow.domain.InterviewAction;

/**
 * <p>
 * 面试执行情况 Mapper 接口
 * </p>
 *
 * @author chenzhuang123
 * @since 2018-01-24
 */
@Mapper
public interface InterviewActionMapper extends BaseMapper<InterviewAction> {

}
