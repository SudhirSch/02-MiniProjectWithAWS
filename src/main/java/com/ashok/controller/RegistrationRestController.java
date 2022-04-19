package com.ashok.controller;


import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashok.binding.UserRegForm;
import com.ashok.entity.User;
import com.ashok.service.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@CrossOrigin
@RestController
public class RegistrationRestController {
	
	@Autowired
	private UserService service;

	@PostMapping("/save")
	public String saveUser(@RequestBody UserRegForm form) {
		
		String s1=service.save(form);
		Map<Integer, String> country = service.loadCountry();
		Set<Integer> keySet = country.keySet();
		
		return s1;
	}
	@GetMapping("/")
	public List<User> getUser(){
	List<User> list=service.getAllUser();
		return list;
	}
	
}
