package com.masai.repository.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.login.module.User;

public interface UserDao extends JpaRepository<User, Integer>{

	public Optional<User> findByMobileNo(String mobileNo);
	
	//public Optional<User> findByUuid(String key);
	
}
