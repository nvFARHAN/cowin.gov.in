package com.masai.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.masai.enums.Slot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bookingID;
	private long mobileNo;
	private LocalDate dateofbooking;
	private boolean bookigStatus;

	Slot slot;

	@ManyToOne(cascade = CascadeType.ALL)
	Member member;

	@ManyToOne(cascade = CascadeType.ALL)
	VaccinationCenter vaccinationCenter;

}
