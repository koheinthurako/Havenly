package com.Havenly.Backend.entity;



import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Subscribe implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int subUserId;

@Column
private String regUserId;

/*
 * @Column(length = 100, nullable = false, unique = true)
 * 
 * @NotBlank(message = "Required") private String gmail;
 * 
 * @Column(length = 30, nullable = false)
 * 
 * @NotBlank(message = "Required") private String name;
 */

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
private int packageId;
@Column
private int banId;
@Column
private int totalPosts;
@Column
private int totalAds;

}
