package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.IdCard;
import com.masai.model.VaccinationCenter;
import com.masai.model.Vaccine;
import com.masai.service.IdCardService;
import com.masai.service.VaccinationCenterService;
import com.masai.service.VaccineService;

@RestController
public class DemoController {

	@Autowired
	private VaccinationCenterService vaccinationCenterService;
	
	@Autowired
	private VaccineService vaccineserv;

	@Autowired
	private IdCardService cardService;

	@GetMapping("/vaccination_centers")
	public ResponseEntity<List<VaccinationCenter>> getVaccineCenters() {
		return new ResponseEntity<List<VaccinationCenter>>(vaccinationCenterService.allVaccineCenters(), HttpStatus.OK);
	}

	@PostMapping("/center")
	public ResponseEntity<VaccinationCenter> addVaccineCenter(@RequestBody VaccinationCenter center) {
		return new ResponseEntity<VaccinationCenter>(vaccinationCenterService.addVaccineCenter(center),
				HttpStatus.CREATED);
	}

	@PostMapping("/idcard")
	public ResponseEntity<IdCard> addIdCard(@RequestBody IdCard idCard) {
		return new ResponseEntity<IdCard>(cardService.addIdCard(idCard), HttpStatus.CREATED);
	}

	@GetMapping("/idcard/{panNo}")
	public ResponseEntity<IdCard> getIdByPanNo(@PathVariable String panNo) {
		return new ResponseEntity<IdCard>(cardService.getIdcardByPanNo(panNo), HttpStatus.FOUND);
	}

	@PostMapping("/vaccine/add")
	public ResponseEntity<Vaccine> addVaccine(@RequestBody Vaccine vaccine){
		return new ResponseEntity<Vaccine>(vaccineserv.addVaccine(vaccine), HttpStatus.ACCEPTED);		
	}
}
