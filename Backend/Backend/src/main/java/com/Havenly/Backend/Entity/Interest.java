package com.Havenly.Backend.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Interest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "register_id", referencedColumnName = "register_id")
    private Reg_user reg_user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    private Posts posts;

	@Column(name = "description", length = 5000)
	private String description;

	@Column(name = "interest_date")
	private LocalDate interest_date;

	@Column(name = "interest_time")
	private LocalTime interest_time;
}
