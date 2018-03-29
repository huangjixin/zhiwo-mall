package com.zwo.xiyangyang.app.web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zwo.xiyangyang.modules.guess.domain.GuessOptions;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestion;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestionCriteria;
import com.zwo.xiyangyang.modules.guess.service.IOptionsService;
import com.zwo.xiyangyang.modules.guess.service.IQuestionService;
import com.zwo.xiyangyang.modules.mem.domain.MemAddress;
import com.zwo.xiyangyang.modules.mem.domain.MemGuessRecord;
import com.zwo.xiyangyang.modules.mem.service.IMemAddressService;
import com.zwo.xiyangyang.modules.mem.service.IMememberService;

import tk.mybatis.mapper.entity.Example;

/**
 * # (¬､¬) (￢_￢)
 * 前端应用竞猜控制器。
 * 
 * @author 黄记新
 *
 */
@Controller
@RequestMapping("appguess")
public class AppGuessController {
	@Autowired
	private IQuestionService questionService;
	@Autowired
	private IMememberService mememberService;
	@Autowired
	private IMemAddressService addressService;
	
	@RequestMapping("gquestions")
	@ResponseBody
	List<GuessQuestion> getQuestions(PageInfo<GuessQuestion> pageInfo,HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse) {
		/*Example example = new Example(GuessQuestion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andGreaterThanOrEqualTo("questionEndTime", new Date());
        */
		GuessQuestionCriteria guessQuestionCriteria = new GuessQuestionCriteria();
		GuessQuestionCriteria.Criteria criteria= guessQuestionCriteria.createCriteria();
		criteria.andQuestionEndTimeGreaterThanOrEqualTo(new Date());
		criteria.andCheckedEqualTo(false);
		guessQuestionCriteria.setOrderByClause("create_date asc");
		List<GuessQuestion> list = questionService.selectByExample(guessQuestionCriteria, pageInfo);
		
		return list;
	}

	@RequestMapping("grecord/{id}")
	@ResponseBody
	List<MemGuessRecord> getGuessRecordById(@PathVariable(name = "id") String id,PageInfo<MemGuessRecord> pageInfo,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		List<MemGuessRecord> records = mememberService.selectByMemId(id,pageInfo);
		return records;
	}
	
	@RequestMapping("maddress/{id}")
	@ResponseBody
	List<MemAddress> getMemberAddressByMid(@PathVariable(name = "id") String id,PageInfo<MemAddress> pageInfo,HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse) {
		Example example = new Example(MemAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andGreaterThanOrEqualTo("memberId", id);
		
		List<MemAddress> list = addressService.selectByExample(example, pageInfo);
		
		return list;
	}
	
	@RequestMapping(value = "maddress/save", method = RequestMethod.POST)
	@ResponseBody
	protected int save(MemAddress record, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	      
		int result = addressService.insertSelective(record);
		return result;
	}
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
