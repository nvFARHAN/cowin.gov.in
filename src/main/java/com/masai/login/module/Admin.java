package com.masai.login.module;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
	
	@NotNull(message = "Mobile is mandatory")
	private String mobileNo;
	
	@NotNull(message = "Password is mandatory")
	private String password;
	
	@NotNull(message = "Email is mandatory")
	private String email;
}
