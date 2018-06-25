package com.fulan.api.paper.domain.el;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import com.fulan.application.util.util.IdAnnon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 试题试卷分享
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "PaperQuesShare", description = "试题试卷分享")
@TableName("el_paper_ques_share")
public class PaperQuesShare implements Serializable{
	
	@ApiModelProperty(value = "系统编号",name="id")
	@IdAnnon
	@TableField("id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;
	
	@ApiModelProperty(value = "试题试卷编号",name="hostId")
	@TableField("host_id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long hostId;
	
	@ApiModelProperty(value = "关联类型 1试题，2试卷",name="type")
	@TableField("type")
	private Integer type;
	
	@ApiModelProperty(value = "用户组编号",name="groupId")
	@TableField("group_id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long groupId;
	
	@ApiModelProperty(value = "创建时间",name="gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;
	
}
