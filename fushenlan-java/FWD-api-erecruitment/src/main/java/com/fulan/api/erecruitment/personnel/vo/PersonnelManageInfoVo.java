package com.fulan.api.erecruitment.personnel.vo;

import java.util.List;

import com.fulan.api.erecruitment.personnel.domain.Apply;
import com.fulan.api.erecruitment.personnel.domain.Educational;
import com.fulan.api.erecruitment.personnel.domain.FamilyMember;
import com.fulan.api.erecruitment.personnel.domain.Personnel;
import com.fulan.api.erecruitment.personnel.domain.Tag;
import com.fulan.api.erecruitment.personnel.domain.WorkExperience;

import lombok.Data;

/**
 * 后台人才个人信息详情 vo
 * @author kang
 *
 */
@Data
public class PersonnelManageInfoVo {
	private Personnel personnel;
	private List<FamilyMember> familyMember;
	private List<Educational> educational;
	private List<WorkExperience> workExperience;
	private List<Tag> tag;
	private List<Apply> apply;
	
	
}
