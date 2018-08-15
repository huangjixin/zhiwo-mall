import {AsyncStorage } from 'react-native';

/**
 * 黄记新（Tony） 2018.07.20
 * 该方法可以设置缓存。
 * @param {*} key 
 * @param {*} value 
 */
const  setCache = async  (key,value)=>{
    if(key==null || key == undefined|| value == undefined|| value == undefined){
        return;
    }
    await AsyncStorage.setItem(key, value);
}

/**
 * 黄记新（Tony） 2018.07.20
 * 该方法可以根据key取缓存。
 * @param {*} key 
 * @param {*} value 
 */
const getCache = async (key)=>{
    if(key==null || key == undefined){
        return null;
    }
    const cacheResult = await AsyncStorage.getItem(key);
    return cacheResult;
}

/**
 * 黄记新（Tony） 2018.07.20
 * 该方法可以根据key取清除缓存。
 * @param {*} key 
 */
const clearCache = async (key)=>{
    if(key==null || key == undefined){
        return null;
    }
    const cacheResult = await AsyncStorage.getItem(key);
    if(cacheResult){
        setCache(key,null);
    }
    return cacheResult;
}

export default{
    setCache,getCache,clearCache
}