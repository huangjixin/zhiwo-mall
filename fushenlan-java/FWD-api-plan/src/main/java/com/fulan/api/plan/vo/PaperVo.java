package com.fulan.api.plan.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 试卷视图表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "Paper", description = "试卷视图")
public class PaperVo implements Serializable {

    private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "试卷编号",name="id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

	@ApiModelProperty(value = "试卷名称",name="name")
	private String name;

@ApiModelProperty(value = "试卷说明",name="discription")
	private String discription;

@ApiModelProperty(value = "职业规范/思维方法/演讲口才/销售技巧/保险产品",name="type")
	private String type;

@ApiModelProperty(value = "试卷满分",name="fullMark")
	private Integer fullMark;

@ApiModelProperty(value = "通过分数",name="passMark")
	private Integer passMark;

@ApiModelProperty(value = "可测试次数",name="testNum")
	private Integer testNum;

@ApiModelProperty(value = "用户得分",name="score")
	private Integer score;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getFullMark() {
		return fullMark;
	}

	public void setFullMark(Integer fullMark) {
		this.fullMark = fullMark;
	}

	public Integer getPassMark() {
		return passMark;
	}

	public void setPassMark(Integer passMark) {
		this.passMark = passMark;
	}

	public Integer getTestNum() {
		return testNum;
	}

	public void setTestNum(Integer testNum) {
		this.testNum = testNum;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
