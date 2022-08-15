package com.masai.service;

import org.springframework.stereotype.Service;

import com.masai.exceptions.MemberNotFoundException;
import com.masai.model.Member;

@Service
public interface MemberService {

<<<<<<< HEAD
	public Member addMember(Member member);

	public Member getMemberById(Integer idcardid) throws MemberNotFoundException;

	public Member getMemberByAdharNo(Long adharno) throws MemberNotFoundException;

	public Member getMemberByPanNo(String panNo) throws MemberNotFoundException;

	public Member updateMember(Member member) throws MemberNotFoundException;

=======
//	public Member addMember(Member member);
	
	public Member getMemberById(int idcardid) throws MemberNotFoundException;
	
	public Member getMemberByAdharNo(long adharno) throws MemberNotFoundException;
	
	public Member getMemberByPanNo(String panNo) throws MemberNotFoundException;
	
	public Member updateMember(Member member, Integer mid) throws MemberNotFoundException;
	
>>>>>>> cb80574f9e835dae56fcdd6177bf177a6f9cae1e
	public boolean deleteMemberRecord(Member member) throws MemberNotFoundException;

	public boolean deleteMember(Member member) throws MemberNotFoundException;
<<<<<<< HEAD

	public Member updatedoseStatus(Member member) throws MemberNotFoundException;

=======
	
	public Member updatedoseStatus(Member member, Integer mid) throws MemberNotFoundException; 
	
	public Member addMemberbymobileNo(Member member, long mobileNo) throws MemberNotFoundException;
	
>>>>>>> cb80574f9e835dae56fcdd6177bf177a6f9cae1e
}
