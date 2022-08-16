package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Vaccine;

@Repository
public interface VaccineDao extends JpaRepository<Vaccine, Integer> {
	
	public Vaccine findByvaccineName(String vaccineName);

}
