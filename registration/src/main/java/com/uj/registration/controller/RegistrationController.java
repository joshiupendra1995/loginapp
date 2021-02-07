package com.uj.registration.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uj.registration.model.User;
import com.uj.registration.service.UserService;

@CrossOrigin("*")
@RestController
public class RegistrationController {

	@Autowired
	private UserService userService;

	@PostMapping("/registeruser")
	public User registerUser(@RequestBody User user) throws Exception {
		if (!Objects.isNull(userService.fetchUserByEmailId(user.getEmailId()))) {
			throw new Exception(String.format("User with email id :: %S Already exists!!", user.getEmailId()));
		}
		return userService.registerUser(user);
	}

	@PutMapping("/login")
	public User loginUser(@RequestBody User user) throws Exception {
		return userService.fetchUserByEmailIdAndPassword(user.getEmailId(), user.getPassword());
	}

}
