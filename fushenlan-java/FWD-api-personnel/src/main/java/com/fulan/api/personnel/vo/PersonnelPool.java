package com.fulan.api.personnel.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 代理人人才库-当前人才信息查看 VO
 * 
 * @author Administrator
 *
 */
@Data
public class PersonnelPool {
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "",name="id")
	private String id; 
	
	private String name;//姓名
	private String sex;//性别
	private String age; //年龄  根据出生日期 再sql中转化
	private String cellphone;//电话
	private String talentPlan; //人才计划
	private String refereeName; //推荐人
	private String referrerName ;//引荐人
	private String education; //学历
	private String school;//毕业院校
	private String channel;//来源渠道
		
}
