package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.model.VaccineInventory;

public interface VaccineInventoryService {
	
	public VaccineInventory saveVaccineInventory(VaccineInventory vaccineInv, String key);

	public List<VaccineInventory> allVaccineInventory(String key);
	
	public VaccineInventory getVaccineInventoryByCenter(Integer centerid,String key);
	
	public  VaccineInventory addVaccineCount(VaccineInventory inv,Integer vaccineId,String key);
	
	public VaccineInventory updateVaccineInventory(VaccineInventory inv,String key);

	public boolean deleteVaccineInventory(VaccineInventory inv,String key);
	
	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate date,String key);
	
	public List<VaccineInventory> getVaccineInventoryByVaccine(String vaccineName,String key);
	
	

}
