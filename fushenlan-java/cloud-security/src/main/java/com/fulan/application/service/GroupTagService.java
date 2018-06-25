package com.fulan.application.service;

import java.util.List;
import java.util.Map;

import com.fulan.api.security.domain.TagTarget;
import com.fulan.application.util.domain.Response;

public interface GroupTagService {

	Response<String> addGroupTag(TagTarget tagtarget);
	
	/**
	 * g根据宿主Id和类型查找
	 * @param hostId
	 * @param type
	 * @return
	 */
	List<TagTarget> listByHostIdAndType(Long hostId, Integer type);
	TagTarget findByTagId(Long tagId);
	
	/**
	 * 根据条件删除
	 * @param map
	 * @return
	 */
	int deleteByHostId(Long hostId);
}
