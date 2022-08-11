package com.masai.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationCenter {

	@Id
	private Integer code;
	private String centername;
	private String city;
	private String address;
	private String state;
	private String pincode;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccinationCenter")
	private List<Appointment> appointments;

	@ManyToOne(cascade = CascadeType.ALL)
	private VaccineInventory vaccineInventory;

}
