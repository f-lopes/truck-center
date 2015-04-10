package com.ingesup.truckcenter.controller;

import com.ingesup.truckcenter.model.User;
import com.ingesup.truckcenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lopes_f on 4/9/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@RequestMapping(value = "/authenticate")
public class AuthenticationEndpoint {

	private static final String AUTH_EMAIL_PARAM = "email";

	private static final String AUTH_PASSWORD_PARAM = "password";

	private final UserService userService;

	@Autowired
	public AuthenticationEndpoint(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping (method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> authenticateUser(@RequestParam (AUTH_EMAIL_PARAM) String email,
												   @RequestParam(AUTH_PASSWORD_PARAM) String password) {
		HttpStatus status = HttpStatus.OK;

		User user = userService.authenticate(email, password);

		return new ResponseEntity<>(user.getId(), status);
	}
}
