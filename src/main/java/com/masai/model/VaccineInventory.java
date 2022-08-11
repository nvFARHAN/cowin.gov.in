package com.masai.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineInventory {

	@Id
	@Pattern(regexp = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$",message="Invalid formate of Date dd-mm-yyyy")
	private LocalDate date;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccineInventory")
	private List<VaccineCount> vaccineCounts;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccineInventory")
	private List<VaccinationCenter> vaccinationCenters;
}
