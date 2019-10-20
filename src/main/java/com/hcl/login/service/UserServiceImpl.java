package com.hcl.login.service;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.login.dto.UserRequest;
import com.hcl.login.entity.User;
import com.hcl.login.repository.UserRepository;
import com.hcl.login.util.LoginApplicationConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * This class includes method for register user
 * @since 2019-10-16
 * @author Sreeshma S Menon
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * This method will accept userRequest as input,copy the properties from
	 * userRequest to user object and call the save method in repository and return
	 * back optional of User object.
	 * 
	 * @param userRequest
	 * @return Optional<User>
	 */
	@Override
	public Optional<User> create(UserRequest userRequest) {
		log.info(LoginApplicationConstants.SAVE_USER_DEBUG_START_SERVICE);
		User user = new User();
		BeanUtils.copyProperties(userRequest, user);
		user.setPassword(userRequest.getPassword());
		user = userRepository.save(user);
		log.info(LoginApplicationConstants.SAVE_USER_DEBUG_END_SERVICE);
		return Optional.of(user);
	}

	/**
	 * This method will accept email and return respective user object.
	 * @param email
	 * @return Optional of User
	 */
	@Override
	public Optional<User> getUserByEmail(String email) {
		log.debug(LoginApplicationConstants.GET_USER_BY_EMAIL_DEBUG_START_SERVICE);
		Optional<User> userOptional = userRepository.findByEmail(email);
		log.debug(LoginApplicationConstants.GET_USER_BY_EMAIL_DEBUG_END_SERVICE);
		return userOptional;
	}

}
