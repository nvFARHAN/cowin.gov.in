package com.masai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.VaccineInventoryNotFoundException;
import com.masai.exceptions.VaccineNotFoundException;
import com.masai.model.CurrentAdminSession;
import com.masai.model.VaccinationCenter;
import com.masai.model.VaccineCount;
import com.masai.model.VaccineInventory;
import com.masai.repository.AdminSessionDAO;
import com.masai.repository.VaccinationCenterDao;
import com.masai.repository.VaccineCountDao;
import com.masai.repository.VaccineInventoryDao;

@Service
public class VaccineInventoryServiceImpl implements VaccineInventoryService {

	@Autowired
	private VaccineInventoryDao vaccineInvDao;

	@Autowired
	private VaccinationCenterService vaccineCenterService;

	@Autowired
	private VaccineCountDao vaccinecountdao;

	@Autowired
	private VaccinationCenterDao vctDao;
	
	@Autowired
	private AdminSessionDAO adminSessionDAO;

	@Override
	public VaccineInventory saveVaccineInventory(VaccineInventory vaccineInv, String key) {
		
		 Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
			
			if(!optCurrAdmin.isPresent()) {
				
				throw new RuntimeException("Unauthorised access");
			}
			
		Optional<VaccineInventory> opt = vaccineInvDao.findById(vaccineInv.getVaccineInventoryId());
		if (opt.isPresent()) {
			throw new VaccineInventoryNotFoundException("VaccineInventory already exists!");
		}

//	 VaccineInventory vct= vaccineInvDao.save(vaccineInv);

		List<VaccinationCenter> vaccineCenterList = vaccineInv.getVaccinationCenters();
		for (VaccinationCenter vaccinationCenter : vaccineCenterList) {
			vaccinationCenter.setVaccineInventory(vaccineInv);
//		vctDao.save(vaccinationCenter);
		}

		List<VaccineCount> vaccinecountList = vaccineInv.getVaccineCounts();
		for (VaccineCount vaccineCount : vaccinecountList) {
			vaccineCount.setVaccineInventory(vaccineInv);
//		 vaccinecountdao.save(vaccineCount);
		}
		return vaccineInvDao.save(vaccineInv);

	}

	@Override
	public List<VaccineInventory> allVaccineInventory(String key) {

		Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
		
		if(!optCurrAdmin.isPresent()) {
			
			throw new RuntimeException("Unauthorised access");
		}
		List<VaccineInventory> vaccineInventoryList = vaccineInvDao.findAll();
		if (vaccineInventoryList.size() > 0) {
			return vaccineInventoryList;
		}
		throw new VaccineInventoryNotFoundException("List empty, need to add Inventory first!");

	}

	@Override
	public VaccineInventory getVaccineInventoryByCenter(Integer centerid,String key) {

		Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
		
		if(!optCurrAdmin.isPresent()) {
			
			throw new RuntimeException("Unauthorised access");
		}
		
		VaccinationCenter vc = vaccineCenterService.getVaccineCenter(centerid,key);
		if (vc == null) {
			throw new VaccineInventoryNotFoundException("Vaccine Inventory not found!");
		}
		return vc.getVaccineInventory();
	}

	@Override
	public VaccineInventory addVaccineCount(VaccineInventory inv, Integer vaccineId,String key) {
		Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
		
		if(!optCurrAdmin.isPresent()) {
			
			throw new RuntimeException("Unauthorised access");
		}
		Optional<VaccineInventory> opt = vaccineInvDao.findById(inv.getVaccineInventoryId());

		if (opt.isPresent()) {
			VaccineInventory vacInv = opt.get();

			List<VaccineCount> vcList = vacInv.getVaccineCounts();
			int count = 0;
			for (VaccineCount vaccineCount : vcList) {
				if (vaccineCount.getVaccine() != null) {
					if (vaccineCount.getVaccine().getVaccineid() == vaccineId) {
						count++;
						vaccineCount.setQuantity(vaccineCount.getQuantity() + 1);
					}
				}
			}
			if (count == 0) {
				throw new VaccineNotFoundException("Vaccine not found by id: " + vaccineId);
			}
			return vaccineInvDao.save(vacInv);
		}
		throw new VaccineInventoryNotFoundException("Vaccine Inventory not found!");

	}

	
	@Override
	public VaccineInventory updateVaccineInventory(VaccineInventory vaccineInv,String key) {
		
		Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
		
		if(!optCurrAdmin.isPresent()) {
			
			throw new RuntimeException("Unauthorised access");
		}
		
		Optional<VaccineInventory> vacInvOpt = vaccineInvDao.findById(vaccineInv.getVaccineInventoryId());
		if (vacInvOpt.isPresent()) {

			// saving vaccine count and centers
			List<VaccinationCenter> vaccineCenterList = vaccineInv.getVaccinationCenters();
			for (VaccinationCenter vaccinationCenter : vaccineCenterList) {
				vctDao.save(vaccinationCenter);
				vaccinationCenter.setVaccineInventory(vaccineInv);
			}

			List<VaccineCount> vaccinecountList = vaccineInv.getVaccineCounts();
			for (VaccineCount vaccineCount : vaccinecountList) {
				vaccinecountdao.save(vaccineCount);
				vaccineCount.setVaccineInventory(vaccineInv);
			}
			// till here
			return vaccineInvDao.save(vaccineInv);
		}
		throw new VaccineInventoryNotFoundException("Vaccine Inventory not found!");

	}

	@Override
	public boolean deleteVaccineInventory(VaccineInventory inv,String key) {


		Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
		
		if(!optCurrAdmin.isPresent()) {
			
			throw new RuntimeException("Unauthorised access");
		}
		
		boolean flag = false;

		Optional<VaccineInventory> vacInvOpt = vaccineInvDao.findById(inv.getVaccineInventoryId());
		if (vacInvOpt.isPresent()) {
			flag = true;
			VaccineInventory vacInv = vacInvOpt.get();
			vaccineInvDao.delete(vacInv);
			return flag;
		}
		throw new VaccineInventoryNotFoundException("Vaccine Inventory not found!");

	}

	
	@Override
	public List<VaccineInventory> getVaccineInventoryByDate(LocalDate date,String key) {

		Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
		
		if(!optCurrAdmin.isPresent()) {
			
			throw new RuntimeException("Unauthorised access");
		}
		
		List<VaccineInventory> vacInvList = vaccineInvDao.findByDate(date);
		if (vacInvList.size() > 0) {
			return vacInvList;
		}
		throw new VaccineInventoryNotFoundException("No Vaccine Inventory found!");
	}

	@Override
	public List<VaccineInventory> getVaccineInventoryByVaccine(String vaccineName,String key) {

		Optional<CurrentAdminSession> optCurrAdmin= adminSessionDAO.findByUuid(key);
		
		if(!optCurrAdmin.isPresent()) {
			
			throw new RuntimeException("Unauthorised access");
		}
		
		List<VaccineInventory> vaccineInventoryList = vaccineInvDao.findAll();
		if (vaccineInventoryList.size() == 0) {
			throw new VaccineInventoryNotFoundException("List empty, need to add Inventory first!");
		}
		List<VaccineInventory> foundedvaccineInventoryList = new ArrayList<>();
		int count = 0;
		for (VaccineInventory vaccineInventory : vaccineInventoryList) {

			List<VaccineCount> vaccineCountList = vaccineInventory.getVaccineCounts();
			if (vaccineCountList.size() == 0) {
				throw new VaccineInventoryNotFoundException("List empty, need to add VaccineCount first!");
			}

			for (VaccineCount vaccineCount : vaccineCountList) {

				if (!(vaccineCount.getVaccine() == null)) {
					if (vaccineCount.getVaccine().getVaccineName().equalsIgnoreCase(vaccineName)) {
						foundedvaccineInventoryList.add(vaccineInventory);
						count++;
					}
				}
			}
		}
		if (count == 0) {
			throw new VaccineNotFoundException("Vaccine not found by name: " + vaccineName);
		}

		return foundedvaccineInventoryList;
	}

}
