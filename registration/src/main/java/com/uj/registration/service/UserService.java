package com.uj.registration.service;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uj.registration.exception.BusinessException;
import com.uj.registration.mapper.UserMapper;
import com.uj.registration.model.User;
import com.uj.registration.repository.UserRepository;
import com.uj.registration.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	public UserVo registerUser(UserVo userVo) {
		return userMapper.getVO(userRepository.save(userMapper.getModel(userVo)));
	}

	public User fetchUserByEmailId(String emailId) {
		return userRepository.findByEmailId(emailId);
		
	}

	public UserVo fetchUserByEmailIdAndPassword(String emailId, String password) throws Exception {
		User user = userRepository.findByEmailIdAndPassword(emailId, password);
		if (Objects.isNull(user)) {
			throw new BusinessException("Bad Credentials!!");
		}
		return userMapper.getVO(user);
	}

	public UserVo resetUserPassword(String emailId, String password) throws Exception {
		User user = userRepository.findByEmailIdAndPassword(emailId, password);
		String pass = user.getPassword();
		if (StringUtils.equals(pass, password)) {
			throw new BusinessException("Old Password Should Not Be Same as New Password!!");
		} else {
			user.setPassword(password);
			return userMapper.getVO(userRepository.save(user));
		}
	}
}
