package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.VaccineRegistration;

public interface VaccineRegistrationDao extends JpaRepository<VaccineRegistration, Integer> {

	

	VaccineRegistration findBymobileno(long mobileno);

}
