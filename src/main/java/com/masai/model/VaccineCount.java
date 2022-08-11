package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineCount {

	@Id
	private Integer vaccineCountId;
	private Integer quantity;
	private Double price;

	@OneToOne(cascade = CascadeType.ALL)
	private Vaccine vaccine;

	@ManyToOne(cascade = CascadeType.ALL)
	private VaccineInventory vaccineInventory;
}
