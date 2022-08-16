package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.masai.model.CurrentAdminSession;

@Service
public interface AdminSessionDAO extends JpaRepository<CurrentAdminSession, Integer>{

	
	public Optional<CurrentAdminSession> findByAdminId(Integer adminId);
	
	public Optional<CurrentAdminSession> findByUuid(String uuid);
}
