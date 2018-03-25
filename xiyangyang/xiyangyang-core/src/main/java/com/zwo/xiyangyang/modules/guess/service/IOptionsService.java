/**
 * 
 */
package com.zwo.xiyangyang.modules.guess.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.guess.domain.GuessOptions;

/**
 * 竞猜选项接口类。
 * @author 黄记新
 *
 */
public interface IOptionsService extends IBaseService<GuessOptions> {
	int checkOption(GuessOptions guessOptions);
	
	/**
	 * 根据问题ID查询选项。
	 * @param questionId
	 * @return
	 */
	List<GuessOptions> selectByQuestionId(String questionId);
}
