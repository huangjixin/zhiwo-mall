package com.fulan.application.service.system;


import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.message.domain.SmsTemplate;
import com.fulan.api.message.vo.SmsTemplateResultVO;
import com.fulan.api.message.vo.SmsTemplateVO;
import com.fulan.application.util.page.PageInfo;

/**
 * @Description: 消息模板管理接口定义
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
public interface SmsTemplateService {

    /**
     * 查询消息模板
     */
    Page<SmsTemplateResultVO> selectSmsTemplate(SmsTemplateVO smsTemplateVO);

    /**
     * 根据id查询消息模板
     */
    SmsTemplate selectSmsTemplateById(Long id);

    /**
     * 插入消息模板
     */
     void saveSmsTemplate(SmsTemplate smsTemplate);

    /**
     * 修改消息模板
     */
    void updateSmsTemplate(SmsTemplate smsTemplate);

    /**
     * 删除消息模板
     */
     void deleteSmsTemplateById(Long id);

    /**
     * 根据模板编码获取消息模板
     */
    SmsTemplate getSmsTemplate(String templateCode);

    /**
     * 根据主备通道编码获取消息模板
     */
    SmsTemplate getSmsTemplateByTempId(String tempId);

	PageInfo<SmsTemplateResultVO> selectTemplateList(Page<SmsTemplateResultVO> page, String title, String type, String code,
			String masterCode, int pageNo, int pageSize);
}