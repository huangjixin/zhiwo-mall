package com.fulan.application.service.system.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.message.domain.SmsBlackUser;
import com.fulan.api.message.domain.SmsParameterFactor;
import com.fulan.application.common.Consts;
import com.fulan.application.common.FactorVO;
import com.fulan.application.mapper.SmsBlackUserMapper;
import com.fulan.application.mapper.SmsParameterFactorMapper;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.service.system.SmsFactorService;
import com.fulan.application.task.FactorTask;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 短信通道业务层
 * @author: guiyang
 * @date: 2018/3/5 11:02
 */
@Service
public class SmsFactorServiceImpl extends ServiceImpl<SmsParameterFactorMapper, SmsParameterFactor> implements SmsFactorService {
	 @Autowired
	    private SmsParameterFactorMapper smsParameterFactorMapper;

    /**
     * 查询短信因子接口
     */
    @Override
    public List<SmsParameterFactor> selectSmsFactor() {
        List<SmsParameterFactor> factors = FactorVO.get();
        if (factors.size()==0){
            factors = selectList(new EntityWrapper<>(new SmsParameterFactor()));
            FactorVO.set(factors);
        }
        return factors;
    }

    /**
     * 添加因子
     * @param smsParameterFactor
     */
    @Override
    public void saveSmsFactor(SmsParameterFactor smsParameterFactor) {
        GenerateVOUtil.generate(smsParameterFactor);
        insert(smsParameterFactor);
        FactorVO.set(smsParameterFactor);
    }

    /**
     * 修改因子
     * @param smsParameterFactor
     */
    @Override
    public void updateSmsFactor(SmsParameterFactor smsParameterFactor) {
        GenerateVOUtil.generate(smsParameterFactor);
        updateById(smsParameterFactor);
        FactorVO.set(smsParameterFactor);
    }

    /**
     * 删除因子
     * @param id
     */
    @Override
    public void deleteSmsFactor(Long id) {
         deleteById(id);
         FactorVO.del(id);
    }

	@Override
	public PageInfo<SmsParameterFactor> selectParameter(Page<SmsParameterFactor> page, int pageNo, int pageSize) {
		PageInfo<SmsParameterFactor> pageInfo = new PageInfo<>();
		  int total = smsParameterFactorMapper.listForManageCount();
		pageInfo.setRecords(smsParameterFactorMapper.listForManage(page, pageNo, pageSize));
		pageInfo.setPageNo(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
        pageInfo.setPageRecords(page.getTotal());
        return pageInfo;
	}

	@Override
	public SmsParameterFactor selectParameterById(String id) {
		SmsParameterFactor smsParameterFactor =smsParameterFactorMapper.selectParameterById(id);
		return smsParameterFactor;
	}

	@Override
	public List<SmsParameterFactor> listFactor() {
		List<SmsParameterFactor> list=smsParameterFactorMapper.listFactor();
		return list;
	}
    
     
}