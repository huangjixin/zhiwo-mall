/**
 * 
 */
package com.zwo.xiyangyang.modules.sys.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.core.utils.TreeBuilder;
import com.zwo.xiyangyang.modules.sys.dao.ResourcesMapper;
import com.zwo.xiyangyang.modules.sys.domain.Resources;
import com.zwo.xiyangyang.modules.sys.service.IResourcesService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 黄记新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class ResourcesServiceImpl extends BaseServiceImpl<Resources> implements IResourcesService {
	private static Logger logger = LoggerFactory.getLogger(ResourcesServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Autowired
	private ResourcesMapper resourcesMapper;
	
	@Override
	public Mapper<Resources> getBaseMapper() {
		return resourcesMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return ResourcesServiceImpl.class;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return Resources.class;
	}

	@Override
	protected String getBaseMessage() {
		return "系统资源基础操作";
	}

	@Override
	public List<Resources> getCategoryTree(Resources resources) {
		if (logger.isInfoEnabled()) {
			String jsonStr = null;
			if(resources!=null) {
				Gson gson = new Gson();
				jsonStr = gson.toJson(resources);
			}else {
				jsonStr = "null";
			}
			
			logger.info(getBaseMessage() + "查询树开始，参数对象的值是：" + jsonStr);
		}
		
		Example example = new Example(Resources.class);
		
		if(resources != null) {
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("parentId", resources.getParentId());
//			example.setOrderByClause(orderByClause);
		}
        
		List<Resources> list = resourcesMapper.selectByExample(example);
		if(list.size()==0) {
			return null;
		}
		TreeBuilder<Resources> tb = new TreeBuilder<Resources>();
		list = tb.buildListToTree(list, false);
		return list;
	}

}
