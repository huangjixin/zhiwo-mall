/**
 * 
 */
package com.zwo.modules.core.vo;

import java.io.Serializable;

/**
 * @author 黄记新
 *
 */
public class Page implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 当前页
	 *  
	 */
	private Integer current;
	/**
	 * 总页数
	 *  
	 */
	private Integer total;
	/**
	 * 当前页
	 *  
	 */
	private Integer size;
	/**
	 * 起始条
	 *  
	 */
	private Integer start;

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}
}
