package com.ashok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashok.binding.LoginForm;
import com.ashok.service.UserService;
@CrossOrigin
@RestController
public class LoginRestController {

	@Autowired
	private UserService service;
	@PostMapping("/login")
	public String checkLogin(@RequestBody LoginForm loginForm) {
		System.out.println("loginForm "+loginForm);
		return service.loginCheck(loginForm);
	}
}
