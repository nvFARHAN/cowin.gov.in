package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.AdharCard;


public interface AdharcardDao extends JpaRepository<AdharCard, Integer> {
	
	public AdharCard findAdharcardByadharNo(long adharNo);

}
