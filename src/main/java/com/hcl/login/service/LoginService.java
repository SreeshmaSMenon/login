package com.hcl.login.service;

import java.util.Optional;

import com.hcl.login.dto.LoginRequest;
import com.hcl.login.entity.User;

public interface LoginService {
	  public Optional<User> getUser(LoginRequest loginRequest);

}
