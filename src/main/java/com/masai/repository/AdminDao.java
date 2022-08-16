package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Admin;

public interface AdminDao extends JpaRepository<Admin, Integer>{

	public Optional<Admin> findByMobileNo(String mobileNo);
	
	
	

}
