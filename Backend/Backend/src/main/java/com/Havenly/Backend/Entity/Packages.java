package com.Havenly.Backend.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Packages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int packageId;
	
	@Column(name = "payment")
	private String payment;

	@Column(name = "purchase_time")
	private LocalDateTime packTime;
	
	@Column(name = "purchase_date")
	private LocalDate packDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="package_type_id")
	private PackageTypes packType;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="subUser_id", referencedColumnName = "subUserId")
	private Subscription sub1;

	
//	@OneToOne(mappedBy = "package_id")
//	@JsonIgnore
//	Subscription sub;
}
