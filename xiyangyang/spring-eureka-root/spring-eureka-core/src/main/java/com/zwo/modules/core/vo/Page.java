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
	private int current = 1;
	/**
	 * 总页数
	 *  
	 */
	private int total = 0;
	/**
	 * 当前页
	 *  
	 */
	private int size = 10;
	/**
	 * 起始条
	 *  
	 */
	private int start = 0;

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
}
