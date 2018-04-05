package com.zwo.xiyangyang.app.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zwo.xiyangyang.modules.core.domain.Result;
import com.zwo.xiyangyang.modules.guess.domain.GuessAccount;
import com.zwo.xiyangyang.modules.guess.domain.GuessMemOptions;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestion;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestionCriteria;
import com.zwo.xiyangyang.modules.guess.service.IGuessAccountHisService;
import com.zwo.xiyangyang.modules.guess.service.IGuessAccountService;
import com.zwo.xiyangyang.modules.guess.service.IGuessMemOptionsService;
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
	@Autowired
	private IGuessMemOptionsService memOptionsService;
	@Autowired
	private IGuessAccountService accountService;
	@Autowired
	private IGuessAccountHisService accountHisService;
	
	
	/**
	 * 分页查询竞猜问题。
	 * @param pageInfo
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
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

	/**
	 * 查询竞猜记录。
	 * @param id
	 * @param pageInfo
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
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
	
	/**
	 * 会员新增地址。
	 * @param record
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = "maddress/save", method = RequestMethod.POST)
	@ResponseBody
	protected int saveAddress(MemAddress record, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
	      
		int result = addressService.insertSelective(record);
		return result;
	}
	
	/**
	 * 编辑会员地址。
	 * @param record
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = "maddress/edit", method = RequestMethod.POST)
	@ResponseBody
	protected int editAddress(MemAddress record, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		int result = addressService.updateByPrimaryKeySelective(record);
		return result;
	}
	
	/**
	 * 下注方法，下注前必须检查会员的登陆情况，以及会员的竞猜逗情况。
	 * @param guessMemOptions
	 * @param bindingResult
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = "bet", method = RequestMethod.POST)
	@ResponseBody
	protected Result bet(GuessMemOptions guessMemOptions,BindingResult bindingResult, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Result result = new Result();
		result.setData(0);
		result.setMsg("");
		if(bindingResult.hasErrors()) {
			result.setErrorMessage("数据有误");
			return result;
		}
		if(guessMemOptions.getQuestionId() == null 
				|| guessMemOptions.getBetValue() == null
				|| guessMemOptions.getOptionId() == null
				|| guessMemOptions.getMemId() == null) {
			result.setErrorMessage("数据有误");
			return result;
		}
		
		GuessQuestion guessQuestion = questionService.selectByPrimaryKey(guessMemOptions.getQuestionId());
		if(guessQuestion == null ) {
			result.setErrorMessage("数据有误");
			return result;
		}
		GuessAccount account  = accountService.selectByMid(guessMemOptions.getMemId());
		if(account.getBalance() < guessMemOptions.getBetValue()) {
			result.setData(0);
			result.setErrorMessage("竞猜逗不够，请冲逗");
			return result;
		}
		
		
		int resu = memOptionsService.add(guessMemOptions,guessQuestion,account);
		if(resu!=1) {
			result.setErrorMessage("下注出错，请联系系统管理员");
			return result;
		}
		
		result.setData(1);
		result.setMsg("竞猜成功");
		result.setErrorMessage("");
		return result;
	}
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
}
