package com.masai.service;

import java.util.List;

import com.masai.exceptions.VaccineCenterException;
import com.masai.exceptions.VaccineCenterNotFoundException;
import com.masai.model.VaccinationCenter;

public interface VaccinationCenterService {

	public List<VaccinationCenter> allVaccineCenters() throws VaccineCenterException;

	public VaccinationCenter getVaccineCenter(Integer centerid) throws VaccineCenterNotFoundException;

	public VaccinationCenter addVaccineCenter(VaccinationCenter center) throws VaccineCenterException;

	public VaccinationCenter updateVaccineCenter(VaccinationCenter center) throws VaccineCenterNotFoundException;

	public boolean deleteVaccineCenter(VaccinationCenter center) throws VaccineCenterNotFoundException;
}
