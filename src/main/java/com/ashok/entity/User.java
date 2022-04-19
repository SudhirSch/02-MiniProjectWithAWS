package com.ashok.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
@Data
@Entity
public class User {

	@Id
	@GeneratedValue
	private int userId; 
	private String fristName;
	private String lastName;
	private String userEmail;
	private String userPwd;
	private long userMobile;
	private String dob;
	private String gender;
	private int cityId;
	private int stateId;
	private int countryId;
	private String accStatus;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime updateDate;
	
}
