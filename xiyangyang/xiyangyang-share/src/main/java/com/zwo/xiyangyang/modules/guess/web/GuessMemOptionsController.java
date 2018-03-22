package com.zwo.xiyangyang.modules.guess.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.core.web.BaseController;
import com.zwo.xiyangyang.modules.guess.domain.GuessMemOptions;
import com.zwo.xiyangyang.modules.guess.service.IGuessMemOptionsService;

@Controller
@Lazy(value=true)
@RequestMapping("gmoptions")
public class GuessMemOptionsController extends BaseController<GuessMemOptions> {

	@Autowired
	private IGuessMemOptionsService optionsService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected IBaseService<GuessMemOptions> getBaseService() {
		return (IBaseService)optionsService;
	}

}
