package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.VaccinationCenter;
import com.masai.service.VaccinationCenterService;

@RestController
public class DemoController {

	@Autowired
	private VaccinationCenterService vaccinationCenterService;

	@GetMapping("/vaccination_centers")
	public ResponseEntity<List<VaccinationCenter>> getVaccineCenters() {
		return new ResponseEntity<List<VaccinationCenter>>(vaccinationCenterService.allVaccineCenters(), HttpStatus.OK);
	}

	@PostMapping("/center")
	public ResponseEntity<VaccinationCenter> addVaccineCenter(@RequestBody VaccinationCenter center) {
		return new ResponseEntity<VaccinationCenter>(vaccinationCenterService.addVaccineCenter(center),
				HttpStatus.CREATED);
	}

}
