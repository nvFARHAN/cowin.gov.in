package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CurrentAdminSession {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(unique =true)
	private Integer adminId;
	
	private String uuid;
	
	private LocalDateTime localDateTime;

	
	public CurrentAdminSession(Integer adminId, String uuid, LocalDateTime localDateTime) {
		super();
		this.adminId = adminId;
		this.uuid = uuid;
		this.localDateTime = localDateTime;
	}
}
