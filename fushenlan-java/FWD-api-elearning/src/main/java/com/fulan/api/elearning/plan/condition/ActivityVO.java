package com.fulan.api.elearning.plan.condition;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.Api;
import lombok.Data;

/**
 * Created by fsl on 2018/1/24.
 */
@Data
@Api(tags = "ActivityVO", description = "线下活动参数")
public class ActivityVO {

    private Long activityId;//活动id

    private Long userId;//用户id

    private Long courseId;//课程id
}
