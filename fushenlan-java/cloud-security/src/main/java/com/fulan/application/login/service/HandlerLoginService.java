package com.fulan.application.login.service;

import com.fulan.api.security.domain.Account;
import com.fulan.api.security.vo.AccountVO;
import com.fulan.application.util.domain.Response;

/**
 * @Description: 登录处理流程转发与处理缓存,session等业务
 * @author: guiyang
 * @date: 2018/1/24 19:34
 */
public interface HandlerLoginService {

    Response<String> login(AccountVO accountVO);

    Response<String> handler(Account account);
}