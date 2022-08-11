package com.masai.model;

import java.time.LocalDate;
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
public class VaccineInventory {

	@Id
	private LocalDate date;

	private List<VaccineCount> vaccineCounts;

	private List<VaccinationCenter> vaccinationCenters;
}
