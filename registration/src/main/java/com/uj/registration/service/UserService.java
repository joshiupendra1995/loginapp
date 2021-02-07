package com.uj.registration.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uj.registration.model.User;
import com.uj.registration.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User registerUser(User user) {
		return userRepository.save(user);
	}

	public User fetchUserByEmailId(String emailId) {
		return userRepository.findByEmailId(emailId);
	}

	public User fetchUserByEmailIdAndPassword(String emailId, String password) throws Exception {
		User user = userRepository.findByEmailIdAndPassword(emailId, password);
		if (Objects.isNull(user)) {
			throw new Exception("Bad Credentials!!");
		}
		return user;
	}
}
