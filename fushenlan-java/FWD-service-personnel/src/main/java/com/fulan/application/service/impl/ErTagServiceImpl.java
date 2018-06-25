package com.fulan.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fulan.api.personnel.domain.ErTag;
import com.fulan.application.mapper.TagMapper;
import com.fulan.application.service.ErTagService;
import com.fulan.application.util.domain.Response;

@Service
@Transactional
public class ErTagServiceImpl implements ErTagService {

	@Autowired
	TagMapper tagMapper;
	
	@Override
	public Response<String> addErTag(ErTag ertag) {
		// TODO Auto-generated method stub
		Response<String> resp=new Response<String>(Response.SUCCESS, "添加标签成功");
		tagMapper.insert(ertag);
		resp.setData("personnelId:"+String.valueOf(ertag.getPersonnelId()));
		return resp;
	}

	@Override
	public Response<String> removeErTag(Long personnelId, Long tagId) {
		// TODO Auto-generated method stub
		Response<String> resp=null;
		int flag=tagMapper.removeErTag(personnelId,tagId);
		if(flag>0){
			resp=new Response<String>(Response.SUCCESS, "删除标签成功");
		}else{
			resp=new Response<String>(Response.SUCCESS, "此人没有此标签");
		}
		resp.setData("personnelId:"+personnelId);
		return resp;
	}

	public Response<String> removeErTagbypersonnelId(Long personnelId) {
		
		Response<String> resp=null;
		int flag=tagMapper.removeErTagbypersonnelId(personnelId);
		if(flag>0){
			resp=new Response<String>(Response.SUCCESS, "删除标签成功");
		}else{
			resp=new Response<String>(Response.SUCCESS, "此人没有此标签");
		}
		resp.setData("personnelId:"+personnelId);
		return resp;
	}
	
	
	
	public List<ErTag> findBypersonnelId(Integer personnelId){
		
		return tagMapper.findBypersonnelId(personnelId);
	}
	
	
}
