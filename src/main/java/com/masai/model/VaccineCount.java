package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineCount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer vaccineCountId;
	private Integer vaccineId;

	private Integer quantity;
	private Double price;

	@OneToOne(cascade = CascadeType.ALL)
	private Vaccine vaccine;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private VaccineInventory vaccineInventory;
}
