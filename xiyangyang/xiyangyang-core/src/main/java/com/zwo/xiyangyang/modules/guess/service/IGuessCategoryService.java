/**
 * 
 */
package com.zwo.xiyangyang.modules.guess.service;

import java.util.List;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.guess.domain.GuessCategory;

/**
 * 竞猜选项接口类。
 * @author 黄记新
 *
 */
public interface IGuessCategoryService extends IBaseService<GuessCategory> {
	/**
	 * 根据code进行类别查询。
	 * @param code
	 * @return
	 */
	GuessCategory selectByCode(String code);
	
	/**
	 * 获取分类树。
	 * @param category
	 * @return
	 */
	List<GuessCategory> getCategoryTree(GuessCategory category);
}
