package com.zwo.modules.core.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zwo.modules.core.service.IBaseService;

/**
 * 控制基类。
 * @author 黄记新
 *
 * @param <T>
 */
public abstract class BaseController<T> {
	
	protected abstract IBaseService<T> getBaseService();


	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	protected int save(T record, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		//利用类反射判断id属性有没有值，没有值就给赋值。
//		Object o = (Object)record;
//		Method method;
//		try {
//			method = o.getClass().getDeclaredMethod("getId");
//			String id = (String) method.invoke(o);
//			if(id == null) {
//				method = o.getClass().getDeclaredMethod("setId",String.class);
//				id = UUID.randomUUID().toString().replaceAll("-", "");
//				method.invoke(o,id);
//			}
//		} catch (NoSuchMethodException e) {
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}
	      
		int result = getBaseService().insertSelective(record);
		return result;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	protected int update(T record, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		int result = getBaseService().updateByPrimaryKeySelective(record);
		return result;
	}
	
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	@ResponseBody
	protected T getById(@PathVariable(name = "id") String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		T record = getBaseService().selectByPrimaryKey(id);
		return record;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	protected int delete(@PathVariable(name = "id") String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		return getBaseService().deleteById(id);
	}
	
	@RequestMapping(value = "deleteBatch", method = RequestMethod.DELETE)
	@ResponseBody
	protected int deleteBatch(@RequestParam("ids") List<String> ids, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		int result =  getBaseService().deteleBatch(ids);
		return result;
	}
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
