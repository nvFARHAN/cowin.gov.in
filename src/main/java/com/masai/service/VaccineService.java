package com.masai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.masai.model.Vaccine;

@Service
public interface VaccineService {

	public List<Vaccine> allVaccine();

	public Vaccine getVaccineByName(String VaccineName);

	public Vaccine getVaccineById(Integer vaccineId);

	public Vaccine addVaccine(Vaccine vaccine);

	public Vaccine updateVaccine(Vaccine vaccine);

	public boolean deleteVaccine(Vaccine vaccine);

}
