package com.Havenly.Backend.Entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {
	
	    @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;
		@NotBlank
		private String email;

	    @NotBlank
		private String password;
}

