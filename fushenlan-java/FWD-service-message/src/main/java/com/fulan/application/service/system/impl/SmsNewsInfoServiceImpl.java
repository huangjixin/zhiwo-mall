package com.fulan.application.service.system.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.message.domain.SmsNewsInfo;
import com.fulan.application.mapper.SmsNewsInfoMapper;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.service.system.SmsNewsInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 消息详情业务层
 * @author: guiyang
 * @date: 2018/3/5 11:02
 */
@Service
public class SmsNewsInfoServiceImpl extends ServiceImpl<SmsNewsInfoMapper, SmsNewsInfo> implements SmsNewsInfoService {

    /**
     * 批量插入消息详情
     * @param smsNewsInfo
     * @return
     */
    @Override
    public void saveSmsNewsInfo(List<SmsNewsInfo> smsNewsInfo) {
        GenerateVOUtil.generate(smsNewsInfo);
        insertBatch(smsNewsInfo);
    }

    /**
     * 修改消息
     * @param smsNewsInfo
     * @return
     */
    @Override
    public void updateSmsNewInfo(SmsNewsInfo smsNewsInfo) {
        GenerateVOUtil.generate(smsNewsInfo);
        updateById(smsNewsInfo);
    }

    /**
     * 根据消息id查询所有消息详情
     * @param smsId
     * @return
     */
    @Override
    public List<SmsNewsInfo> selectSmsNewsInfo(Long smsId,Long status) {
        SmsNewsInfo smsNewsInfo = new SmsNewsInfo();
        smsNewsInfo.setSmsNewsId(smsId);
        smsNewsInfo.setStatus(status);
        return selectList(new EntityWrapper<>(smsNewsInfo));
    }

    /**
     * 根据消息通道消息id查询消息详情
     * @param phone receivePhone
     * @return
     */
    @Override
    public SmsNewsInfo selectSmsNewsInfo(String phone,String receivePhone) {
        SmsNewsInfo smsNewsInfo = new SmsNewsInfo();
        smsNewsInfo.setPhone(phone);
        smsNewsInfo.setReceivePhone(receivePhone);
        return selectOne(new EntityWrapper<>(smsNewsInfo));
    }

    @Override
    public Integer selectCount(String phone) {
        SmsNewsInfo smsNewsInfo = new SmsNewsInfo();
        smsNewsInfo.setPhone(phone);
        return selectCount(new EntityWrapper<>(smsNewsInfo));
    }
}