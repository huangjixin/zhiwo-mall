package com.zwo.xiyangyang.modules.shiro;
import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.crazycake.shiro.ObjectSerializer;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSerializer;
import org.crazycake.shiro.RedisSessionDAO;
import org.crazycake.shiro.SerializationException;
import org.crazycake.shiro.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;  
  
/** 
 * Created by Administrator on 2017/12/11. 
 * 自定义sessionId获取 
 */  
public class StatelessSessionManager extends DefaultWebSessionManager{  
  
	@Autowired
	private RedisSessionDAO redisSessionDAO;
	
	@Autowired
	private RedisManager redisManager;

	private RedisSerializer keySerializer = new StringSerializer();
	private RedisSerializer valueSerializer = new ObjectSerializer();
	
	
    private static final String AUTHORIZATION = "Authorization";  
  
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";  
  
    public StatelessSessionManager() {  
        super();  
    }  
  
    @Override  
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {  
        String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);  
        //如果请求头中有 Authorization 则其值为sessionId  
        if (!StringUtils.isEmpty(id)) {  
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);  
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);  
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);  
            return id;  
        } else {  
            //否则按默认规则从cookie取sessionId  
            return super.getSessionId(request, response);  
        }  
    }  
    

    /*@Override
    protected void onInvalidation(Session session, InvalidSessionException ise, SessionKey key) {
    	System.out.println(key.toString()+","+session.getId());
    	try {
    		byte[] bs = redisManager.get(keySerializer.serialize(session.getId()));
    		if(bs!=null) {
    			return;
    		}
		} catch (SerializationException e) {
			e.printStackTrace();
		}
    	Serializable sId = session.getId();
    	redisSessionDAO.readSession(sId);
    	
        super.onInvalidation(session, ise, key);
    }
    

    @Override
    protected void onExpiration(Session s, ExpiredSessionException ese, SessionKey key) {
    	System.out.println(key.toString()+","+s.getId());
        super.onExpiration(s, ese, key);
    }*/
} 