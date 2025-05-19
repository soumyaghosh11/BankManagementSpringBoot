package com.bankManagementSystem.bank.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankManagementSystem.bank.model.User;
import com.bankManagementSystem.bank.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	public AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user){
		return ResponseEntity.ok(authService.Register(user));
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Map<String, String> credentials){
		String token = authService.login(credentials.get("username"), credentials.get("password"));
		return ResponseEntity.ok(token);
	}
}
