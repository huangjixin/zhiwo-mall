package com.fulan.api.achievement.vo;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "ActivityAmountVo",description = "活动量视图")
public class ActivityAmountVo implements Serializable{

    private String activityName;//活动量名称

    private Integer finishedNum;//完成数量

    private Integer targetNum;//目标数量
}
