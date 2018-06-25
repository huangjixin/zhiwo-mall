package com.fulan.application.service.system;


import com.fulan.api.message.domain.SmsSystem;

import java.util.List;

/**
 * @Description:  短信业务系统接口定义
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
public interface SmsSystemService {

    /**
     * 查询短信业务系统
     */
    List<SmsSystem> selectSmsSystem();

}