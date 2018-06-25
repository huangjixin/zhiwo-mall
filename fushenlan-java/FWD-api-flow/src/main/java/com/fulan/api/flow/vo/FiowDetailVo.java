/**
 * Project Name:FWD-api-flow
 * File Name:FiowDetailVo.java
 * Package Name:com.fulan.api.flow.vo
 * Date:2018年2月2日下午3:41:18
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.api.flow.vo;

import java.util.List;

import com.fulan.api.flow.domain.InterviewAction;
import com.fulan.api.paper.vo.PaperDetailVo;
import com.fulan.api.personnel.vo.PersonnelManageInfoVo;

import io.swagger.annotations.Api;
import lombok.Data;

/**
 * ClassName:FiowDetailVo
 * Reason:	 TODO ADD REASON
 * Date:     2018年2月2日 下午3:41:18 
 * @author   chen.zhuang
 * @version  
 * @since    JDK 1.8 
 */
@Data
@Api(tags = "FiowDetailVo", description = "面试详情")
public class FiowDetailVo {

	private   InterviewerDetialVo interviewerDetialVo;
	
	private   List<PaperDetailVo>   paperList;
	
	
	private  PersonnelManageInfoVo  personnelManageInfoVo;
	
	
}

