package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineCount {

	private Integer quantity;
	private Double price;

	@Id
	private Vaccine vaccine;

	private VaccineInventory vaccineInventory;
}
