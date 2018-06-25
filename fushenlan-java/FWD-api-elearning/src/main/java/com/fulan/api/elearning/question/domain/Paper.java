package com.fulan.api.elearning.question.domain;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.Version;
import lombok.Data;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 试卷表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "Paper", description = "试卷表")
@TableName("el_paper")

public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "试卷编号",name="id")
	private Long id;

@ApiModelProperty(value = "试卷名称",name="name")
	private String name;

@ApiModelProperty(value = "试卷说明",name="discription")
	private String discription;

@ApiModelProperty(value = "职业规范/思维方法/演讲口才/销售技巧/保险产品",name="type")
	private Integer type;

@ApiModelProperty(value = "答题时间",name="examTime")
	@TableField("exam_time")
	private Integer examTime;

@ApiModelProperty(value = "试卷满分",name="fullMark")
	@TableField("full_mark")
	private Integer fullMark;

@ApiModelProperty(value = "通过分数",name="passMark")
	@TableField("pass_mark")
	private Integer passMark;

@ApiModelProperty(value = "可测试次数",name="testNum")
	@TableField("test_num")
	private Integer testNum;

@ApiModelProperty(value = "是否人工阅卷,1是，0否",name="isArtificialMark")
	@TableField("is_artificial_mark")
	private Integer isArtificialMark;

@ApiModelProperty(value = "一级分类-用户组/角色编号",name="groupId")
	@TableField("group_id")
	private Long groupId;

@ApiModelProperty(value = "二级分类-用户组/角色下的标签",name="tagId")
	@TableField("tag_id")
	private Long tagId;

@ApiModelProperty(value = "是否随机选题",name="isRandom")
	@TableField("is_random")
	private Integer isRandom;

@ApiModelProperty(value = "是否考题乱序1乱序0不乱",name="isQuestionShuffle")
	@TableField("is_question_shuffle")
	private Integer isQuestionShuffle;

@ApiModelProperty(value = "是否考题选项乱序1乱序0不乱",name="isQuestionItemShuffle")
	@TableField("is_question_item_shuffle")
	private Integer isQuestionItemShuffle;

@ApiModelProperty(value = "创建人",name="createUser")
	@TableField("create_user")
	private Long createUser;

@ApiModelProperty(value = "创建时间",name="gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

@ApiModelProperty(value = "修改人",name="modifyUser")
	@TableField("modify_user")
	private Long modifyUser;

@ApiModelProperty(value = "修改时间",name="gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;



}
