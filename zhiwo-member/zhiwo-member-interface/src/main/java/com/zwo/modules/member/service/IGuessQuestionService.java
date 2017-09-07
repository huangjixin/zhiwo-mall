/**
 * 
 */
package com.zwo.modules.member.service;

import java.util.List;

import com.zwo.modules.member.domain.GuessQuestion;
import com.zwo.modules.member.domain.GuessQuestionOption;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface IGuessQuestionService extends IBaseService<GuessQuestion> {
	/**
	 * 查询在用的竞猜问题。
	 * @return
	 */
	List<GuessQuestionOption> selectIneffectQuestion();
}
