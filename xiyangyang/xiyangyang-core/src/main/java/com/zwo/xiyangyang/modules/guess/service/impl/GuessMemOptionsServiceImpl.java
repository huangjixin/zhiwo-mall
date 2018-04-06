/**
 * 
 */
package com.zwo.xiyangyang.modules.guess.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;
import com.zwo.xiyangyang.modules.guess.dao.GuessAccountMapper;
import com.zwo.xiyangyang.modules.guess.dao.GuessMemOptionsMapper;
import com.zwo.xiyangyang.modules.guess.domain.GuessAccount;
import com.zwo.xiyangyang.modules.guess.domain.GuessMemOptions;
import com.zwo.xiyangyang.modules.guess.domain.GuessQuestion;
import com.zwo.xiyangyang.modules.guess.service.IGuessMemOptionsService;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 黃記新
 *
 */
@Service
@Lazy(true)
@Transactional(readOnly = false)
public class GuessMemOptionsServiceImpl extends BaseServiceImpl<GuessMemOptions> implements IGuessMemOptionsService {
	private static Logger logger = LoggerFactory.getLogger(GuessMemOptionsServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Autowired
	private GuessMemOptionsMapper guessMemOptionsMapper ;
	
	@Autowired
	private GuessAccountMapper accountMapper ;

	@Override
	public Mapper<GuessMemOptions> getBaseMapper() {
		return guessMemOptionsMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return GuessMemOptionsServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return GuessMemOptions.class;
	}

	@Override
	protected String getBaseMessage() {
		return "竞猜下注表基础操作";
	}
	

	@Override
	public int insertSelective(GuessMemOptions record) {
		if(record.getId() == null) {
			String id = UUID.randomUUID().toString().replaceAll("-", "");
			record.setId(id);
		}

		if (getLogger().isInfoEnabled()) {
			try {
				Gson gson = new Gson();
				String jsonStr = gson.toJson((Object) record);
				getLogger().info(getBaseMessage() + "插入开始，参数对象的值是：" + jsonStr);
			} catch (Exception e) {
				getLogger().info("系统打印参数序列化的时候发生了异常，该异常不会影响数据库操作");
			}
		}

		int result = getBaseMapper().insertSelective(record);
		if (getLogger().isInfoEnabled())
			getLogger().info(getBaseMessage() + "插入" + (result == 1 ? "成功" : "失败"));
		return result;
	}

	@Override
	public int add(GuessMemOptions guessMemOptions, GuessQuestion question, GuessAccount account) {
		double blance = account.getBalance()-guessMemOptions.getBetValue();
		account.setBalance(blance);
		account.setDeposit(Double.valueOf(guessMemOptions.getBetValue()));
		this.accountMapper.updateByPrimaryKeySelective(account);
		this.getBaseMapper().insertSelective(guessMemOptions);
		return 1;
	}

}
