package com.fulan.application.service.system;


import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.message.domain.SmsParameterFactor;
import com.fulan.application.util.page.PageInfo;

import java.util.List;

/**
 * @Description:  短信因子接口定义
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
public interface SmsFactorService {

    /**
     * 查询短信因子接口
     */
    List<SmsParameterFactor> selectSmsFactor();

    /**
     * 新增短信因子接口
     */
    void saveSmsFactor(SmsParameterFactor smsParameterFactor);

    /**
     * 修改短信因子接口
     */
    void updateSmsFactor(SmsParameterFactor smsParameterFactor);

    /**
     *删除短信因子接口
     */
    void deleteSmsFactor(Long id);

    /**
     * 分页查询 参数列表
     * @param page
     * @param pageNo
     * @param pageSize
     * @return
     */
	PageInfo<SmsParameterFactor> selectParameter(Page<SmsParameterFactor> page, int pageNo, int pageSize);

	SmsParameterFactor selectParameterById(String id);

	List<SmsParameterFactor> listFactor();

}