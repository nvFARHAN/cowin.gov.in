package com.masai.service.login;

import com.masai.login.module.User;

public interface UserService {

	public User createUser(User user);
	
	public User updateUser(User user, String key);
}
