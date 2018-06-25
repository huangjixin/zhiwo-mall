/**
  * Copyright 2018 bejson.com 
  */
package com.fulan.api.agent.domain;
import java.util.List;

import com.fulan.api.agent.vo.Addresses;
import com.fulan.api.agent.vo.CircInfo;
import com.fulan.api.agent.vo.CrsInfos;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Auto-generated: 2018-03-29 11:30:7
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 *  Changed by Lycol for explain what is mean of some fields
 */
@Data
@Api(tags = "客户模型", description = "CCR系统客户模型")
public class Customer {

    @ApiModelProperty(name = "customerId",value = "客户编号",example = "C00001")
    private String customerId;
    private String clientType;
    @ApiModelProperty(name = "occupationCode",value = "职业代码")
    private String occupationCode;
    private String status;
    @ApiModelProperty(name = "gender",value = "性别")
    private String gender;
    private String birthPlace;
    private String birthPlaceCode;
    private String title;
    @ApiModelProperty(name = "identityNo",value = "证件号码",example = "432123***77878")
    private String identityNo;
    private String issueCountry;
    private String issuePlace;
    @ApiModelProperty(name = "identityType",value = "证件类型",example = "1")
    private String identityType;
    private String firstName;
    private String middleName;
    private String lastName;
    @ApiModelProperty(name = "fullName",value = "客户全名",example = "C00001")
    private String fullName;
    private String companyName;
    private String companyName2;
    private String companyName3;
    private String companyIndustry;
    private String maritalStatus;
    private String countryCode;
    private String nationality;
    private String nationality2;
    private String nationality3;
    @ApiModelProperty(name = "mobileNumber",value = "手机号码",example = "188****6789")
    private String mobileNumber;
    private String phoneNumber1;
    private String phoneNumber2;
    private String faxNumber;
    private String blackList;
    private String blackListReason;
    private String amla;
    private String fatca;
    private String directmail;
    private String mailing;
    private String vip;
    private String drivingLicenseNo;
    private String preferName;
    private String preferName2;
    private String preferName3;
    private String alternateContactType;
    private String alternateContact;
    private String fpmsClientNumber;
    private String birthCountry;
    private String taxId;
    private String agent;
    private String amlReportStatus;
    private String legalRep;
    private String regCountry;
    private String regPlace;
    @ApiModelProperty(name = "addressLine1",value = "省")
    private String addressLine1;
    @ApiModelProperty(name = "addressLine2",value = "市")
    private String addressLine2;
    @ApiModelProperty(name = "addressLine3",value = "区")
    private String addressLine3;
    @ApiModelProperty(name = "addressLine4",value = "详细地址")
    private String addressLine4;
    private List<Addresses> addresses;
    private List<CrsInfos> crsInfos;
   // private CircInfo circInfo;
    private String nameFormat;
    @ApiModelProperty(name = "jobPosition",value = "职业信息")
    private String jobPosition;
    private String workingStatus;
    private String workingTime;
    private String taxFlag;
    private String sourceOfEvidence;
    private String category;
    private String docNo;
    private String staffFlag;
    private String oldIdNo;
    private String riskRating;
    private String natOfChina;
    private String educationCode;
    private String censusRegister;
    private String householdBookletRegPlace1;
    private String householdBookletRegPlace2;
    private String householdBookletRegPlace3;
    private String drivingLicenseCode;
    private String fao;
    private String originCountry;
    private String capital;
    private String staffNo;
    private String socialInsurance;
    private String businessRegType;
    private String businessRegNo;
    private String agentCode;
    //private String familyAnnualIncome;
   // private String annualIncome;
    //private String circInfo;
    @ApiModelProperty(name = "birthDate",value = "出生年月日")
    private Date birthDate;
    //private String deathDate;
    private String inception;
    private String dateOfWork;
    private String joinDate;
    private Date idExpiryDate;
    //private String fatcaEffdate;
    private String idIssueDate;
    private String marriageDate;

}