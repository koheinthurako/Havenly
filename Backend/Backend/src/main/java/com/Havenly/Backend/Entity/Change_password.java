package com.Havenly.Backend.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Change_password {
	private String username;
	private String password;
	private String new_password;

}
