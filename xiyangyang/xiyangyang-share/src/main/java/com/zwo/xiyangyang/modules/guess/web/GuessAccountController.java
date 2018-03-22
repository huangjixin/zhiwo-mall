package com.zwo.xiyangyang.modules.guess.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.core.web.BaseController;
import com.zwo.xiyangyang.modules.guess.domain.GuessAccount;
import com.zwo.xiyangyang.modules.guess.service.IGuessAccountService;

@Controller
@Lazy(value=true)
@RequestMapping("gaccount")
public class GuessAccountController extends BaseController<GuessAccount> {

	@Autowired
	private IGuessAccountService guessAccountService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected IBaseService<GuessAccount> getBaseService() {
		return (IBaseService)guessAccountService;
	}

}
