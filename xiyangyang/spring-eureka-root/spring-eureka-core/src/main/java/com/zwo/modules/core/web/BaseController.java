package com.zwo.modules.core.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zwo.modules.core.service.IBaseService;
import com.zwo.modules.core.vo.Response;

import io.swagger.annotations.ApiOperation;

/**
 * 控制基类。
 * @author 黄记新
 *
 * @param <T>
 */
public abstract class BaseController<T> {
	
	protected abstract IBaseService<T> getBaseService();

	@ApiOperation(value="基础保存对象", notes="")
	@PostMapping(value = "save")
	@ResponseBody
	protected Response save(T record, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Response response = new Response();
		int result = getBaseService().insertSelective(record);
		response.setCode(result+"");
		return response;
	}

	@PostMapping(value = "update")
	@ResponseBody
	protected int update(T record, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		int result = getBaseService().updateByPrimaryKeySelective(record);
		return result;
	}
	
	@GetMapping(value = "view/{id}")
	@ResponseBody
	protected T getById(@PathVariable(name = "id") String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		T record = getBaseService().selectByPrimaryKey(id);
		return record;
	}

	@DeleteMapping(value = "delete/{id}")
	@ResponseBody
	protected int delete(@PathVariable(name = "id") String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		return getBaseService().deleteById(id);
	}
	
	@DeleteMapping(value = "deleteBatch")
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
