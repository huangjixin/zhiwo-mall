package com.fulan.application.oa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.application.achievement.vo.BankCardDto;
import com.fulan.application.achievement.vo.CommonQueryRepsonse;
import com.fulan.application.achievement.vo.ErrorMessage;
import com.fulan.application.oa.domain.FwdOaBankCard;
import com.fulan.application.oa.service.IBankCardService;
import com.fulan.application.oa.service.OaAgentClient;
import com.fulan.application.oa.vo.FwdCqRespAgentGroupInfoDto;
import com.fulan.application.oa.vo.OaReqParamAgentCodeDto;
import com.fulan.application.util.domain.Response;

/**
 * 
 * @author 曾文明
 *
 */
@RestController
@RequestMapping("/bankCard")
public class BankCardController {

	private static final Logger logger = LoggerFactory.getLogger(BankCardController.class);
	
	@Autowired
	private IBankCardService bankCardService;
	

	/**
	 * 保存数据
	 * 
	 * @param save
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(FwdOaBankCard bankCard) {
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
	public int delete(int id) {
		int result = bankCardService.delete(id);
		return result;
	}

	/**
	 * 修改默认银行卡
	 * 
	 * @param update
	 * @return
	 */
	@RequestMapping(value = "/toDefault", method = RequestMethod.POST)
	public Response<String> update(@RequestBody BankCardDto bcDto) {
		
		try {
			String vcCode = bcDto.getVerificationCode();
			//TODO 验证码校验
			if(vcCode==null || vcCode.length()!=4) {
				Response<String> response = new Response<String>(Response.ERROR,"验证码错误");
				return response;
			}
			
			FwdOaBankCard card = bankCardService.selectById(bcDto.getCardId());
			if(card==null || !card.getAgentCode().equals(bcDto.getAgentCode())) {
				Response<String> response = new Response<String>(Response.ERROR,"没有权限");
				return response;
			}
			
			FwdOaBankCard bankCard = new FwdOaBankCard();
			bankCard.setAgentCode(bcDto.getAgentCode());
			bankCard.setId(bcDto.getCardId());
			bankCardService.update(bankCard);
		} catch (Exception e) {
			logger.error("Unknow Error", e);
			Response<String> response = new Response<String>(Response.ERROR,e.getMessage());
			return response;
		}
		Response<String> response = new Response<String>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		return response;
	}



	/**
	 * 根据ID查询数据
	 * 
	 * @param selectById
	 * @return
	 */
	@RequestMapping(value = "/selectById/{id}", method = RequestMethod.GET)
	public FwdOaBankCard selectById(@RequestParam(name = "id", required = true) Integer id) {
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
	public Response<List<FwdOaBankCard>> selectByAgentCode(@RequestParam(name = "agentCode", required = true) String agentCode) {
		List<FwdOaBankCard> oaBankCardList = null;
		try {
//			List<FwdOaBankCard> thirdOaBankCardList = new ArrayList<>();
//			FwdOaBankCard fwdOaBankCard = new FwdOaBankCard();
//			fwdOaBankCard.setAgentCode("888999");
//			fwdOaBankCard.setCardNo("1234123412344511");
//			fwdOaBankCard.setDescription("农业银行");
//			fwdOaBankCard.setCardType("4");
//			thirdOaBankCardList.add(fwdOaBankCard);
			// 调用第三方接口数据
			List<FwdOaBankCard> thirdOaBankCardList = bankCardService.getAgentBankCardFromCommonQuery(agentCode);
			// 查询本地数据库
			List<FwdOaBankCard> fwdOaBankCardList = bankCardService.selectByAgentCode(agentCode);
			oaBankCardList = bankCardService.saveOrUpdate(thirdOaBankCardList, fwdOaBankCardList, agentCode);
		} catch (Exception e) {
			logger.error("Unknow Error", e);
			Response<List<FwdOaBankCard>> response = new Response<List<FwdOaBankCard>>(Response.ERROR,e.getMessage());
			return response;
		}
		Response<List<FwdOaBankCard>> response = new Response<List<FwdOaBankCard>>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		response.setData(oaBankCardList);
		return response;
	}

}
