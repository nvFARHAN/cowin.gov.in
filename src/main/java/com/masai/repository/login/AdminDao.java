package com.masai.repository.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.login.module.Admin;

public interface AdminDao extends JpaRepository<Admin, Integer>{

	public Optional<Admin> findByMobileNo(String mobileNo);
	
	
	

}
