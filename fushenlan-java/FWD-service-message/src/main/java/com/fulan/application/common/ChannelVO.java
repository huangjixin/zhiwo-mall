package com.fulan.application.common;

import com.fulan.api.message.domain.SmsChannel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: 通道静态缓存
 * @author: guiyang
 * @date: 2018/3/30 14:45
 */
public class ChannelVO {

    private static List<SmsChannel> smsChannels = new ArrayList<>();

    public static List<SmsChannel> get(){
        return smsChannels;
    }

    public static void set(List<SmsChannel> list){
        if (smsChannels.size()>0){
            smsChannels.clear();
        }
        smsChannels.addAll(list);
    }

    public static void set(SmsChannel smsChannel){
        smsChannels.add(smsChannel);
    }

    public static void del(Long id){
        Iterator<SmsChannel> iterator = smsChannels.iterator();
        while(iterator.hasNext()){
            SmsChannel smsChannel = iterator.next();
            if (id.equals(smsChannel.getId())){
                iterator.remove();
            }
        }
    }
}