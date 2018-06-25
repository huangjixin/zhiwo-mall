package com.fulan.api.message.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @author: guiyang
 * @date: 2018/3/5 11:15
 */
@Data
public class SmsPageVO {

    @ApiModelProperty(value = "分页索引", name = "pageIndex")
    private int pageIndex;

    @ApiModelProperty(value = "分页数量", name = "pageSize")
    private int pageSize;

    @ApiModelProperty(value = "开始时间", name = "startDate")
    private String startDate;

    @ApiModelProperty(value = "结束时间", name = "endDate")
    private String endDate;
}