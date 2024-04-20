package com.Havenly.Backend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="package_type_id", referencedColumnName = "packageTypeId")
	PackageTypes packType;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="subUser_id", referencedColumnName = "subUserId")
	Subscription sub1;
	
//	@OneToOne(mappedBy = "package_id")
//	@JsonIgnore
//	Subscription sub;
}
