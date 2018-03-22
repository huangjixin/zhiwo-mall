package com.zwo.xiyangyang.modules.guess.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.core.web.BaseController;
import com.zwo.xiyangyang.modules.guess.domain.GuessAccountHis;
import com.zwo.xiyangyang.modules.guess.service.IGuessAccountHisService;

@Controller
@Lazy(value=true)
@RequestMapping("gaccountHis")
public class GuessAccountHisController extends BaseController<GuessAccountHis> {

	@Autowired
	private IGuessAccountHisService accountHisService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected IBaseService<GuessAccountHis> getBaseService() {
		return (IBaseService)accountHisService;
	}

}
