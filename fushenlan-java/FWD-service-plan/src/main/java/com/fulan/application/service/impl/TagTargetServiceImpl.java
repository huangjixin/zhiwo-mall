package com.fulan.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fulan.api.plan.domain.TagTarget;
import com.fulan.application.mapper.TagTargetMapper;
import com.fulan.application.service.TagTargetService;
import com.fulan.application.util.domain.Response;

@Service
@Transactional
public class TagTargetServiceImpl implements TagTargetService {

	@Autowired
	TagTargetMapper tagTargetMapper;
	@Override
	public Response<String> addTagTarget(TagTarget tagtarget) {
		// TODO Auto-generated method stub
		Response<String> resp=new Response<String>(Response.SUCCESS, "添加标签成功");
		tagTargetMapper.insert(tagtarget);
		resp.setData("personnelId:"+String.valueOf(tagtarget.getHostId()));
		return resp;
	}
	@Override
	public List<TagTarget> findAllPublicClassTag(Integer type) {
		// TODO Auto-generated method stub
		return tagTargetMapper.findAllPublicClassTag(type);
	}

}
