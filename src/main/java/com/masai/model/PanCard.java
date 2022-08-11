package com.masai.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PanCard {
	
	@Size(max=10,message="Pan card number length is maximum 10!")
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}",message="Pan Card Number is Invalid!")
    private String panoNo;

}