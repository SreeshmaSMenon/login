package com.hcl.login.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.hcl.login.dto.CommonResponse;
import com.hcl.login.dto.UserRequest;
import com.hcl.login.entity.User;
import com.hcl.login.exception.UserException;
import com.hcl.login.service.UserService;
import com.hcl.login.validator.UserRequestValidator;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@Mock
	UserService userService;
    @Mock
	UserRequestValidator userRequestValidator;
    @InjectMocks
    UserController userController;  
    BindingResult bindingResult;
    User user;
    FieldError fieldError;
    UserRequest userRequest;
    
    @Before
    public void setup() {
 	   bindingResult = mock(BindingResult.class);
 	   user=new User();
 	   user.setUserId(1);
 	   user.setEmail("sree@gmail.com");
 	   user.setPassword("sree123");
 	   userRequest=new UserRequest();
 	   BeanUtils.copyProperties(user, userRequest);
 	   fieldError=new FieldError("loginRequest", "userName", "Must Not empty");
    }
    
    @Test
    public void testCreate() throws UserException {
 	   Mockito.when(bindingResult.hasErrors()).thenReturn(false);
 	   Optional<User> optionalUser=Optional.of(user);
	   Mockito.when( userService.create(Mockito.any())).thenReturn(optionalUser);
       ResponseEntity<CommonResponse> commonResponse=userController.save(userRequest, bindingResult);
       assertNotNull(commonResponse);
    }
    @Test(expected=UserException.class)
    public void testCreateUserException() throws UserException {
 	   Mockito.when(bindingResult.hasErrors()).thenReturn(true);
	   Mockito.when(bindingResult.getFieldError()).thenReturn(fieldError);
 	   ResponseEntity<CommonResponse> commonResponse=userController.save(userRequest, bindingResult);
       assertNotNull(commonResponse);
    }
    
    
    
 
}
