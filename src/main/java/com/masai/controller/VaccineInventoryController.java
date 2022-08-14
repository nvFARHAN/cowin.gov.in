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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Vaccine;
import com.masai.model.VaccineInventory;
import com.masai.service.VaccineInventoryService;

@RestController
@RequestMapping("/vaccineinventory")
public class VaccineInventoryController {

	
	@Autowired
	private VaccineInventoryService vaccineInvService;
	
	@PostMapping("/save")
	public VaccineInventory saveVaccineHandler(@RequestBody VaccineInventory vaccineInv) {
		
		return vaccineInvService.saveVaccineInventory(vaccineInv);
		//return vaccineInv;
	}
	
	
	@GetMapping("/getlist")
	public List<VaccineInventory> getAllInventoryHandler(){
		return vaccineInvService.allVaccineInventory();
	}
	
	@GetMapping("/center/{centerid}")
	public VaccineInventory getVaccineInventoryByCenterHandler(@PathVariable ("centerid") Integer centerid) {
		
		return vaccineInvService.getVaccineInventoryByCenter(centerid);
		
	}
	
	@PutMapping("/add/{vaccineId}")
	public VaccineInventory addVaccineCountHandler(@RequestBody VaccineInventory inv ,@PathVariable
			("vaccineId") Integer vaccineId) {
		
		return vaccineInvService.addVaccineCount(inv, vaccineId);
	}
	
	@PutMapping("/update")
	public VaccineInventory updateVaccineInventoryHandler(@RequestBody VaccineInventory vaccineInventory) {
		
		return vaccineInvService.updateVaccineInventory(vaccineInventory);
	}
	
	
	@DeleteMapping("/delete")
	public boolean deleteVaccineInventory(@RequestBody VaccineInventory inv) {
		return vaccineInvService.deleteVaccineInventory(inv);
	}
	
	@GetMapping("/getlist/{date}")
	public List<VaccineInventory> getVaccineInventoryByDate(@PathVariable ("date") @DateTimeFormat (pattern = "dd-MM-yyyy") LocalDate date){

		return vaccineInvService.getVaccineInventoryByDate(date);
	}
	
	@GetMapping("/vaccine/{vaccineName}")
	public List<VaccineInventory> getVaccineInventoryByVaccine(@PathVariable("vaccineName") String vaccineName){
		
		return vaccineInvService.getVaccineInventoryByVaccine(vaccineName);
	}
	
}
