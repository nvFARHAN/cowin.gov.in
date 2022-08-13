package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.masai.service.VaccineRegistrationService;

@RestController
public class VaccineRegistrationController {

	@Autowired
	VaccineRegistrationService vaccineRegistrationService;
}
