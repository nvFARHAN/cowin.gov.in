package com.masai.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	
	@NotNull(message = "Center name may not be null")
	@NotBlank(message = "Center name is Mandatory")
	@Pattern(regexp = "^[A-Z][a-z]*")
	private String centername;
	
	@NotNull(message = "City may not be null")
	@NotBlank(message = "City name is Mandatory")
	@Pattern(regexp = "^[A-Z][a-z]*")
	private String city;
	
	@NotNull(message = "Address may not be null")
	@NotBlank(message = "Address is Mandatory")
	private String address;
	
	@NotNull(message = "State may not be null")
	@NotBlank(message = "State name is Mandatory")
	@Pattern(regexp = "^[A-Z][a-z]*")
	private String state;
	
	@NotNull(message = "Pincode may not be null")
	@NotBlank(message = "Pincode is Mandatory")
	@Size(min = 6, max = 8)
	private String pincode;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccinationCenter")
	private List<Appointment> appointments;

	@ManyToOne(cascade = CascadeType.ALL)
	private VaccineInventory vaccineInventory;

}
