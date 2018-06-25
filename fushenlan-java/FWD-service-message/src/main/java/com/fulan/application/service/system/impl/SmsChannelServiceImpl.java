package com.fulan.application.service.system.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.message.domain.SmsChannel;
import com.fulan.application.common.ChannelVO;
import com.fulan.application.mapper.SmsChannelMapper;
import com.fulan.application.service.system.SmsChannelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 短信通道业务层
 * @author: guiyang
 * @date: 2018/3/5 11:02
 */
@Service
public class SmsChannelServiceImpl extends ServiceImpl<SmsChannelMapper, SmsChannel> implements SmsChannelService {

    /**
     * 查询所有短信通道
     */
    @Override
    public List<SmsChannel> selectSmsChannel(){
        List<SmsChannel> smsChannels = ChannelVO.get();
        if (smsChannels.size()==0){
            smsChannels = selectList(new EntityWrapper<>(new SmsChannel()));
            ChannelVO.set(smsChannels);
        }
        return smsChannels;
    }

}