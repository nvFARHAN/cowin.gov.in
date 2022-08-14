package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	

	@PostMapping(value="/member")
	public Member saveMember(@RequestBody Member member) {
		return memberService.addMember(member);
	}
	
	@GetMapping("/member/idcardid/{idcardId}")
	public Member getMemberById(@PathVariable("idcardId") Integer idcardId) throws MemberNotFoundException
	{
		return memberService.getMemberById(idcardId);
	}
	
	@GetMapping("/member/adharno/{adharNo}")
	public Member getMemberByAdharNo(@PathVariable("adharNo") long adharNo) throws MemberNotFoundException
	{
		return memberService.getMemberByAdharNo(adharNo);
	}
	
	@GetMapping("/member/panno/{panNo}")
	public Member getMemberByPanNo(@PathVariable("panNo") String panNo) throws MemberNotFoundException
	{
		return memberService.getMemberByPanNo(panNo);
	}
	
	@PutMapping("/member/updatemember")
	public Member updateMember(@RequestBody Member member) throws MemberNotFoundException
	{
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member/delete")
	public boolean deleteMember(@RequestBody Member member) throws MemberNotFoundException
	{    
		return memberService.deleteMember(member);
	}
}
