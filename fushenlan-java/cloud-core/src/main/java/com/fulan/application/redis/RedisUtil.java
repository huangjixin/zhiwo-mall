package com.fulan.application.redis;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.fulan.application.session.SessionUtil;
import com.fulan.application.token.TokenHelper;
import com.fulan.application.util.constant.GlobalConstant;
import com.fulan.core.monitoring.cat.constant.Constant;

import io.jsonwebtoken.Claims;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil{
@SuppressWarnings("rawtypes")
@Autowired
private RedisTemplate redisTemplate;

@Autowired
private TokenHelper tokenHelper;

/**
* 批量删除对应的value
*
* @param keys
*/
public void remove(final String... keys) {
	for (String key : keys) {
	remove(key);
}
}

/**
* 批量删除key
*
* @param pattern
*/
@SuppressWarnings("unchecked")
public void removePattern(final String pattern) {
	Set<Serializable> keys = redisTemplate.keys(pattern);
	if (keys.size() > 0)
	redisTemplate.delete(keys);
}

/**
* 删除对应的value
*
* @param key
*/
@SuppressWarnings("unchecked")
public void remove(final String key) {
	if (exists(key)) {
	redisTemplate.delete(key);
	}
}

/**
* 判断缓存中是否有对应的value
*
* @param key
* @return
*/
@SuppressWarnings("unchecked")
public boolean exists(final String key) {
	return redisTemplate.hasKey(key);
}

/**
* 读取缓存
*
* @param key
* @return
*/
@SuppressWarnings("unchecked")
public Object get(final String key) {
	Object result = null;
	ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
	result = operations.get(key);
	return result;
}

/**
* 写入缓存
*
* @param key
* @param value
* @return
*/
@SuppressWarnings("unchecked")
public boolean set(final String key, Object value) {
	boolean result = false;
	try {
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		operations.set(key, value);
		result = true;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

/**
* 写入缓存
*
* @param key
* @param value
* @return
*/
@SuppressWarnings("unchecked")
public boolean set(final String key, Object value, Long expireTime) {
	boolean result = false;
	try {
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		operations.set(key, value);
		redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
		result = true;
	} catch (Exception e) {
		e.printStackTrace();
	}
		return result;
	}

	@SuppressWarnings("unchecked")
	public boolean set(final String key, Object value, Long expireTime,TimeUnit timeUnit) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, timeUnit);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


/**
* 获取用户id
*
* @return
*/
@SuppressWarnings("unchecked")
public Object getUserId() {
	Object result = null ;
	try {
		Object token =  SessionUtil.getHeader(GlobalConstant.TOKEN_HEADER);
		Claims claims = tokenHelper.parseToken(token.toString());
		Object key = claims.get(GlobalConstant.USER_ID);
		result = key;
	} catch (Exception e) {
		e.printStackTrace();
	}
		return result;
	}

@SuppressWarnings("unchecked")
public Object getUserInfo() {
	Object result = null;
	try {
		Object token =  SessionUtil.getHeader(GlobalConstant.TOKEN_HEADER);
		System.out.println("------token:-------"+token+"-------------------");
		Claims claims = tokenHelper.parseToken(token.toString());
		System.out.println("------claims:-------"+claims+"-------------------");
		Object key = claims.get(GlobalConstant.USER_ID);
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(Constant.LOGIN_ACCOUNT + key);
	} catch (Exception e) {
		e.printStackTrace();
	}
		return result;
	}

}
