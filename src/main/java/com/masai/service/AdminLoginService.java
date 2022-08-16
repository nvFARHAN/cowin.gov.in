package com.masai.service;

import com.masai.model.AdminDTO;

public interface AdminLoginService {

	public String logIntoAccount(AdminDTO adminDTO);
	
	public String logOutAccount(String key);
}
