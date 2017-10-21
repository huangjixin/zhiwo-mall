/**
 * 一句话描述该类：<br/>
 * @author 黄记新
 * @date 2016-8-4,下午3:39:21
 *
 */
package com.zwotech.common.service.impl;

import java.io.Serializable;
import java.util.List;

import com.zwotech.common.service.IBaseService;

public class BaseServiceImpl<T extends Serializable, PK extends Serializable> implements IBaseService<T, PK> {

	public PK deleteBatch(List<PK> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public PK deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public PK insert(T record) {
		// TODO Auto-generated method stub
		return null;
	}

	public PK insertSelective(T record) {
		// TODO Auto-generated method stub
		return null;
	}


	public T selectByPrimaryKey(PK id) {
		// TODO Auto-generated method stub
		return null;
	}

	public PK updateByPrimaryKeySelective(T record) {
		// TODO Auto-generated method stub
		return null;
	}

	public PK updateByPrimaryKey(T record) {
		// TODO Auto-generated method stub
		return null;
	}

}
