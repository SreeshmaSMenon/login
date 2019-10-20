package com.hcl.login.controller;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hcl.login.dto.CommonResponse;
import com.hcl.login.dto.UserRequest;
import com.hcl.login.entity.User;
import com.hcl.login.exception.UserException;
import com.hcl.login.service.UserService;
import com.hcl.login.util.LoginApplicationConstants;
import com.hcl.login.validator.UserRequestValidator;

import lombok.extern.slf4j.Slf4j;

/**
 * This class includes method for register user.
 * @since 2019-10-16
 * @author Sreeshma S Menon
 */
@Slf4j
@RestController
@RequestMapping("/users")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserRequestValidator userRequestValidator;

	@InitBinder("userRequest")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(userRequestValidator);
	}

	/**
	 * This method will accept userRequest and bindingResult as inputs and call the
	 * save method in service layer if there is no binding errors, otherwise throw
	 * an exception.
	 * @param userRequest
	 * @param bindingResult
	 * @exception UserException
	 * @return ResponseEntity of String which includes the message that user created
	 *         successfully or not.
	 * 
	 */
	@PostMapping(value = "/")
	public ResponseEntity<CommonResponse> save(@Valid @RequestBody UserRequest userRequest,
			BindingResult bindingResult) throws UserException {
		log.debug(LoginApplicationConstants.SAVE_USER_DEBUG_START_CONTROLLER);
		CommonResponse commonResponse = new CommonResponse();
		if (bindingResult.hasErrors()) {
			throw new UserException(
					bindingResult.getFieldError().getField() + " " + bindingResult.getFieldError().getDefaultMessage());
		}
		Optional<User> optionalUser = userService.create(userRequest);
		if (optionalUser.isPresent() && optionalUser.get().getUserId() != null) {
			commonResponse.setStatusCode(HttpStatus.CREATED.value());
			commonResponse.setMessage(LoginApplicationConstants.CREATE_SUCESS_MESSAGE);
		}
		log.debug(LoginApplicationConstants.SAVE_USER_DEBUG_END_CONTROLLER);
		return new ResponseEntity<>(commonResponse, HttpStatus.OK);
	}

}
