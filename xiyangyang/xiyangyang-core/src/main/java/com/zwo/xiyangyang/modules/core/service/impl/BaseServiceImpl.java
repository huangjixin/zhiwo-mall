/**
 * 
 */
package com.zwo.xiyangyang.modules.core.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.zwo.xiyangyang.modules.core.service.IBaseService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

/**
 * 基础类实现增删改查。
 * 
 * @author 黄记新
 *
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

//	private static Logger logger;

	protected abstract Mapper<T> getBaseMapper();

	protected abstract String getBaseMessage();

	@SuppressWarnings("rawtypes")
	public abstract Class getImplClass();

	@SuppressWarnings("rawtypes")
	public abstract Class getTypeClass();
	
	public abstract Logger getLogger();
	
	public BaseServiceImpl() {
//		logger = LoggerFactory.getLogger(this.getImplClass());
	}

	@Override
	public int deteleBatch(List<T> list) {
		Example example = new Example(getTypeClass());
		Example.Criteria criteria = example.createCriteria();
		criteria.andIn("id", list);
		return getBaseMapper().deleteByExample(example);
	}
	
	@Override
	public int insertBatch(List<T> list) {
		return 0;
	}
	
	@Override
	@Transactional(readOnly = true)
	public int countByExample(Object example) {
		return getBaseMapper().selectCountByExample(example);
	}

	@Override
	public int deleteByExample(Object example) {
		if (getLogger().isInfoEnabled()) {
			getLogger().info(getBaseMessage() + "删除开始");
		}

		int result = getBaseMapper().deleteByExample(example);
		if (getLogger().isInfoEnabled())
			getLogger().info(getBaseMessage() + "删除" + (result != 0 ? "成功" : "失败"));

		return result;
	}

	@Override
	public int deleteById(String id) {
		if (getLogger().isInfoEnabled()) {
			getLogger().info(getBaseMessage() + "删除开始，参数id的值是：" + id);
		}

		int result = getBaseMapper().deleteByPrimaryKey(id);
		if (getLogger().isInfoEnabled())
			getLogger().info(getBaseMessage() + "插入" + (result == 1 ? "成功" : "失败"));

		return result;
	}

	@Override
	public int insert(T record) {
		// 利用类反射判断id属性有没有值，没有值就给赋值。
		Object o = (Object) record;
		Method method;
		try {
			method = o.getClass().getDeclaredMethod("getId");
			String id = (String) method.invoke(o);
			if (id == null) {
				method = o.getClass().getDeclaredMethod("setId", String.class);
				id = UUID.randomUUID().toString().replaceAll("-", "");
				method.invoke(o, id);
			}
			
			Method[]methods = o.getClass().getMethods();
			for (Method method2 : methods) {
				if(method2.getName().equals("getParentId")) {
					String parentId = (String) method.invoke(o);
					if("".equals(parentId)) {
						method2 = o.getClass().getDeclaredMethod("setParentId", String.class);
						method2.invoke(o, null);
					}
					break;
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (getLogger().isInfoEnabled()) {
			Gson gson = new Gson();
			String jsonStr = gson.toJson((Object) record);
			getLogger().info(getBaseMessage() + "插入开始，参数对象的值是：" + jsonStr);
		}

		int result = getBaseMapper().insert(record);
		if (getLogger().isInfoEnabled())
			getLogger().info(getBaseMessage() + "插入" + (result == 1 ? "成功" : "失败"));
		return result;
	}

	@Override
	public int insertSelective(T record) {
		// 利用类反射判断id属性有没有值，没有值就给赋值。
		Object o = (Object) record;
		Method method;
		try {
			method = o.getClass().getDeclaredMethod("getId");
			String id = (String) method.invoke(o);
			if (id == null) {
				method = o.getClass().getDeclaredMethod("setId", String.class);
				id = UUID.randomUUID().toString().replaceAll("-", "");
				method.invoke(o, id);
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		if (getLogger().isInfoEnabled()) {
			Gson gson = new Gson();
			String jsonStr = gson.toJson((Object) record);
			getLogger().info(getBaseMessage() + "插入开始，参数对象的值是：" + jsonStr);
		}

		int result = getBaseMapper().insertSelective(record);
		if (getLogger().isInfoEnabled())
			getLogger().info(getBaseMessage() + "插入" + (result == 1 ? "成功" : "失败"));
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public T selectByPrimaryKey(String id) {
		if (getLogger().isInfoEnabled()) {
			getLogger().info(getBaseMessage() + "查询单条记录开始，参数id的值是：" + id);
		}

		T result = getBaseMapper().selectByPrimaryKey(id);
		if (getLogger().isInfoEnabled()) {
			String jsonStr = null;
			if (result != null) {
				Gson gson = new Gson();
				jsonStr = gson.toJson((Object) result);
			} else {
				jsonStr = "查询不到";
			}

			getLogger().info(getBaseMessage() + "查询单条记录结果：" + jsonStr);
		}

		return result;
	}

	@Override
	public int updateByExampleSelective(T record, Object example) {
		if (getLogger().isInfoEnabled()) {
			Gson gson = new Gson();
			String jsonStr = gson.toJson((Object) record);
			getLogger().info(getBaseMessage() + "更新记录开始，参数对象是：" + jsonStr);
		}
		int result = getBaseMapper().updateByExampleSelective(record, example);
		if (getLogger().isInfoEnabled()) {
			getLogger().info(getBaseMessage() + "更新记录结束");
		}

		return result;
	}

	@Override
	public int updateByExample(T record, Object example) {
		return getBaseMapper().updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		if (getLogger().isInfoEnabled()) {
			Gson gson = new Gson();
			String jsonStr = gson.toJson((Object) record);
			getLogger().info(getBaseMessage() + "更新开始，参数对象的值是：" + jsonStr);
		}

		int result = getBaseMapper().updateByPrimaryKeySelective(record);
		if (getLogger().isInfoEnabled())
			getLogger().info(getBaseMessage() + "更新" + (result == 1 ? "成功" : "失败"));
		return result;
	}

	@Override
	public int updateById(T record) {
		if (getLogger().isInfoEnabled()) {
			Gson gson = new Gson();
			String jsonStr = gson.toJson((Object) record);
			getLogger().info(getBaseMessage() + "更新开始，参数对象的值是：" + jsonStr);
		}

		int result = getBaseMapper().updateByPrimaryKey(record);
		if (getLogger().isInfoEnabled())
			getLogger().info(getBaseMessage() + "更新" + (result == 1 ? "成功" : "失败"));
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly = true)
	public List<T> selectByExample(Object example, PageInfo pageInfo) {
		if (pageInfo != null && pageInfo.getPageSize() != 0) {
			PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		}
		return getBaseMapper().selectByExample(example);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> selectByExample(Object example) {
		return getBaseMapper().selectByExample(example);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional(readOnly = true)
	public PageInfo selectByPageInfo(Object example, PageInfo pageInfo) {
		if (pageInfo != null && pageInfo.getPageSize() != 0) {
			PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
		}
		List list = getBaseMapper().selectByExample(example);
		pageInfo.setList(list);
		return pageInfo;
	}

}
