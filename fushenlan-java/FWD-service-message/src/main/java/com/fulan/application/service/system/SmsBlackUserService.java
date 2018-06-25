package com.fulan.application.service.system;


import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.message.domain.SmsBlackUser;
import com.fulan.api.message.vo.SmsBlackUserVO;
import com.fulan.application.util.page.PageInfo;

import java.util.List;

/**
 * @Description: 短信手机号码黑名单接口定义
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
public interface SmsBlackUserService {

    /**
     * 查询手机号码黑名单列表
     */
    Page<SmsBlackUser> selectSmsBlackUser(SmsBlackUserVO smsBlackUserVO);

    /**
     * 查询所有的手机号码黑名单
     */
    List<SmsBlackUser> selectBlackUsers();
    /**
     * 根据id集合查询所有的手机号码黑名单
     */
    List<SmsBlackUser> selectBlackUsers(List<Long> ids);
    /**
     * 查询所有的手机号码黑名单
     */
    List<String> selectPhone(String phones,String type);

    /**
     * 批量插入手机号码黑名单
     */
     void saveSmsBlackUser(List<SmsBlackUser> smsBlackUserList);

    /**
     * 批量移除手机号码黑名单
     */
    void deleteBatchSmsBlackUser(List<Long> ids);

    /**
     * 移除手机号码黑名单
     */
     void deleteSmsBlackUserById(Long id);

     
     /**
      * 查询手机号码黑名单列表
     * @param type 
      */
	PageInfo<SmsBlackUser> selectSmsList(Page<SmsBlackUser> page, String phone, String type, int pageNo, int pageSize);
}