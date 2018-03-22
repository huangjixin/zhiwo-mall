package com.zwo.xiyangyang.modules.sys.web;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zwo.xiyangyang.modules.core.service.IBaseService;
import com.zwo.xiyangyang.modules.core.web.BaseController;
import com.zwo.xiyangyang.modules.sys.domain.User;
import com.zwo.xiyangyang.modules.sys.service.IUserService;

@Controller
public class LoginController extends BaseController<User> {
	private static final String DEFAULT_SESSION_KEY_PREFIX = "shiro:session:";
	
	@Autowired
	private IUserService userService;
	@Autowired
	private RedisTemplate redisTemplate;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected IBaseService<User> getBaseService() {
		return (IBaseService) userService;
	}
	
	@RequestMapping(value ="login",method=RequestMethod.POST)
	@ResponseBody
	String login(User user,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		 JSONObject jsonObject = new JSONObject();  
		    Subject subject = SecurityUtils.getSubject();  
		    UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());  
		    token.setRememberMe(true);
		    try {  
		        subject.login(token);  
		        jsonObject.put("token", subject.getSession().getId());  
		        jsonObject.put("msg", "登录成功");
		    } catch (IncorrectCredentialsException e) {  
		        try {
					jsonObject.put("msg", "密码错误");
//					SecurityUtils.getSubject().getSession().stop();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}  
		    } catch (LockedAccountException e) {  
		        try {
//		        	SecurityUtils.getSubject().getSession().stop();
					jsonObject.put("msg", "登录失败，该用户已被冻结");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}  
		    } catch (AuthenticationException e) {  
		        try {
//		        	SecurityUtils.getSubject().getSession().stop();
					jsonObject.put("msg", "该用户不存在");
				} catch (JSONException e1) {
					e1.printStackTrace();
				}  
		    } catch (Exception e) {  
		        e.printStackTrace();  
//		        SecurityUtils.getSubject().getSession().stop();
		    }  
//		    String key = "shiro:session:"+SecurityUtils.getSubject().getSession().getId();
//		    redisTemplate.expire(key, -1, TimeUnit.SECONDS);
//		    if(redisTemplate.opsForValue().get(key)!=null) {
//		    	
//		    }
		    
		    return jsonObject.toString(); 
	}

	@RequestMapping(value = "/unauth")  
    @ResponseBody  
    public Object unauth() {  
        Map<String, Object> map = new HashMap<String, Object>();  
        map.put("code", "1000000");  
        map.put("msg", "未登录");  
        return map;  
    }  
	
	
}
