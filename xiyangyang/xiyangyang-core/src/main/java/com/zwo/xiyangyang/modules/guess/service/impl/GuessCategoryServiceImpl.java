/**
 * 
 */
package com.zwo.xiyangyang.modules.guess.service.impl;

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
import com.zwo.xiyangyang.modules.guess.dao.GuessCategoryMapper;
import com.zwo.xiyangyang.modules.guess.domain.GuessAccount;
import com.zwo.xiyangyang.modules.guess.domain.GuessCategory;
import com.zwo.xiyangyang.modules.guess.service.IGuessCategoryService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class GuessCategoryServiceImpl extends BaseServiceImpl<GuessCategory> implements IGuessCategoryService {
	private static Logger logger = LoggerFactory.getLogger(GuessCategoryServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	
	@Autowired
	private GuessCategoryMapper guessCategoryMapper ;

	
	@Override
	public Mapper<GuessCategory> getBaseMapper() {
		return guessCategoryMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return GuessCategory.class;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return GuessAccount.class;
	}
	
	@Override
	protected String getBaseMessage() {
		return "竞猜分类基础操作";
	}

	@Override
	public GuessCategory selectByCode(String code) {
		Example example = new Example(GuessCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("code", code);
        List<GuessCategory> list =  guessCategoryMapper.selectByExample(example);
		return list == null ||list.size()==0?null:list.get(0);
	}

	@Transactional(readOnly = true)
	@Override
	public List<GuessCategory> getCategoryTree(GuessCategory category) {
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
		
		Example example = new Example(GuessCategory.class);
		
		if(category != null) {
			Example.Criteria criteria = example.createCriteria();
			criteria.andEqualTo("parentId", category.getParentId());
//			example.setOrderByClause(orderByClause);
		}
        
		List<GuessCategory> list = guessCategoryMapper.selectByExample(example);
		if(list.size()==0) {
			return null;
		}
		TreeBuilder<GuessCategory> tb = new TreeBuilder<GuessCategory>();
		list = tb.buildListToTree(list, false);
		return list;
	}

}
