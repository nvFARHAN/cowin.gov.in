package com.masai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.VaccinationCenter;
import com.masai.model.VaccineCount;
import com.masai.model.VaccineInventory;
import com.masai.repository.VaccineInventoryDao;

@Service
public class VaccineInventoryServiceImpl implements VaccineInventoryService{

	@Autowired
	private VaccineInventoryDao vaccineInvDao;
	
	@Autowired
	private VaccinationCenterService vaccineCenterService;
	
	
	
	
	@Override
	public VaccineInventory saveVaccineInventory(VaccineInventory vaccineInv) {
		
		
	 return vaccineInvDao.save(vaccineInv);
	}
	
	
	@Override
	public List<VaccineInventory> allVaccineInventory() {

		return vaccineInvDao.findAll();
		
	}

	@Override
	public VaccineInventory getVaccineInventoryByCenter(Integer centerid) {
		VaccinationCenter vc= vaccineCenterService.getVaccineCenter(centerid);
		return vc.getVaccineInventory();
	}

	@Override
	public VaccineInventory addVaccineCount(VaccineInventory inv ,Integer vaccineId) {
		Optional<VaccineInventory> opt= vaccineInvDao.findById(inv.getVaccineInventoryId());
		
		 VaccineInventory vacInv= opt.get();
		
		 List<VaccineCount> vcList= vacInv.getVaccineCounts();
		 
		 for (VaccineCount vaccineCount : vcList) {
			if(vaccineCount.getVaccine().getVaccineid()==vaccineId) {
				vaccineCount.setQuantity(vaccineCount.getQuantity()+1);
			}
		}
		 return vaccineInvDao.save(vacInv);
	}

	@Override
	public VaccineInventory updateVaccineInventory(VaccineInventory inv) {
		
		Optional<VaccineInventory> vacInvOpt= vaccineInvDao.findById(inv.getVaccineInventoryId());
		
		VaccineInventory vacInv= vacInvOpt.get();
		
		return vaccineInvDao.save(vacInv);
		
	}

	@Override
	public boolean deleteVaccineInventory(VaccineInventory inv) {
		
		boolean flag=false;
		
		Optional<VaccineInventory> vacInvOpt=	vaccineInvDao.findById(inv.getVaccineInventoryId());
		if(vacInvOpt.isPresent()) {
			flag= true;
			VaccineInventory vacInv= vacInvOpt.get();
			vaccineInvDao.delete(vacInv);
		}
		
		return flag;
	}

	@Override
	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate date) {
		
			return vaccineInvDao.findByDate(date);
		
	}

	@Override
	public List<VaccineInventory> getVaccineInventoryByVaccine(Integer vaccineid) {
			
		
		 List<VaccineInventory> vaccineInventoryList= vaccineInvDao.findAll();
		 List<VaccineInventory> foundedvaccineInventoryList =new ArrayList<>();
		 for (VaccineInventory vaccineInventory : vaccineInventoryList) {
			List<VaccineCount> vaccineCountList=vaccineInventory.getVaccineCounts();
			for (VaccineCount vaccineCount : vaccineCountList) {
				if(vaccineCount.getVaccine().getVaccineid()==vaccineid) {
					foundedvaccineInventoryList.add(vaccineInventory);
				}
			}
		}
		 return foundedvaccineInventoryList;
	}

	

}
