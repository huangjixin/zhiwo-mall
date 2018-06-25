package com.fulan.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fulan.api.system.domain.Version;
import com.fulan.application.mapper.VersionMapper;
import com.fulan.application.service.VersionService;
import com.fulan.application.util.domain.Response;

@Service
public class VersionServiceImpl implements VersionService {

	@Autowired
	VersionMapper versionMapper;
	
	@Override
	public Response<Boolean> insert(Version version) {
		// TODO Auto-generated method stub
		Response<Boolean> resp=new Response<Boolean>(Response.SUCCESS, "添加成功");
		Boolean flag=false;
		int ins=versionMapper.insert(version);
		if(ins>0){
			flag=true;
		}
		resp.setData(flag);
		return resp;
	}

	@Override
	public Boolean delete(Long id) {
		// TODO Auto-generated method stub
		Boolean flag=false;
		int ins=versionMapper.deleteById(id);
		if(ins>0){
			flag=true;
		}
		return flag;
	}

	@Override
	public Response<Boolean> updateById(Version version) {
		// TODO Auto-generated method stub
		Response<Boolean> resp=new Response<Boolean>(Response.SUCCESS, "修改成功");
		Boolean flag=false;
		int ins=versionMapper.updateById(version);
		if(ins>0){
			flag=true;
		}
		resp.setData(flag);
		return resp;
	}

	@Override
	public Response<Version> findById(Long id) {
		// TODO Auto-generated method stub
		Response<Version> resp=new Response<Version>(Response.SUCCESS, "查询成功");
		resp.setData(versionMapper.selectById(id));
		return resp;
	}

	@Override
	public Response<String> theNewstVersion(int type) {
		// TODO Auto-generated method stub
		Response<String> resp=new Response<String>(Response.SUCCESS, "查询成功");
		String version=versionMapper.findVersionByType(type);
		resp.setData(version);
		return resp;
	}

}
