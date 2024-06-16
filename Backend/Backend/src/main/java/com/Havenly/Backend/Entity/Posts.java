package com.Havenly.Backend.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Posts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int post_id;
	private String post_type;
	private String status;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "sell_post_id", referencedColumnName = "sell_post_id")
	SellPost sellpost;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "rent_post_id", referencedColumnName = "rent_post_id")
	RentPost rentpost;
	
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "ads_post_id", referencedColumnName = "ads_post_id")
//	AdsPost adsPosts;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
	@JoinColumn(name="sub_user_id")
	private Subscription subUser;
	
	@JsonIgnore
	@OneToMany(mappedBy = "posts")
	private List<Interest> interest;
	
	
}
