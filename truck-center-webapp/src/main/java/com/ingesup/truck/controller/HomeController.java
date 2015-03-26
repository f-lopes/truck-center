package com.ingesup.truck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@RequestMapping("/")
public class HomeController {

	private static final String HOME_VIEW = "home";

	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		return HOME_VIEW;
	}
}
