package com.nextplus.housetolet.controller.manage;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nextplus.housetolet.controller.BaseController;
import com.nextplus.housetolet.domain.User;

/**
 * 参数设置
 * 
 * @author didi
 *
 * 创建时间：2013-1-8
 *
 */
@Controller
public class ConfigController extends BaseController {
	
	@RequestMapping(value = "/config", method = RequestMethod.GET) 
	public String configForm(
			HttpSession session, 
			ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		user = userRepository.findOne(user.getId());
		modelMap.addAttribute("userConfig", user);
		modelMap.addAttribute(PAGE_PATH, "content/config/config");
		return  LAYOUT_PAGE;
	}
	
	@RequestMapping(value = "/config", method = RequestMethod.POST) 
	public String configUpdate(
			@Valid @ModelAttribute("userConfig") User u, 
			Errors errors, 
			HttpSession session, 
			ModelMap modelMap) {
		User sessionUser = (User) session.getAttribute("user");
		if(sessionUser == null) {
			return NEED_LOGIN;
		}
		modelMap.addAttribute(PAGE_PATH, "content/config/config");
		if(errors.hasErrors()) {
			return LAYOUT_PAGE;
		} 
		User user = userRepository.findOne(sessionUser.getId());
		user.setElectPrice(u.getElectPrice());
		user.setWaterPrice(u.getWaterPrice());
		user.setNetPrice(u.getNetPrice());
		session.setAttribute("user", userRepository.save(user));
		return LAYOUT_PAGE;
	}
	
}
