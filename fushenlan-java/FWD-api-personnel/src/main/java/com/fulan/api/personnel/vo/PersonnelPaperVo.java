package com.fulan.api.personnel.vo;

import java.util.List;

import com.fulan.api.personnel.domain.InterviewAction;
import com.fulan.api.personnel.domain.PersonnelPaper;
import com.fulan.api.personnel.domain.PersonnelPaperInfo;

import lombok.Data;
/**
 * 我的增员  vo类
 * @author chenzhuang
 *
 */
@Data
public class PersonnelPaperVo{
	
	List<PersonnelPaperInfo> paperInfoList;
	
	PersonnelPaper personnelPaper;
	
	InterviewAction interviewAction;
	
}
