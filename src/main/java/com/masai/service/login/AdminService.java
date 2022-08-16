package com.masai.service.login;

import com.masai.login.module.Admin;

public interface AdminService {

	public Admin createAdmin(Admin admin);
	
	public Admin updateAdmin(Admin admin, String key);
}
