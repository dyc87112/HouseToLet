package com.nextplus.housetolet.controller.manage;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nextplus.housetolet.controller.BaseController;
import com.nextplus.housetolet.domain.User;

/**
 * 用户管理主页
 * 
 * @author didi
 *
 * 创建时间：2013-1-8
 *
 */
@Controller
public class IndexController extends BaseController {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET) 
	public String userMainPage(HttpSession session, ModelMap modelMap) {
		User user = (User) session.getAttribute("user");
		if(user == null) {
			return NEED_LOGIN;
		}
		modelMap.addAttribute(PAGE_PATH, "content/index");
		return LAYOUT_PAGE;
	}

}
