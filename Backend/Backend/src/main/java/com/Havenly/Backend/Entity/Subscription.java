package com.Havenly.Backend.Entity;



import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

//@Column(columnDefinition = "ENUM('registered', 'subscribed','premium', 'banned') NOT NULL")	
//@Enumerated(EnumType.STRING)
//private UserStatus status;

@Column(name = "post_id")
private int postId;
@Column(name = "ads_id")
private int adsId;
@Column(name = "ban_id")
private int banId;
@Column(name = "total_posts")
private int totalPosts;
@Column(name = "total_ads")
private int totalAds;
private LocalDate subStartDate;

private LocalDate subEndDate;

private LocalDateTime subStartTime;

private LocalDateTime subEndTime;



@OneToOne(fetch = FetchType.EAGER)
@JoinColumn(name="reg_user_id", referencedColumnName = "register_id")
Reg_user reg_user;

//@OneToOne(fetch = FetchType.EAGER)
//@JsonIgnore
//@JoinColumn(name="package_id", referencedColumnName = "packageId")
//Packages packages;

@OneToOne(mappedBy = "sub1")
Packages packages;
}
