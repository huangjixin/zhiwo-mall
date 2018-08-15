'use strict';

import HomeConstants from "../constants/HomeConstants";
import User from "../dto/User";
import {AsyncStorage } from 'react-native';
/**
 * 黄记新（Tony）
 * 2018.07.20
 * 登录动作。
 */
const signIn = ()=>{
    return dispatch => {
        dispatch(signInIng()); // 正在执行登录请求
        setTimeout(()=>{
            let user = {
                id :'123456',
                username:'Tony',
                password:'123456',
                email:'123456@qq.com'
            };
            let u = new User(user);
            console.log(u.id);
            dispatch(signInSuccess(u)); // 登录请求完成
        }, 1000);

        
        //模拟用户登录
        // let result = fetch('https://www.baidu.com/')
        //     .then((res) => {
        //  }).catch((e) => {
        //         dispatch(signInRejected()); // 登录请求出错
        //  })
    }
}

const signInIng = ()=>{
    return {
        type: HomeConstants.SIGN_IN_DOING,
        status:'登录中……'
    }
}

/**
 * 
 * @param {登录成功} user 
 */
const signInSuccess = (user)=>{
    return {
        type: HomeConstants.SIGN_IN_DONE,
        status:'登录成功',
        user:user
    }
}

/**
 * 
 * @param {登录失败} user 
 */
const signInRejected = ()=>{
    return {
        type: HomeConstants.SIGN_IN_ERROR,
        status:'用户名或者密码不正确'
    }
}

/**
 * 退出登录
 */
const signOut = ()=>{
    return dispatch => {
        dispatch(signOutAction()); // 正在执行登录请求
    }
}

/**
 * 
 * @param {登录失败} user 
 */
const signOutAction = ()=>{
    return {
        type: HomeConstants.SIGN_OUT,
        status:'SIGN_OUT'
    }
}

/**
 * 跳转到目标对象,目前用于判断授权页跳转。
 * @param {} target 
 */
const navigateToAction = (target)=>{
    return {
        type: HomeConstants.NAVIGATE_TARGET,
        target:target
    }
}

const loadUCache= () =>
{
    AsyncStorage.getItem(HomeConstants.User_TOKEN_CACHE, function(err, result) {
        if (err) {
            let user = {username:'黄记新'};
            return {
                type: HomeConstants.LOAD_User_TOKEN_CACHE,
                userInfo:user
            }
        }
            let user = {username:'黄记新'};
            return {
                type: HomeConstants.LOAD_User_TOKEN_CACHE,
                userInfo:user
            }
        });
    // console.log('获取本地存储的用户缓存信息开始');
    // let userInfo =  '123';
    // // //获取本地存储的用户信息
    // // let userInfo= await AsyncStorage.getItem(HomeConstants.User_TOKEN_CACHE).then(response=>{
    // //     return response;
    // // }).catch(error=>{
    // //     return null;
    // // });
    // console.log('获取本地存储的用户缓存信息结束');
    // return userInfo;
}
/**
 * 加载用户缓存。
 * @param {} target 
 */
const loadUserTokenCache = ()=>{
    let user = loadUCache();
    return {
        type: HomeConstants.LOAD_User_TOKEN_CACHE,
        userInfo:user
    }
}


export default{
    signIn,signOut,navigateToAction,loadUserTokenCache
}