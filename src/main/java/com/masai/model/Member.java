package com.masai.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	@Id
	private Integer memberId;

	@OneToOne(cascade = CascadeType.ALL)
	private IdCard idCard;

	@ManyToOne(cascade = CascadeType.ALL)
	private Vaccine vaccine;

	@OneToOne(cascade = CascadeType.ALL)
	private VaccineRegistration vaccineRegistration;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
	private List<Appointment> appointments;

	private boolean dose1Status;
	private boolean dose2Status;
	private LocalDate dose1Date;
	private LocalDate dose2Date;

}
