/**
 * 
 */
package com.zwo.modules.member.service;

import java.util.List;

import com.zwo.modules.member.domain.GuessQuestion;
import com.zwo.modules.member.domain.GuessQuestionOption;
import com.zwo.modules.member.domain.GuessQuestionOptions;
import com.zwo.modules.member.domain.MemberPlayAccount;
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
	
	/**
	 * 根据问题查询选项。
	 * @param questionId
	 * @return
	 */
	List<GuessQuestionOptions> selectByQuestionId(String questionId);
	
	/**
	 * 竞猜下注
	 * @param betNum
	 * @param optionId
	 * @return
	 */
	int bet(MemberPlayAccount memberPlayAccount,String memId,int betNum,GuessQuestion guessQuestion,String optionId);
}
