package com.fulan.api.erecruitment.personnel.vo;

import lombok.Data;
/**
 * 后台人才管理列表  vo类
 * @author kang
 *
 */
@Data
public class PersonnelManageVo{
	private String id;
	private String name;
	private String sex;
	private String age; //年龄  根据出生日期 再sql中转化
	private String cellphone;
	private String identityCode;
	private String maxEducation;
	private String School; //取最高学历所在学校
	private String RefereeName; //推荐人 er_apply.referee_name
	private String ReferrerName;//引荐人   暂未发现 在哪里
	
}
