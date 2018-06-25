package com.fulan.application.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fulan.api.personnel.domain.PersonnelPaper;
import com.fulan.api.personnel.domain.PersonnelPaperInfo;
import com.fulan.api.personnel.vo.PersonnelPaperVo;
import com.fulan.application.mapper.PersonnelPaperInfoMapper;
import com.fulan.application.mapper.PersonnelPaperMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.PersonnelPaperInfoService;
import com.fulan.application.util.domain.Response;
@Service
@Transactional
public class PersonnelPaperInfoServiceImpl implements PersonnelPaperInfoService {
	@Autowired
	PersonnelPaperInfoMapper personnelPaperInfoMapper;
	@Autowired
	PersonnelPaperMapper personnelPaperMapper;
	@Autowired
	IdGenerator idGenerator;
	@Override
	public Response<Integer> insertPersonnelPaperInfoByList(PersonnelPaperVo personnelPaperVo) {
		// TODO Auto-generated method stub
		PersonnelPaper personnelPaper = personnelPaperVo.getPersonnelPaper();
			personnelPaperMapper.deleteByRecord(personnelPaper);
			personnelPaperInfoMapper.deleteByRecord(personnelPaper);
		//试题答题结果录入
		List<PersonnelPaperInfo> list = personnelPaperVo.getPaperInfoList();
		for(int i=0;i<list.size();i++){
			PersonnelPaperInfo personnelPaperInfo = list.get(i);
			Long id  = idGenerator.generate();
			personnelPaperInfo.setId(id);
			personnelPaperInfo.setPersonnelId(personnelPaper.getPersonnelId());
			list.set(i, personnelPaperInfo);
		}
		personnelPaperInfoMapper.insertByList(list);
		//试卷结果信息录入
		Long id  = idGenerator.generate();
		personnelPaper.setId(id);
		List<PersonnelPaper> result = personnelPaperMapper.getPersonnelPaperList(personnelPaper);
		if(result.size()<1){
			personnelPaperMapper.insertSelective(personnelPaper);
		}else{
			personnelPaperMapper.updateByPersonnelPaper(personnelPaper);
		}
		Response<Integer> resp = new Response<Integer>(Response.SUCCESS, "数据插入成功");
		resp.setData(1);
		return resp;
	}
	
	
	
}
