package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Member;
import com.masai.service.MemberService;

@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;

	@PostMapping("/member")
	public Member saveMember(@RequestBody Member member) {
		return memberService.addMember(member);
	}
}
