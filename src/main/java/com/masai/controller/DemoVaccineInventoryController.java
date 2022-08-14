package com.masai.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Vaccine;
import com.masai.model.VaccineInventory;
import com.masai.service.VaccineInventoryService;

@RestController
public class DemoVaccineInventoryController {

	
	@Autowired
	private VaccineInventoryService vaccineInvService;
	
	@PostMapping("/vaccineinv")
	public VaccineInventory saveVaccineHandler(@RequestBody VaccineInventory vaccineInv) {
		
		return vaccineInvService.saveVaccineInventory(vaccineInv);
		//return vaccineInv;
	}
	
	
	@GetMapping("/vaccine")
	public List<VaccineInventory> getAllInventoryHandler(){
		return vaccineInvService.allVaccineInventory();
	}
	
	@GetMapping("/getvaccine/{centerid}")
	public VaccineInventory getVaccineInventoryByCenterHandler(@PathVariable ("centerid") Integer centerid) {
		
		return vaccineInvService.getVaccineInventoryByCenter(centerid);
		
	}
	
	@PutMapping("/vaccineadd/{vaccineId}")
	public VaccineInventory addVaccineCountHandler(@RequestBody VaccineInventory inv ,@PathVariable
			("vaccineId") Integer vaccineId) {
		
		return vaccineInvService.addVaccineCount(inv, vaccineId);
	}
	
	@PutMapping("/vaccine")
	public VaccineInventory updateVaccineInventoryHandler(@RequestBody VaccineInventory vaccineInventory) {
		
		return vaccineInvService.updateVaccineInventory(vaccineInventory);
	}
	
	
	@DeleteMapping("/vaccine")
	public boolean deleteVaccineInventory(@RequestBody VaccineInventory inv) {
		return vaccineInvService.deleteVaccineInventory(inv);
	}
	
	@GetMapping("/vaccines/{date}")
	public List<VaccineInventory> getVaccineInventoryByDate(@PathVariable ("date") @DateTimeFormat (pattern = "dd-MM-yyyy") LocalDate date){
//		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//		LocalDate parsedDate = LocalDate.parse(date, formatter);
		return vaccineInvService.getVaccineInventoryByDate(date);
	}
	
	@GetMapping("/vaccineinventory/{vaccineid}")
	public List<VaccineInventory> getVaccineInventoryByVaccine(@PathVariable("vaccineid") Integer vaccineid){
		
		return vaccineInvService.getVaccineInventoryByVaccine(vaccineid);
	}
	
}
