package com.zwo.xiyangyang.modules.sys.service;

import java.util.List;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.sys.domain.Resources;

public interface IResourcesService extends IBaseService<Resources>{

	/**
	 * 获取分类树。
	 * @param category
	 * @return
	 */
	List<Resources> getCategoryTree(Resources resources);
}
