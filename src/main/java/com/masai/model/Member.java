package com.masai.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	@Id
	private IdCard idCard;

	private Vaccine vaccine;

	private VaccineRegistration vaccineRegistration;

	private List<Appointment> appointments;

	private boolean dose1Status;
	private boolean dose2Status;
	private LocalDate dose1Date;
	private LocalDate dose2Date;

}
