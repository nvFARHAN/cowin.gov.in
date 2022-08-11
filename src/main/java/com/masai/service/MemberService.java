package com.masai.service;

import org.springframework.stereotype.Service;

import com.masai.model.Member;

@Service
public interface MemberService {

	public Member addMember(Member member);
}
