package com.ashok.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class City {

	@Id
	private int cityId;
	private String cityname;
	private int stateId;
	
}
