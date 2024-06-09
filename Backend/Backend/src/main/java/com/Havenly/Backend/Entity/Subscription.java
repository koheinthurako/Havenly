package com.Havenly.Backend.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subUserId;

	@Column(name = "nrc", nullable = false, unique = true)
	@NotBlank
	private String nrc;

	private LocalDate subStartDate;

	private LocalDateTime subStartTime;

	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reg_user_id", referencedColumnName = "register_id")
	private Reg_user reg_user;

	@JsonIgnore
	@OneToOne(mappedBy = "sub1")
	private Packages packages;
	
}
