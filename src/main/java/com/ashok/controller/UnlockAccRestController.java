package com.ashok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashok.binding.UnlockForm;
import com.ashok.service.UserService;

@CrossOrigin
@RestController
public class UnlockAccRestController {
@Autowired
private UserService service;
	
	@PostMapping("/unlock")
	public String updatePassword(@RequestBody UnlockForm unlockForm) {
		return service.unlockAcc(unlockForm);
		
	}
}
