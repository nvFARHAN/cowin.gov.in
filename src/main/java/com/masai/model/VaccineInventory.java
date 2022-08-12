package com.masai.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineInventory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vaccineInventoryId;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	@NotNull(message = "Date should not be Null")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate date;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccineInventory")
	private List<VaccineCount> vaccineCounts;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vaccineInventory")
	private List<VaccinationCenter> vaccinationCenters;
}
