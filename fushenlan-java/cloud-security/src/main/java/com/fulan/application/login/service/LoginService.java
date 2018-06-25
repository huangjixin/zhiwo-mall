package com.fulan.application.login.service;

import com.fulan.api.security.vo.AccountVO;
import com.fulan.application.util.domain.Response;

/**
 * @Description: 登录业务层接口
 * @author: guiyang
 * @date: 2018/1/24 19:13
 */
public interface LoginService {
    Response<String> login(AccountVO accountVO);
}