package com.Havenly.Backend.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
public class Reg_user {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int register_id;
	
	@Column(name = "name",length = 40,nullable = false	)
	@NotBlank
	private String name;
	
	@Column(name = "phone",length = 11,nullable = false)
	@NotBlank
	private String phone;
	
	@Column(name = "email",nullable = false,unique = true)
	@Email(message = "Invalid Email Format")
	@NotBlank
	private String email;
	
	@Column(name = "password",nullable = false)
	@NotBlank
	@NotEmpty
	private String password;
	
	private LocalDate date;
	private LocalTime time;
	
	@OneToOne(mappedBy = "reg_user",cascade = CascadeType.ALL,fetch = FetchType.EAGER )
	private PasswordResetToken passwordResetToken;
	
	
	
	

}
