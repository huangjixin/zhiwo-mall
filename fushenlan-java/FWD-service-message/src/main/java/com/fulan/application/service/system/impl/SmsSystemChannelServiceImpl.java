package com.fulan.application.service.system.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.message.domain.SmsChannel;
import com.fulan.api.message.domain.SmsSystem;
import com.fulan.api.message.domain.SmsSystemChannel;
import com.fulan.api.message.vo.SmsSystemChannelVO;
import com.fulan.application.mapper.SmsSystemChannelMapper;
import com.fulan.application.orm.page.PageUtil;
import com.fulan.application.service.system.SmsChannelService;
import com.fulan.application.service.system.SmsSystemChannelService;
import com.fulan.application.service.system.SmsSystemService;
import com.fulan.application.util.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 短信通道规则管理业务层
 * @author: guiyang
 * @date: 2018/3/5 11:02
 */
@Service
public class SmsSystemChannelServiceImpl extends ServiceImpl<SmsSystemChannelMapper, SmsSystemChannel> implements SmsSystemChannelService {

    @Autowired
    private SmsSystemService smsSystemService;

    @Autowired
    private SmsChannelService channelService;

    /**
     * 查询短信通道规则管理
     */
    @Override
    public SmsSystemChannel selectSmsSystemChannel() {
        List<SmsSystem> systems = smsSystemService.selectSmsSystem();
        List<SmsChannel> smsChannels = channelService.selectSmsChannel();
        SmsSystemChannel  smsSystemChannel = new SmsSystemChannel();
        smsSystemChannel.setChannels(smsChannels);
        smsSystemChannel.setSystems(systems);
        return smsSystemChannel;
    }

    /**
     * 插入短信通道规则管理
     */
    @Override
    public void saveSmsSystemChannel(SmsSystemChannel smsSystemChannel) {
        insert(smsSystemChannel);
    }

    /**
     * 修改短信通道规则管理
     */
    @Override
    public void updateSmsSystemChannel(SmsSystemChannel smsSystemChannel) {
        updateById(smsSystemChannel);
    }

    /**
     * 删除短信通道规则管理
     */
    @Override
    public void deleteSmsSystemChannelById(Long id) {
        deleteById(id);
    }
}