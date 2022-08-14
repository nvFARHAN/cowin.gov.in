package com.masai.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	
<<<<<<< HEAD
//	@NotBlank(message = "Mobile Number is Mandatory")
//	@Size(max=10,message="Moblie Number length should be 10!")
//	@Pattern(regexp = "^[7-9][0-9]{9}$",message="Mobile No is Invalid!")
=======

	@Digits(integer=10,fraction =0,message="moblie maximum size 10")
>>>>>>> 69c1eaa2c5258745a92a442dc5af299f74e5ee6d
	private long mobileNo;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
	private LocalDate dateofbooking;
	
	private boolean bookigStatus;

    Slot slot;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	Member member;

	@ManyToOne(cascade = CascadeType.ALL)
	VaccinationCenter vaccinationCenter;

}
