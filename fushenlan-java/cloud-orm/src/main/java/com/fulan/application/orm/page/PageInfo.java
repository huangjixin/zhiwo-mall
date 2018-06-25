package com.fulan.application.orm.page;

import com.baomidou.mybatisplus.plugins.Page;

public class PageInfo extends Page{
	
	public void setPageNo(int pageNo) {
		setCurrent(pageNo);
	}
	
	public void setPageSize(int pageSize) {
		setSize(pageSize);
	}
	
	public void setPageSortFiled(String pageSortFiled) {
		setOrderByField(pageSortFiled);
	}
	
	public void setPageSortType(String pageSortType) {
		setAsc((null == pageSortType || "desc".equals(pageSortType)) ? false : true);
	}
}
