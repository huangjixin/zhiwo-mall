package com.fulan.application.service;

import com.fulan.api.personnel.vo.PersonnelPaperVo;
import com.fulan.application.util.domain.Response;

public interface PersonnelPaperInfoService {
	 /**
     * 在线增员-试题评分批量插入
     * */
	Response<Integer> insertPersonnelPaperInfoByList(PersonnelPaperVo personnelPaperVo);
}
