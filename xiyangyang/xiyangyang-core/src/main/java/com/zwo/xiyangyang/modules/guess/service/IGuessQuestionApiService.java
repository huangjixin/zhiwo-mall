/**
 * 
 */
package com.zwo.xiyangyang.modules.guess.service;

import java.util.List;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestionApi;

/**
 * @author 黄记新
 *
 */
public interface IGuessQuestionApiService extends IBaseService<GuessQuestionApi> {
	/**
	 * 根据类型去查询
	 * @param type
	 * @return
	 */
	List<GuessQuestionApi> selectByType(String type);
}
