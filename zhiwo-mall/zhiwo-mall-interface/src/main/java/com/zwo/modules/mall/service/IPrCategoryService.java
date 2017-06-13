/**
 * 
 */
package com.zwo.modules.mall.service;

import java.util.List;

import com.zwo.modules.mall.domain.CategoryTree;
import com.zwo.modules.mall.domain.PrCategory;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface IPrCategoryService extends IBaseService<PrCategory> {
	/**
	 * 根据parentId查询树结构列表数组。
	 * @param parentId
	 * @return
	 */
	List<CategoryTree> getTreeCategory(String parentId);
}
