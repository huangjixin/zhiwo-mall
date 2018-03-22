/**
 * 
 */
package com.zwo.xiyangyang.modules.guess.service;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.guess.domain.GuessOptions;

/**
 * 竞猜选项接口类。
 * @author 黄记新
 *
 */
public interface IOptionsService extends IBaseService<GuessOptions> {
	int checkOption(GuessOptions guessOptions);
}
