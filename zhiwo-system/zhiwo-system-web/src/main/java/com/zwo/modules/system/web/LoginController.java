package com.zwo.modules.system.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.zwo.modules.system.domain.TbUser;
import com.zwotech.common.utils.PasswordHelper;
import com.zwotech.common.web.BaseController;

/**
 * @author Administrator
 * 登录控制器。
 */
@Controller
@Lazy(true)
public class LoginController extends BaseController<TbUser> {
	
	private static final String basePath = "views/login/";
	
	@RequestMapping(value = {"login"},method=RequestMethod.GET)
	public String login(HttpServletRequest httpServletRequest) {
		return basePath+"login";
	}
	
	@RequestMapping(value ="/login",method=RequestMethod.POST)
    public String loginForm(@ModelAttribute TbUser tbuser,Model model,HttpServletRequest request) throws Exception{
		String password = PasswordHelper.encryptPassword(tbuser.getPassword());
    	String username = tbuser.getUsername();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
        Subject currentUser = SecurityUtils.getSubject();  
        try{
        	
            if (!currentUser.isAuthenticated()){
                token.setRememberMe(true);  
                currentUser.login(token);//验证角色和权限  
            } 
        }catch(Exception ex){
//            throw new Exception("用户名或者密码错误");
        	model.addAttribute("message", "用户名或者密码错误");
            return "login";
        }
        return "/user";
    }
	
	@RequestMapping(value = "/checkLogin",method=RequestMethod.POST)
	@ResponseBody
	public String checkLogin(@ModelAttribute TbUser tbuser,HttpServletRequest httpServletRequest) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
        try{
        	String password = PasswordHelper.encryptPassword(tbuser.getPassword());
        	String username = tbuser.getUsername();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
            Subject currentUser = SecurityUtils.getSubject();  
            if (!currentUser.isAuthenticated()){
                //使用shiro来验证  
                token.setRememberMe(true);  
                currentUser.login(token);//验证角色和权限  
            } 
        }catch(Exception ex){
            throw new Exception("用户名或者密码错误");
        }
        
        result.put("success", true);
        return JSONUtils.toJSONString(result);
	}
	
}
