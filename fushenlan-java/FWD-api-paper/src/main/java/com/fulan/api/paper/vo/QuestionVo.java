package com.fulan.api.paper.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.api.paper.domain.el.QuestionAnswer;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 试题管理，讲师，管理员共用
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
public class QuestionVo implements Serializable {

    private static final long serialVersionUID = 1L;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

	private String content;

	private Integer type;

	private Integer isRight;
	
	private Integer isShare;

	private String analysis;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long groupId;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long tagId;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long createUser;

	private Date gmtCreate;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long modifyUser;

	private Date gmtModified;
	
	private String stairTagName;
	
	private String secondName;

	private String questionScore;//问题分数

	private String answer;//用户答案
	private String answerQuestionScore;//用户得分
	
	private String eueId;//el_user_exam 主键id
	
	/**
	 * 试题答案List
	 */
	private List<QuestionAnswer> questionAnswer;

	/**
	 * 用户答案List
	 */
	private List<QuestionAnswerVo> userAnswer;
	
	/**
	 * 是否关联
	 */
	private String isNotRelation;
	
	/**
	 * 
	 */
	private List<Map<String,Object>> listRelationMap;
	
	private List<Map<String,Object>> listShareMap;
	
	/**
	 * 是否分享
	 */
	private String isNotShare;



}
