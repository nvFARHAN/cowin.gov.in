package com.masai.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
public class IdCard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "idCard")
	private Member member;

	@Pattern(regexp = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$")
	private LocalDate dob;

	@NotBlank(message = "Gender is Mandatory")
	@NotNull(message = "Gender may not be null")
	@Pattern(regexp = "^[A-Z][a-z]*")
	private String gender;

	@NotNull(message = "Address may not be null")
	@NotBlank(message = "Address is Mandatory")
	private String address;

	@NotNull(message = "City may not be null")
	@NotBlank(message = "City name is Mandatory")
	@Pattern(regexp = "^[A-Z][a-z]*")
	private String city;

	@NotNull(message = "State may not be null")
	@NotBlank(message = "State name is Mandatory")
	@Pattern(regexp = "^[A-Z][a-z]*")
	private String state;

	@NotNull(message = "Pincode may not be null")
	@NotBlank(message = "Pincode is Mandatory")
	@Size(min = 6, max = 8)
	private String pincode;

	@OneToOne(mappedBy = "idcard")
	AdharCard adharcard;
	
	@OneToOne(mappedBy = "idcard")
	PanCard pancard;
}
