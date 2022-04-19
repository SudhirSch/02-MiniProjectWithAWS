package com.ashok.service;

import java.util.List;
import java.util.Map;

import com.ashok.binding.LoginForm;
import com.ashok.binding.UnlockForm;
import com.ashok.binding.UserRegForm;
import com.ashok.entity.User;

public interface UserService {

	
	public String saveUser(User user);

	public List<User> getAllUser();

	//login screen related method
	public String loginCheck(LoginForm loginFrom);

	//Registration screen related methods
	public boolean emailCheck(String email);
	public Map<Integer, String> loadCountry();
	public Map<Integer, String> loadState(int cpuntryId);
	public Map<Integer, String> loadCity(int stateId);
	public String save(UserRegForm form);
	
	//unlock account screen related method
	public String unlockAcc(UnlockForm unlockForm);
	//forgotpwd screen related method
	public String forgotPwd(String email);
	
	
	
	
}
