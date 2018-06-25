package com.fulan.api.achievement.vo;

import lombok.Data;

@Data
public class PerformanceTopVo {

	private PersonalPerformVo personalPerformVo;//我的个人业绩
	
	private CentralGovernmentVo centralGovernmentVo;//直辖组业绩
	
	private BusinessDepartmentVo businessDepartmentVo;//营业部业绩


}
