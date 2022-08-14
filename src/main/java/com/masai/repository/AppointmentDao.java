package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Appointment;

public interface AppointmentDao extends JpaRepository<Appointment, Long> {

}
