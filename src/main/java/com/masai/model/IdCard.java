package com.masai.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdCard {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer id;

	private LocalDate dob;

	private String gender;

	private String address;

	private String city;

	private String state;

	private String pincode;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "idCard")
	private Member member;

//	@OneToOne(mappedBy = "idcard")
	@Embedded
	AdharCard adharcard;

//	@OneToOne(mappedBy = "idcard")
	@Embedded
	PanCard pancard;

}
