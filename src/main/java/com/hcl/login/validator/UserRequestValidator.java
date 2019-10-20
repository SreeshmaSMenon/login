package com.hcl.login.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.hcl.login.dto.UserRequest;
import com.hcl.login.entity.User;
import com.hcl.login.service.UserService;
import com.hcl.login.util.LoginApplicationConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * This class will validate the userRequest properties.
 * @since 2019-10-14 
 * @author Sreeshma S Menon
 */
@Slf4j
@Component
public class UserRequestValidator implements Validator {

	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UserRequest.class);
	}
	
	
	/**
	 *This method will validate UserRequest inputs and will throw exception
	 *if any invalid inputs met.
	 *@param target
	 *@param errors
	 */
	@Override
	public void validate(Object target, Errors errors) {
		log.debug(LoginApplicationConstants.VALIDATING);
		UserRequest bean = (UserRequest) target;
		 validateEmail(bean,errors);
		 validatePhoneNumber(bean.getPhone(),errors);
	}
	/**
	 *This is a private method to check existence of email in database, and will
	 *throw EmailExistException in case exist.
	 *@param target
	 */
	private void validateEmail(UserRequest bean,Errors errors){
		Optional<User> user = userService.getUserByEmail(bean.getEmail());
		if(user.isPresent()) {
		 errors.rejectValue("email", "", LoginApplicationConstants.EMAIL_EXIST_EXCEPTION);
		}
	}
	/**
	 *This is a private method to check phoneNumber length, and will throw 
	 *UserException if length is not equal to 10.
	 *@param target
	 */
	private void validatePhoneNumber(Long phoneNumber,Errors errors) {
		if (ObjectUtils.isEmpty(phoneNumber)|| phoneNumber.toString().length() != 10) {
			 errors.rejectValue("phone", "", LoginApplicationConstants.INVALID_PHONE_NO);
		}
	}
}
