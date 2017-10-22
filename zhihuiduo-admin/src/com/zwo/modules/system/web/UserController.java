package com.zwo.modules.system.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zwo.modules.shop.domain.ShopWithBLOBs;
import com.zwo.modules.shop.service.IShopService;
import com.zwo.modules.system.domain.TbUser;
import com.zwo.modules.system.service.ITbUserService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("user")
@Lazy(true)
public class UserController extends BaseController<TbUser> {
	@Autowired
	@Lazy(true)
	private ITbUserService userService;
	@Autowired
	@Lazy(true)
	private IShopService shopService;

	private static final String basePath = "views/system/user/";

	@RequiresPermissions("system:user:view")
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		System.out.println("hello");
		return basePath + "user_list";
	}

	@RequiresPermissions("system:user:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid TbUser user, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("user", user);
		return basePath + "user_edit";
	}

	@RequiresPermissions("system:user:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TbUser user = userService.selectByPrimaryKey(id);

		uiModel.addAttribute("user", user);
		uiModel.addAttribute("operation", "edit");
		return basePath + "user_edit";
	}

	/**
	 * 
	 * @param id
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping(value = "changePass", method = RequestMethod.GET)
	public String toChangePass(@Valid TbUser user, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		if (SecurityUtils.getSecurityManager() != null) {
			Subject currentUser = SecurityUtils.getSubject();
			if (currentUser != null) {
				TbUser tbuser = (TbUser) currentUser.getSession().getAttribute("user");
				if (user != null) {
					user.setId(tbuser.getId());
					uiModel.addAttribute("user", user);
				}
			}
		}
		return basePath + "user_changePass";
	}

	/**
	 * 淇敼瀵嗙爜
	 * 
	 * @param id
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping(value = "changePass", method = RequestMethod.POST)
	public String changePass(@Valid TbUser tbUser, @RequestParam String newPassword, Model uiModel,
			RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser != null) {
			TbUser user = (TbUser) currentUser.getSession().getAttribute("user");
			if (user != null) {
				if (user.getPassword() != null && user.getPassword().equals(tbUser.getPassword())) {
					redirectAttributes.addAttribute("user", tbUser);
					redirectAttributes.addAttribute("message", "鐢ㄦ埛鏃у瘑鐮佷笉姝ｇ‘");
					return "redirect:/user/changePass";
				}
			}
		}
		tbUser.setPassword(newPassword);
		userService.updateByPrimaryKey(tbUser);
		redirectAttributes.addAttribute("user", tbUser);
		redirectAttributes.addAttribute("newPassword", newPassword);
		redirectAttributes.addAttribute("message", "瀵嗙爜淇敼鎴愬姛锛岃閲嶆柊閫�鍑洪噸鏂扮櫥褰�");
		return "redirect:/user/changePass";
	}

	@RequiresPermissions("system:user:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid TbUser user, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("user", user);
			redirectAttributes.addFlashAttribute("message", "鏁版嵁缁戝畾鏈夎锛�");
			return "redirect:/user/create";
		}

		int res = userService.insertSelective(user);
		if (res != 0) {
			//榛樿缁欑郴缁熺敤鎴峰垱寤轰竴涓晢閾恒��
			redirectAttributes.addFlashAttribute("user", user);
			redirectAttributes.addFlashAttribute("message", "淇濆瓨鎴愬姛锛�");
			ShopWithBLOBs record = new ShopWithBLOBs();
			record.setCategoryId(null);
			record.setUserId(user.getId());
			record.setEmail(user.getEmail());
			record.setAdminName(user.getMobilPhone());
			record.setContactTelephone(user.getMobilPhone());
			shopService.insertSelective(record);
		}

		return "redirect:/user/create";
	}

	@RequiresPermissions("system:user:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid TbUser user, BindingResult result, Model uiModel, RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("user", user);
			redirectAttributes.addFlashAttribute("message", "濉叆鐨勬暟鎹湁璇紒");
		}

		int res = this.userService.updateByPrimaryKeySelective(user);
		if (res == 1) {
			redirectAttributes.addFlashAttribute("user", user);
			redirectAttributes.addFlashAttribute("message", "淇濆瓨鎴愬姛锛�");
		}
		redirectAttributes.addAttribute("operation", "edit");
		return "redirect:/user/edit/" + user.getId();
	}
}
