package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.model.VaccineInventory;

public interface VaccineInventoryService {
	
	public VaccineInventory saveVaccineInventory(VaccineInventory vaccineInv);

	public List<VaccineInventory> allVaccineInventory();
	
	public VaccineInventory getVaccineInventoryByCenter(Integer centerid);
	
	public  VaccineInventory addVaccineCount(VaccineInventory inv,Integer vaccineId);
	
	public VaccineInventory updateVaccineInventory(VaccineInventory inv);

	public boolean deleteVaccineInventory(VaccineInventory inv);
	
	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate date);
	
	public List<VaccineInventory> getVaccineInventoryByVaccine(String vaccineName);
	
	

}
