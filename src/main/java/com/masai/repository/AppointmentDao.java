package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Appointment;
import com.masai.model.Member;

public interface AppointmentDao extends JpaRepository<Appointment, Long> {

	List<Appointment> findByMember(Member member);

}
