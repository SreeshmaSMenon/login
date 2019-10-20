package com.hcl.login.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.login.dto.LoginRequest;
import com.hcl.login.entity.User;
import com.hcl.login.repository.UserRepository;
import com.hcl.login.util.LoginApplicationConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * This class contains the method for login.
 * 
 * @author sreeshma
 * @since 2109/16/10
 *
 */

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserRepository userRepository;

	/**
	 * This method will accept loginRequest as input and get user object for given
	 * user name and password.Then return optional of user.
	 * @param loginRequest
	 * @return optional of User
	 */
	@Override
	public Optional<User> getUser(LoginRequest loginRequest) {
		log.debug(LoginApplicationConstants.LOGIN_DEBUG_START_SERVICE);
		Optional<User> optionalUser = userRepository.findByEmailAndPassword(loginRequest.getEmail(),
				loginRequest.getPassword());
		log.debug(LoginApplicationConstants.LOGIN_DEBUG_END_SERVICE);
		return optionalUser;
	}
}
