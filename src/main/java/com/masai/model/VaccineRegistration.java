package com.masai.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineRegistration {

	@Id
	private long mobileno;
	private LocalDate dateofregistration;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "vaccineRegistration")
	private Member member;
}
