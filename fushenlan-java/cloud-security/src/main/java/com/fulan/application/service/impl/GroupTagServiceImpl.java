package com.fulan.application.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fulan.api.security.domain.TagTarget;
import com.fulan.application.mapper.GroupTagMapper;
import com.fulan.application.service.GroupTagService;
import com.fulan.application.util.domain.Response;

@Service
@Transactional
public class GroupTagServiceImpl  implements GroupTagService {

	@Autowired
	GroupTagMapper groupTagMapper;
	
	@Override
	public Response<String> addGroupTag(TagTarget tagtarget) {
		// TODO Auto-generated method stub
		Response<String> resp=new Response<String>(Response.SUCCESS, "添加标签成功");
		groupTagMapper.insert(tagtarget);
		resp.setData("hostId:"+String.valueOf(tagtarget.getHostId()));
		return resp;
	}

    @Override
    public List<TagTarget> listByHostIdAndType(Long hostId, Integer type) {
        
        Map<String,Object> columnMap = new HashMap<String, Object>();
        columnMap.put("host_id", hostId);
        columnMap.put("type", type);
        return groupTagMapper.selectByMap(columnMap);
    }

	@Override
	public TagTarget findByTagId(Long tagId) {
		return groupTagMapper.findByTagId(tagId);
	}
	@Override
    public int deleteByHostId(Long hostId) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("host_id", hostId);
        int i = groupTagMapper.deleteByMap(map);
        return i;
    }

}
