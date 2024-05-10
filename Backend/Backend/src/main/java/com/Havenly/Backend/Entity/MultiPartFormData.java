package com.Havenly.Backend.Entity;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MultiPartFormData {
	
	 private String title;
	    private String description;
	    private String houseType;
	    private String propertyType;
	    private String price;
	    private String area;
	    private MultipartFile[] files;
	    private Locations locationId;

}
