package com.bankManagementSystem.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankManagementSystem.bank.model.User;
import com.bankManagementSystem.bank.repo.UserRepository;

@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;

    public Page<User> getAllUsers(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }

	public Optional<User> getUserById(String id) {
		int userId= Integer.parseInt(id);
		Optional<User> user= Optional.of(userRepository.findById(userId).orElse(null));
		return user;
	}

	
	public ResponseEntity<User> addUserInfo(User user) {
        try {
            User savedUser = userRepository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED); // HTTP 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // HTTP 500
        }
    }


	public User updateUser(int id, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            // Update only non-null fields to prevent overwriting existing data
            if (updatedUser.getFirstName() != null) existingUser.setFirstName(updatedUser.getFirstName());
            if (updatedUser.getLastName() != null) existingUser.setLastName(updatedUser.getLastName());
            if (updatedUser.getFathersName() != null) existingUser.setFathersName(updatedUser.getFathersName());
            if (updatedUser.getMotherName() != null) existingUser.setMotherName(updatedUser.getMotherName());
            if (updatedUser.getUsername() != null) existingUser.setUsername(updatedUser.getUsername());
            if (updatedUser.getPassword() != null) existingUser.setPassword(updatedUser.getPassword());
            if (updatedUser.getGender() != null) existingUser.setGender(updatedUser.getGender());
            if (updatedUser.getEmail() != null) existingUser.setEmail(updatedUser.getEmail());
            if (updatedUser.getPanNumber() != null) existingUser.setPanNumber(updatedUser.getPanNumber());
            if (updatedUser.getAddress() != null) existingUser.setAddress(updatedUser.getAddress());
            if (updatedUser.getPhone() != null) existingUser.setPhone(updatedUser.getPhone());
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }

	public String deleteUserById(int id) {
		User user=userRepository.findById(id).get();
		user.setActive(false);
		return "Customer with customerId "+id+"is no longer a member of the bank";
	}

}
