package com.ashok.binding;

import lombok.Data;

@Data
public class UserRegForm {

	private String fristName;
	private String lastName;
	private String userEmail;
	private long userMobile;
	private String dob;
	private String gender;
	private int cityId;
	private int stateId;
	private int countryId;
	
}
