package com.masai.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine {

	@Id
	private Integer vaccineid;
	private String vaccineName;
	private String description;
	private VaccineCount vaccinecount;
	
}
