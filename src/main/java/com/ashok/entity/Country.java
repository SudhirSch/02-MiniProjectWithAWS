package com.ashok.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Country {

	@Id
	private int countryId;
	private String countryName;
	private String countryCode;
	
}
