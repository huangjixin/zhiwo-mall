package com.fulan.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fulan.api.personnel.domain.Apply;
import com.fulan.api.personnel.vo.ApplyScan;
import com.fulan.application.mapper.ApplyMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.ApplyService;
import com.fulan.application.util.domain.Response;

@Service
@Transactional
public class ApplyServiceImpl implements ApplyService{
	
	@Autowired
	ApplyMapper applyMapper;
	
	@Autowired
	IdGenerator idGenerator;
	
	@Override
	public Response<String> save(Long personnelId,Apply apply) {
		Response<String> resp=new Response<String>(Response.SUCCESS, "添加人才计划申请信息成功");
		//删除指定个人的计划申请信息
		deleteByPersonelId(personnelId);
		Long ids = idGenerator.generate();
		apply.setId(ids);
		applyMapper.insert(apply);
		return resp;
	}

	@Override
	public Response<ApplyScan> getScanApply(Long personnelId) {
		Response<ApplyScan> resp = new Response<ApplyScan>(Response.SUCCESS, "在线增员-查找计划申请预览信息成功");
		ApplyScan apply = applyMapper.getScanApply(personnelId);
		resp.setData(apply);
		return resp;
	
	}

	@Override
	public Response<Apply> getApply(String personnelId) {
		Response<Apply> resp = new Response<Apply>(Response.SUCCESS, "在线增员-查找计划申请数据库信息成功");
		Apply apply = applyMapper.getApply(personnelId);
		resp.setData(apply);
		return resp;
	}

	@Override
	public Response<String> deleteByPersonelId(Long personnelId) {
		Response<String> resp = new Response<String>(Response.SUCCESS, "删除指定个人计划申请信息成功");
		applyMapper.deleteByPersonnelId(personnelId);
		return resp;
	}

}
