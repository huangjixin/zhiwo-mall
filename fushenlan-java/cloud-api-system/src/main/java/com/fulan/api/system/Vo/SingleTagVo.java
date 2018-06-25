package com.fulan.api.system.Vo;

import com.fulan.api.system.domain.Tag;

/**
 * <p>
 * 标签包装类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
public class SingleTagVo extends Tag{
	private String parentTagName;
	
	public String getParentTagName() {
		return parentTagName;
	}

	public void setParentTagName(String parentTagName) {
		this.parentTagName = parentTagName;
	}
}
