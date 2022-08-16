package com.masai.service;

import org.springframework.stereotype.Service;

import com.masai.exceptions.MemberNotFoundException;
import com.masai.model.Member;

@Service
public interface MemberService {

//	public Member addMember(Member member);

	public Member getMemberById(Integer idcardid) throws MemberNotFoundException;

	public Member getMemberByAdharNo(Long adharno) throws MemberNotFoundException;

	public Member getMemberByPanNo(String panNo) throws MemberNotFoundException;

	public Member updateMember(Member member, Integer mid) throws MemberNotFoundException;

	public boolean deleteMemberRecord(Member member) throws MemberNotFoundException;

	public boolean deleteMember(Integer mid) throws MemberNotFoundException;

	public Member updatedoseStatus(Member member, Integer mid) throws MemberNotFoundException;

	public Member addMemberbyMobileNo(Member member, String mobileNo) throws MemberNotFoundException;

}