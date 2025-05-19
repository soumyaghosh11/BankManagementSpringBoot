package com.bankManagementSystem.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankManagementSystem.bank.model.JwtUtil;
import com.bankManagementSystem.bank.model.User;
import com.bankManagementSystem.bank.repo.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	 @Autowired
	    private AuthenticationManager authenticationManager;
	
	public String Register(User user) {
		user.setUsername(user.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "User registered successfully";
	}
	
	public String login(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
		return jwtUtil.generateToken(username);
	}

}
