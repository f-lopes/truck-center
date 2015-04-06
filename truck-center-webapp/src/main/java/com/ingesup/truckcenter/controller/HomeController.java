package com.ingesup.truckcenter.controller;

import com.ingesup.truckcenter.model.User;
import com.ingesup.truckcenter.security.SecurityUtil;
import com.ingesup.truckcenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.mapping.SimpleAttributes2GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
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

	private final UserService userService;

	@Autowired
	public HomeController(UserService userService) {
		this.userService= userService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		return HOME_VIEW;
	}

	@RequestMapping(value = "/login-as-admin", method = RequestMethod.POST)
	public String loginAsAdmin() {
		final User adminUser = userService.getByEmail("admin@admin.fr");
		if (adminUser != null) {
			UserDetails securityUser =
					new org.springframework.security.core.userdetails.User(
							adminUser.getEmail(), adminUser.getPassword(), new SimpleAttributes2GrantedAuthoritiesMapper().getGrantedAuthorities(adminUser.getRolesAsString()));
			SecurityUtil.loginUser(securityUser);
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/login-as-driver", method = RequestMethod.POST)
	public String loginAsDriver() {
		final User adminUser = userService.getByEmail("driver@driver.fr");
		if (adminUser != null) {
			UserDetails securityUser =
					new org.springframework.security.core.userdetails.User(
							adminUser.getEmail(), adminUser.getPassword(), new SimpleAttributes2GrantedAuthoritiesMapper().getGrantedAuthorities(adminUser.getRolesAsString()));
			SecurityUtil.loginUser(securityUser);
		}

		return "redirect:/";
	}
}
