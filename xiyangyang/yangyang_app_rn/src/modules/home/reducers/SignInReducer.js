'use strict';
import HomeConstants from "../constants/HomeConstants";
// 初始状态
const initialState = {
    status: '点击登录',
    user:null,
    target:null,
    userInfo:null,
}

// 不同类别的事件使用switch对应处理过程
const SignReducer = (state=initialState, action)=> {
    switch (action.type) {
        case HomeConstants.LOAD_User_TOKEN_CACHE:
            return {
                ...state,
                userInfo:action.userInfo
            }
            break;
        case HomeConstants.NAVIGATE_TARGET:
            return {
                ...state,
                target: action.target
            }
            break;
        case HomeConstants.SIGN_IN_DOING:
            return {
                ...state,
                status: 'SIGN_IN_DOING'
            }
            break;

        case HomeConstants.SIGN_IN_DONE:
            return {
                ...state,
                status: 'SIGN_IN_DONE',
                user:action.user
            }
            break;

            case HomeConstants.SIGN_IN_ERROR:
            return {
                ...state,
                status: '登录出错'
            }
            break;
        case HomeConstants.SIGN_IN_ERROR:
            return {
                ...state,
                status: '登录出错'
            }
            break;
        
        case HomeConstants.SIGN_OUT:
            return {
                ...state,
                status: HomeConstants.SIGN_OUT
            }
            break;
        default:
            return state;
    }
}

export default SignReducer;