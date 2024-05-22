package com.Havenly.Backend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private String email;
	private String password;

}
