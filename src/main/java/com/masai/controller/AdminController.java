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
import com.masai.model.VaccineRegistration;
import com.masai.service.IdCardService;
import com.masai.service.MemberService;
import com.masai.service.VaccineRegistrationService;

@RestController
@RequestMapping("/admin")
public class AdminController {


	@Autowired
	private MemberService memberService;
	
	@Autowired
	private IdCardService idservice;
	
	@Autowired
	VaccineRegistrationService vaccineRegistrationService;
	
	

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
	

	
	@DeleteMapping("/member/deletememberrecord")
	public boolean deleteMemberRecod(@RequestBody Member member) throws MemberNotFoundException
	{    
		return memberService.deleteMemberRecord(member);
	}
	

	
	@PutMapping("/member/updatestatus/{mid}")
	public Member updatedosestatus(@RequestBody Member member,@PathVariable("mid") Integer mid) throws MemberNotFoundException
	{
		return memberService.updatedoseStatus(member,mid);
	}
	
	
}
