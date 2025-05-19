package com.bankManagementSystem.bank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankManagementSystem.bank.model.User;
import com.bankManagementSystem.bank.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public ResponseEntity<Page<User>> getAllUsers(@RequestParam(name = "page", defaultValue = "0") int page,
	                                              @RequestParam(name = "size", defaultValue = "20") int size) {
	    Page<User> users = userService.getAllUsers(PageRequest.of(page, size));
	    return ResponseEntity.ok(users);
	}


	@GetMapping("/getUserById/{id}")
	public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") String id){
		Optional<User> user=userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		return userService.addUserInfo(user);
	}
	
	@PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(user);
    }
	
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		String msg= userService.deleteUserById(id);
		return msg;
	}
}
