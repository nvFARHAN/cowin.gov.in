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
import com.masai.repository.VaccinationCenterDao;
import com.masai.repository.VaccineCountDao;
import com.masai.repository.VaccineInventoryDao;

@Service
public class VaccineInventoryServiceImpl implements VaccineInventoryService{

	@Autowired
	private VaccineInventoryDao vaccineInvDao;
	
	@Autowired
	private VaccinationCenterService vaccineCenterService;
	
	@Autowired
	private VaccineCountDao vaccinecountdao;
	
	@Autowired
	private VaccinationCenterDao vctDao;
	
	
	@Override
	public VaccineInventory saveVaccineInventory(VaccineInventory vaccineInv) {
//		vaccineInv.setVaccinationCenters();
//		vaccineInv.setVaccineCounts(null);
	
	VaccineInventory vct= vaccineInvDao.save(vaccineInv);
	 List<VaccinationCenter> vaccineCenterList=vaccineInv.getVaccinationCenters();
	 for (VaccinationCenter vaccinationCenter : vaccineCenterList) {
		vctDao.save(vaccinationCenter);
		vaccinationCenter.setVaccineInventory(vaccineInv);
	}
	 
	 List<VaccineCount> vaccinecountList=vaccineInv.getVaccineCounts();
	 for (VaccineCount vaccineCount : vaccinecountList) {
		 vaccinecountdao.save(vaccineCount);
		 vaccineCount.setVaccineInventory(vaccineInv);
	}
	 
	 return  vaccineInvDao.save(vaccineInv);
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
		//need changes
		return vaccineInvDao.save(inv);
		
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
			
		System.out.println(vaccineid);
		 List<VaccineInventory> vaccineInventoryList= vaccineInvDao.findAll();
		 List<VaccineInventory> foundedvaccineInventoryList =new ArrayList<>();
		 for (VaccineInventory vaccineInventory : vaccineInventoryList) {
			// System.out.println("inside vci list");
			List<VaccineCount> vaccineCountList=vaccineInventory.getVaccineCounts();
			for (VaccineCount vaccineCount : vaccineCountList) {
				// System.out.println("inside vcount list");
				//	System.out.println("ID of vaccine: "+vaccineCount.getVaccine().getVaccineid());
				System.out.println(vaccineCount.getVaccine()==null);
					if(!(vaccineCount.getVaccine()==null)) {
			 		// System.out.println("what is null");
			 		if(vaccineCount.getVaccine().getVaccineid()==vaccineid) {
			 			foundedvaccineInventoryList.add(vaccineInventory);
			 		}
					//System.out.println("ID of vaccine: "+vaccineCount.getVaccine().getVaccineid());
					//foundedvaccineInventoryList.add(vaccineInventory);
				}
			}
		}
		 return foundedvaccineInventoryList;
	}

	

}
