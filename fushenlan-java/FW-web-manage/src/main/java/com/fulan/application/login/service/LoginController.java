package com.fulan.application.login.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fulan.api.security.domain.Resource;
import com.fulan.api.security.service.ResourceService;
import com.fulan.api.security.vo.ResourceListVO;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.md5.MD5Util;

/**
 * @Author: shenzhongwu
 * @Date: 2018/1/12 12:52
 */
@Controller
public class LoginController {

	@Autowired
	private ResourceService resourceService;

	@RequestMapping("/login")
	public String reLogin(Model model,HttpServletRequest request) {
		System.err.println("---------------/login---------------------");
		String name  = request.getParameter("name");
		if("wrong".equals(name)){
			model.addAttribute("name", name);
		}
		return "login";
	}

	@RequestMapping("/loginAccount")
	public String login(String accountName, String password, HttpSession session,HttpServletResponse response) {
		System.err.println("---------------/loginAccount---------------------");
		password = MD5Util.MD5(password);
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(accountName, password);
		System.err.println("---------------/loginAccount----1-----------------");
		Subject subject = SecurityUtils.getSubject();
		// subject.getSession().getAttribute("currentUser");
		try {
			System.err.println("---------------/loginAccount------2---------------");
			subject.login(usernamePasswordToken); // 完成登录
			System.err.println("---------------/loginAccount----------3-----------");
			return "redirect:main";
		} catch (Exception e) {
			e.printStackTrace();
			
			String name="wrong";
			
			
			
			/*response.setContentType("application/json; charset=utf-8");
			 PrintWriter out = null;  
			    try {  
			        out = response.getWriter();  
			        out.append("wrong");  
			    } catch (IOException d) {  
			        d.printStackTrace();  
			    } finally {  
			        if (out != null) {  
			            out.close();  
			        }  
			    }*/
			
			return "redirect:login?name="+name;// 返回登录页面
		}
	}

	// @PostMapping("/checkLogin")
	// @ResponseBody
	// public String checkLogin(String uName,String pCode){
	// UsernamePasswordToken token = new UsernamePasswordToken(uName, pCode);
	// Subject subject = SecurityUtils.getSubject();
	// subject.login(token); // 完成登录
	// return "main";
	// }

	@RequestMapping("/main")
	public String index(Model model) {
		System.err.println("---------------/main---------------------");
		Response<List<Resource>> response = resourceService.listPIdOrIdFM("0");
		List<Resource> resourceList = response.getData();
		model.addAttribute("resourceList", resourceList);
		return "/elearning/public_class/public_class2";
		//return "main";
	}

	@RequestMapping("/logout")
	public void logOut(HttpSession session,HttpServletResponse res,HttpServletRequest req) throws IOException {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		res.sendRedirect(req.getContextPath()+"/login");		
	}

	@RequestMapping("/main/system")
	public String system(Model model, String name, HttpServletRequest request) {
		String id = "2";
		if ("elearning".equals(name)) {
			id = "0";
		} else if ("erecruitment".equals(name)) {
			id = "1";
		}
		HttpSession session = request.getSession();
		Long accountId = SessionContextUtils.getLoginUserId();
		List<ResourceListVO> resourceList = resourceService.listByParentIdForManage(id,accountId);
		session.setAttribute("resourceList", resourceList);
	    session.setAttribute("resourcePID", id);
		return "redirect:/main/index";
	}

	@RequestMapping("/main/index")
	public String systemIndex() {
		return "index";
	}

}