package com.nextplus.housetolet.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nextplus.housetolet.domain.User;

/**
 * 1、首页展示
 * 2、登录处理
 * 3、登出处理
 * 
 * @author didi
 *
 * 创建时间：2013-1-6
 *
 */
@Controller
public class LoginController extends BaseController {
	
	/**
	 * 首页，准备登录表单
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET) 
	public String loginForm(ModelMap modelMap) {
		modelMap.addAttribute("user", new User());
		return "login";
	}
	
	/**
	 * 登录处理
	 * 
	 * @param user
	 * @param errors
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST) 
	public String loginProcess(
			@Valid @ModelAttribute("user") User loginUserForm, 
			Errors errors,
			HttpSession session) {
		if(errors.hasErrors()) {
			return "login";
		} 
		User user = userRepository.findByUsername(loginUserForm.getUsername());
		if(user == null) {
			errors.rejectValue("username", "username.invalid");
			return "login";
		} 
		if(!user.getPassword().equals(loginUserForm.getPassword())) {
			errors.rejectValue("password", "password.invalid");
			return "login";
		} 
		session.setAttribute("user", user);
		log.info("用户登录：" + loginUserForm);
		return "redirect:/index";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET) 
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return NEED_LOGIN;
	}
	
}
