package com.masai.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VaccineInventory {

	@Id
	private Integer vaccineInventoryId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@NotNull(message = "Date should not be Null")
	private LocalDate date;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccineInventory")
	private List<VaccineCount> vaccineCounts;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccineInventory")
	private List<VaccinationCenter> vaccinationCenters;
}
