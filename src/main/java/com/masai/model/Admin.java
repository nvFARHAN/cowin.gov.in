package com.masai.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@NotNull(message = "Name is mandatory")
	private String name;
	
	@Column(unique = true)
	@Size(max = 10,min = 10)
	@NotNull(message = "Mobile is mandatory")
	private String mobileNo;
	
	@NotNull(message = "Password is mandatory")
	private String password;
	
	@Email(message="Enter your Email properly")
	@NotNull(message = "Email is mandatory")
	private String email;
}
