package com.fulan.application.service.system.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.message.domain.SmsTemplate;
import com.fulan.api.message.vo.SmsTemplateResultVO;
import com.fulan.api.message.vo.SmsTemplateVO;
import com.fulan.application.mapper.SmsTemplateMapper;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;
import com.fulan.application.service.customer.SmsService;
import com.fulan.application.service.system.SmsTemplateService;
import com.fulan.application.util.spring.SpringUtil;
import com.fulan.application.util.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 消息模板管理业务层
 * @author: guiyang
 * @date: 2018/3/5 11:02
 */
@Service
public class SmsTemplateServiceImpl extends ServiceImpl<SmsTemplateMapper, SmsTemplate> implements SmsTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(SmsTemplateServiceImpl.class);

    @Autowired
    private SmsTemplateMapper templateMapper;

    private static final String SMS_TEMPLATE = "SMS_ALI_CLOUD,SMS_ETONENET";
    /**
     * 查询消息模板
     */
    @Override
    public Page<SmsTemplateResultVO> selectSmsTemplate(SmsTemplateVO smsTemplateVO) {
        Page<SmsTemplateResultVO> page = new Page<>(smsTemplateVO.getPageIndex(), smsTemplateVO.getPageSize());
        page.setRecords(templateMapper.selectSmsTemplate(smsTemplateVO));
        return page;
    }

    /**
     * 根据id查询消息模板
     */
    @Override
    public SmsTemplate selectSmsTemplateById(Long id) {
        return selectById(id);
    }

    /**
     * 插入消息模板
     */
    @Override
    public void saveSmsTemplate(SmsTemplate smsTemplate) {
        if(getSmsTemplate(smsTemplate.getCode())!=null){
            throw new RuntimeException("此模板code码已存在");
        }
        SmsService smsService;
        //主通道模板
        smsService = SpringUtil.getBean(smsTemplate.getMasterChannelCode(), SmsService.class);
        String masterTemplateId = smsService.createTemplate(smsTemplate);
        if (!StringUtils.isEmptry(masterTemplateId)){
            smsTemplate.setMasterTempId(masterTemplateId);
        }
        //备用通道模板
        smsService = SpringUtil.getBean(smsTemplate.getSlaveChannelCode(), SmsService.class);
        String slaveTemplateId = smsService.createTemplate(smsTemplate);
        if (!StringUtils.isEmptry(slaveTemplateId)){
            smsTemplate.setSlaveTempId(slaveTemplateId);
        }
        if (SMS_TEMPLATE.contains(smsTemplate.getMasterChannelCode())){
            smsTemplate.setMasterStatus(SmsTemplate.PASS);
        } else {
            smsTemplate.setMasterStatus(SmsTemplate.PASSING);
        }
        if (SMS_TEMPLATE.contains(smsTemplate.getSlaveChannelCode())){
            smsTemplate.setSlaveStatus(SmsTemplate.PASS);
        }else {
            smsTemplate.setSlaveStatus(SmsTemplate.PASSING);
        }
        //本地创建模板
        GenerateVOUtil.generate(smsTemplate);
        insert(smsTemplate);
    }


    /**
     * 修改消息模板
     */
    @Override
    public void updateSmsTemplate(SmsTemplate smsTemplate) {
        SmsTemplate template = getSmsTemplate(smsTemplate.getCode());
        if (template!=null&&!smsTemplate.getId().equals(template.getId())){
            throw new RuntimeException("此模板code码已存在");
        }
        SmsService smsService;
        boolean status;
        smsService = SpringUtil.getBean(smsTemplate.getMasterChannelCode(), SmsService.class);
        status = smsService.updateTemplate(smsTemplate.getMasterTempId(), smsTemplate.getContent(), smsTemplate.getType(), smsTemplate.getRemark());
        if (!status){
            throw new RuntimeException("主模板修改失败");
        }
        smsService = SpringUtil.getBean(smsTemplate.getSlaveChannelCode(), SmsService.class);
        status = smsService.updateTemplate(smsTemplate.getSlaveTempId(),smsTemplate.getContent(), smsTemplate.getType(), smsTemplate.getRemark());
        if (!status){
            throw new RuntimeException("备用模板修改失败");
        }
        try {
            updateById(smsTemplate);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("-----修改模板失败------");
            throw new RuntimeException("修改模板失败");
        }
    }

    /**
     * 删除消息模板
     */
    @Override
    public void deleteSmsTemplateById(Long id) {
        SmsTemplate smsTemplate = selectById(id);
        SmsService smsService;
        boolean status;
        smsService = SpringUtil.getBean(smsTemplate.getMasterChannelCode(), SmsService.class);
        status = smsService.deleteTemplate(smsTemplate.getMasterTempId());
        if (!status){
            throw new RuntimeException("主模板删除失败");
        }
        smsService = SpringUtil.getBean(smsTemplate.getSlaveChannelCode(), SmsService.class);
        status = smsService.deleteTemplate(smsTemplate.getSlaveTempId());
        if (!status){
            throw new RuntimeException("备用模板删除失败");
        }
        try {
            deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("-----模板删除失败------");
            throw new RuntimeException("模板删除失败");
        }
    }

    @Override
    public SmsTemplate getSmsTemplate(String templateCode) {
        SmsTemplate smsTemplate = new SmsTemplate();
        smsTemplate.setCode(templateCode);
        EntityWrapper<SmsTemplate> ew = new EntityWrapper<>(smsTemplate);
       return selectOne(ew);
    }

    @Override
    public SmsTemplate getSmsTemplateByTempId(String tempId) {
        return templateMapper.getSmsTemplateByTempId(tempId,SmsTemplate.NO_PASS);
    }

	@Override
	public PageInfo<SmsTemplateResultVO> selectTemplateList(Page<SmsTemplateResultVO> page, String title, String type, String code,
			String masterCode, int pageNo, int pageSize) {
		PageInfo<SmsTemplateResultVO> pageInfo = new PageInfo<SmsTemplateResultVO>();
		int total = templateMapper.listForManageCount(title,type,code,masterCode);
		pageInfo.setRecords(templateMapper.listForManage(page,title,type,code,masterCode, pageNo, pageSize));
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}
    
    
}