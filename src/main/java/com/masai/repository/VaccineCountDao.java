package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Vaccine;
import com.masai.model.VaccineCount;
@Repository
public interface VaccineCountDao extends JpaRepository<VaccineCount, Integer>{

	VaccineCount findByvaccine(Vaccine vaccine);

	VaccineCount findByvaccineId(Integer vaccineid);

	

	
}
