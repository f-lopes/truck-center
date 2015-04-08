package com.ingesup.truckcenter.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
public class SecurityUtil {

	public static TruckCenterUserDetails getCurrentLoggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication == null ? null : (TruckCenterUserDetails) authentication.getPrincipal();
	}

	public static String getCurrentLoggedUsername() {
		return getCurrentLoggedUser().getUsername();
	}

	public static void loginUser(UserDetails user) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
