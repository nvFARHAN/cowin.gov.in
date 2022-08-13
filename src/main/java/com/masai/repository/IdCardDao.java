package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.AdharCard;
import com.masai.model.IdCard;
import com.masai.model.PanCard;

public interface IdCardDao extends JpaRepository<IdCard, Integer> {

	public IdCard findByPancard(PanCard pancard);

	public IdCard findByAdharcard(AdharCard adharcard);

	

	

	

}
