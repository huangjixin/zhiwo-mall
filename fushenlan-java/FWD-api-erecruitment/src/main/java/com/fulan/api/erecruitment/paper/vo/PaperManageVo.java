package com.fulan.api.erecruitment.paper.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Api(tags = "PaperManageVo", description = "增员试卷管理")
@Data
public class PaperManageVo {
	@ApiModelProperty(value = "试卷主键",name="id")
	private String id;
	@ApiModelProperty(value = "试卷名称",name="paperName")
	private String paperName;
	@ApiModelProperty(value = "试卷类型(1.初审，2:甄选，3：决定)",name="paperType")
	private Integer paperType;
	@ApiModelProperty(value = "试卷说明",name="paperDesc")
	private String paperDesc;
	@ApiModelProperty(value = "分公司",name="orgId")
	private String orgId;
	@ApiModelProperty(value = "考题量",name="paperNum")
	private Integer paperNum;
	@ApiModelProperty(value = "试卷总分",name="paperScore")
	private Integer paperScore;
}
