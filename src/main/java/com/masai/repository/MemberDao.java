package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.IdCard;
import com.masai.model.Member;
import com.masai.model.PanCard;
import com.masai.model.VaccineRegistration;

import antlr.collections.List;

public interface MemberDao extends JpaRepository<Member, Integer> {

	
	public Member findByIdCard(Optional<IdCard> idcard);

	


	public Member findByvaccineRegistration(VaccineRegistration vr);

}
