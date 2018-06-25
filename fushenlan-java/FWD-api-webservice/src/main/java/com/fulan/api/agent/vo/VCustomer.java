package com.fulan.api.agent.vo;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

/**
 * ClassName:VCustomer
 * Date:     2018-04-10 16:47
 * Reason: 针对IRIS接口添加客户用的,方便回写客户,客户数据暂时不做落地处理
 *
 * @author Lycol
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class VCustomer {

    @ApiModelProperty(value = "客户编号", name = "id")
    private String id;

    @ApiModelProperty(value = "客户名称", name = "customerName")
    private String customerName;

    @ApiModelProperty(value = "性别", name = "sex")
    private byte sex;

    @ApiModelProperty(value = "出生日期", name = "birthDate")
    private Date birthDate;

    @ApiModelProperty(value = "证件类型", name = "paperType")
    private Integer paperType;

    @ApiModelProperty(value = "手机号", name = "mobile")
    private String  mobile;

    @ApiModelProperty(value = "家庭住址", name = "homeAddress")
    private String homeAddress;

    @ApiModelProperty(value = "省", name = "province")
    private String province;

    @ApiModelProperty(value = "市", name = "city")
    private String city;

    @ApiModelProperty(value = "区", name = "area")
    private String area;


}
