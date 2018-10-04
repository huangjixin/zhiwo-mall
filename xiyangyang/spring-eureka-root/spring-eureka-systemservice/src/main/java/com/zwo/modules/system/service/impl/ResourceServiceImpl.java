/**
 * 
 */
package com.zwo.modules.system.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwo.modules.core.mapper.BaseMapper;
import com.zwo.modules.core.service.impl.BaseServiceImpl;
import com.zwo.modules.system.domain.Resource;
import com.zwo.modules.system.mapper.ResourceMapper;
import com.zwo.modules.system.service.IResourceService;

/**
 * @author 黄记新
 *
 */
@Transactional
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements IResourceService {

	@Autowired
	private ResourceMapper resourceMapper;
	
	private static Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
	
	private static final String BASE_MESSAGE = "用户角色类ResourceServiceImpl增删改查";
	
	@Override
	protected BaseMapper<Resource> getBaseMapper() {
		return this.resourceMapper;
	}

	@Override
	protected String getBaseMessage() {
		return BASE_MESSAGE;
	}

	@Override
	public Class getTypeClass() {
		return Resource.class;
	}

	@Override
	public Logger getLogger() {
		return logger;
	}


}
