package com.fulan.application.login.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
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
import com.fulan.application.context.CommenConstant;
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
		String name  = request.getParameter("name");
		if("wrong".equals(name)){
			model.addAttribute("name", name);
		}
		return "login";
	}

	@RequestMapping("/loginAccount")
	public String login(String accountName, String password, HttpSession session,HttpServletResponse response) {
		/*password = MD5Util.MD5(password);*/
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(accountName, password);
		Subject subject = SecurityUtils.getSubject();
		// subject.getSession().getAttribute("currentUser");
		try {
			subject.login(usernamePasswordToken); // 完成登录
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
		Response<List<Resource>> response = resourceService.listPIdOrIdFM("0");
		List<Resource> resourceList = response.getData();
		model.addAttribute("resourceList", resourceList);
		return "main";
	}

	@RequestMapping("/logout")
	public void logOut(HttpSession session,HttpServletResponse res,HttpServletRequest req) throws IOException {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		res.sendRedirect(req.getContextPath()+"/login");		
	}

	@RequestMapping("/main/system")
	public String system(Model model, String name, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String pId = CommenConstant.FWD_PRODUCTCENTER;
		Long id = null;
		if (CommenConstant.FWD_ELEARNING_NAME.equals(name)) {
			pId = CommenConstant.FWD_ELEARNING;
		} else if (CommenConstant.FWD_ERECRUITMENT_NAME.equals(name)) {
			pId = CommenConstant.FWD_ERECRUITMENT;
		} else if (CommenConstant.FWD_MESSAGE_NAME.equals(name)){
			pId = CommenConstant.FWD_MESSAGE;
		}
		Resource resource = new Resource();
		resource.setParentId(pId);
		List<Resource> resList = resourceService.seleByParentIdForManage(resource).getData();
		if(CollectionUtils.isNotEmpty(resList)){
			id = resList.get(0).getId();
			Long accountId = SessionContextUtils.getLoginUserId();
			List<ResourceListVO> resourceList = resourceService.listByParentIdForManage(String.valueOf(id),accountId);
			session.setAttribute("resourceList", resourceList);
			session.setAttribute("resourcePID", String.valueOf(id));
		}
		return "redirect:/main/index";
	}

	@RequestMapping("/main/index")
	public String systemIndex() {
		return "index";
	}

}