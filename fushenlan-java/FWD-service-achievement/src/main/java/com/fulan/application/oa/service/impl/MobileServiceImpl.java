/**
 * 
 */
package com.fulan.application.oa.service.impl;

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
import com.fulan.application.oa.domain.FwdOaMobile;
import com.fulan.application.oa.domain.FwdOaMobileExample;
import com.fulan.application.oa.mapper.FwdOaApplyFormMapper;
import com.fulan.application.oa.mapper.FwdOaMobileMapper;
import com.fulan.application.oa.service.IMobileService;

/**
 * @author 黄记新 Tony
 *
 */
@Service
@Transactional
public class MobileServiceImpl implements IMobileService {

	private static String BASE_MESSAGE = "手机业务类MobileServiceImpl";
	private static Logger logger = Logger.getLogger(MobileServiceImpl.class);

	@Autowired
	private FwdOaMobileMapper mobileMapper;

	@Autowired
	private FwdOaApplyFormMapper oaApplyFormMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fulan.application.oa.service.IMobileService#save(com.fulan.application.oa
	 * .domain.FwdOaMobile)
	 */
	@Override
	public int save(FwdOaMobile mobile) throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "保存手机开始，传入的参数是：" + mobile.toString());
		}
		// 添加表单数据
		FwdOaApplyForm applyForm = this.saveApplyForm(mobile.getAgentCode());
		mobile.setOaApplyId(applyForm.getId());
		int result = mobileMapper.insertSelective(mobile);
		if (logger.isInfoEnabled()) {
			String msg = result == 1 ? "成功" : "失败";
			logger.info(BASE_MESSAGE + "保存手机结束，结果是：" + msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.IMobileService#delete(int)
	 */
	@Override
	public int delete(int id) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "删除手机开始，传入的参数id是：" + id);
		}
		int result = mobileMapper.deleteByPrimaryKey(id);
		if (logger.isInfoEnabled()) {
			String msg = result == 1 ? "成功" : "失败";
			logger.info(BASE_MESSAGE + "删除手机结束，结果是：" + msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fulan.application.oa.service.IMobileService#update(com.fulan.application.
	 * oa.domain.FwdOaMobile)
	 */
	@Override
	public int update(FwdOaMobile mobile) throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "更新手机开始，传入的参数是：" + mobile.toString());
		}
		// 添加表单数据
		FwdOaApplyForm applyForm = this.saveApplyForm(mobile.getAgentCode());
		mobile.setOaApplyId(applyForm.getId());
		int result = mobileMapper.updateByPrimaryKeySelective(mobile);
		if (logger.isInfoEnabled()) {
			String msg = result == 1 ? "成功" : "失败";
			logger.info(BASE_MESSAGE + "更新手机结束，结果是：" + msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.IMobileService#selectAll()
	 */
	@Override
	public List<FwdOaMobile> selectAll() {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "查询所有手机开始");
		}
		List<FwdOaMobile> mobilees = mobileMapper.selectByExample(null);
		if (logger.isInfoEnabled()) {
			String msg = mobilees.size() + "";
			logger.info(BASE_MESSAGE + "保存手机，结果的条目数是：" + msg);
		}
		return mobilees;
	}

	@Override
	public FwdOaMobile selectById(int id) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "查询根据id查询手机开始");
		}
		FwdOaMobile oaMobile = mobileMapper.selectByPrimaryKey(id);
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "根据id查询手机数据，结果是：" + JSONObject.toJSONString(oaMobile));
		}
		return oaMobile;
	}

	@Override
	public List<FwdOaMobile> selectByAgentCode(String agentCode) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "根据代理人编号" + agentCode + "查询手机开始");
		}
		FwdOaMobileExample example = new FwdOaMobileExample();
		example.createCriteria().andAgentCodeEqualTo(agentCode);
		List<FwdOaMobile> mobile = mobileMapper.selectByExample(example);
		if (logger.isInfoEnabled()) {
			String msg = mobile.size() + "";
			logger.info(BASE_MESSAGE + "根据代理人编号手机，结果的条目数是：" + msg);
		}
		return mobile;
	}

	@Override
	public List<FwdOaMobile> saveOrUpdate(List<FwdOaMobile> thirdoaMobileCardList, List<FwdOaMobile> fwdOaMobileList,
			String agentCode) throws Exception { // 根据第三方的数据，判断本地是否数据已经存在，如果存在的话要更新这些数据，如果不存在则要添加进库
		boolean dataChanged = false;
		for (FwdOaMobile fwdOaMobile : thirdoaMobileCardList) {
			boolean flag = false;
			FwdOaMobile mobileC = null;
			// 循环本地手机号。
			for (FwdOaMobile oaMobile : fwdOaMobileList) {
				if (fwdOaMobile.getMobileNo().equals(oaMobile.getMobileNo())) {
					flag = true;
					mobileC = oaMobile;
					break;
				}
			}
			// 如果数据库已经有了相同的手机号了，检查两者的其它信息是否相同，如果不同则更新本地的数据库
			if (flag == true) {
				// 比较其它的信息，比如bankC.getAgentName()和其它的信息看看有没有变更，如果有再更新数据库。
				// 如果有变更了 dataChanged=true;
				if (!mobileC.getAgentCode().equals(fwdOaMobile.getAgentCode())
						|| !mobileC.getMobileNo().equals(fwdOaMobile.getMobileNo())
						|| !mobileC.getAgentName().equals(fwdOaMobile.getAgentName())) {
					// 修改手机变更的默认选中为false
					FwdOaMobile record = new FwdOaMobile();
					record.setIsDefault(false);
					FwdOaMobileExample example = new FwdOaMobileExample();
					example.createCriteria().andAgentCodeEqualTo(agentCode);
					mobileMapper.updateByExampleSelective(record, example);
					// 添加数据
					fwdOaMobile.setId(mobileC.getId());
					fwdOaMobile.setIsDefault(true);
					fwdOaMobile.setUpdateDatetime(new Date());
					this.mobileMapper.updateByPrimaryKey(fwdOaMobile);
					dataChanged = true;
				}
			} else {
				// 如果数据库还没有这个号码，先把其他的号码的isDefault改成false;
				// 插入这个
				// 修改手机变更的默认选中为false
				FwdOaMobile record = new FwdOaMobile();
				record.setIsDefault(false);
				FwdOaMobileExample example = new FwdOaMobileExample();
				example.createCriteria().andAgentCodeEqualTo(agentCode);
				mobileMapper.updateByExampleSelective(record, example);
				// 添加数据
				fwdOaMobile.setIsDefault(true);
				this.mobileMapper.insertSelective(fwdOaMobile);
				dataChanged = true;
			}
		}
		// 本地数据库
		List<FwdOaMobile> oaMobileList = null;
		if (dataChanged == true) {
			oaMobileList = this.selectByAgentCode(agentCode);
		} else {
			oaMobileList = fwdOaMobileList;
		}
		return oaMobileList;
	}

	public FwdOaApplyForm saveApplyForm(String agentCode) throws Exception {
		FwdOaApplyForm applyForm = new FwdOaApplyForm();
		applyForm.setType(ApplyTypeEnum.MobileUpdate.getCode());
		applyForm.setAgentCode(agentCode);
		applyForm.setCreateDatetime(new Date());
		applyForm.setStatus(ApplyConstant.APPLY_STATUS_PASS);
		applyForm.setEndDatetime(new Date());
		
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "添加一张变更或新增成功的手机申请表单开始,参数是：" + applyForm.toString());
		}
		
		int result = oaApplyFormMapper.insertSelective(applyForm);
		if (logger.isInfoEnabled()) {
			String msg = result == 1 ? "成功" : "失败";
			logger.info(BASE_MESSAGE + "添加一张变更或新增成功的手机申请表单结束，结果是：" + msg);
		}
		return applyForm;
	}
}
