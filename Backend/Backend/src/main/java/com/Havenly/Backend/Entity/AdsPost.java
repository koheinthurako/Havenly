package com.Havenly.Backend.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdsPost {
	
	@Id
	private String ads_post_id;
	
	private LocalDate date;
	private LocalDateTime time;
	private String title;
	private String status;

	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String imageUrl;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="sub_user_id")
	private Subscription subUser;
}
