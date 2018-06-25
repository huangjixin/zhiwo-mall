package com.fulan.application.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fulan.api.paper.vo.PaperVo;
import com.fulan.application.service.PersonnelPaperService;
@Service
@Transactional
public class PersonnelPaperServiceImpl implements PersonnelPaperService {
	
	@Override
	public PaperVo getPaperExam(String paperType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePersonnelPaper(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
