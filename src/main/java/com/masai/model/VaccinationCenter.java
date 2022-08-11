package com.masai.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

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

	private List<Appointment> appointments;

	private VaccineInventory vaccineInventory;

}
