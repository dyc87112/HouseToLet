package com.nextplus.housetolet.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * 注册表单
 * 
 * @author didi
 *
 * 创建时间：2013-1-8
 *
 */
public class RegisterUserForm extends BaseForm {

	@Length(min = 5, max = 20, message = "{username.length}")
	private String username;
	
	@Length(min = 5, max = 20, message = "{password.length}")
	private String password;
	
	@Length(min = 5, max = 20, message = "{password.length}")
	private String repassword;
	
	@Email(message = "{email.invalid}")
	@Length(max = 200, message = "{email.length}")
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}