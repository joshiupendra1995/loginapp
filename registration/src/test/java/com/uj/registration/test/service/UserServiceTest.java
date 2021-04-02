package com.uj.registration.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.uj.registration.exception.BusinessException;
import com.uj.registration.mapper.UserMapper;
import com.uj.registration.model.User;
import com.uj.registration.repository.UserRepository;
import com.uj.registration.service.UserService;
import com.uj.registration.vo.UserVo;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserMapper userMapper;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	private User getUser() {
		return new User("joshiupendra00@gmail.com", "root", "99999999999");

	}

	private UserVo getUserVo() {
		return new UserVo("joshiupendra00@gmail.com", "root", "99999999999");
	}

	@Test
	public void testFetchUserByEmailId() {
		String emailId = "joshiupendra00@gmail.com";
		when(userRepository.findByEmailId(emailId)).thenReturn(getUser());
		User user = userService.fetchUserByEmailId(emailId);
		assertEquals(user.getContact(), "99999999999");
		assertEquals(user.getEmailId(), emailId);

	}

	@Test
	public void testRegisterUser() {
		when(userMapper.getModel(getUserVo())).thenReturn(getUser());
		userService.registerUser(getUserVo());
		verify(userRepository).save(getUser());
	}

	@Test(expected = BusinessException.class)
	public void testResetUserPasswordWithExistingPassword() throws Exception {
		String emailId = "joshiupendra00@gmail.com";
		String password = "root";
		when(userRepository.findByEmailIdAndPassword(emailId, password)).thenReturn(getUser());
		userService.resetUserPassword(emailId, password);
	}

	@Test
	public void testResetUserPassword() throws Exception {
		String emailId = "joshiupendra00@gmail.com";
		when(userRepository.findByEmailIdAndPassword(emailId, "")).thenReturn(getUser());
		when(userMapper.getVO(null)).thenReturn(getUserVo());
		UserVo user = userService.resetUserPassword(emailId, "");
		assertEquals(user.getEmailId(), emailId);
	}
}
