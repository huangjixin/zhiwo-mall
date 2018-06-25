package com.fulan.application.service;

import java.util.Map;

import com.fulan.api.paper.vo.PaperVo;

public interface PersonnelPaperService {
	/**
	 * 增员试卷获取
	 * @param 
	 * @return
	 */
	PaperVo getPaperExam(String paperType); 
	
	/**
	 * 增员考试更新或插入
	 * @param 
	 * @return
	 */
	int updatePersonnelPaper(Map<String,Object> map); 
}
