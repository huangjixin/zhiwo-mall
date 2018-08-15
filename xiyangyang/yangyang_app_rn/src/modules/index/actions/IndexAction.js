'use strict';

import {AsyncStorage } from 'react-native';
import IndexConstants from '../constants/IndexConstants';
import Response from '../../../core/Response';
/**
 * 黄记新（Tony）
 * 2018.07.25
 * 首页数据请求和刷新。
 */
const getIndexData = (response)=>{
    return dispatch => {
        dispatch(getIndexDataIng()); // 正在执行数据请求
        // let resp = this.response;
        setTimeout(()=>{
            let resp = new Response();
            let data1 = {
                id :'123456',
                title:'【95折限时特惠】【立即到账】移动联通电信话费充值100元',
                price:'100',
                url:'http://t00img.yangkeduo.com/goods/images/2018-07-22/937ada44d0c76df0a449c34d909c3155.jpeg'
            };
            let data2 = {
                id :'234567',
                title:'【买二送杯】【瘦 瘦 瘦】瘦到尖叫荷叶茶减肥排毒通便50g/250g',
                price:'50',
                url:'http://t00img.yangkeduo.com/t01img/images/2018-07-13/f986d78285465ed23cc8fdabb54c6759.jpeg'
            };
            
            // let dataSource = response.dataList;
 
            resp.dataList.push(data1);
            resp.dataList.push(data2);
            resp.dataList.push(data1);
            resp.dataList.push(data2);
            resp.dataList.push(data1);
            resp.dataList.push(data2);
            resp.dataList.push(data1);
            resp.dataList.push(data2);
            resp.code = '10000';
            resp.message = "成功查询到10条数据";
            // response.dataList = dataSource;
            // console.dir(resp);
            // response.page.currentPage+=1;;
            dispatch(getIndexDataSuccess(resp)); //数据请求完成
        }, 1000);

        
        //模拟用户登录
        // let result = fetch('https://www.baidu.com/')
        //     .then((res) => {
        //  }).catch((e) => {
        //         dispatch(getIndexDataRejected()); // 登录请求出错
        //  })
    }
}

// 派发正在获取数据的动作。
const getIndexDataIng = ()=>{
    return {
        type: IndexConstants.INDEX_DOING,
    }
}

/**
 * 
 * @param 
 */
const getIndexDataSuccess = (response)=>{
    return {
        type: IndexConstants.INDEX_DONE,
        response:response
    }
}

/**
 * 
 * @param {}  
 */
const getIndexDataRejected = (response)=>{
    return {
        type: IndexConstants.INDEX_ERROR,
        response:response
    }
}

export default{
    getIndexData,
}