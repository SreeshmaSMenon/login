package com.hcl.login.service;

import java.util.Optional;
import com.hcl.login.dto.UserRequest;
import com.hcl.login.entity.User;

public interface UserService {
	public Optional<User> create(UserRequest userRequest);
	public Optional<User> getUserByEmail(String email);

}
