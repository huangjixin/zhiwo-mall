package com.zwo.xiyangyang.modules.guess.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.core.web.BaseController;
import com.zwo.xiyangyang.modules.guess.domain.GuessOptions;
import com.zwo.xiyangyang.modules.guess.service.IOptionsService;

@Controller
@Lazy(value=true)
@RequestMapping("goptions")
public class GuessOptionsController extends BaseController<GuessOptions> {

	@Autowired
	private IOptionsService optionsService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected IBaseService<GuessOptions> getBaseService() {
		return (IBaseService)optionsService;
	}

}
