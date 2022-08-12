package com.masai.service;

import org.springframework.stereotype.Service;

import com.masai.exceptions.MemberNotFoundException;
import com.masai.model.Member;

@Service
public interface MemberService {

	public Member addMember(Member member);
	
	public Member getMemberById(int idcardid) throws MemberNotFoundException;
	
	public Member getMemberByAdharNo(long adharno) throws MemberNotFoundException;
	
	public Member getMemberByPanNo(String panNo) throws MemberNotFoundException;
	
	public Member updateMember(Member member) throws MemberNotFoundException;
	
	public boolean deleteMember(Member member) throws MemberNotFoundException;
	
	
	
}
