package com.ashok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashok.binding.UnlockForm;
import com.ashok.entity.User;
import com.ashok.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import springfox.documentation.spring.web.json.Json;
@CrossOrigin
@RestController
public class ForgotPwdRestController {

	@Autowired
	private UserService service;

	@GetMapping("/forgot/{email}")
	public String forgotPassword(@PathVariable String email) throws Exception {

				return service.forgotPwd(email);
	}
}
