/**
 * 
 */
package com.zwo.xiyangyang.modules.guess.service;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestion;

/**
 * @author FWDuser
 *
 */
public interface IQuestionService extends IBaseService<GuessQuestion> {
	GuessQuestion selectByName(String name);
	
}
