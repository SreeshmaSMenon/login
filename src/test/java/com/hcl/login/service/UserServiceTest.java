package com.hcl.login.service;

import static org.junit.Assert.assertNotNull;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import com.hcl.login.dto.UserRequest;
import com.hcl.login.entity.User;
import com.hcl.login.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserServiceImpl userServiceImpl;

	User user;
	UserRequest userRequest;

	@Before
	public void setup() {
		user = new User();
		user.setUserId(1);
		user.setEmail("sree@gmail.com");
		user.setPassword("sree123");
		userRequest = new UserRequest();
		BeanUtils.copyProperties(user, userRequest);
	}
	
	@Test
	public void testCreate() {
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		Optional<User> user = userServiceImpl.create(userRequest);
		assertNotNull(user);
	}
	
   @Test
   public void testGetUserByEmail() {
	   Mockito.when(userRepository.findByEmail(Mockito.any())).thenReturn(Optional.of(user));
	   Optional<User> user = userServiceImpl.getUserByEmail("sree@gmail.com");
	   assertNotNull(user);
   }
}
