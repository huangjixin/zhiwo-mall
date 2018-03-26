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
	/**
	 * 根据名称查询问题。
	 * @param name
	 * @return
	 */
	GuessQuestion selectByName(String name);

	/**
	 * 针对某个问题写答案，更新猜中会员账号和账号历史记录。
	 * 
	 * @param questionId
	 * @param optionId
	 */
	void checkQuestion(String questionId, String optionId);
}
