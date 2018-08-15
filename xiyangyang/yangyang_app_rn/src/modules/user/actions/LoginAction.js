'use strict';

import UserContant from '../constants/UserContant'; // 导入事件类型,用来做分配给各个事件

// 模拟用户信息
let user = {
    name: '黄记新',
    age: 24,
}


// 访问登录接口 根据返回结果来划分action属于哪个type,然后返回对象,给reducer处理
const login= () => {
    return dispatch => {
        dispatch(isLogining()); // 正在执行登录请求
        dispatch(loginSuccess(true)); 
        // 模拟用户登录
        let result = fetch('https://www.baidu.com/')
            .then((res) => {
                dispatch(loginSuccess(true)); // 登录请求完成
            }).catch((e) => {
                dispatch(loginError(false)); // 登录请求出错
            })
    }

}

// 正在登录。
const isLogining = ()=> {
    return {
        type: UserContant.LOGIN_IN_DOING
    }
}

// 登录成功。
const loginSuccess=(isSuccess)=> {
    return {
        type: UserContant.LOGIN_IN_DONE,
        status:'登录成功'
    }
}

// 登录错误。
const loginError=(isSuccess)=> {
    return {
        type: UserContant.LOGIN_IN_ERROR,
    }
}

export default {
    login
}