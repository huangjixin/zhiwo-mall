package com.fulan.application.common;

import com.fulan.api.message.domain.SmsParameterFactor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: 参数因子静态缓存
 * @author: guiyang
 * @date: 2018/3/30 14:45
 */
public class FactorVO {

    private static List<SmsParameterFactor> smsParameterFactors = new ArrayList<>();

    public static List<SmsParameterFactor> get(){
        return smsParameterFactors;
    }

    public static void set(List<SmsParameterFactor> list){
        if (smsParameterFactors.size()>0){
            smsParameterFactors.clear();
        }
        smsParameterFactors.addAll(list);
    }

    public static void set(SmsParameterFactor smsParameterFactor){
        smsParameterFactors.add(smsParameterFactor);
    }

    public static void del(Long id){
        Iterator<SmsParameterFactor> iterator = smsParameterFactors.iterator();
        while(iterator.hasNext()){
            SmsParameterFactor factor = iterator.next();
            if (id.equals(factor.getId())){
                iterator.remove();
            }
        }
    }
}