/**
 * 
 */
package com.zwo.modules.member.service;

import java.util.List;

import com.zwo.modules.member.domain.GuessQuestionOptions;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface IGuessQuestionOptionsService extends IBaseService<GuessQuestionOptions> {
	/**
	 * 根据问题查询选项。
	 * @param questionId
	 * @return
	 */
	List<GuessQuestionOptions> selectByQuestionId(String questionId);
}
