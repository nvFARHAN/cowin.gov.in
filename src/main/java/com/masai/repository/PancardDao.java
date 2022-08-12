package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.PanCard;

public interface PancardDao extends JpaRepository<PanCard, String> {
   
	
//	public PanCard findpanBypanNo(String panNo);
}
