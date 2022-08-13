package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Vaccine;

public interface VaccineDao extends JpaRepository<Vaccine, Integer> {
	
	public Vaccine findByVaccineName(String vaccineName);

}
