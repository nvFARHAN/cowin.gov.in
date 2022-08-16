package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.VaccineRegistration;

public interface VaccineRegistrationDao extends JpaRepository<VaccineRegistration, String> {

}
