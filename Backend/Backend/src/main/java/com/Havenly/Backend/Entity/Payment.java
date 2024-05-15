package com.Havenly.Backend.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class Payment {

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int paymentId;
	
	private float amount;
	private float totalAmount;
}
