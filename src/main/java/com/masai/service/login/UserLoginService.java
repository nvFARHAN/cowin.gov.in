package com.masai.service.login;

import java.io.FileNotFoundException;

import com.masai.login.module.UserDTO;

public interface UserLoginService {

	public String logIntoAccount(UserDTO userDTO) throws Exception;
	
	public String logOutAccount(String key);
}
