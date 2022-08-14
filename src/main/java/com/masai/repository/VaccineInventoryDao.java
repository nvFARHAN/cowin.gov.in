package com.masai.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Vaccine;
import com.masai.model.VaccineInventory;


public interface VaccineInventoryDao extends JpaRepository<VaccineInventory, Integer>{

	public List<VaccineInventory> findByDate(LocalDate date);
	
	
//	@Query("select * from vaccineinventory vi where vaccinecountid IN (select vaccinecountid where vaccineid=?1)")
//	public List<VaccineInventory> getVaccineInventoryByVaccine(Integer vaccineid);

}
