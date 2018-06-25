package com.fulan.api.paper.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.api.paper.domain.el.UserExam;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ElPaperVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

	private String name;

	private String discription;

	private Integer type;

	private Integer examTime;

	private Integer fullMark;

	private Integer passMark;
	
	private Integer isShare;

	private Integer testNum;

	private Integer isArtificialMark;
	
	private Integer isSeeAnswer;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long groupId;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long tagId;

	private Integer isRandom;

	private Integer isQuestionShuffle;

	private Integer isQuestionItemShuffle;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long createUser;

	private Date gmtCreate;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long modifyUser;

	 @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date gmtModified;

	//试卷状态
	private String paperState;
	
	private Long planCourseId;
	
	private String classNum;
	
	private String isNotShare;
	
	private String isNotRelation;
	
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

	private Integer examNum;
	
	private List<UserExamVo> userExam;
	
	private List<QuestionVo> questionVo;
	
	private List<Map<String,Object>> mapName;


}
