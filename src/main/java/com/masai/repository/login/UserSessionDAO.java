package com.masai.repository.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.masai.login.module.CurrentUserSession;

@Service
public interface UserSessionDAO extends JpaRepository<CurrentUserSession, Integer>{

	public Optional<CurrentUserSession> findByUserId(Integer userId);
	
	public Optional<CurrentUserSession> findByUuid(String uuid);
}
