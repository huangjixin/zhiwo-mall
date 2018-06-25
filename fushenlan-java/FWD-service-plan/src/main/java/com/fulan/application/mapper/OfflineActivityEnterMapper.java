package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.plan.domain.OfflineActivityEnter;
import com.fulan.api.plan.vo.ActivityVO;
import com.fulan.api.plan.vo.OfflineActivityVO;
import com.fulan.api.plan.vo.SignTypeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 线下活动报名表 Mapper 接口
 * </p>
 *
 * @author fulan123
 * @since 2018-01-24
 */
@Mapper
public interface OfflineActivityEnterMapper extends BaseMapper<OfflineActivityEnter> {

    Date selectEnterTime(Long id);

    Integer countCourse(Long id);

    List<OfflineActivityVO> selectBeginActivity(ActivityVO activityVO);

    Integer queryForCount(ActivityVO activityVO);

    Integer queryForEndCount(ActivityVO activityVO);

    List<OfflineActivityVO> selectEndActivity(ActivityVO activityVO);

    SignTypeDto selectSignType(Long activityId);


    Integer selectSignNumber(Long activityId);

    Integer selectEnterCount(Long activityId);

    Integer selectSignCount(Long id);
}
