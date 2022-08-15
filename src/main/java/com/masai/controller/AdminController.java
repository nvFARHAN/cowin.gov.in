package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.MemberNotFoundException;
import com.masai.model.Member;
import com.masai.service.IdCardService;
import com.masai.service.MemberService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private IdCardService idservice;

//	@PutMapping("/member/updatemember")
//	public Member updateMember(@RequestBody Member member) throws MemberNotFoundException
//	{
//		return memberService.updateMember(member);
//	}

//	@DeleteMapping("/member/deletememberrecord")
//	public boolean deleteMemberRecod(@RequestBody Member member) throws MemberNotFoundException
//	{    
//		return memberService.deleteMemberRecord(member);
//	}

//	@DeleteMapping("/member/delete")
//	public boolean deleteMember(@RequestBody Member member) throws MemberNotFoundException
//	{    
//		return memberService.deleteMember(member);
//	}

	@DeleteMapping("/member/deletememberrecord")
	public boolean deleteMemberRecod(@RequestBody Member member) throws MemberNotFoundException {
		return memberService.deleteMemberRecord(member);
	}

	@PutMapping("/member/updatestatus/{mid}")
	public Member updatedosestatus(@RequestBody Member member, @PathVariable("mid") Integer mid)
			throws MemberNotFoundException {
		return memberService.updatedoseStatus(member, mid);
	}

}
