package com.Havenly.Backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Packages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int packId;
	@Column
	private int subUserId;
	@Column
	private int packageTypeId;
	@Column
	private String paymentMethod;

	
}
