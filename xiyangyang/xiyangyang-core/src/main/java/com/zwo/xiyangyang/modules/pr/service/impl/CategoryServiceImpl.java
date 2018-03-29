/**
 * 
 */
package com.zwo.xiyangyang.modules.pr.service.impl;

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
import com.zwo.xiyangyang.modules.pr.dao.PrCategoryMapper;
import com.zwo.xiyangyang.modules.pr.domain.PrCategory;
import com.zwo.xiyangyang.modules.pr.service.ICategoryService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class CategoryServiceImpl extends BaseServiceImpl<PrCategory> implements ICategoryService {
	private static Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Autowired
	private PrCategoryMapper categoryMapper ;

	public CategoryServiceImpl() {
		logger = LoggerFactory.getLogger(this.getImplClass());
	}
	
	@Override
	public Mapper<PrCategory> getBaseMapper() {
		return categoryMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return CategoryServiceImpl.class;
	}

	@Override
	protected String getBaseMessage() {
		return "商品分类基础操作";
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return PrCategory.class;
	}

	@Transactional(readOnly = true)
	@Override
	public List<PrCategory> getCategoryTree(PrCategory category) {
		if (logger.isInfoEnabled()) {
			String jsonStr = null;
			if(category!=null) {
				Gson gson = new Gson();
				jsonStr = gson.toJson(category);
			}else {
				jsonStr = "null";
			}
			
			logger.info(getBaseMessage() + "查询树开始，参数对象的值是：" + jsonStr);
		}
		
		Example example = new Example(PrCategory.class);
		
		if(category != null) {
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("parentId", category.getParentId());
//			example.setOrderByClause(orderByClause);
		}
        
		List<PrCategory> list = categoryMapper.selectByExample(example);
		if(list.size()==0) {
			return null;
		}
		TreeBuilder<PrCategory> tb = new TreeBuilder<PrCategory>();
		list = tb.buildListToTree(list, false);
		return list;
	}

}
