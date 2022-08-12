package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.IdCard;
import com.masai.model.Member;

import antlr.collections.List;

public interface MemberDao extends JpaRepository<Member, Integer> {

	
	
}
