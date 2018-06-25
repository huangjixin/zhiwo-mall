package com.fulan.api.achievement.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(tags = "IndexVo", description = "指标VO")
public class IndexVo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 2034075667988267172L;

	@ApiModelProperty(value = "指标编号",name="id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

	private Integer isReachIndicator;// 是否达成指标

	private String indexName;// 指标名称

	private BigDecimal actualVal;// 实际值

	private BigDecimal targetVal;// 目标值

	private BigDecimal balanceVal;// 差额

	private BigDecimal finishedProgress;//完成进度
}
