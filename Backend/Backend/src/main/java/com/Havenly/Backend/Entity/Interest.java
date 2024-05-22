package com.Havenly.Backend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Interest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "register_id", referencedColumnName = "register_id")
	Reg_user user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "post_id", referencedColumnName = "post_id")
	Posts posts;

}
