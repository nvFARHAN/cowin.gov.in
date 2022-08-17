package com.masai.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer memberId;

	@OneToOne(cascade = CascadeType.ALL)
	private IdCard idCard;

	@ManyToOne(cascade = CascadeType.ALL)
	private Vaccine vaccine;

	@ManyToOne(cascade = CascadeType.ALL)
	private VaccineRegistration vaccineRegistration;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "member", fetch = FetchType.EAGER)
	private List<Appointment> appointments = new ArrayList<Appointment>();

	private boolean dose1Status;
	private boolean dose2Status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dose1Date;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dose2Date;

}
