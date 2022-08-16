package com.masai.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdharCard {
//	@Size(max = 14, message = "Addhar card number length is maximum 12!")
//	@Pattern(regexp = "^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$", message = "Adhar card Number is Invalid!")
	
	@Digits(integer = 12,fraction = 12,message = "Length must be 12")
	private long adharNo;

}
