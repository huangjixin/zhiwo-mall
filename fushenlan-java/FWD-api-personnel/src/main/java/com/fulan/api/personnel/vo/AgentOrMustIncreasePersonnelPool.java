package com.fulan.api.personnel.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 代理人或者准增员人才库显示信息VO
 * @author Administrator
 *
 */
@Data
public class AgentOrMustIncreasePersonnelPool {
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "",name="id")
	private long id;//姓名
	
	private String name;//姓名
	private String sex;//性别
	private String age; //年龄  根据出生日期 再sql中转化
	private String cellphone;//电话
	private String talentPlan; //人才计划
	private String education; //学历
	private String school;//毕业院校
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date createTime;//创建时间
}
