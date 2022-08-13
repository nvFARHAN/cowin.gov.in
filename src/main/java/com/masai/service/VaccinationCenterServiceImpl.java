package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.VaccinationCenter;
import com.masai.repository.VaccinationCenterDao;

@Service
public class VaccinationCenterServiceImpl implements VaccinationCenterService {

	@Autowired
	private VaccinationCenterDao dao;

	@Override
	public List<VaccinationCenter> allVaccineCenters() {
		List<VaccinationCenter> list = dao.findAll();
		if (list.size() == 0)
			throw new RuntimeException("No Vaccination Center Found...");
		return list;
	}

	@Override
	public VaccinationCenter getVaccineCenter(Integer centerid) {
		return dao.findById(centerid).orElseThrow(() -> new RuntimeException());
	}

	@Override
	public VaccinationCenter addVaccineCenter(VaccinationCenter center) {

		
		Optional<VaccinationCenter> vc = dao.findById(center.getCode());

		if (vc.isPresent()) {
			throw new RuntimeException("Vaccination center is present with the same Id");
		}
		return dao.save(center);
	}

	@Override
	public VaccinationCenter updateVaccineCenter(VaccinationCenter center) {

		Optional<VaccinationCenter> vc = dao.findById(center.getCode());

		if (vc.isPresent()) {
			return dao.save(center);
		} else
			throw new RuntimeException("Vaccination center is not present with the same Id");

	}

	@Override
	public boolean deleteVaccineCenter(VaccinationCenter center) {
		Optional<VaccinationCenter> vc = dao.findById(center.getCode());

		if (vc.isPresent()) {
			dao.delete(center);
			return true;
		} else
			throw new RuntimeException("Vaccination center is not present with the same Id");
	}

}
