package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.masai.model.Member;
import com.masai.model.VaccineRegistration;
import com.masai.service.VaccineRegistrationService;

@RestController
@RequestMapping("/registration")
public class VaccineRegistrationController {

	@Autowired
	VaccineRegistrationService vaccineRegistrationService;

	@GetMapping("/getlist")
	public List<VaccineRegistration> getAllVaccineRegistrationHandler() {
		return vaccineRegistrationService.allVaccineRegistration();
	}

	@GetMapping("/{mobileNo}")
	public VaccineRegistration getVaccineRegistrationByMobileNoHandler(@PathVariable("mobileNo") long mobileNo) {
		return vaccineRegistrationService.getVaccineRegistration(mobileNo);
	}

	@GetMapping("/member/{mobileNo}")
	public Member getMemberbyMobileNoHandler(@PathVariable("mobileNo") long mobileNo) {
		return vaccineRegistrationService.getAllMember(mobileNo);
	}

	@PostMapping("/add")
	public VaccineRegistration saveVaccineRegistrationHandler(@RequestBody VaccineRegistration vaccineReg) {

		return vaccineRegistrationService.addVaccineRegistration(vaccineReg);

	}

	@PostMapping("/update")
	public VaccineRegistration updateVaccineRegistrationHandler(@RequestBody VaccineRegistration vaccineReg) {
		return vaccineRegistrationService.updateVaccineRegistration(vaccineReg);
	}

	@DeleteMapping("/delete")
	public boolean deleteVaccineRegistrationhandler(@RequestBody VaccineRegistration vaccineReg) {
		return vaccineRegistrationService.deleteVaccineRegistration(vaccineReg);
	}

}
