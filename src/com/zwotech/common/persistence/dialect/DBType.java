/**
 * 一句话描述该类：数据库类型<br/>
 * @author 黄记新
 * @date 2016-7-29,下午3:16:35
 *
 */
package com.zwotech.common.persistence.dialect;

public class DBType {
	private String jdbcType = "mysql";

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}
}
