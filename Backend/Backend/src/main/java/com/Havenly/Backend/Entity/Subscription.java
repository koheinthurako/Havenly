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
private String nrc;

@Column(name = "name")
private String name;
//@Column(columnDefinition = "ENUM('registered', 'subscribed','premium', 'banned') NOT NULL")	
//@Enumerated(EnumType.STRING)
//private UserStatus status;

@Column(name = "email",nullable = false,unique = true)
@Email(message = "Invalid Email Format")
private String email;

@Column(name = "package_name")
private String packageType;
@Column(name = "post_id")
private int postId;
@Column(name = "ads_id")
private int adsId;
@Column(name = "total_posts")
private int totalPosts;
@Column(name = "total_ads")
private int totalAds;
private LocalDate subStartDate;

private LocalDateTime subStartTime;


@OneToOne(fetch = FetchType.EAGER)
@JoinColumn(name="reg_user_id", referencedColumnName = "register_id")
private Reg_user reg_user;;

@OneToOne(mappedBy = "sub1")
private Packages packages;
}
