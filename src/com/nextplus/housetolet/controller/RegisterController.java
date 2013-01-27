package com.nextplus.housetolet.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nextplus.housetolet.domain.User;
import com.nextplus.housetolet.form.RegisterUserForm;
import com.nextplus.housetolet.repository.UserRepository;

/**
 * 注册处理
 * 
 * @author didi
 *
 * 创建时间：2013-1-8
 *
 */
@Controller
public class RegisterController extends BaseController {
	
	@RequestMapping(value = "/register",method = RequestMethod.GET) 
	public String registerForm(ModelMap modelMap) {
		modelMap.addAttribute("registerUserForm", new RegisterUserForm());
		return "register";
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.POST) 
	public String registerProcess(
			@Valid @ModelAttribute("registerUserForm") RegisterUserForm registerUserForm, 
			Errors errors) {
		// 校验
		if(errors.hasErrors()) {
			return "register";
		} 
		if(!registerUserForm.getPassword().equals(registerUserForm.getRepassword())) {
			errors.rejectValue("repassword", "repassword.mustequals");
			return "register";
		}
		if(userRepository.findByUsername(registerUserForm.getUsername()) != null) {
			errors.rejectValue("username", "username.exist");
			return "register";
		}
		if(userRepository.findByEmail(registerUserForm.getEmail()) != null) {
			errors.rejectValue("email", "email.exist");
			return "register";
		}
		// 入库
		User user = new User();
		user.setUsername(registerUserForm.getUsername());
		user.setPassword(registerUserForm.getPassword());
		user.setEmail(registerUserForm.getEmail());
		user.setElectPrice(1.0);
		user.setWaterPrice(1.0);
		user.setNetPrice(50.0);
		userRepository.save(user);
		
		log.info("新用户注册 : " + registerUserForm);
		return NEED_LOGIN;
	}
	
	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}

