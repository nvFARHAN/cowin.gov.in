package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.login.module.AdminDTO;
import com.masai.login.module.UserDTO;
import com.masai.service.login.AdminLogInServiceImpl;
import com.masai.service.login.UserLogInServiceImpl;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private UserLogInServiceImpl userLogInServiceImpl;
	
	@Autowired
	private AdminLogInServiceImpl adminLogInServiceImpl;
	
	//for user login
	@PostMapping("/userlogin")
	public String logInUser(@RequestBody UserDTO userDTO) throws Exception {
		return userLogInServiceImpl.logIntoAccount(userDTO);
	}
	
	//for user logout
	@PatchMapping("/userlogout")
	public String logOutUser(@RequestParam(required=false) String key) {
		return userLogInServiceImpl.logOutAccount(key);
	}
	
	
		//for admin login
		@PostMapping("/adminlogin")
		public String logInAdmin(@RequestBody AdminDTO adminDTO) {
			return adminLogInServiceImpl.logIntoAccount(adminDTO);
		}
		
		//for admin logout
		@PatchMapping("/adminlogout")
		public String logOutAdmin(@RequestParam(required=false) String key) {
			return adminLogInServiceImpl.logOutAccount(key);
		}
}
