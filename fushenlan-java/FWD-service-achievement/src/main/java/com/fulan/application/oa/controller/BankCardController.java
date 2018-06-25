package com.fulan.application.oa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.application.achievement.vo.ErrorMessage;
import com.fulan.application.oa.domain.FwdOaBankCard;
import com.fulan.application.oa.service.IBankCardService;
import com.fulan.application.util.domain.Response;

/**
 * 
 * @author 曾文明
 *
 */
@RestController
@RequestMapping("/bankCard")
public class BankCardController {

	@Autowired
	private IBankCardService bankCardService;

	/**
	 * 保存数据
	 * 
	 * @param save
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	private void save(FwdOaBankCard bankCard) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setState("1");
		bankCard.setCreateDatetime(new Date());
		bankCard.setUpdateDatetime(new Date());
		try {
			bankCardService.save(bankCard);
		} catch (Exception e) {
			errorMessage.setState("0");
			errorMessage.setErrorMessage("error:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 删除数据
	 * 
	 * @param delete
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	private int delete(int id) {
		int result = bankCardService.delete(id);
		return result;
	}

	/**
	 * 修改数据
	 * 
	 * @param update
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	private void update(FwdOaBankCard bankCard) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setState("1");
		bankCard.setUpdateDatetime(new Date());
		try {
			bankCardService.update(bankCard);
		} catch (Exception e) {
			errorMessage.setState("0");
			errorMessage.setErrorMessage("error:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 查询数据
	 * 
	 * @param selectAll
	 * @return
	 */
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	private List<FwdOaBankCard> selectAll() {
		List<FwdOaBankCard> fwdOaBankCardList = bankCardService.selectAll();
		return fwdOaBankCardList;
	}

	/**
	 * 根据ID查询数据
	 * 
	 * @param selectById
	 * @return
	 */
	@RequestMapping(value = "/selectById/{id}", method = RequestMethod.GET)
	private FwdOaBankCard selectById(@RequestParam(name = "id", required = true) Integer id) {
		FwdOaBankCard oaBankCard = bankCardService.selectById(id);
		return oaBankCard;
	}

	/**
	 * 根据代理人编号查询数据
	 * 
	 * @param selectByAgentCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectByAgentCode", method = RequestMethod.GET)
	private Response selectByAgentCode(@RequestParam(name = "agentCode", required = true) String agentCode) {
		// 调用第三方接口数据
		List<FwdOaBankCard> thirdOaBankCardList = new ArrayList<>();
		// 第三方模拟数据
		FwdOaBankCard fwdOaBankCard = new FwdOaBankCard();
		fwdOaBankCard.setAgentCode("evan");
		fwdOaBankCard.setCardNo("123456");
		fwdOaBankCard.setAgentName("evan");
		fwdOaBankCard.setUsername("evan");
		thirdOaBankCardList.add(fwdOaBankCard);
		FwdOaBankCard fwdOaBankCard2 = new FwdOaBankCard();
		fwdOaBankCard2.setAgentCode("evan");
		fwdOaBankCard2.setCardNo("6789");
		fwdOaBankCard2.setAgentName("evan");
		fwdOaBankCard2.setUsername("evan");
		thirdOaBankCardList.add(fwdOaBankCard2);
		// 查询本地数据库
		List<FwdOaBankCard> fwdOaBankCardList = bankCardService.selectByAgentCode(agentCode);

		List<FwdOaBankCard> oaBankCardList = null;
		try {
			oaBankCardList = bankCardService.saveOrUpdate(thirdOaBankCardList, fwdOaBankCardList, agentCode);
		} catch (Exception e) {
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setState("0");
			errorMessage.setErrorMessage("error:" + e.getMessage());
			Response<ErrorMessage> response = new Response<ErrorMessage>(Response.ERROR,
					Response.ERROR_MESSAGE);
			response.setData(errorMessage);
			e.printStackTrace();
			return response;
		}
		Response<List<FwdOaBankCard>> response = new Response<List<FwdOaBankCard>>(Response.SUCCESS,
				Response.SUCCESS_MESSAGE);
		response.setData(oaBankCardList);
		return response;
	}
}
