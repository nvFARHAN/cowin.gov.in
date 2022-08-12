package com.masai.service;

import java.util.List;

import com.masai.model.VaccinationCenter;

public interface VaccinationCenterService {

	public List<VaccinationCenter> allVaccineCenters();

	public VaccinationCenter getVaccineCenter(Integer centerid);

	public VaccinationCenter addVaccineCenter(VaccinationCenter center);

	public VaccinationCenter updateVaccineCenter(VaccinationCenter center);

	public boolean deleteVaccineCenter(VaccinationCenter center);
}
