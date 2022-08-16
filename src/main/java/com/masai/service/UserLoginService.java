package com.masai.service;

import com.masai.model.UserDTO;

public interface UserLoginService {

	public String logIntoAccount(UserDTO userDTO) throws Exception;
	
	public String logOutAccount(String key);
}
