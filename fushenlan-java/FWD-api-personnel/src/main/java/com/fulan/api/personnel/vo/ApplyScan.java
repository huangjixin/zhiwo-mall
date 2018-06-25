package com.fulan.api.personnel.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import lombok.Data;
/**
 * 在线增员-计划申请提交预览VO
 *
 */
@Data
public class ApplyScan{
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long personnelId;//人才Id
	private String referrerName;//引荐人
	private String referrerNo;//引荐人编号
	private String branchName; //所属分公司城市
	private String branchCity;//所属城市
	private String operationNo; //运营组号
	private String talentPlan; //申请人才计划
	private String refereeName;//推荐人
	private String refereeNo; //推荐人编号
	private String occupation;//推荐人证件号码
	private String refereeStartTime;//推荐人开始日期
	
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long createUser;//创建人
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date createTime;//申请时间
	
	
}
