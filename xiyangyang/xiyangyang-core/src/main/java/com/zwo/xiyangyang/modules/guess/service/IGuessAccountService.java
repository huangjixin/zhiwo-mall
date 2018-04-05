/**
 * 
 */
package com.zwo.xiyangyang.modules.guess.service;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.guess.domain.GuessAccount;

/**
 * 竞猜选项接口类。
 * @author 黄记新
 *
 */
public interface IGuessAccountService extends IBaseService<GuessAccount> {
	/**
	 * 根据会员查询竞猜账号。
	 * @param mid
	 * @return
	 */
	GuessAccount selectByMid(String mid);
}
