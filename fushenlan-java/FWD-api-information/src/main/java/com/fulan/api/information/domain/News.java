package com.fulan.api.information.domain;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.Version;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import lombok.Data;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 资讯中心
 * </p>
 *
 * @author fulan123
 * @since 2018-04-09
 */
@Data
@Api(tags = "News", description = "资讯中心")
@TableName("iris_news")

public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键/编号", name = "id")
	@JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

	private String code;

	@ApiModelProperty(value = "标题", name = "title")
	private String title;

	@ApiModelProperty(value = " 1_全部 2_公司动态   3_热门活动 4_政策公告   5_产品上线", name = "type")
	private Integer type;

	@ApiModelProperty(value = "1_即使发送   2_定时发送", name = "sendType")
	@TableField("send_type")
	private Integer sendType;

	@ApiModelProperty(value = "1_图文 2_ 第三方链接地址", name = "contentType")
	@TableField("content_type")
	private Integer contentType;

	@TableField("schedule_time")
	private Date scheduleTime;

	@ApiModelProperty(value = "正文", name = "content")
	private String content;

	@TableField("is_secret")
	private Integer isSecret;

	@ApiModelProperty(value = "0_关闭,1_开启", name = "openShare")
	@TableField("open_share")
	private Integer openShare;

	@ApiModelProperty(value = "1_所有人2_ 指定部门 3_ 指定人员", name = "msgType")
	@TableField("msg_type")
	private Integer msgType;

	@ApiModelProperty(value = "信息状态 0_未发布,1_发布中,2_已发布", name = "status")
	private Integer status;

	@ApiModelProperty(value = "外部链接url", name = "externalLinkUrl")
	@TableField("external_link_url")
	private String externalLinkUrl;

	@ApiModelProperty(value = "图片路径", name = "path")
	private String path;
	
	
	@ApiModelProperty(value = "图片路径", name = "attachment_path")
	private String attachmentPath;
	
	@TableField("is_active")
	private Integer isActive;

	@ApiModelProperty(value = "备注", name = "remark")
	private String remark;

	@ApiModelProperty(value = "是否删除,0_否,1_是", name = "deleted")
	private Integer deleted;

	@ApiModelProperty(value = "修改时间", name = "gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;

	@ApiModelProperty(value = "修改人", name = "modifyUser")
	@TableField("modify_user")
	private Long modifyUser;

	@ApiModelProperty(value = "创建时间", name = "gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

	@ApiModelProperty(value = "创建人", name = "createUser")
	@TableField("create_user")
	private Long createUser;

	private String bak1;

	private String bak2;

	private String bak3;

	private String bak4;

	private String bak5;

}
