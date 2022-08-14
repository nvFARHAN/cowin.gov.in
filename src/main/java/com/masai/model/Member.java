package com.masai.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@OneToOne(cascade = CascadeType.ALL)
	private VaccineRegistration vaccineRegistration;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "member",fetch = FetchType.EAGER)
	private List<Appointment> appointments=new ArrayList<Appointment>();

	private boolean dose1Status;
	private boolean dose2Status;
	

    @JsonFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dose1Date;
    

    @JsonFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dose2Date;

}
