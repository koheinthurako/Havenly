package com.Havenly.Backend.Entity;



import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Subscription {


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int subUserId;

@Column(name = "nrc",nullable = false,unique = true)
@Email(message = "Invalid NRC Format")
private String nrc;

@Column(columnDefinition = "ENUM('registered', 'subscribed','premium', 'banned') NOT NULL")	
@Enumerated(EnumType.STRING)
private UserStatus status;


@Column(nullable = false)
@ColumnDefault("current date")
private LocalDate subStartDate;

private LocalDate subEndDate;


@Column(nullable = false)
@ColumnDefault("current timestamp")
private LocalDateTime subStartTime;

private LocalDateTime subEndTime;


@Column
private int postId;
@Column
private int adsId;
@Column
private int banId;
@Column
private int totalPosts;
@Column
private int totalAds;

@OneToOne(fetch = FetchType.EAGER)
@JoinColumn(name="reg_user_id", referencedColumnName = "register_id")
Reg_user reg_user;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name="package_id", referencedColumnName = "packageId")
Packages packages;

}
