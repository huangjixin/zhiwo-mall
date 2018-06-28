/**
 * 
 */
package com.fulan.application.oa.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;
import com.fulan.application.oa.constant.ApplyConstant;
import com.fulan.application.oa.constant.ApplyTypeEnum;
import com.fulan.application.oa.domain.FwdOaApplyForm;
import com.fulan.application.oa.domain.FwdOaBankCard;
import com.fulan.application.oa.domain.FwdOaBankCardExample;
import com.fulan.application.oa.mapper.FwdOaApplyFormMapper;
import com.fulan.application.oa.mapper.FwdOaBankCardMapper;
import com.fulan.application.oa.service.IBankCardService;

/**
 * @author 黄记新 Tony
 *
 */
@Service
@Transactional
public class BankCardServiceImpl implements IBankCardService {

	private static String BASE_MESSAGE = "银行业务类BankCardServiceImpl";
	private static Logger logger = Logger.getLogger(BankCardServiceImpl.class);

	@Autowired
	private FwdOaBankCardMapper bankCardMapper;

	@Autowired
	private FwdOaApplyFormMapper oaApplyFormMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fulan.application.oa.service.IBankCardService#save(com.fulan.application.
	 * oa.domain.FwdOaBankCard)
	 */
	@Override
	public int save(FwdOaBankCard bankCard) throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "保存银行开始，传入的参数是：" + bankCard.toString());
		}
		// 添加表单数据
		FwdOaApplyForm applyForm = this.saveApplyForm(bankCard.getAgentCode());
		bankCard.setOaApplyId(applyForm.getId());
		int result = bankCardMapper.insertSelective(bankCard);
		if (logger.isInfoEnabled()) {
			String msg = result == 1 ? "成功" : "失败";
			logger.info(BASE_MESSAGE + "保存银行结束，结果是：" + msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.IBankCardService#delete(int)
	 */
	@Override
	public int delete(int id) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "删除银行开始，传入的参数id是：" + id);
		}
		int result = bankCardMapper.deleteByPrimaryKey(id);
		if (logger.isInfoEnabled()) {
			String msg = result == 1 ? "成功" : "失败";
			logger.info(BASE_MESSAGE + "删除银行结束，结果是：" + msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.IBankCardService#update(com.fulan.
	 * application.oa.domain.FwdOaBankCard)
	 */
	@Override
	public int update(FwdOaBankCard bankCard) throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "更新银行开始，传入的参数是：" + bankCard.toString());
		}
		
		//TODO 更新第三方系统
		
		//全部设为非default
		FwdOaBankCard record = new FwdOaBankCard();
		record.setIsDefault(false);
		FwdOaBankCardExample example = new FwdOaBankCardExample();
		example.createCriteria().andAgentCodeEqualTo(bankCard.getAgentCode());
		bankCardMapper.updateByExampleSelective(record, example);
				
		// 添加表单数据
		FwdOaApplyForm applyForm = this.saveApplyForm(bankCard.getAgentCode());
		bankCard.setOaApplyId(applyForm.getId());
		bankCard.setUpdateDatetime(new Date());
		bankCard.setIsDefault(true);
		int result = bankCardMapper.updateByPrimaryKeySelective(bankCard);
		
		if (logger.isInfoEnabled()) {
			String msg = result == 1 ? "成功" : "失败";
			logger.info(BASE_MESSAGE + "更新银行结束，结果是：" + msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.IBankCardService#selectAll()
	 */
	@Override
	public List<FwdOaBankCard> selectAll() {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "查询所有银行开始");
		}
		List<FwdOaBankCard> bankCardes = bankCardMapper.selectByExample(null);
		if (logger.isInfoEnabled()) {
			String msg = bankCardes.size() + "";
			logger.info(BASE_MESSAGE + "保存银行，结果的条目数是：" + msg);
		}
		return bankCardes;
	}

	@Override
	public FwdOaBankCard selectById(int id) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "查询根据id查询银行开始");
		}
		FwdOaBankCard oaBankCard = bankCardMapper.selectByPrimaryKey(id);
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "根据id查询银行数据，结果是：" + JSONObject.toJSONString(oaBankCard));
		}
		return oaBankCard;
	}

	@Override
	public List<FwdOaBankCard> selectByAgentCode(String agentCode) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "根据代理人编号" + agentCode + "查询所有银行开始");
		}
		FwdOaBankCardExample example = new FwdOaBankCardExample();
		example.createCriteria().andAgentCodeEqualTo(agentCode);
		List<FwdOaBankCard> bankCardes = bankCardMapper.selectByExample(example);
		if (logger.isInfoEnabled()) {
			String msg = bankCardes.size() + "";
			logger.info(BASE_MESSAGE + "根据代理人编号银行，结果的条目数是：" + msg);
		}
		return bankCardes;
	}

	@Override
	public List<FwdOaBankCard> saveOrUpdate(List<FwdOaBankCard> thirdOaBankCardList,
			List<FwdOaBankCard> fwdOaBankCardList, String agentCode) throws Exception {
		// 根据第三方的数据，判断本地是否数据已经存在，如果存在的话要更新这些数据，如果不存在则要添加进库
		boolean dataChanged = false;
		// 循环第三方的银行卡。
		for (FwdOaBankCard oaBankCard : thirdOaBankCardList) {
			boolean flag = false;
			FwdOaBankCard bankC = null;
			// 循环本地银行卡。
			for (FwdOaBankCard bankCard : fwdOaBankCardList) {
				if (bankCard.getCardNo().equals(oaBankCard.getCardNo())) {
					flag = true;
					bankC = bankCard;
					break;
				}
			}
			// 如果数据库已经有了相同的银行卡了，检查两者的其它信息是否相同，如果不同则更新本地的数据库
			if (flag == true) {
				// 比较其它的信息，比如bankC.getAgentName()和地址等其它的信息看看有没有变更，如果有再更新数据库。
				// 如果有变更了 dataChanged=true;
				if (!bankC.getAgentCode().equals(oaBankCard.getAgentCode())
						|| !bankC.getCardNo().equals(oaBankCard.getCardNo())) {
					// 修改银行卡默认选中为false
					FwdOaBankCard record = new FwdOaBankCard();
					record.setIsDefault(false);
					FwdOaBankCardExample example = new FwdOaBankCardExample();
					example.createCriteria().andAgentCodeEqualTo(agentCode);
					bankCardMapper.updateByExampleSelective(record, example);
					// 添加数据
					oaBankCard.setId(bankC.getId());
					oaBankCard.setIsDefault(true);
					this.bankCardMapper.updateByPrimaryKey(oaBankCard);
					dataChanged = true;
				}
			} else {
				// 如果数据库还没有这个号码，先把其他的号码的isDefault改成false;
				// 插入这个
				// 修改银行卡默认选中为false
				FwdOaBankCard record = new FwdOaBankCard();
				record.setIsDefault(false);
				FwdOaBankCardExample example = new FwdOaBankCardExample();
				example.createCriteria().andAgentCodeEqualTo(agentCode);
				bankCardMapper.updateByExampleSelective(record, example);
				// 添加数据
				oaBankCard.setIsDefault(true);
				this.bankCardMapper.insertSelective(oaBankCard);
				dataChanged = true;
			}
		}

		// 本地数据库
		List<FwdOaBankCard> oaBankCardList = null;
		if (dataChanged == true) {
			oaBankCardList = this.selectByAgentCode(agentCode);
		} else {
			oaBankCardList = fwdOaBankCardList;
		}
		
		if(oaBankCardList!=null && oaBankCardList.size()>0) {
			Collections.sort(oaBankCardList,new Comparator<FwdOaBankCard>() {
				@Override
				public int compare(FwdOaBankCard c1, FwdOaBankCard c2) {
					return c1.getIsDefault()?-1:1;
				}
			});
		}

		return oaBankCardList;
	}

	public FwdOaApplyForm saveApplyForm(String agentCode) throws Exception {
		
		FwdOaApplyForm applyForm = new FwdOaApplyForm();
		applyForm.setType(ApplyTypeEnum.BankCardUpdate.getCode());
		applyForm.setAgentCode(agentCode);
		applyForm.setCreateDatetime(new Date());
		applyForm.setStatus(ApplyConstant.APPLY_STATUS_PASS);
		applyForm.setEndDatetime(new Date());
		
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "添加一张变更或新增成功的申请表单开始,参数是：" + applyForm.toString());
		}
		
		int result = oaApplyFormMapper.insertSelective(applyForm);
		if (logger.isInfoEnabled()) {
			String msg = result == 1 ? "成功" : "失败";
			logger.info(BASE_MESSAGE + "添加一张变更或新增成功的申请表单数据，结果是：" + msg);
		}

		return applyForm;
	}
}
