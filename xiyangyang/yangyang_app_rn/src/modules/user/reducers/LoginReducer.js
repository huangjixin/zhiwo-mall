'use strict';

import UserContant from '../constants/UserContant'; // 导入事件类别,用来做事件类别的判断

// 初始状态
const initialState = {
    status: '点击登录'
}

// 不同类别的事件使用switch对应处理过程
const LoginReducer = (state = initialState, action)=> {
    switch (action.type) {
        case UserContant.LOGIN_IN_DOING:
            return {
                ...state,
                status: '正在登陆'
            }
            break;

        case UserContant.LOGIN_IN_DONE:
            return {
                ...state,
                status: '登陆成功'
            }
            break;

        case UserContant.LOGIN_IN_ERROR:
            return {
                ...state,
                status: '登录出错'
            }
            break;
        default:
            return state;
    }
}

export default LoginReducer;