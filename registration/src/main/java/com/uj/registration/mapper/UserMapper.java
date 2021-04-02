package com.uj.registration.mapper;

import org.springframework.stereotype.Component;

import com.uj.registration.model.User;
import com.uj.registration.vo.UserVo;

@Component
public class UserMapper {

	public UserVo getVO(User user) {
		return new UserVo(user.getEmailId(), user.getPassword(), user.getContact());
	}

	public User getModel(UserVo userVo) {
		return new User(userVo.getEmailId(), userVo.getPassword(), userVo.getContact());
	}

}
