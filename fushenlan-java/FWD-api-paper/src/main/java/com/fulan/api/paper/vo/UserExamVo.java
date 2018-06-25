package com.fulan.api.paper.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserExamVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long paperId;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long questionId;

	private Integer questionType;
	
	private Integer questionState;

	private Integer questionSeq;

	private Integer questionScore;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long answerId;

	private Integer answerSeq;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long userId;

	private String userName;

	private String answer;

	private Integer score;

	private Date gmtCreate;

	private Date gmtModified;

	private Integer examNum;
	
	private QuestionVo question;

}
