/**
 * 一句话描述该类：Controller基础类<br/>
 * @author 黄记新
 * @date 2015-1-4,下午3:23:34
 *
 */
package com.zwotech.common.web;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import com.github.pagehelper.DatagridPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

@Controller
public class BaseController<T extends Serializable> {
	public static final String SUCCESS = "操作成功";
	public static final String FAIL = "操作失败";

	public String root = "WEB-INF/";

	public String getTimestamp() {
		return "?timeStamp=" + new Date().getTime();
	}

	public void select(PageInfo<T> pageInfo, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		String p = httpServletRequest.getParameter("page");
		String rows = httpServletRequest.getParameter("rows");
		// 当前页 
		int intPage = Integer.parseInt((p == null || p == "0") ? "1" : p);
		// 每页显示条数 
		int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);

		pageInfo.setPageNum(intPage);
		pageInfo.setPageSize(number);
	}

	public DatagridPage<T> setPage(PageInfo<T> pageInfo) {
		DatagridPage<T> page = new DatagridPage<T>();
		page.setTotal(pageInfo.getTotal());
		page.setRows(pageInfo.getList());
		page.setRowCount(pageInfo.getSize());
		if(page.getRowCount()!=0){
			int i = (int) (page.getTotal()%page.getRowCount());
			int j = (int) (page.getTotal()/page.getRowCount());
			if(i !=0){
				j+=1;
			}
			page.setPages(j);
		}
		
		return page;
	}

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
		String enc = httpServletRequest.getCharacterEncoding();
		if (enc == null) {
			enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
		}
		try {
			pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
		} catch (UnsupportedEncodingException uee) {
		}
		return pathSegment;
	}

	void populateEditForm(Model uiModel, String name, Object obj) {
		uiModel.addAttribute(name, obj);
	}
	/*
	 * public Page select(Page page, Model uiModel, HttpServletRequest
	 * httpServletRequest, HttpServletResponse httpServletResponse) { // 当前页
	 * String startStr = httpServletRequest.getParameter("start"); // 当前页 String
	 * pageStr = httpServletRequest.getParameter("page"); // 当前页 String current
	 * = httpServletRequest.getParameter("current"); Integer intPage = null; if
	 * (null != pageStr) { intPage = Integer.parseInt(pageStr); } else if (null
	 * != startStr) { intPage = Integer.parseInt(startStr); } else if (null !=
	 * current) { intPage = Integer.parseInt(current); } else { intPage = 0; }
	 * 
	 * // 每页显示条数 String rowCount = httpServletRequest.getParameter("rowCount");
	 * // 每页显示条数 String limit = httpServletRequest.getParameter("limit"); //
	 * 每页显示条数 String rows = httpServletRequest.getParameter("rows"); Integer
	 * number = null; if (null != rows) { number = Integer.parseInt(rows); }
	 * else if (null != limit) { number = Integer.parseInt(limit); } else if
	 * (null != rowCount) { number = Integer.parseInt(rowCount); } else { number
	 * = 10; }
	 * 
	 * int start = 0; // 每页的开始记录 第一页为1 第二页为number +1 if (page != null && rows !=
	 * null) { start = (intPage - 1) * number; } else if (rowCount != null &&
	 * current != null) { start = (intPage - 1) * number; } else if (startStr !=
	 * null) { start = Integer.parseInt(startStr); }
	 * 
	 * page.setStart(start); page.setPageSize(number);
	 * 
	 * return page; }
	 */

	public String create(@Valid T record, BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest) {
		@SuppressWarnings("rawtypes")
		Class clazz = record.getClass();
		try {
			@SuppressWarnings("unchecked")
			Method method = clazz.getDeclaredMethod("setId", String.class);
			method.invoke(record, new Date().getTime() + "");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<T> selectAll(HttpServletRequest httpServletRequest) {
		return null;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
