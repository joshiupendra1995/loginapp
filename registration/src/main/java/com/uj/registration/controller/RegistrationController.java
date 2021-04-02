package com.uj.registration.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uj.registration.exception.BusinessException;
import com.uj.registration.service.UserService;
import com.uj.registration.vo.UserVo;

@CrossOrigin("*")
@RestController
public class RegistrationController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public UserVo registerUser(@RequestBody UserVo user) throws Exception {
		if (!Objects.isNull(userService.fetchUserByEmailId(user.getEmailId()))) {
			throw new BusinessException(String.format("User with email id :: %S Already exists!!", user.getEmailId()));
		}
		return userService.registerUser(user);
	}

	@PutMapping("/login")
	public UserVo loginUser(@RequestBody UserVo user) throws Exception {
		return userService.fetchUserByEmailIdAndPassword(user.getEmailId(), user.getPassword());
	}

	@PutMapping("/forgot")
	public UserVo resetUserPassword(@RequestBody UserVo user) throws Exception {
		return userService.resetUserPassword(user.getEmailId(), user.getPassword());
	}

}
