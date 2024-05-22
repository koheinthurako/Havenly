package com.Havenly.Backend.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Packages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int packageId;
	
	@Column(name = "payment")
	private String payment;

	@Column(name = "purchase_time")
	private LocalDateTime packTime;
	
	@Column(name = "purchase_date")
	private LocalDate packDate;
	
	@Column(name = "avail_posts")
	private int availPosts;
	
	@Column(name = "avail_ads")
	private int availAds;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="package_type_id")
	@JsonIgnore
	private PackageTypes packType;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="subUser_id", referencedColumnName = "subUserId")
	@JsonIgnore
	private Subscription sub1;


}
