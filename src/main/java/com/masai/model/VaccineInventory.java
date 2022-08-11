package com.masai.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineInventory {

	@Id
	private LocalDate date;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccineInventory")
	private List<VaccineCount> vaccineCounts;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccineInventory")
	private List<VaccinationCenter> vaccinationCenters;
}
