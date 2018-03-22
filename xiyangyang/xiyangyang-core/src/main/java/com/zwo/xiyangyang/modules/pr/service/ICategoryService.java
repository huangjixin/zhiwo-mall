/**
 * 
 */
package com.zwo.xiyangyang.modules.pr.service;

import java.util.List;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.pr.domain.PrCategory;

/**
 * 商品分类接口
 * @author 黄记新
 *
 */
public interface ICategoryService extends IBaseService<PrCategory> {
	/**
	 * 获取分类树。
	 * @param category
	 * @return
	 */
	List<PrCategory> getCategoryTree(PrCategory category);
}
