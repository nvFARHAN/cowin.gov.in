package com.masai.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VaccinationCenter {

	@Id
	private Integer code;

	@NotBlank(message = "Center name is Mandatory")
	private String centername;

	@NotBlank(message = "City name is Mandatory")
	private String city;

	@NotBlank(message = "Address is Mandatory")
	private String address;

	@NotBlank(message = "State name is Mandatory")
	private String state;

	@NotBlank(message = "Pincode is Mandatory")
	@Size(min = 6, max = 8)
	private String pincode;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccinationCenter")
	private List<Appointment> appointments;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private VaccineInventory vaccineInventory;

}
