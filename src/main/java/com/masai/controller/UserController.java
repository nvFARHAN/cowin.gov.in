package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.MemberNotFoundException;
import com.masai.login.module.User;
import com.masai.model.Member;
import com.masai.service.IdCardService;
import com.masai.service.MemberService;
import com.masai.service.login.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private IdCardService idservice;
	
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	//to register user
	@PostMapping("/")
	public User saveUser(@RequestBody User user) {
		return userServiceImpl.createUser(user);
	}
	
	//to update user by passing key
	@PutMapping("/update")
	public User updateUser(@RequestBody User user, @RequestParam(required=false) String key) {
		
		return userServiceImpl.updateUser(user,key);
	}

	@PostMapping("/adddetails")
	public Member saveMember(@RequestBody Member member) {
		return memberService.addMember(member);
	}
	
	@GetMapping("/getdetails/idcardid/{idcardId}")
	public Member getMemberById(@PathVariable("idcardId") Integer idcardId) throws MemberNotFoundException
	{
		return memberService.getMemberById(idcardId);
	}
	
	@GetMapping("/getdetails/adharno/{adharNo}")
	public Member getMemberByAdharNo(@PathVariable("adharNo") long adharNo) throws MemberNotFoundException
	{
		return memberService.getMemberByAdharNo(adharNo);
	}
	
	@GetMapping("/getdetails/panno/{panNo}")
	public Member getMemberByPanNo(@PathVariable("panNo") String panNo) throws MemberNotFoundException
	{
		return memberService.getMemberByPanNo(panNo);
	}
	

}
