package com.soumya.service;

import com.soumya.binding.LoginForm;
import com.soumya.binding.SignUpForm;
import com.soumya.binding.UnlockForm;



public interface UserService {
	
	public String login(LoginForm form); 
	
	public boolean signup(SignUpForm form);
	
	public boolean unlockAccount(UnlockForm form);
	
	public boolean forgotPwd(String email);
	
	

}
