package com.zwo.modules.core.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Description: 分页参数
 * @author xieyc
 * @date 2018年9月29日 上午11:55:32 
 *
 */
public class PageParams<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	//当前页
	private int curPage = 1;
	//一页记录数
	private int pageSize = 10;
	//结果集
	private List<T> result;
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}


}
