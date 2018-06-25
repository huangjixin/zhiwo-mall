package com.fulan.application.orm.page;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.fulan.application.util.page.PageInfo;

/**
 * 分页辅助类
 * @author scotthu
 *
 */
public class PageUtil {
	
	
	/**
	 * 框架分页转换到mybatis分页
	 * @param pageInfo
	 * @return
	 */
	public static Page vice(PageInfo pageInfo) {
		Page page = new Page();
		page.setCurrent(pageInfo.getPageNo()==null ? 1:pageInfo.getPageNo());
		page.setSize(pageInfo.getPageSize()==null ? 10:pageInfo.getPageSize());
		if(pageInfo.getPageTotal() != null)
		   page.setTotal(pageInfo.getPageTotal());
		if(StringUtils.isNotEmpty(pageInfo.getPageSortFiled()))
		   page.setOrderByField(pageInfo.getPageSortFiled());
        page.setAsc((null == pageInfo.getPageSortType() || "desc".equals(pageInfo.getPageSortType())) ? false : true);
        
        return page;
	}

	/**
	 * mybatis分页转换到框架分页
	 * @param page
	 * @return
	 */
	public static PageInfo versa(Page page) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(page.getPages());
		pageInfo.setPageRecords(page.getTotal());
		pageInfo.setRecords(page.getRecords());
		
		return pageInfo;
	}
}
