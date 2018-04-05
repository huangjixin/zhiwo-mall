/**
 * 
 */
package com.zwo.xiyangyang.modules.guess.service;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.guess.domain.GuessAccount;
import com.zwo.xiyangyang.modules.guess.domain.GuessMemOptions;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestion;

/**
 * 竞猜选项接口类。
 * @author 黄记新
 *
 */
public interface IGuessMemOptionsService extends IBaseService<GuessMemOptions> {
	/**
	 * 新增竞猜赌注。
	 * @param guessMemOptions
	 * @param question
	 * @return
	 */
	int add(GuessMemOptions guessMemOptions, GuessQuestion question,GuessAccount account);
}
