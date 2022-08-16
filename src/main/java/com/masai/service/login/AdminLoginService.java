package com.masai.service.login;

import com.masai.login.module.AdminDTO;

public interface AdminLoginService {

	public String logIntoAccount(AdminDTO adminDTO);
	
	public String logOutAccount(String key);
}
