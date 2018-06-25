package com.fulan.application.common;

import com.fulan.api.message.domain.SmsSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: 系统静态缓存
 * @author: guiyang
 * @date: 2018/3/30 14:45
 */
public class SmstemVO {

    private static List<SmsSystem> smsSystems = new ArrayList<>();

    public static List<SmsSystem> get(){
        return smsSystems;
    }

    public static void set(List<SmsSystem> list){
        if (smsSystems.size()>0){
            smsSystems.clear();
        }
        smsSystems.addAll(list);
    }

    public static void set(SmsSystem smsSystem){
        smsSystems.add(smsSystem);
    }

    public static void del(Long id){
        Iterator<SmsSystem> iterator = smsSystems.iterator();
        while(iterator.hasNext()){
            SmsSystem smsSystem = iterator.next();
            if (id.equals(smsSystem.getId())){
                iterator.remove();
            }
        }
    }
}