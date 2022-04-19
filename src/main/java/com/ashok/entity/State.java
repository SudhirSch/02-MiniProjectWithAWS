package com.ashok.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class State {

	@Id
	private int stateId;
	private String stateName;
	private int countryId; 

}
