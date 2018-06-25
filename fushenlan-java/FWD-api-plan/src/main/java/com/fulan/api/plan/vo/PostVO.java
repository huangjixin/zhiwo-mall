package com.fulan.api.plan.vo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(tags = "PostVO", description = "岗位路线")
public class PostVO {

    @ApiModelProperty(value = "课程名称",name="cname")
    private String cname;
    @ApiModelProperty(value = "是否必修-0/1（1表示必修）", name = "isCompulsory")
    private Integer iscompulsory;
    @ApiModelProperty(value = "試卷名称",name="pname")
    private String pname;
    @ApiModelProperty(value = "試卷id",name="pid")
    private String pid;
    @ApiModelProperty(value = "课程id",name="cid")
    private String cid;
    @ApiModelProperty(value = "课程试卷排序",name="seq")
    private String seq;  
}
